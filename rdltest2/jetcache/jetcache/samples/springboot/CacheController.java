package jetcache.samples.springboot;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CacheController {

	@Autowired
	MyService myService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping( value="getuser" )
	public User getUserInfo( String id ) {
		return userService.getUserById( Long.parseLong(id) );
	}
	
	@RequestMapping( value="getuser" )
	@ResponseBody
	public User getUserResponse( String id ) {
		return userService.getUserById( Long.parseLong(id) );
	}
	
	
	
	@RequestMapping( value="deleteuser" )
	public void deleteUserInfo( String id ) {
		userService.deleteUser( Long.parseLong(id) );
	}
	
}
