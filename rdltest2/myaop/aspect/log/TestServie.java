package aspect.log;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service 
@Transactional
public class TestServie {

//	@Autowireds
	private JdbcTemplate jt;
	
	public void insertA(){
		jt.execute("insert into a(m,n)values(1,2)");
		insertB();
	}
	
	public void insertB(){
		jt.execute("insert into b(h,i)values(1,2)");
	}
	
	public static void main(){
		for( int i = 0; i <100000; i++ ){
			System.out.println("hello world!");
			System.out.println("hello world!");
		}
	}
	
}
