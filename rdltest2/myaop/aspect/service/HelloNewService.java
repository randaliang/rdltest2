package aspect.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class HelloNewService {
	@Autowired
	JdbcTemplate jd;
	public String getHelloMessage() {
		
		List list = jd.queryForList("	SELECT * FROM role ");
		return list.toString();
	}
}
