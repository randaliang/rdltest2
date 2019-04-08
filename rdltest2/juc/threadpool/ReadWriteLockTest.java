package threadpool;

import java.io.Reader;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import java.util.Random;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReadWriteLockTest {

	public static void main(String[] args) {
		final ReadWriteObject object = new ReadWriteObject();
		for (int i = 0; i < 5; i++) {
			new Thread() {
				public void run() {
					while (true) {
						object.read();
					}
				};
			}.start();
			for (int j = 0; j < 5; j++) {
				new Thread() {
					public void run() {
						while (true) {
							object.write(new Random().nextInt(10000));
						}
					};
				}.start();
			}
		}

	}

}

class ReadWriteObject {
	// 需要有一个可以读写的数据对象,就用Object
	Object data = null;// 共享数据，只能有一个线程能写该数据，但可以有多个线程同时读该数据。
	private ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();

	// 读取数据接口
	public void read() {
		
		rwl.readLock().lock();// 上读锁，其他线程只能读不能写
		System.out.println(Thread.currentThread().getName() + "准备开始读取数据!");
		try {
			// 模拟读取
			Thread.sleep((long) (Math.random()));
			Thread.sleep((long) (100));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(Thread.currentThread().getName() + "读取完毕，data :" + data);
		rwl.readLock().unlock(); // 释放读锁，最好放在finnaly里面
	}

	public void write(Object data) {
		rwl.writeLock().lock();// 上写锁，不允许其他线程读也不允许写
		System.out.println(Thread.currentThread().getName() + " 准备写数据!");
		// 模拟写数据
		try {
			Thread.sleep((long) (10));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		this.data = data;
		System.out.println(Thread.currentThread().getName() + " have write data: " + data);
		rwl.writeLock().unlock();// 释放写锁
		try {
			Thread.sleep((long) (1000));
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
