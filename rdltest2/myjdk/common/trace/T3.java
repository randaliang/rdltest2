package common.trace;

public class T3 {
	public static void method() {
		StackTraceElement[] stackTrace = new RuntimeException().getStackTrace();
		for (StackTraceElement e : stackTrace) {
			System.out.println(  e.getFileName() + ":"+ e.getClassName() + ":" 
					+ e.getMethodName() +":" +e.getLineNumber() );
		}
	}
}
