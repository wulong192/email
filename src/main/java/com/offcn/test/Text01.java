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
			
			// 1. ��дһ��job
			
			// MyJob.class: ָ�������jobʵ��������һ��
			// withIdentity�� �����ù����ı�ʶ�ţ� ��һ����job�����֣� �ڶ������������ĸ���
			JobDetail job = JobBuilder.newJob(MyJob.class).withIdentity("job1", "group1").build();
			
			
			// ��ȡδ����ĳ��ʱ�䣺��:::::
			
			// 2. ����������trigger
			SimpleTrigger trigger = (SimpleTrigger) TriggerBuilder.newTrigger().withIdentity("trigger1", "group1")
					
					// ����ʱ��������ʱ������ִ��
					//.startNow()
					
					// ָ��ʱ�俪ʼִ��job
					.startAt(new Date("2018/11/19 21:39:00"))
					// �����й�trigger��ִ�й���
					.withSchedule(SimpleScheduleBuilder.simpleSchedule().withIntervalInSeconds(2)   // ����ִ�еļ��ʱ�� ����λ  ��
							.withRepeatCount(10))		// ָ��������ظ�����
					.build();
				
			
			// 3. ����schedularʱ�������� �� job ��    trigger��������
			Scheduler sche = StdSchedulerFactory.getDefaultScheduler();
			
			// ����job ��  trigger
			sche.scheduleJob(job, trigger);
			
			// 4. ������ʱ����
			sche.start();
			
			Thread.sleep(12 * 1000);
			
			System.out.println("�������");
			
		}
}
