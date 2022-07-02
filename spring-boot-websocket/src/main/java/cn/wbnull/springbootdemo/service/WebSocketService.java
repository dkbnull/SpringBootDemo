package cn.wbnull.springbootdemo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * WebSocket 服务类
 *
 * @author dukunbiao(null)  2022-07-02
 * https://github.com/dkbnull/SpringBootDemo
 */
@Component
@ServerEndpoint("/websocket/{terminalId}")
public class WebSocketService {

    private final Logger logger = LoggerFactory.getLogger(WebSocketService.class);

    /**
     * 保存连接信息
     */
    private static final Map<String, Session> CLIENTS = new ConcurrentHashMap<>();
    private static final Map<String, AtomicInteger> TERMINAL_IDS = new HashMap<>();

    /**
     * 需要注入的Service声明为静态，让其属于类
     */
    private static TerminalService terminalService;

    /**
     * 注入的时候，给类的Service注入
     */
    @Autowired
    public void setMchDeviceInfoService(TerminalService terminalService) {
        WebSocketService.terminalService = terminalService;
    }

    @OnOpen
    public void onOpen(@PathParam("terminalId") String terminalId, Session session) throws Exception {
        logger.info(session.getRequestURI().getPath() + "，打开连接开始：" + session.getId());

        //当前连接已存在，关闭
        if (CLIENTS.containsKey(terminalId)) {
            onClose(CLIENTS.get(terminalId));
        }

        TERMINAL_IDS.put(terminalId, new AtomicInteger(0));
        CLIENTS.put(terminalId, session);

        logger.info(session.getRequestURI().getPath() + "，打开连接完成：" + session.getId());

        terminalService.terminal();
    }

    @OnClose
    public void onClose(@PathParam("terminalId") String terminalId, Session session) throws Exception {
        logger.info(session.getRequestURI().getPath() + "，关闭连接开始：" + session.getId());

        CLIENTS.remove(terminalId);
        TERMINAL_IDS.remove(terminalId);

        logger.info(session.getRequestURI().getPath() + "，关闭连接完成：" + session.getId());
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        logger.info("前台发送消息：" + message);

        if ("心跳".equals(message)) {
            //重置当前终端心跳次数
            TERMINAL_IDS.get(message).set(0);
            return;
        }

        sendMessage("收到消息：" + message, session);
    }

    @OnError
    public void onError(Session session, Throwable error) {
        logger.error(error.toString());
    }

    public void onClose(Session session) {
        //判断当前连接是否在线
//        if (!session.isOpen()) {
//            return;
//        }

        try {
            session.close();
        } catch (IOException e) {
            logger.error("金斗云关闭连接异常：" + e);
        }
    }

    public void heartbeat() {
        //检查所有终端心跳次数
        for (String key : TERMINAL_IDS.keySet()) {
            //心跳3次及以上的主动断开
            if ((TERMINAL_IDS.get(key).intValue() >= 3)) {
                logger.info("心跳超时，关闭连接：" + key);
                onClose(CLIENTS.get(key));
            }
        }

        for (String key : CLIENTS.keySet()) {
            //记录当前终端心跳次数
            TERMINAL_IDS.get(key).incrementAndGet();
            sendMessage("心跳", CLIENTS.get(key));
        }
    }

    public void sendMessage(String message, Session session) {
        try {
            session.getAsyncRemote().sendText(message);

            logger.info("推送成功：" + message);
        } catch (Exception e) {
            logger.error("推送异常：" + e);
        }
    }

    public boolean sendMessage(String terminalId, String message) {
        try {
            Session session = CLIENTS.get(terminalId);
            session.getAsyncRemote().sendText(message);

            logger.info("推送成功：" + message);
            return true;
        } catch (Exception e) {
            logger.error("推送异常：" + e);
            return false;
        }
    }
}
