package com.superhope.parsesql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;

public class CvsBatchPreparedStatementSetter implements BatchPreparedStatementSetter {

	List<Object[]> list = null;
	
	public CvsBatchPreparedStatementSetter( List<Object[]> list ) {
		this.list = list;
	}
	
	@Override
	public void setValues(PreparedStatement ps, int t) throws SQLException {
		Object objArray[] = list.get(t);
		for( int i= 0; i< objArray.length;i++ ) {
			ps.setObject(i+1,objArray[i] );
		}
	}

	@Override
	public int getBatchSize() {
		return list.size();
	}

}
