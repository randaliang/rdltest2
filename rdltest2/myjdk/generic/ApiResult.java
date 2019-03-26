package generic;

import lombok.Data;

@Data
public class ApiResult<T> {

	int resultCode;
	String resultMsg;
	T resultObject;
	
	public static void main( String ... strings  ) {
		ApiResult<String> ar = new ApiResult<String>();
		ar.setResultObject("randaliang");
		System.out.println( ar.getResultObject() );
//		List<Integer>[] arrayOfLists = new List<Integer>[2];  // compile-time error
		Object[] strings1 = new String[2];
//		strings1[0] = "hi";   // OKstrings[1] = 100;    // An ArrayStoreException is thrown.
	}

}