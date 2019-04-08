package automic;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public class LockInt {

	private int i;
	ReentrantLock lock = new ReentrantLock();
	
	
	public int getI() {
		return i;
	}


	public void setI(int i) {
		
		try {
			lock.lockInterruptibly();
			this.i = i;
			System.out.println("----------" + i);
			Thread.currentThread().sleep(5000L);
		
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			lock.unlock();
		}
		
	}


	public static void main( String args[] ) {
		final LockInt l = new LockInt();
		Thread t = new Thread() {
			public void run() {
				l.setI(1);
			}
		};
		
		t.start();
		try {
			Thread.currentThread().sleep(1000L);
			t.interrupt();
			l.setI(2);
		}catch( Exception e ) {
			System.out.println(e.getMessage());
		}
		
		
	}
	
	int m ;
	public void setT( int t) {
		if(m==0) {
			m = t;
		}
	}
	
}
