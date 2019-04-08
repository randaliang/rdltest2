package threadpool;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Condtion1 {

	ReentrantLock lock = new ReentrantLock();
	LinkedBlockingDeque<Long> q = new LinkedBlockingDeque<Long>();
	
	public Condtion1() {
		lock.lock();
		lock.lock();
		Condition c = lock.newCondition();
		
	}
	
	
}
