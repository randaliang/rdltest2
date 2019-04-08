package automic;

import java.lang.ref.PhantomReference;
import java.lang.ref.ReferenceQueue;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;

public class Reference {

	public static void main( String args[] ) {
		SoftReference<Object> soft = new SoftReference<>(new Object());
		WeakReference<Object> weak = new WeakReference<>(new Object());
		WeakReference<String> weakString = new WeakReference<>("abc");
		WeakReference<String> weakString2 = new WeakReference<>(new String("abc"));
		PhantomReference<Object> phantom = new PhantomReference<>(new Object(), new ReferenceQueue<>());
		System.gc();
		System.out.println(soft.get());
		System.out.println(weak.get());
		System.out.println(weakString.get());
		System.out.println(weakString2.get());
		System.out.println(phantom.get());
	}
}
