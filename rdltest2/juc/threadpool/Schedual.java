package threadpool;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.regex.Pattern;

import org.apache.logging.log4j.Logger;
import org.junit.Test;

public class Schedual {
	volatile static  int num = 0;	
	volatile static int i = 0;
	
	
	public static void main( String args[] ) {
		
		ReentrantLock rlock = new ReentrantLock( true );
		ScheduledExecutorService s = Executors.newScheduledThreadPool(5);
		List<Callable> list = new ArrayList<Callable>(); 
		for( i = 0; i < 20; i++) {
			Callable<String> r = new Callable<String>() {
				@Override
				public String call() {
					rlock.lock();
					int t = num++;
					System.out.println("-------" + t );
					try {
//						if( t == 8) {
//							Thread.currentThread().interrupt();
//						}
						Thread.currentThread().sleep(1000L);
						if( t == 8) {
							Thread.currentThread().interrupt();
						}
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rlock.unlock();
					return t+"";
				}
			};
			s.submit(r);
		}
		
		s.shutdown();
		try {
			System.in.read();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
public static void main( String a) {
		
		ReentrantLock rlock = new ReentrantLock();
		ScheduledExecutorService s = Executors.newScheduledThreadPool(5);
		List<Callable> list = new ArrayList<Callable>(); 
		for( int i = 0; i < 20; i++) {
			Callable<String> r = new Callable<String>() {
				@Override
				public String call() {
					rlock.lock();
					int t = num++;
					System.out.println("-------" + t );
					try {
						Thread.currentThread().sleep(20000L);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					rlock.unlock();
					return t+"";
				}
			};
		}
		try {
			s.invokeAll((Collection<? extends Callable<String>>) list);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		s.shutdown();
		
	}
	
	@Test
	public void lock() {
		ReentrantLock r = new ReentrantLock();
		r.lock();
	}
}
