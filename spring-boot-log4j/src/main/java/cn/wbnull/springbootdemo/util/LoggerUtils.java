package cn.wbnull.springbootdemo.util;

import org.apache.log4j.Logger;

/**
 * Logger 工具类
 *
 * @author dukunbiao(null)  2018-12-17
 * https://github.com/dkbnull/Util
 */
public class LoggerUtils {

    private static Logger logger;

    public static Logger getLogger() {
        if (logger == null) {
            logger = Logger.getLogger("SpringBootDemoLogs");
        }
        return logger;
    }
}