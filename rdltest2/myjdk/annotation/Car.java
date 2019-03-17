package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.springframework.context.annotation.ComponentScan;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)

@Repeatable( CarShow.class ) 
public @interface Car {
	public String color();
	public String shape();
	
}
