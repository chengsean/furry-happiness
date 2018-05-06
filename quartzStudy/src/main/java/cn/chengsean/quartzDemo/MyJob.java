package cn.chengsean.quartzDemo;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.PersistJobDataAfterExecution;

/***
 * 单任务执行
 */
@DisallowConcurrentExecution
/**
 * 任务调度完成保存JobData
 */
@PersistJobDataAfterExecution
public interface MyJob extends Job{
	public long getId();

	public void setId(long id);
	public String getName();

	public void setName(String name);
}
