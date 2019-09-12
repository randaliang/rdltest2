package jetcache.samples.springboot;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CategoryController {
  
    @RequestMapping("/home")
    public String listCategory(){

        return "home";
    }

}
