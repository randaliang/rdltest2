package jetcache.samples.springboot;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping(value = "status")
public class ResponseStatusController {
 
@RequestMapping(value = "e/{id}", method = { RequestMethod.GET })
@ResponseBody
//@ResponseStatus(value=HttpStatus.BAD_GATEWAY)
public String status(@PathVariable(value = "id") Integer id){
if(id % 2 != 0){
throw new HttpStatusException();
}
return id.toString();
}
}
 