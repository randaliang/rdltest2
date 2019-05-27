package aspect.controller;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import aspect.service.IHelloWorldService;

@RestController
public class LogController {

  private final static Logger LOG = LoggerFactory.getLogger(LogController.class);

  @Autowired
  IHelloWorldService hello;


  @GetMapping(value = "/hello")
  public String shutdown() {
	hello.getHelloMessage();
    return "shutdown";
  }

  @GetMapping(value = "/msg")
  public String getMessage() {
//	hello.getHelloMessage();
    return "msg";
  }

}
