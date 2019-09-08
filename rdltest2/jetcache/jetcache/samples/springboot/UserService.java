/**
 * Created on 2018/8/11.
 */
package jetcache.samples.springboot;

import java.util.concurrent.TimeUnit;

import com.alicp.jetcache.anno.CacheInvalidate;
import com.alicp.jetcache.anno.CacheRefresh;
import com.alicp.jetcache.anno.CacheUpdate;
import com.alicp.jetcache.anno.Cached;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
public interface UserService {

    
    @Cached(name="userCache-", key="#userId", expire = 3600)
    @CacheRefresh(timeUnit = TimeUnit.SECONDS, refresh = 5, stopRefreshAfterLastAccess = 100)
    User getUserById(long userId);

    @CacheUpdate(name="userCache-", key="#user.userId", value="#user")
    void updateUser(User user);

    @CacheInvalidate(name="userCache-", key="#userId")
    void deleteUser(long userId);
    
    
}
