package juc.unsafe;

import java.util.concurrent.atomic.AtomicInteger;

import sun.misc.Unsafe;

public class UnsafeAnlysis {

	private volatile  int value;
	private static final Unsafe unsafe = Unsafe.getUnsafe();
	private static final long valueOffset;

	    static {
	        try {
	            valueOffset = unsafe.objectFieldOffset
	                (AtomicInteger.class.getDeclaredField("value"));
	        } catch (Exception ex) { throw new Error(ex); }
	    }

	    
}
