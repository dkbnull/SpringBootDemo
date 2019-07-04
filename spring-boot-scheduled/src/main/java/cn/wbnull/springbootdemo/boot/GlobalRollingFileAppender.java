package cn.wbnull.springbootdemo.boot;

import cn.wbnull.springbootdemo.util.DateUtils;
import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;

/**
 * 全局log4j日志输出
 * 按日期记录日志，超过指定大小后记录为新日志文件
 *
 * @author dukunbiao(null)  2018-12-31
 * https://github.com/dkbnull/Util
 */
public class GlobalRollingFileAppender extends RollingFileAppender {

    private long nextRollover = 0L;

    private static String DATE_FORMAT = "yyyyMMdd";

    @Override
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

    @Override
    protected void subAppend(LoggingEvent event) {
        boolean flag = false;
        String fileNameNow = fileName.substring(fileName.lastIndexOf("/"));
        String fileNameDate = DateUtils.dateFormat(DATE_FORMAT);
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
            boolean bool = (size >= maxFileSize && size >= nextRollover) || flag;
            if (bool) {
                rollOver();
            }
        }

        super.subAppend(event);
    }

    @Override
    public void setFile(String file) {
        fileName = file.trim().replace(".log", "") +
                "_" + DateUtils.dateFormat(DATE_FORMAT) + ".log";
    }
}
