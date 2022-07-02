log4j提供了标准的日志输出，简单配置即可记录日志（参考[Spring Boot入门：使用AOP实现拦截器](https://blog.csdn.net/dkbnull/article/details/82847647) 8、日志记录），但使用默认Appender记录日志时，有时并不能满足我们的需求，这时我们可以自定义log4j日志输出格式。

下面我们实现按日期记录日志，日志达到指定大小后重命名保存为新文件。

首先新建GlobalRollingFileAppender继承RollingFileAppender

 ~~~java
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class GlobalRollingFileAppender extends RollingFileAppender {

    private long nextRollover = 0L;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");

    public void rollOver() {
        if (qw != null) {
            long size = ((CountingQuietWriter) qw).getCount();
            LogLog.debug("rolling over count=" + size);
            nextRollover = size + maxFileSize;
        }

        LogLog.debug("maxBackupIndex=" + maxBackupIndex);
        boolean renameSucceeded = true;
        if (maxBackupIndex > 0) {
            File file;
            File target;
            for (int i = maxBackupIndex - 1; i >= 1 && renameSucceeded; i--) {
                file = new File(genFileName(fileName, i));
                if (file.exists()) {
                    target = new File(genFileName(fileName, i + 1));
                    LogLog.debug("Renaming file " + file + " to " + target);
                    renameSucceeded = file.renameTo(target);
                }
            }

            if (renameSucceeded) {
                target = new File(genFileName(fileName, 1));
                this.closeFile();

                file = new File(fileName);
                LogLog.debug("Renaming file " + file + " to " + target);
                renameSucceeded = file.renameTo(target);

                if (!renameSucceeded) {
                    try {
                        this.setFile(fileName, true, bufferedIO, bufferSize);
                    } catch (IOException e) {
                        if (e instanceof InterruptedIOException) {
                            Thread.currentThread().interrupt();
                        }
                        LogLog.error("setFile(" + fileName + ", true) call failed.", e);
                    }
                }
            }
        }

        if (renameSucceeded) {
            try {
                this.setFile(fileName, false, bufferedIO, bufferSize);
                nextRollover = 0L;
            } catch (IOException e) {
                if (e instanceof InterruptedIOException) {
                    Thread.currentThread().interrupt();
                }
                LogLog.error("setFile(" + fileName + ", false) call failed.", e);
            }
        }
    }

    private String genFileName(String name, int index) {
        String fileName;
        if (index > 0) {
            String num = index < 10 ? "0" + index : String.valueOf(index);
            fileName = name.replace(".log", "") + "_" + num + ".log";
        } else {
            fileName = name;
        }

        return fileName;
    }

    protected void subAppend(LoggingEvent event) {
        boolean flag = false;
        String fileNameNow = fileName.substring(fileName.lastIndexOf("/"));
        String fileNameDate = simpleDateFormat.format(new Date());
        if (!fileNameNow.contains(fileNameDate)) {
            flag = true;
            int tag = fileNameNow.indexOf("_");
            fileName = fileName.replace(fileNameNow.substring(tag + 1, tag + 9), fileNameDate);
        }

        if (fileName != null && qw != null) {
            long size = ((CountingQuietWriter) qw).getCount();
            if (flag) {
                size = 0L;
            }
            if ((size >= maxFileSize && size >= nextRollover) || flag) {
                rollOver();
            }
        }

        super.subAppend(event);
    }

    public void setFile(String file) {
        fileName = file.trim().replace(".log", "") +
                "_" + simpleDateFormat.format(new Date()) + ".log";
    }
}
 ~~~

然后我们将log4j.properties日志输出appender设为GlobalRollingFileAppender ，即**log4j.appender.logInfo=cn.wbnull.springbootdemo.boot.GlobalRollingFileAppender**

~~~properties
log4j.rootLogger=debug,Console,logInfo,logError,logDebug
log4j.category.org.springframework=debug,Console,logInfo,logError,logDebug
#
#输出到控制台
log4j.appender.Console=org.apache.log4j.ConsoleAppender
log4j.appender.Console.Target=System.out
log4j.appender.Console.layout=org.apache.log4j.PatternLayout
log4j.appender.Console.layout.ConversionPattern=[%p][%d{yyyy-MM-dd HH\:mm\:ss,SSS}][%C.%M(%F\:%L)] || %m%n
#
#输出到文件
log4j.appender.logInfo=cn.wbnull.springbootdemo.boot.GlobalRollingFileAppender
log4j.appender.logInfo.File=./logs/log.log
log4j.appender.logInfo.Append=true
log4j.appender.logInfo.Threshold=INFO
log4j.appender.logInfo.MaxFileSize=2048KB
log4j.appender.logInfo.MaxBackupIndex=1024
log4j.appender.logInfo.layout=org.apache.log4j.PatternLayout
log4j.appender.logInfo.layout.ConversionPattern=[%p][%d{yyyy-MM-dd HH\:mm\:ss,SSS}][%C.%M(%F\:%L)] || %m%n
#
#错误日志单独记录
log4j.appender.logError=cn.wbnull.springbootdemo.boot.GlobalRollingFileAppender
log4j.appender.logError.File=./logs/error.log
log4j.appender.logError.Append=true
log4j.appender.logError.Threshold=ERROR
log4j.appender.logError.MaxFileSize=2048KB
log4j.appender.logError.MaxBackupIndex=1024
log4j.appender.logError.layout=org.apache.log4j.PatternLayout
log4j.appender.logError.layout.ConversionPattern=[%p][%d{yyyy-MM-dd HH\:mm\:ss,SSS}][%C.%M(%F\:%L)] || %m%n
#
#Debug日志单独记录
log4j.appender.logDebug=cn.wbnull.springbootdemo.boot.GlobalRollingFileAppender
log4j.appender.logDebug.File=./logs/debug.log
log4j.appender.logDebug.Append=true
log4j.appender.logDebug.Threshold=DEBUG
log4j.appender.logDebug.MaxFileSize=2048KB
log4j.appender.logDebug.MaxBackupIndex=1024
log4j.appender.logDebug.layout=org.apache.log4j.PatternLayout
log4j.appender.logDebug.filter.infoFilter=org.apache.log4j.varia.LevelRangeFilter
log4j.appender.logDebug.filter.infoFilter.LevelMin=DEBUG
log4j.appender.logDebug.filter.infoFilter.LevelMax=DEBUG
log4j.appender.logDebug.layout.ConversionPattern=[%p][%d{yyyy-MM-dd HH\:mm\:ss,SSS}][%C.%M(%F\:%L)] || %m%n
~~~

<br>

---

GitHub：[https://github.com/dkbnull/SpringBootDemo](https://github.com/dkbnull/SpringBootDemo)

CSDN：[https://blog.csdn.net/dkbnull/article/details/84558062](https://blog.csdn.net/dkbnull/article/details/84558062)

微信：[https://mp.weixin.qq.com/s/TY0lb921oZlvz2mnH_L2jg](https://mp.weixin.qq.com/s/TY0lb921oZlvz2mnH_L2jg)

微博：[https://weibo.com/ttarticle/p/show?id=2309404310748749823379](https://weibo.com/ttarticle/p/show?id=2309404310748749823379)

知乎：[https://zhuanlan.zhihu.com/p/95405807](https://zhuanlan.zhihu.com/p/95405807)