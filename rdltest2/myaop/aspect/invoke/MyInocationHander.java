package aspect.invoke;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class MyInocationHander<T> implements InvocationHandler{
	
	T target;
	MyInocationHander( T target ){
		this.target = target;
	}

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("------开始执行" +method.getName() + "参数是" + args[0].toString());
		Object o = method.invoke(target, args);
		System.out.println("------返回结果" +method.getName() + o );
		return o;
	}

	public static void main( String args[] ) {
		InovkeServce is = new InovkeServiceImpl();
		InovkeServce proxy = (InovkeServce)Proxy.newProxyInstance(MyInocationHander.class.getClassLoader(), 
				new Class[] {InovkeServce.class} , new MyInocationHander( is));
		proxy.print("aaa");
		proxy.encode("test");
		
	}
	
}
