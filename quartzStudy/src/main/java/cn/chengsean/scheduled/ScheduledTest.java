package cn.chengsean.scheduled;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class ScheduledTest {
	public static void main(String[] args) {
		ScheduledExecutorService service = Executors.newScheduledThreadPool(2);
		service.scheduleAtFixedRate(new Runnable() {
			long start = 0L;
			@Override
			public void run() {
				System.out.println(this+": "+System.currentTimeMillis());
				System.out.println(this+": executed by time "+(System.currentTimeMillis() - start));
				start = System.currentTimeMillis();
			}
		}, 1000, 5000, TimeUnit.MILLISECONDS);
		service.scheduleAtFixedRate(new Runnable() {
			long start = 0L;
			@Override
			public void run() {
				System.out.println(this+": "+System.currentTimeMillis());
				System.out.println(this+": executed by time "+(System.currentTimeMillis() - start));
				start = System.currentTimeMillis();
			}
		}, 1000, 5000, TimeUnit.MILLISECONDS);
	}
}
