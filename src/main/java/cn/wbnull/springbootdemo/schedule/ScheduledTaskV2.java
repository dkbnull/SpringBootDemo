package cn.wbnull.springbootdemo.schedule;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.stereotype.Component;

/**
 * 定时任务
 *
 * @author dukunbiao(null)  2019-02-18
 *         https://github.com/dkbnull/SpringBootDemo
 */
@Component
public class ScheduledTaskV2 implements SchedulingConfigurer {

    @Value("${demo.corn}")
    private String corn;
    @Value("${demo.cornV2}")
    private String cornV2;

    private int tag = 0;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
//        scheduledTaskRegistrar.addTriggerTask(() -> {
//            LoggerUtils.info("定时任务V2：" + DateUtils.dateFormat());
//        }, (triggerContext) -> {
//            CronTrigger cronTrigger;
//            if (tag % 2 == 0) {
//                LoggerUtils.info("定时任务V2动态修改corn表达式：" + corn + "," + DateUtils.dateFormat());
//                cronTrigger = new CronTrigger(corn);
//                tag++;
//            } else {
//                LoggerUtils.info("定时任务V2动态修改corn表达式：" + cornV2 + "," + DateUtils.dateFormat());
//                cronTrigger = new CronTrigger(cornV2);
//                tag++;
//            }
//
//            return cronTrigger.nextExecutionTime(triggerContext);
//        });
    }
}
