package aspect.log;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.SourceLocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
 
import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
 
@Component
@Aspect
public class LogAspect {
 
    private static final Logger logger = LoggerFactory.getLogger(LogAspect.class);
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
  private final String POINT_CUT = "execution(public *  aspect.service.*.*(..))";
    
//    private final String POINT_CUT = "execution(public *  aspect.service.*.*(..))";
//    private final String POINT_CUT = "execution(public * *(..))";
   
    @Pointcut(POINT_CUT)
    public void pointCut(){}
 
   @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint){
       System.out.println("@Before通知执行:");
        //获取目标方法参数信息
        Object[] args = joinPoint.getArgs();
        Arrays.stream(args).forEach(arg->{  // 大大
            try {
               System.out.println(OBJECT_MAPPER.writeValueAsString(arg));
            } catch (JsonProcessingException e) {
               System.out.println("JsonProcessingException"+arg.toString());
            }
        });
 
 
        //aop代理对象
        Object aThis = joinPoint.getThis();
       System.out.println(aThis.toString()); //com.xhx.springboot.controller.HelloController@69fbbcdd
 
        //被代理对象
        Object target = joinPoint.getTarget();
       System.out.println(target.toString()); //com.xhx.springboot.controller.HelloController@69fbbcdd
 
        //获取连接点的方法签名对象
        Signature signature = joinPoint.getSignature();
       System.out.println(signature.toLongString()); //public java.lang.String com.xhx.springboot.controller.HelloController.getName(java.lang.String)
       System.out.println(signature.toShortString()); //HelloController.getName(..)
       System.out.println(signature.toString()); //String com.xhx.springboot.controller.HelloController.getName(String)
        //获取方法名
       System.out.println(signature.getName()); //getName
        //获取声明类型名
       System.out.println(signature.getDeclaringTypeName()); //com.xhx.springboot.controller.HelloController
        //获取声明类型  方法所在类的class对象
       System.out.println(signature.getDeclaringType().toString()); //class com.xhx.springboot.controller.HelloController
        //和getDeclaringTypeName()一样
       System.out.println(signature.getDeclaringType().getName());//com.xhx.springboot.controller.HelloController
 
        //连接点类型
        String kind = joinPoint.getKind();
       System.out.println(kind);//method-execution
 
        //返回连接点方法所在类文件中的位置  打印报异常
        SourceLocation sourceLocation = joinPoint.getSourceLocation();
       System.out.println(sourceLocation.toString());
        //logger.info(sourceLocation.getFileName());
        //logger.info(sourceLocation.getLine()+"");
        //logger.info(sourceLocation.getWithinType().toString()); //class com.xhx.springboot.controller.HelloController
 
        ///返回连接点静态部分
        JoinPoint.StaticPart staticPart = joinPoint.getStaticPart();
       System.out.println(staticPart.toLongString());  //execution(public java.lang.String com.xhx.springboot.controller.HelloController.getName(java.lang.String))
 
 
        //attributes可以获取request信息 session信息等
//        ServletRequestAttributes attributes =
//                (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
//        HttpServletRequest request = attributes.getRequest();
//       System.out.println(request.getRequestURL().toString()); //http://127.0.0.1:8080/hello/getName
//       System.out.println(request.getRemoteAddr()); //127.0.0.1
//       System.out.println(request.getMethod()); //GET
 
      System.out.println("before通知执行结束");
    }
 
 
    /**
     * 后置返回
     *      如果第一个参数为JoinPoint，则第二个参数为返回值的信息
     *      如果第一个参数不为JoinPoint，则第一个参数为returning中对应的参数
     * returning：限定了只有目标方法返回值与通知方法参数类型匹配时才能执行后置返回通知，否则不执行，
     *            参数为Object类型将匹配任何目标返回值
     */
    @AfterReturning(value = POINT_CUT,returning = "result")
    public void doAfterReturningAdvice1(JoinPoint joinPoint,Object result){
       System.out.println("第一个后置返回通知的返回值："+result);
    }
 
    @AfterReturning(value = POINT_CUT,returning = "result",argNames = "result")
    public void doAfterReturningAdvice2(String result){
       System.out.println("第二个后置返回通知的返回值："+result);
    }
    //第一个后置返回通知的返回值：姓名是大大
    //第二个后置返回通知的返回值：姓名是大大
    //第一个后置返回通知的返回值：{name=小小, id=1}
 
 
    /**
     * 后置异常通知
     *  定义一个名字，该名字用于匹配通知实现方法的一个参数名，当目标方法抛出异常返回后，将把目标方法抛出的异常传给通知方法；
     *  throwing:限定了只有目标方法抛出的异常与通知方法相应参数异常类型时才能执行后置异常通知，否则不执行，
     *            对于throwing对应的通知方法参数为Throwable类型将匹配任何异常。
     * @param joinPoint
     * @param exception
     */
    @AfterThrowing(value = POINT_CUT,throwing = "exception")
    public void doAfterThrowingAdvice(JoinPoint joinPoint,Throwable exception){
       System.out.println(joinPoint.getSignature().getName());
        if(exception instanceof NullPointerException){
           System.out.println("发生了空指针异常!!!!!");
        }
    }
 
    @After(value = POINT_CUT)
    public void doAfterAdvice(JoinPoint joinPoint){
       System.out.println("后置通知执行了!");
    }
 
    /**
     * 环绕通知：
     *   注意:Spring AOP的环绕通知会影响到AfterThrowing通知的运行,不要同时使用
     *
     *   环绕通知非常强大，可以决定目标方法是否执行，什么时候执行，执行时是否需要替换方法参数，执行完毕是否需要替换返回值。
     *   环绕通知第一个参数必须是org.aspectj.lang.ProceedingJoinPoint类型
     */
    @Around(value = POINT_CUT)
    @Order(2)
    public Object doAroundAdvice(ProceedingJoinPoint proceedingJoinPoint){
       System.out.println("@Around环绕通知=====================================："+proceedingJoinPoint.getSignature().toString());
        Object obj = null;
        try {
            obj = proceedingJoinPoint.proceed(); //可以加参数
           System.out.println(obj.toString());
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
       System.out.println("@Around环绕通知执行结束++++++++++++++++++++++++++++++++++++");
        return obj;
    }
    

}
