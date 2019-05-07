package com.superhope.parsesql;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.superhope.TestService;
@Service
public class CsvService {

	public final static String DATATYPE_STRING = "VARCHAR2";
	public final static String DATATYPE_NUMBER = "NUMBER";
	public final static String DATATYPE_DATE = "DATE";
	
	@Autowired
	private JdbcTemplate jd;
	
	private Pattern pattern =Pattern.compile("(?<=\").*?(?=\")");

	public void parseCsvFile(File file, String tableName) {
			//列描述信息
		Map map = getColumnDesc( tableName );
		int cou = 0;
		String s = null;
		try {

			System.out.println("begin----" + file.getName());

			InputStreamReader isr = new InputStreamReader(new FileInputStream(file), "GBK"); // 或GB2312,GB18030
			BufferedReader read = new BufferedReader(isr);
			StringBuilder b = new StringBuilder();
			String header = read.readLine();
			List<String> columnList = parseColumnLine(header);
			//获得sql信息
			String insertSql = getInsertSql(columnList, tableName );
			
			int i = 0;
			//保存解析后的数据
			List<Object[]> valueLineList = new ArrayList<>(1000);
			
			while (true) {
				cou++;
				s = read.readLine();
				if (s == null) {
					int[]  m = jd.batchUpdate(insertSql, new CvsBatchPreparedStatementSetter( valueLineList ));
					valueLineList.clear();
					break;
				}
				Object[] oArray = this.parseValueLine( s, columnList, map );
				valueLineList.add(oArray);
				if( valueLineList.size() % 10000 == 0 ) {
					System.out.println("cou---" + cou);
					int[]  m = jd.batchUpdate(insertSql, new CvsBatchPreparedStatementSetter( valueLineList ));
					valueLineList.clear();
				}
			}

		} catch (Exception e) {
			System.out.print("excpeption cou" + cou);
			throw new RuntimeException("解析错误",e);
		}
		System.out.println("end----" + file.getName());
	}


	
	
	public void parseTest() {
		//获取列名
		List<String> list = getParseColumnLine();
		Map map = getColumnDesc( "PB_PAY_VOUCHER" );
		String valueLine = "\"20170711\",\"1\",\"普通业务\",\"\",\"0\",\"101102592\",\"\",\"\",\"20170710\",\"\",\"0\",\"320000801156313999008000178\",\"财政预算内资金授权支付清算户\",\"建行江苏省分行\",\"3\",\"1\",\"0\",\"0\",\"1\",\"跨行转账\",\"0.00\",\"\",\"对方签收成功\",\"\",\"已核销\",\"3\",\"0.00\",\"0\",\"\",\"\",\"\",\"\",\"\",\"0\",\"0\",\"0\",\"6222084301005833046\",\"李志宏\",\"工行虹桥支行\",\"0\",\"0\",\"0\",\"0\",\"\",\"0\",\"\",\"\",\"2017授00230133号\",\"1\",\"101117040\",\"2017/7/11 17:02:04\",\"0\",\"划款退款申请\",\"2017105320000200004\",\"-1\",\"73816873\",\"1\",\"0\",\"\",\"96576301\",\"\",\"0\",\"\",\"20170711\",\"2017授00230133号\",\"0\",\"1\",\"0\",\"0.00\",\"1\",\"正常支付\",\"73816873\",\"1\",\"101102592\",\"201710532000022040000002\",\"2017\",\"17\",\"7967\",\"-1031.00\",\"1031.00\",\"1\",\"1\",\"2017/7/12 9:06:16\",\"2017/7/11 9:31:45\",\"\",\"2017/7/11 9:13:02\",\"101086223\",\"1499764492906\",\"送审\",\"6222084301005833046\",\"\",\"李志宏\",\"工行虹桥支行\",\"0\",\"\",\"江苏省妇女联合会（机关）\",\"\",\"\",\"1\",\"RJ01-账号不存在\",\"12\",\"授权支付\",\"1686359\",\"1\",\"2017/7/11 17:14:52\",\"2204\",\"32001594040058040002\",\"2\",\"2017授00230133号\",\"103301010833\",\"320000\",\"建行南京丰富路支行\",\"0\",\"\",\"0\",\"1707113200000506830\"\r\n" + 
				"";
		Object[] o = parseValueLine(valueLine,list,map);
		List<Object[]>  valueList = new ArrayList<>();
		valueList.add(o);
//		int i = jd.update(getInsertSql(list,"PB_PAY_VOUCHER"),o);
//		int i = jd.update("insert into rdl_test(i)values(?)",new Object[] {1});
//		i++;
		
	}
	
