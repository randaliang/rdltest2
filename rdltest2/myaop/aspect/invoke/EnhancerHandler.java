package aspect.invoke;

import java.lang.reflect.Method;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

public class EnhancerHandler implements MethodInterceptor {

	//要代理的真实对象
	private Object obj;
	
	public Object createProxy(Object target) {
		this.obj = target;
		Enhancer enhancer = new Enhancer();
		//设置代理目标
		enhancer.setSuperclass(this.obj.getClass());
		//设置单一回调对象，在调用中拦截对目标方法的调用
		enhancer.setCallback(this);
		//设置类加载器
		enhancer.setClassLoader(this.obj.getClass().getClassLoader());
		return enhancer.create();
	}
	/**
	 * 
	 * 方法描述 当对基于代理的方法回调时，在调用原方法之前会调用该方法
	 * 拦截对目标方法的调用
	 *
	 * @param obj 代理对象
	 * @param method 拦截的方法
	 * @param args 拦截的方法的参数
	 * @param proxy 代理
	 * @return
	 * @throws Throwable
	 */
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		Object result = null;
		try {
			//前置通知
			
			System.out.println("------开始执行" +method.getName() + "参数是" + args[0].toString());
			result = proxy.invokeSuper(obj, args);
			System.out.println("------返回结果" +method.getName() + result );
			//后置通知
		} catch (Exception e) {
			//异常通知
		} finally {
			//方法返回前通知
		}
		
		return result;
	}
	
	public static void main(String[] args) throws Exception {
		InovkeServiceImpl hello = new InovkeServiceImpl();
		EnhancerHandler proxy = new EnhancerHandler();
		InovkeServiceImpl world = (InovkeServiceImpl)proxy.createProxy(hello);
		world.print("abc");
		world.encode("ttt");
	}


}
