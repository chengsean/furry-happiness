package cn.chengsean.quartzDemo;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class MyJobImpl implements MyJob {
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
	long start = 0L;
	
	/*public MyJobImpl() {
	}*/

	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		this.execute0(context);
	}

	private void execute0(JobExecutionContext context) {
		JobDataMap dataMap = context.getMergedJobDataMap();
		this.id = Long.parseLong(String.valueOf(dataMap.get("id")));
		this.name = String.valueOf(dataMap.get("name"));
		System.out.println(this+" id: "+this.id);
		System.out.println(this+" name: "+this.name);
		System.err.println(this+" execute by: "+(System.currentTimeMillis() - start));
		start = System.currentTimeMillis();
	}

}
