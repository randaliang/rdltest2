package com.superhope.log;

import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LogController {

  private final static Logger LOG = LoggerFactory.getLogger(LogController.class);

  @GetMapping("/log")
  public Map<String, Object> home() {
    if(LOG.isTraceEnabled()){
      LOG.trace("trace level log");
    }

    if(LOG.isDebugEnabled()){
      LOG.debug("debug level log");
    }

    if(LOG.isInfoEnabled()){
      LOG.info("info level log");
    }

    if(LOG.isWarnEnabled()){
      LOG.warn("warn level log");
    }

    if(LOG.isErrorEnabled()){
      LOG.error("error level log");
    }

    Map<String, Object> result = new HashMap<>();
    result.put("status", "good");
    result.put("name", "abc");
    result.put("password", "abc");
    return result;
  }

  @GetMapping(value = "/admin")
  public String shutdown() {
    return "shutdown";
  }

}
