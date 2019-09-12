package jetcache.samples.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "exception")
public class ExceptionHandlerController {

	@ExceptionHandler({ ArithmeticException.class })
	public String handleArithmeticException(Exception e) {
		e.printStackTrace();
		return "abc";
	}

	@RequestMapping(value = "e/{id}", method = { RequestMethod.GET })
	public String testExceptionHandle(@PathVariable(value = "id") Integer id) {
		System.out.println(10 / id);
		return id.toString();
	}
}