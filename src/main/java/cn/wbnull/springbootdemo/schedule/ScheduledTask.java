package cn.wbnull.springbootdemo.schedule;

import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author dukunbiao(null)  2019-02-18
 */
@Component
public class ScheduledTask {

//    @Scheduled(cron = "0/10 * * * * ?") //每10秒执行一次
//    public void scheduledTaskByCorn() {
//        LoggerUtils.info("定时任务开始 ByCorn：" + DateUtils.dateFormat());
//        scheduledTask();
//        LoggerUtils.info("定时任务结束 ByCorn：" + DateUtils.dateFormat());
//    }
//
//    @Scheduled(fixedRate = 10000) //每10秒执行一次
//    public void scheduledTaskByFixedRate() {
//        LoggerUtils.info("定时任务开始 ByFixedRate：" + DateUtils.dateFormat());
//        scheduledTask();
//        LoggerUtils.info("定时任务结束 ByFixedRate：" + DateUtils.dateFormat());
//    }
//
//    @Scheduled(fixedDelay = 10000) //每10秒执行一次
//    public void scheduledTaskByFixedDelay() {
//        LoggerUtils.info("定时任务开始 ByFixedDelay：" + DateUtils.dateFormat());
//        scheduledTask();
//        LoggerUtils.info("定时任务结束 ByFixedDelay：" + DateUtils.dateFormat());
//    }
//
//    @Scheduled(cron = "${demo.corn}")
//    public void scheduledTaskByConfig() {
//        LoggerUtils.info("定时任务 ByConfig：" + DateUtils.dateFormat());
//    }

    private void scheduledTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
