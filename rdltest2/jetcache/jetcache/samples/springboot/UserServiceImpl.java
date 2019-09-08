/**
 * Created on 2018/8/11.
 */
package jetcache.samples.springboot;

import org.springframework.stereotype.Repository;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
@Repository
public class UserServiceImpl implements UserService {

    @Override
    public User getUserById(long userId) {
    	try {
			Thread.sleep(10000L);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        System.out.println("load user: " + userId);
        User user = new User();
        user.setUserId(userId);
        user.setUserName("user" + userId);
        return user;
    }

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUser(long userId) {
		// TODO Auto-generated method stub
		
	}
}
