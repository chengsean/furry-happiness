package cn.chengsean.quartzDemo;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;


public class MyJobImpl2 implements MyJob {
	static long start = 0L;
	private long id;
	private String name;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	/*public MyJobImpl2() {
	}*/

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		execute0(context);
		System.err.println(this.getName()+" executed!!!!");
	}

	private void execute0(JobExecutionContext context) {
		JobDataMap dataMap = context.getMergedJobDataMap();
		this.id = Long.parseLong(String.valueOf(dataMap.get("id")));
		this.name = String.valueOf(dataMap.get("name"));
		System.err.println(this+" id: "+this.id);
		System.err.println(this+" name: "+this.name);
		System.err.println(this+" execute by: "+(System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
	}
}
