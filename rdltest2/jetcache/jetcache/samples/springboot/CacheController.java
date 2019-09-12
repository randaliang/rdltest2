package jetcache.samples.springboot;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

	@Autowired
	MyService myService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping( value="getuser" )
	public User getUserInfo(@RequestParam(value="id",required=true) String id ) {
		return userService.getUserById( Long.parseLong(id) );
	}
	
	@RequestMapping( value="getuserbody" )
	@ResponseBody
	public User getUserResponse( String id ) {
		return userService.getUserById( Long.parseLong(id) );
	}
	
	
	
	@RequestMapping( value="getuserlist" )
	public List<User> getUserResponse(  ) {
		List<User> list = new ArrayList<>();
		list.add(userService.getUserById(1));
		list.add(userService.getUserById(2));
		return list;
		
	}
	
	
	
	
	@RequestMapping( value="deleteuser" )
	public void deleteUserInfo( String id ) {
		userService.deleteUser( Long.parseLong(id) );
	}
	
}
