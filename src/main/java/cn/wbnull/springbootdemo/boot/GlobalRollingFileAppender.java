package cn.wbnull.springbootdemo.boot;

import org.apache.log4j.RollingFileAppender;
import org.apache.log4j.helpers.CountingQuietWriter;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

import java.io.File;
import java.io.IOException;
import java.io.InterruptedIOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 自定义log4j日志输出
 *
 * @author dukunbiao(null)  2018-11-26
 *         https://github.com/dkbnull/Util
 */
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
            // 删除序号最大（最早的文件）的文件
            /*File file = new File(genFileName(fileName, maxBackupIndex));
            if (file.exists()) {
                renameSucceeded = file.delete();
            }*/

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
