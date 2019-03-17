package myenum;

public class AppleTest {

	public static void main( String args[] ) {
		Apple app[] = Apple.values();
		for( Apple a : app ) {
			System.out.println(a +"==" +a.getMoney() +"==" +a.toString());
		}
		
	}
	
}
