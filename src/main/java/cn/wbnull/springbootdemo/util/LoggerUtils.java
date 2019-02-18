package cn.wbnull.springbootdemo.util;

import cn.wbnull.springbootdemo.config.UtilConfig;
import org.apache.log4j.Logger;


/**
 * Logger 工具类
 *
 * @author dukunbiao(null)  2018-12-17
 *         https://github.com/dkbnull/Util
 */
public class LoggerUtils {

    private static final int OFF = 0;
    private static final int ERROR = 3;
    private static final int WARN = 4;
    private static final int INFO = 6;
    private static final int DEBUG = 7;
    private static final int ALL = 8;

    private static Logger logger;

    private LoggerUtils() {
    }

    private static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("rootLogger");
        }

        return logger;
    }

    private static boolean offLevel() {
        return StringUtils.toInt(UtilConfig.getLogLevel(), 0) == OFF;
    }

    private static boolean errorLevel() {
        return StringUtils.toInt(UtilConfig.getLogLevel(), 0) > ERROR;
    }

    private static boolean warnLevel() {
        return StringUtils.toInt(UtilConfig.getLogLevel(), 0) > WARN;
    }

    private static boolean infoLevel() {
        return StringUtils.toInt(UtilConfig.getLogLevel(), 0) > INFO;
    }

    private static boolean debugLevel() {
        return StringUtils.toInt(UtilConfig.getLogLevel(), 0) > DEBUG;
    }

    public static void error(Object message) {
        if (offLevel()) return;

        if (errorLevel()) {
            getLogger().error(message);
        }
    }

    public static void error(String mac, String position, String method, String content) {
        if (offLevel()) return;

        if (errorLevel()) {
            getLogger().error(toMessage(Thread.currentThread().getId(), mac, position, method, content));
        }
    }

    public static void error(long id, String mac, String position, String method, String content) {
        if (offLevel()) return;

        if (errorLevel()) {
            getLogger().error(toMessage(id, mac, position, method, content));
        }
    }

    public static void error(Object message, Throwable t) {
        if (offLevel()) return;

        if (errorLevel()) {
            getLogger().error(message, t);
        }
    }

    public static void error(String mac, String position, String method, Throwable t) {
        if (offLevel()) return;

        if (errorLevel()) {
            getLogger().error(toMessage(Thread.currentThread().getId(), mac, position, method, ""), t);
        }
    }

    public static void error(long id, String mac, String position, String method, Throwable t) {
        if (offLevel()) return;

        if (errorLevel()) {
            getLogger().error(toMessage(id, mac, position, method, ""), t);
        }
    }

    public static void warn(Object message) {
        if (offLevel()) return;

        if (warnLevel()) {
            getLogger().warn(message);
        }
    }

    public static void warn(String mac, String position, String method, String content) {
        if (offLevel()) return;

        if (warnLevel()) {
            getLogger().warn(toMessage(Thread.currentThread().getId(), mac, position, method, content));
        }
    }

    public static void warn(long id, String mac, String position, String method, String content) {
        if (offLevel()) return;

        if (warnLevel()) {
            getLogger().warn(toMessage(id, mac, position, method, content));
        }
    }

    public static void info(Object message) {
        if (offLevel()) return;

        if (infoLevel()) {
            getLogger().info(message);
        }
    }

    public static void info(String mac, String position, String method, String content) {
        if (offLevel()) return;

        if (infoLevel()) {
            getLogger().info(toMessage(Thread.currentThread().getId(), mac, position, method, content));
        }
    }

    public static void info(long id, String mac, String position, String method, String content) {
        if (offLevel()) return;

        if (infoLevel()) {
            getLogger().info(toMessage(id, mac, position, method, content));
        }
    }

    public static void debug(Object message) {
        if (offLevel()) return;

        if (debugLevel()) {
            getLogger().debug(message);
        }
    }

    public static void debug(String mac, String position, String method, String content) {
        if (offLevel()) return;

        if (debugLevel()) {
            getLogger().debug(toMessage(Thread.currentThread().getId(), mac, position, method, content));
        }
    }

    public static void debug(long id, String mac, String position, String method, String content) {
        if (offLevel()) return;

        if (debugLevel()) {
            getLogger().debug(toMessage(id, mac, position, method, content));
        }
    }

    private static String toMessage(long id, String mac, String position, String method, String content) {
        return "[" + id + "][" + mac + "][" + position + "]" + "\n" + "方法：" + method + "\n" + "参数：" + content + "\n";
    }
}
