package aspect.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import aspect.common.exception.AccountException;
import aspect.common.model.Code;
import aspect.service.IHelloWorldService;

@RestController
public class LogController {

  private final static Logger LOG = LoggerFactory.getLogger(LogController.class);
  
	@Value("${com.example.demo.name:World}")
	private String name;
  
	 
  @Autowired
  IHelloWorldService hello;


  @GetMapping(value = "/hello")
  public String shutdown() {
	hello.getHelloMessage();
    return "shutdown";
  }

  @GetMapping(value = "/msg")
  public String getMessage() {
    return name;
  }
  
  
  @GetMapping(value = "/config")
  public String getConfigMessage() {
    return hello.getHelloMessage("111");
  }

  @GetMapping(value = "/exc")
  public String getExceptionMessage() {
	  if(true) {
		  throw new AccountException("myexec",Code.FAILED);
	  }
   
	  return "getException";
  }
  
  
  @GetMapping(value = "/divide")
  public int getDivideMessage() {
	  return 1/0;
  }
}
