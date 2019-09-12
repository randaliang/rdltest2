/**
 * Created on 2018/8/11.
 */
package jetcache.samples.springboot;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author <a href="mailto:areyouok@gmail.com">huangli</a>
 */
@Data
@Accessors(chain=true)
public class User implements Serializable {


	private static final long serialVersionUID = -5157877924507849953L;
    private long userId;
    private String userName;
    
    @DateTimeFormat(pattern="yyyy-MM-dd")    
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date date = new Date();
    

	private Date createDate = new Date();
    
}
