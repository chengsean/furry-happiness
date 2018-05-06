package cn.chengsean.quartzDemo;

import java.util.HashMap;
import java.util.Map;
import org.quartz.DailyTimeIntervalScheduleBuilder;
import org.quartz.DateBuilder;
import org.quartz.DateBuilder.IntervalUnit;
import org.quartz.JobBuilder;
import org.quartz.JobDataMap;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;

public class Test {
	public Test() {
	}
	public static void main(String[] args) throws SchedulerException {
		StdSchedulerFactory factory = new StdSchedulerFactory();
		Scheduler scheduler = factory.getScheduler();
		Test t = new Test();
		t.executeJob1(scheduler);
		t.executeJob2(scheduler);
		//System.out.println(Thread.currentThread().getContextClassLoader().getResource(""));
	}
	 void executeJob2(Scheduler scheduler) throws SchedulerException {
		 Map<String, Object> map = new HashMap<>();
		  map.put("id", System.currentTimeMillis());
		  map.put("name", "job1_group1");
		  JobDetail job1 = JobBuilder.newJob(MyJobImpl2.class)
			      .withIdentity("job1", "group1")
			      .setJobData(new JobDataMap(map))
			      .build();

			  // 任务触发器
			  Trigger trigger1 = TriggerBuilder.newTrigger()
			      .withIdentity("trigger1", "group1")
			      .startAt(DateBuilder.futureDate(5, IntervalUnit.SECOND))
			      .withSchedule(DailyTimeIntervalScheduleBuilder
			    		  .dailyTimeIntervalSchedule()
			    		  .withInterval(5, IntervalUnit.SECOND))
			      .build();

		  // Tell quartz to schedule the job using our trigger
		  scheduler.scheduleJob(job1, trigger1);
		  // and start it off
		  scheduler.start();
	}
	 void executeJob1(Scheduler scheduler) throws SchedulerException {
		// Grab the Scheduler instance from the Factory
		  Map<String, Object> map = new HashMap<>();
		  map.put("id", System.currentTimeMillis());
		  map.put("name", "job_group");
		// define the job and tie it to our MyJob class
		  JobDetail job = JobBuilder.newJob(MyJobImpl.class)
		      .withIdentity("job", "group")
		      .setJobData(new JobDataMap(map))
		      .build();

		  // Trigger the job to run now, and then repeat every 40 seconds
		  Trigger trigger = TriggerBuilder.newTrigger()
		      .withIdentity("trigger", "group")
		      .startAt(DateBuilder.futureDate(5, IntervalUnit.SECOND))
		      .withSchedule(DailyTimeIntervalScheduleBuilder
		    		  .dailyTimeIntervalSchedule()
		    		  .withInterval(5, IntervalUnit.SECOND))
		      .build();
		  // Tell quartz to schedule the job using our trigger
		  scheduler.scheduleJob(job, trigger);
		  // and start it off
		  scheduler.start();
	}
}