	private String getInsertSql(List<String> list, String tableName) {
		StringBuilder sb = new StringBuilder("insert into " + tableName + " ( ");
		for( String s : list ) {
			sb.append(s);
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(") values (");
		for( String s : list ) {
			sb.append("?");
			sb.append(",");
		}
		sb.deleteCharAt(sb.length()-1);
		sb.append(")");
		return sb.toString();
		
	}
	
	public Map getColumnDesc(String tableName) {
		String querySql = "select column_name, data_type from user_tab_columns where table_name = ?";
		CvsRowmapper cr = new CvsRowmapper();
		List list =  jd.query(querySql, new Object[]{tableName}, cr );
		return cr.getColumnConfig();
	}
	
	public List<String> getParseColumnLine() {
		String s = "\"VOU_DATE\",\"BUSINESS_TYPE_CODE\",\"BUSINESS_TYPE_NAME\",\"BATCH_NO\",\"BATCHREQ_STATUS\",\"STR_VOUCHER_ID\",\"RETURN_REASON\",\"TRANS_RES_MSG\",\"BATCHREQ_DATE\",\"IS_BATCHREQ\",\"IS_IMPORT\",\"ADVANCE_ACCOUNT_NO\",\"ADVANCE_ACCOUNT_NAME\",\"ADVANCE_ACCOUNT_BANK\",\"VOUCHER_STATUS\",\"VOUCHER_QUERY_FLAG\",\"WRITEOFF_FLAG\",\"IS_ONLYREQ\",\"PB_SET_MODE_CODE\",\"PB_SET_MODE_NAME\",\"PAY_REFUND_AMOUNT\",\"ORI_REFREQ_CODE\",\"VOUCHER_STATUS_DES\",\"VOUCHER_STATUS_ERR\",\"WRITE_OFF_DESCRIBE\",\"WRITE_OFF_STATUS\",\"XPAYAMT\",\"URGENT_FLAG\",\"URGENT_FLAG_NAME\",\"AGENT_ACCOUNT_BANK\",\"PAY_IN_CLEAR_VOUCHER_CODE\",\"AGENT_ACCOUNT_NO\",\"CLEAR_ACCOUNT_NO\",\"PAY_IN_CLEAR_VOUCHER_ID\",\"ELIMINATION_CLEAR_FLAG\",\"MANUAL_TRANS_FLAG\",\"ORI_PAYEE_ACCOUNT_NO\",\"ORI_PAYEE_ACCOUNT_NAME\",\"ORI_PAYEE_ACCOUNT_BANK\",\"IS_SAME_BANK\",\"IS_SELF_COUNTER\",\"FORWARD_FLAG\",\"IMPORT_FLAG\",\"OPERATE_USER_NAME\",\"REPAYMENT_FLAG\",\"ORI_PAYEE_ACCOUNT_BANK_NO\",\"ORI_CHECKNO\",\"ORI_VOUCHER_CODE\",\"CLEAR_FLAG\",\"PAY_CLEAR_VOUCHER_ID\",\"CLEAR_DATE\",\"PAY_ACCOUNT_NOTE_ID\",\"ADD_WORD\",\"PAY_CLEAR_VOUCHER_CODE\",\"FIN_FEEDBACK_STATUS\",\"PAYUSER_CODE\",\"TRANS_SUCC_FLAG\",\"PAY_DAILY_ID\",\"ACESS_AUTH_GROUP_CODE\",\"AUDITUSER_CODE\",\"ACCTHOST_SEQID\",\"IS_ACCEPT\",\"PAY_REFUND_DATE\",\"PAYDATE\",\"ORI_PAYVOUCHER_CODE\",\"IS_AUTO_TRANS\",\"TOTAL_NUM\",\"SUCC_NUM\",\"SUCC_AMOUNT\",\"ORI_PAY_MGR_CODE\",\"ORI_PAY_MGR_NAME\",\"SENDUSER_CODE\",\"IS_CURYEAR_REFUND\",\"PAY_VOUCHER_ID\",\"PAY_VOUCHER_CODE\",\"YEAR\",\"BILL_TYPE_ID\",\"BIZ_TYPE_ID\",\"PAY_AMOUNT\",\"ORI_PAY_AMOUNT\",\"IS_VALID\",\"PRINT_NUM\",\"PRINT_DATE\",\"PAY_DATE\",\"CONFIRM_DATE\",\"CREATE_DATE\",\"ORI_VOUCHER_ID\",\"LAST_VER\",\"AUDIT_REMARK\",\"PAYEE_ACCOUNT_NO\",\"PAYEE_ACCOUNT_CODE\",\"PAYEE_ACCOUNT_NAME\",\"PAYEE_ACCOUNT_BANK\",\"PAY_ACCOUNT_ID\",\"PAY_ACCOUNT_CODE\",\"PAY_ACCOUNT_NAME\",\"AGENT_ACCOUNT_CODE\",\"AGENT_ACCOUNT_NAME\",\"SEND_FLAG\",\"REMARK\",\"PAY_TYPE_CODE\",\"PAY_TYPE_NAME\",\"TASK_ID\",\"BUSINESS_TYPE\",\"SEND_DATE\",\"VT_CODE\",\"PAY_ACCOUNT_NO\",\"REFUND_TYPE\",\"ORI_PAY_VOUCHER_CODE\",\"PAYEE_ACCOUNT_BANK_NO\",\"ADMDIV_CODE\",\"PAY_ACCOUNT_BANK\",\"ACCOUNTFAILURE_FLAG\",\"CHECKNO\",\"VOUCHERFLAG\",\"AGENT_BUSINESS_NO\"";
		List<String> l = parseColumnLine(s);
		return l;
	}
	
	/**
	 * "VOU_DATE","BUSINESS_TYPE_CODE","BUSINESS_TYPE_NAME","BATCH_NO","BATCHREQ_STATUS","STR_VOUCHER_ID","RETURN_REASON","TRANS_RES_MSG","BATCHREQ_DATE","IS_BATCHREQ","IS_IMPORT","ADVANCE_ACCOUNT_NO","ADVANCE_ACCOUNT_NAME","ADVANCE_ACCOUNT_BANK","VOUCHER_STATUS","VOUCHER_QUERY_FLAG","WRITEOFF_FLAG","IS_ONLYREQ","PB_SET_MODE_CODE","PB_SET_MODE_NAME","PAY_REFUND_AMOUNT","ORI_REFREQ_CODE","VOUCHER_STATUS_DES","VOUCHER_STATUS_ERR","WRITE_OFF_DESCRIBE","WRITE_OFF_STATUS","XPAYAMT","URGENT_FLAG","URGENT_FLAG_NAME","AGENT_ACCOUNT_BANK","PAY_IN_CLEAR_VOUCHER_CODE","AGENT_ACCOUNT_NO","CLEAR_ACCOUNT_NO","PAY_IN_CLEAR_VOUCHER_ID","ELIMINATION_CLEAR_FLAG","MANUAL_TRANS_FLAG","ORI_PAYEE_ACCOUNT_NO","ORI_PAYEE_ACCOUNT_NAME","ORI_PAYEE_ACCOUNT_BANK","IS_SAME_BANK","IS_SELF_COUNTER","FORWARD_FLAG","IMPORT_FLAG","OPERATE_USER_NAME","REPAYMENT_FLAG","ORI_PAYEE_ACCOUNT_BANK_NO","ORI_CHECKNO","ORI_VOUCHER_CODE","CLEAR_FLAG","PAY_CLEAR_VOUCHER_ID","CLEAR_DATE","PAY_ACCOUNT_NOTE_ID","ADD_WORD","PAY_CLEAR_VOUCHER_CODE","FIN_FEEDBACK_STATUS","PAYUSER_CODE","TRANS_SUCC_FLAG","PAY_DAILY_ID","ACESS_AUTH_GROUP_CODE","AUDITUSER_CODE","ACCTHOST_SEQID","IS_ACCEPT","PAY_REFUND_DATE","PAYDATE","ORI_PAYVOUCHER_CODE","IS_AUTO_TRANS","TOTAL_NUM","SUCC_NUM","SUCC_AMOUNT","ORI_PAY_MGR_CODE","ORI_PAY_MGR_NAME","SENDUSER_CODE","IS_CURYEAR_REFUND","PAY_VOUCHER_ID","PAY_VOUCHER_CODE","YEAR","BILL_TYPE_ID","BIZ_TYPE_ID","PAY_AMOUNT","ORI_PAY_AMOUNT","IS_VALID","PRINT_NUM","PRINT_DATE","PAY_DATE","CONFIRM_DATE","CREATE_DATE","ORI_VOUCHER_ID","LAST_VER","AUDIT_REMARK","PAYEE_ACCOUNT_NO","PAYEE_ACCOUNT_CODE","PAYEE_ACCOUNT_NAME","PAYEE_ACCOUNT_BANK","PAY_ACCOUNT_ID","PAY_ACCOUNT_CODE","PAY_ACCOUNT_NAME","AGENT_ACCOUNT_CODE","AGENT_ACCOUNT_NAME","SEND_FLAG","REMARK","PAY_TYPE_CODE","PAY_TYPE_NAME","TASK_ID","BUSINESS_TYPE","SEND_DATE","VT_CODE","PAY_ACCOUNT_NO","REFUND_TYPE","ORI_PAY_VOUCHER_CODE","PAYEE_ACCOUNT_BANK_NO","ADMDIV_CODE","PAY_ACCOUNT_BANK","ACCOUNTFAILURE_FLAG","CHECKNO","VOUCHERFLAG","AGENT_BUSINESS_NO"
	 * @param s
	 */
	public List<String> parseColumnLine(String s ) {
		String[] arr = s.split(",");
		List<String> list = new ArrayList<String>();
		for( int i = 0; i < arr.length; i++ ) {
			list.add(arr[i].replaceAll("\"", ""));
		}
		return list;
	}

	public Object[] parseValueLline( String value ) {
	
		List<String> slist=new ArrayList<String>();		
		
		return slist.toArray();
	}
	
	/**
	 * 根据解析出来的行及参数赋值
	 * @param s
	 * @param columnList
	 * @param colType
	 * @return
	 */
	public static Object[] parseValueLine(String s,List<String> columnList,Map<String,String> colType ) {
		//存储解析出来的值
		Object[] objValueArray  = new Object[columnList.size()];
		s =s.replace("\",\"","###");
		String[] arr = s.split("###");
		List<String> list = new ArrayList<String>();
		String  value = "";
		for( int i = 0; i < columnList.size(); i++ ) {
			
			value = arr[i].replaceAll("\"", "");
			if( StringUtils.isEmpty(value) ){
				objValueArray[i] = null;
				continue;
			}
			//如果是字符串
			if( DATATYPE_STRING.equals(colType.get( columnList.get(i) )) ) {
				objValueArray[i] = value;
			}else if( DATATYPE_NUMBER.equals( colType.get( columnList.get(i) ) ) )  {//数字
				try {
					objValueArray[i] = new BigDecimal(value);
				}catch( Exception e ) {
					e.getMessage();
				}
				
				
			}else if( DATATYPE_DATE.equals(colType.get( columnList.get(i) )) ) {
				SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
				Date date = null;
				try {
					date = sdf.parse(value);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				objValueArray[i] = new Timestamp(date.getTime());
				
			}else {
				throw new RuntimeException("没有对应的字段");
			}
			
		}
		return objValueArray;
	}
}

class CvsRowmapper implements RowMapper{
	Map map = new HashMap();
	
	@Override
	public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
		String column = rs.getString(1);
		String dataType = rs.getString(2);
		map.put(column, dataType);
		return null;
	};
	
	public Map getColumnConfig() {
		return map;
	}
}


