# 0. 开发环境

- IDE：IntelliJ IDEA 2017.1 x64

- jdk：1.8.0_91

- Spring Boot：2.1.1.RELEASE

  <br>

# 1. 简单定时任务

对于一些比较简单的定时任务，比如固定时间间隔执行固定方法，在标准Java方法上注解<font color="#FF0000">@Scheduled</font>即可

~~~java
package cn.wbnull.springbootdemo.schedule;

import cn.wbnull.springbootdemo.util.DateUtils;
import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(cron = "0/10 * * * * ?") //每10秒执行一次
    public void scheduledTaskByCorn() {
        LoggerUtils.info("定时任务开始 ByCorn：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务结束 ByCorn：" + DateUtils.dateFormat());
    }

    @Scheduled(fixedRate = 10000) //每10秒执行一次
    public void scheduledTaskByFixedRate() {
        LoggerUtils.info("定时任务开始 ByFixedRate：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务结束 ByFixedRate：" + DateUtils.dateFormat());
    }

    @Scheduled(fixedDelay = 10000) //每10秒执行一次
    public void scheduledTaskByFixedDelay() {
        LoggerUtils.info("定时任务开始 ByFixedDelay：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务结束 ByFixedDelay：" + DateUtils.dateFormat());
    }

    private void scheduledTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
~~~

然后项目启动类上增加注解<font color="#FF0000">@EnableScheduling</font>，表示开启定时任务

~~~java
package cn.wbnull.springbootdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class SpringBootDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoApplication.class, args);
    }
}
~~~

这里因为我们在ScheduledTask类创建了三个定时任务，@Scheduled默认是不并发执行的，因此我们先注释掉其他，分别进行测试。

## 1.1 @Scheduled(cron = "0/10 * * * * ?")

~~~java
package cn.wbnull.springbootdemo.schedule;

import cn.wbnull.springbootdemo.util.DateUtils;
import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(cron = "0/10 * * * * ?") //每10秒执行一次
    public void scheduledTaskByCorn() {
        LoggerUtils.info("定时任务开始 ByCorn：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务结束 ByCorn：" + DateUtils.dateFormat());
    }

    private void scheduledTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
~~~

启动项目，运行结果如下

~~~
[INFO][2019-02-18 16:08:40,095]||定时任务开始 ByCorn：2019-02-18 16:08:40
[INFO][2019-02-18 16:08:45,097]||定时任务结束 ByCorn：2019-02-18 16:08:45
[INFO][2019-02-18 16:08:50,001]||定时任务开始 ByCorn：2019-02-18 16:08:50
[INFO][2019-02-18 16:08:55,003]||定时任务结束 ByCorn：2019-02-18 16:08:55
[INFO][2019-02-18 16:09:00,002]||定时任务开始 ByCorn：2019-02-18 16:09:00
[INFO][2019-02-18 16:09:05,004]||定时任务结束 ByCorn：2019-02-18 16:09:05
[INFO][2019-02-18 16:09:10,001]||定时任务开始 ByCorn：2019-02-18 16:09:10
[INFO][2019-02-18 16:09:15,003]||定时任务结束 ByCorn：2019-02-18 16:09:15
[INFO][2019-02-18 16:09:20,001]||定时任务开始 ByCorn：2019-02-18 16:09:20
[INFO][2019-02-18 16:09:25,002]||定时任务结束 ByCorn：2019-02-18 16:09:25
[INFO][2019-02-18 16:09:30,001]||定时任务开始 ByCorn：2019-02-18 16:09:30
[INFO][2019-02-18 16:09:35,002]||定时任务结束 ByCorn：2019-02-18 16:09:35
~~~

我们再改下scheduledTask方法中线程休眠时间，使休眠时间大于定时任务间隔时间<font color="8B8B7A">Thread.sleep(12000);</font>，然后启动项目，查看运行结果。

~~~
[INFO][2019-02-18 16:14:20,080]||定时任务开始 ByCorn：2019-02-18 16:14:20
[INFO][2019-02-18 16:14:32,081]||定时任务结束 ByCorn：2019-02-18 16:14:32
[INFO][2019-02-18 16:14:40,001]||定时任务开始 ByCorn：2019-02-18 16:14:40
[INFO][2019-02-18 16:14:52,002]||定时任务结束 ByCorn：2019-02-18 16:14:52
[INFO][2019-02-18 16:15:00,000]||定时任务开始 ByCorn：2019-02-18 16:15:00
[INFO][2019-02-18 16:15:12,002]||定时任务结束 ByCorn：2019-02-18 16:15:12
~~~

