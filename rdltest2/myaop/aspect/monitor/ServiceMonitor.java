/*
 * Copyright 2012-2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package aspect.monitor;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceMonitor {


	
	@Before("execution(* aspect..*Service.*(..))")
	public void before(JoinPoint joinPoint){
		System.out.println("@before: " + joinPoint.getSignature() );
	}
	
    @AfterReturning(value = "execution(* aspect..*Service.*(..))",returning = "result")
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object result){
    	System.out.println("@AfterReturning1："+result );
    }
    
    @Around("execution(* aspect..*Service.*(..))") 
    public Object aroundLogCalls(ProceedingJoinPoint jp) throws Throwable {  
    	System.out.println("@around" + jp.toString() );
        return jp.proceed();  
    }

 
    @AfterReturning(value = "execution(* aspect..*Service.*(..))",returning = "result",argNames = "result")
    public void doAfterReturningAdvice2(String result){
    	System.out.println("@AfterReturning2第二个后置返回通知的返回值："+result);
    }
 
    @After(value = "execution(* aspect..*Service.*(..))")
    public void doAfterAdvice(JoinPoint joinPoint){
    	System.out.println("@After后置通知执行了!");
    }
    
    
    @AfterThrowing(value = "execution(* aspect..*Service.*(..))",throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
    	System.out.println("@AfterThrowing:" +exception.getClass() );
   
    }
    
    @Pointcut("@annotation(aspect.service.AnnotationTag)")
    public void myPointcut() {
    }
    
    
    @Around("myPointcut()") 
    @Order(1)
    public Object aroundTag(ProceedingJoinPoint jp) throws Throwable {  
     	System.out.println("@around anno begin===========================");
    	Signature sig = jp.getSignature();
    	MethodSignature msig = null;
    	if (!(sig instanceof MethodSignature)) {
    		throw new IllegalArgumentException("该注解只能用于方法");
    	}
    	msig = (MethodSignature) sig;
    	Object target = jp.getTarget();
    	Method currentMethod = target.getClass().getMethod(msig.getName(), msig.getParameterTypes());
    	
        Object o =  jp.proceed();
    	System.out.println("@around anno end====================================");
        return o;
    }


}
