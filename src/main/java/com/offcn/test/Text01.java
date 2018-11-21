package com.offcn.test;

import java.util.Date;

import org.apache.http.impl.conn.DefaultSchemePortResolver;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.ScheduleBuilder;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SimpleScheduleBuilder;
import org.quartz.SimpleTrigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Text01 {
		
	
		
		public static void main(String[] args) throws Exception {
			
			// 1. 书写一个job
			
			// MyJob.class: 指定具体的job实现类是哪一个
			// withIdentity： 给定该工作的标识号， 第一个是job的名字， 第二个工作属于哪个组
			JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
			
			
			// 获取未来的某个时间：：:::::
			
			// 2. 创建触发器trigger
			SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
					
					// 当定时任务启动时，马上执行
					//.startNow()
					
					// 指定时间开始执行job
					.startAt(new Date("2018/11/19 21:39:00"))
					// 设置有关trigger的执行规则
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)   // 任务执行的间隔时间 ，单位  秒
							.withRepeatCount(10))		// 指定任务的重复次数
					.build();
				
			
			// 3. 创建schedular时间管理对象： 将 job 和    trigger关联起来
			Scheduler sche = StdSchedulerFactory.getDefaultScheduler();
			
			// 关联job 和  trigger
			sche.scheduleJob(job, trigger);
			
			// 4. 启动定时任务
			sche.start();
			
			Thread.sleep(12 * 1000);
			
			System.out.println("程序结束");
			
		}
}