我们可以看到，对于cron表达式 来说，如果业务代码执行时间小于定时任务间隔时间，那么定时任务每10秒执行一次，且不受业务代码影响，无论业务代码执行多久，定时任务都是10秒执行一次；

如果业务代码执行时间大于定时任务间隔时间，因定时任务默认不并发，所以一直到业务代码执行完成的那个10秒，定时任务也是整10秒执行一次，不受业务代码影响。

<font color="#FF0000">注意：@Scheduled(cron = "0/10 * * * * ?")控制的每10秒执行一次的定时任务，是每10秒整执行一次，即一分钟内，如果当前秒数能够整除10，则执行定时任务，或理解为每分钟0秒开始执行，10秒后执行下一次，执行完一分钟后，再从0秒开始。即只会在10s，20s，30s...的时候执行，如果配置定时任务@Scheduled(cron = "0/7 * * * * ?")这种，则只会在0s，7s，14s...的时候执行。</font>

## 1.2 @Scheduled(fixedRate = 10000)

~~~java
package cn.wbnull.springbootdemo.schedule;

import cn.wbnull.springbootdemo.util.DateUtils;
import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(fixedRate = 10000) //每10秒执行一次
    public void scheduledTaskByFixedRate() {
        LoggerUtils.info("定时任务开始 ByFixedRate：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务结束 ByFixedRate：" + DateUtils.dateFormat());
    }

    private void scheduledTask() {
        try {
            Thread.sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
~~~

启动项目，运行结果如下

~~~
[INFO][2019-02-18 17:33:18,235]||定时任务开始 ByFixedRate：2019-02-18 17:33:18
[INFO][2019-02-18 17:33:23,239]||定时任务结束 ByFixedRate：2019-02-18 17:33:23
[INFO][2019-02-18 17:33:28,191]||定时任务开始 ByFixedRate：2019-02-18 17:33:28
[INFO][2019-02-18 17:33:33,195]||定时任务结束 ByFixedRate：2019-02-18 17:33:33
[INFO][2019-02-18 17:33:38,189]||定时任务开始 ByFixedRate：2019-02-18 17:33:38
[INFO][2019-02-18 17:33:43,191]||定时任务结束 ByFixedRate：2019-02-18 17:33:43
[INFO][2019-02-18 17:33:48,184]||定时任务开始 ByFixedRate：2019-02-18 17:33:48
[INFO][2019-02-18 17:33:53,186]||定时任务结束 ByFixedRate：2019-02-18 17:33:53
[INFO][2019-02-18 17:33:58,190]||定时任务开始 ByFixedRate：2019-02-18 17:33:58
[INFO][2019-02-18 17:34:03,193]||定时任务结束 ByFixedRate：2019-02-18 17:34:03
~~~

我们再改下scheduledTask方法中线程休眠时间，使休眠时间大于定时任务间隔时间<font color="8B8B7A">Thread.sleep(12000);</font>，然后启动项目，查看运行结果。

~~~
[INFO][2019-02-18 17:31:30,122]||定时任务开始 ByFixedRate：2019-02-18 17:31:30
[INFO][2019-02-18 17:31:42,122]||定时任务结束 ByFixedRate：2019-02-18 17:31:42
[INFO][2019-02-18 17:31:42,123]||定时任务开始 ByFixedRate：2019-02-18 17:31:42
[INFO][2019-02-18 17:31:54,123]||定时任务结束 ByFixedRate：2019-02-18 17:31:54
[INFO][2019-02-18 17:31:54,124]||定时任务开始 ByFixedRate：2019-02-18 17:31:54
[INFO][2019-02-18 17:32:06,127]||定时任务结束 ByFixedRate：2019-02-18 17:32:06
[INFO][2019-02-18 17:32:06,127]||定时任务开始 ByFixedRate：2019-02-18 17:32:06
[INFO][2019-02-18 17:32:18,134]||定时任务结束 ByFixedRate：2019-02-18 17:32:18
~~~

对于fixedRate 来说，如果业务代码执行时间小于定时任务间隔时间，那么定时任务每10秒执行一次，且不受业务代码影响，无论业务代码执行多久，定时任务都是10秒执行一次；

如果业务代码执行时间大于定时任务间隔时间，则定时任务循环执行。

## 1.3 @Scheduled(fixedDelay = 10000)

~~~java
package cn.wbnull.springbootdemo.schedule;

import cn.wbnull.springbootdemo.util.DateUtils;
import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(fixedDelay = 10000) //每10秒执行一次
    public void scheduledTaskByFixedDelay() {
        LoggerUtils.info("定时任务开始 ByFixedDelay：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务结束 ByFixedDelay：" + DateUtils.dateFormat());
    }

    private void scheduledTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
~~~

启动项目，运行结果如下

~~~
[INFO][2019-02-18 17:45:30,784]||定时任务开始 ByFixedDelay：2019-02-18 17:45:30
[INFO][2019-02-18 17:45:35,792]||定时任务结束 ByFixedDelay：2019-02-18 17:45:35
[INFO][2019-02-18 17:45:45,803]||定时任务开始 ByFixedDelay：2019-02-18 17:45:45
[INFO][2019-02-18 17:45:50,812]||定时任务结束 ByFixedDelay：2019-02-18 17:45:50
[INFO][2019-02-18 17:46:00,814]||定时任务开始 ByFixedDelay：2019-02-18 17:46:00
[INFO][2019-02-18 17:46:05,817]||定时任务结束 ByFixedDelay：2019-02-18 17:46:05
[INFO][2019-02-18 17:46:15,821]||定时任务开始 ByFixedDelay：2019-02-18 17:46:15
[INFO][2019-02-18 17:46:20,825]||定时任务结束 ByFixedDelay：2019-02-18 17:46:20
[INFO][2019-02-18 17:46:30,829]||定时任务开始 ByFixedDelay：2019-02-18 17:46:30
[INFO][2019-02-18 17:46:35,834]||定时任务结束 ByFixedDelay：2019-02-18 17:46:35
~~~

我们再改下scheduledTask方法中线程休眠时间，使休眠时间大于定时任务间隔时间<font color="8B8B7A">Thread.sleep(12000);</font>，然后启动项目，查看运行结果。

~~~
[INFO][2019-02-18 17:47:06,871]||定时任务开始 ByFixedDelay：2019-02-18 17:47:06
[INFO][2019-02-18 17:47:18,879]||定时任务结束 ByFixedDelay：2019-02-18 17:47:18
[INFO][2019-02-18 17:47:28,890]||定时任务开始 ByFixedDelay：2019-02-18 17:47:28
[INFO][2019-02-18 17:47:40,896]||定时任务结束 ByFixedDelay：2019-02-18 17:47:40
[INFO][2019-02-18 17:47:50,903]||定时任务开始 ByFixedDelay：2019-02-18 17:47:50
[INFO][2019-02-18 17:48:02,911]||定时任务结束 ByFixedDelay：2019-02-18 17:48:02
[INFO][2019-02-18 17:48:12,917]||定时任务开始 ByFixedDelay：2019-02-18 17:48:12
[INFO][2019-02-18 17:48:24,924]||定时任务结束 ByFixedDelay：2019-02-18 17:48:24
~~~

对于fixedDelay 来说，不管业务代码执行时间与定时任务间隔时间熟长熟短，定时任务都会等业务代码执行完成后再开启新一轮定时。

<br>

不过，一般大家在使用定时任务时，都是定时任务时间间隔大于业务代码执行时间。

## 1.4 多说一点

<font color="#FF0000">对于固定时间执行的定时任务，比如每天凌晨4点执行，只能使用**cron表达式**的方式</font>

<br>

# 2. corn表达式

## 2.1 corn表达式格式

<font color="#FF0000">**corn表达式格式：秒 分 时 日 月 星期 年（可选）**</font>

| 字段名     | 允许的值        | 允许的特殊字符  |
| ---------- | --------------- | --------------- |
| 秒         | 0-59            | , - * /         |
| 分         | 0-59            | , - * /         |
| 时         | 0-23            | , - * /         |
| 日         | 1-31            | , - * ? / L W C |
| 月         | 1-12 或 JAN-DEC | , - * /         |
| 星期       | 1-7 或 SUN-SAT  | , - * ? / L C # |
| 年（可选） | 空 或 1970-2099 | , - * /         |

释义：

1、**\***：通配符，表示该字段可以接收任意值。

2、**？** ：表示不确定的值，或不关心它为何值，仅在日期和星期中使用，当其中一个设置了条件时，另外一个用"?" 来表示"任何值"。 

3、**,**：表示多个值，附加一个生效的值。

4、**-**：表示一个指定的范围

5、**/**：指定一个值的增量值。例n/m表示从n开始，每次增加m

6、**L**：用在日期表示当月的最后一天，用在星期"L"单独使用时就等于"7"或"SAT"，如果和数字联合使用表示该月最后一个星期X。例如，"0L"表示该月最后一个星期日。

7、**W**：指定离给定日期最近的工作日（周一到周五），可以用"LW"表示该月最后一个工作日。例如，"10W"表示这个月离10号最近的工作日

8、**C**：表示和calendar联系后计算过的值。例如：用在日期中，"5C"表示该月第5天或之后包括calendar的第一天；用在星期中，"5C"表示这周四或之后包括calendar的第 一天。 

9、**#**：表示该月第几个星期X。例6#3表示该月第三个周五。

## 2.2 示例值

0 * * * * ?	每分钟触发

0 0 * * * ? 	每小时整触发

0 0 4 * * ?	每天凌晨4点触发

0 15 10 * * ?	每天早上10：15触发

*/5 * * * * ?	每隔5秒触发

0 */5 * * * ?	每隔5分钟触发

0 0 4 1 * ?	每月1号凌晨4点触发

0 0 4 L * ?	每月最后一天凌晨3点触发

0 0 3 ? * L	每周星期六凌晨3点触发

0 11,22,33 * * * ?	每小时11分、22分、33分触发

<br>

# 3. 配置定时任务 

对于上面那些简单的定时任务，定时任务的corn表达式写死在代码里，如果要改动表达式，需要修改代码，重新打包发布，比较麻烦。因此，我们可以把corn表达式配置在配置文件中，然后程序读取配置，当需要修改表达式时，只需要修改配置文件即可。

application.yml增加配置

~~~yaml
demo:
  corn: 0/11 * * * * ?
~~~

定时任务

~~~java
package cn.wbnull.springbootdemo.schedule;

import cn.wbnull.springbootdemo.util.DateUtils;
import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTask {

    @Scheduled(cron = "${demo.corn}")
    public void scheduledTaskByConfig() {
        LoggerUtils.info("定时任务 ByConfig：" + DateUtils.dateFormat());
    }
}
~~~

启动项目，运行结果如下

~~~
[INFO][2019-02-18 23:47:33,047]||定时任务 ByConfig：2019-02-18 23:47:33
[INFO][2019-02-18 23:47:44,003]||定时任务 ByConfig：2019-02-18 23:47:44
[INFO][2019-02-18 23:47:55,009]||定时任务 ByConfig：2019-02-18 23:47:55
[INFO][2019-02-18 23:48:00,008]||定时任务 ByConfig：2019-02-18 23:48:00
[INFO][2019-02-18 23:48:11,009]||定时任务 ByConfig：2019-02-18 23:48:11
[INFO][2019-02-18 23:48:22,009]||定时任务 ByConfig：2019-02-18 23:48:22
[INFO][2019-02-18 23:48:33,009]||定时任务 ByConfig：2019-02-18 23:48:33
~~~

修改application.yml配置

~~~
demo:
  corn: 0/23 * * * * ?
~~~

启动项目，运行结果如下

~~~
[INFO][2019-02-18 23:52:23,089]||定时任务 ByConfig：2019-02-18 23:52:23
[INFO][2019-02-18 23:52:46,008]||定时任务 ByConfig：2019-02-18 23:52:46
[INFO][2019-02-18 23:53:00,009]||定时任务 ByConfig：2019-02-18 23:53:00
[INFO][2019-02-18 23:53:23,002]||定时任务 ByConfig：2019-02-18 23:53:23
[INFO][2019-02-18 23:53:46,009]||定时任务 ByConfig：2019-02-18 23:53:46
~~~

定时任务根据配置文件动态变化。

<br>

# 4. 动态修改定时任务

对于有些情况，我们需要在代码中，通过方法动态修改定时任务corn表达式

application.yml配置

~~~yaml
demo:
  corn: 0/7 * * * * ?
  cornV2: 0/22 * * * * ?
~~~

新建ScheduledTaskV2.java

~~~java
package cn.wbnull.springbootdemo.schedule;

import cn.wbnull.springbootdemo.util.DateUtils;
import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskV2 implements SchedulingConfigurer {

    @Value("${demo.corn}")
    private String corn;
    @Value("${demo.cornV2}")
    private String cornV2;

    private int tag = 0;

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.addTriggerTask(() -> {
            LoggerUtils.info("定时任务V2：" + DateUtils.dateFormat());
        }, (triggerContext) -> {
            CronTrigger cronTrigger;
            if (tag % 2 == 0) {
                LoggerUtils.info("定时任务V2动态修改corn表达式：" + corn + "," + DateUtils.dateFormat());
                cronTrigger = new CronTrigger(corn);
                tag++;
            } else {
                LoggerUtils.info("定时任务V2动态修改corn表达式：" + cornV2 + "," + DateUtils.dateFormat());
                cronTrigger = new CronTrigger(cornV2);
                tag++;
            }

            return cronTrigger.nextExecutionTime(triggerContext);
        });
    }
}
~~~

启动项目，运行结果如下

~~~
[INFO][2019-02-19 00:19:49,011]||定时任务V2：2019-02-19 00:19:49
[INFO][2019-02-19 00:19:49,011]||定时任务V2动态修改corn表达式：0/22 * * * * ?,2019-02-19 00:19:49
[INFO][2019-02-19 00:20:00,007]||定时任务V2：2019-02-19 00:20:00
[INFO][2019-02-19 00:20:00,007]||定时任务V2动态修改corn表达式：0/7 * * * * ?,2019-02-19 00:20:00
[INFO][2019-02-19 00:20:07,006]||定时任务V2：2019-02-19 00:20:07
[INFO][2019-02-19 00:20:07,006]||定时任务V2动态修改corn表达式：0/22 * * * * ?,2019-02-19 00:20:07
[INFO][2019-02-19 00:20:22,008]||定时任务V2：2019-02-19 00:20:22
[INFO][2019-02-19 00:20:22,008]||定时任务V2动态修改corn表达式：0/7 * * * * ?,2019-02-19 00:20:22
[INFO][2019-02-19 00:20:28,010]||定时任务V2：2019-02-19 00:20:28
[INFO][2019-02-19 00:20:28,010]||定时任务V2动态修改corn表达式：0/22 * * * * ?,2019-02-19 00:20:28
[INFO][2019-02-19 00:20:44,003]||定时任务V2：2019-02-19 00:20:44
[INFO][2019-02-19 00:20:44,003]||定时任务V2动态修改corn表达式：0/7 * * * * ?,2019-02-19 00:20:44
[INFO][2019-02-19 00:20:49,004]||定时任务V2：2019-02-19 00:20:49
[INFO][2019-02-19 00:20:49,004]||定时任务V2动态修改corn表达式：0/22 * * * * ?,2019-02-19 00:20:49
[INFO][2019-02-19 00:21:00,011]||定时任务V2：2019-02-19 00:21:00
[INFO][2019-02-19 00:21:00,011]||定时任务V2动态修改corn表达式：0/7 * * * * ?,2019-02-19 00:21:00
[INFO][2019-02-19 00:21:07,011]||定时任务V2：2019-02-19 00:21:07
[INFO][2019-02-19 00:21:07,011]||定时任务V2动态修改corn表达式：0/22 * * * * ?,2019-02-19 00:21:07
~~~

成功通过代码动态修改corn表达式且运行结果正确。

<br>

# 5. 并发执行定时任务

回到我们 **1. 简单定时任务** 中创建的三个定时任务，当时因为@Scheduled默认是不并发执行的，所以我们先注释掉了其他定时任务，分别进行的测试。

那我们实际开发中，确实创建了多个定时任务，且想并发执行时，该怎么做呢？

<font color="#FF0000">**定时任务类添加注解@EnableAsync，需并发执行的定时任务方法添加注解@Async**</font>

新建定时任务类ScheduledTaskV3

~~~java
package cn.wbnull.springbootdemo.schedule;

import cn.wbnull.springbootdemo.util.DateUtils;
import cn.wbnull.springbootdemo.util.LoggerUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableAsync
public class ScheduledTaskV3 {

    @Scheduled(cron = "0/7 * * * * ?")
    @Async
    public void scheduledTaskV1() {
        LoggerUtils.info("定时任务V3，定时任务1开始：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务V3，定时任务1结束：" + DateUtils.dateFormat());
    }

    @Scheduled(cron = "0/10 * * * * ?")
    @Async
    public void scheduledTaskV2() {
        LoggerUtils.info("定时任务V3，定时任务2开始：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务V3，定时任务2结束：" + DateUtils.dateFormat());
    }

    @Scheduled(cron = "0/22 * * * * ?")
    @Async
    public void scheduledTaskV3() {
        LoggerUtils.info("定时任务V3，定时任务3开始：" + DateUtils.dateFormat());
        scheduledTask();
        LoggerUtils.info("定时任务V3，定时任务3结束：" + DateUtils.dateFormat());
    }

    private void scheduledTask() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
~~~

 启动项目，运行结果如下

~~~
[INFO][2019-02-19 00:36:21,077]||定时任务V3，定时任务1开始：2019-02-19 00:36:21
[INFO][2019-02-19 00:36:22,003]||定时任务V3，定时任务3开始：2019-02-19 00:36:22
[INFO][2019-02-19 00:36:26,078]||定时任务V3，定时任务1结束：2019-02-19 00:36:26
[INFO][2019-02-19 00:36:27,006]||定时任务V3，定时任务3结束：2019-02-19 00:36:27
[INFO][2019-02-19 00:36:28,003]||定时任务V3，定时任务1开始：2019-02-19 00:36:28
[INFO][2019-02-19 00:36:30,003]||定时任务V3，定时任务2开始：2019-02-19 00:36:30
[INFO][2019-02-19 00:36:33,003]||定时任务V3，定时任务1结束：2019-02-19 00:36:33
[INFO][2019-02-19 00:36:35,004]||定时任务V3，定时任务1开始：2019-02-19 00:36:35
[INFO][2019-02-19 00:36:35,005]||定时任务V3，定时任务2结束：2019-02-19 00:36:35
[INFO][2019-02-19 00:36:40,003]||定时任务V3，定时任务2开始：2019-02-19 00:36:40
[INFO][2019-02-19 00:36:40,005]||定时任务V3，定时任务1结束：2019-02-19 00:36:40
[INFO][2019-02-19 00:36:42,001]||定时任务V3，定时任务1开始：2019-02-19 00:36:42
[INFO][2019-02-19 00:36:44,003]||定时任务V3，定时任务3开始：2019-02-19 00:36:44
[INFO][2019-02-19 00:36:45,004]||定时任务V3，定时任务2结束：2019-02-19 00:36:45
[INFO][2019-02-19 00:36:47,002]||定时任务V3，定时任务1结束：2019-02-19 00:36:47
[INFO][2019-02-19 00:36:49,002]||定时任务V3，定时任务1开始：2019-02-19 00:36:49
[INFO][2019-02-19 00:36:49,004]||定时任务V3，定时任务3结束：2019-02-19 00:36:49
[INFO][2019-02-19 00:36:50,001]||定时任务V3，定时任务2开始：2019-02-19 00:36:50
~~~

定时任务能够并发执行。

<br>

---

GitHub：[https://github.com/dkbnull/SpringBootDemo](https://github.com/dkbnull/SpringBootDemo)

CSDN：[https://blog.csdn.net/dkbnull/article/details/87659898](https://blog.csdn.net/dkbnull/article/details/87659898)

微信：[https://mp.weixin.qq.com/s/qJZpCjcZYQWnmDkztiA_uQ](https://mp.weixin.qq.com/s/qJZpCjcZYQWnmDkztiA_uQ)

微博：[https://weibo.com/ttarticle/p/show?id=2309404446645717696521](https://weibo.com/ttarticle/p/show?id=2309404446645717696521)

知乎：[https://zhuanlan.zhihu.com/p/95813468](https://zhuanlan.zhihu.com/p/95813468)