package automic;

public class PointerTest {

	public static void main( String args[] ) {
		String t = "a";
		String tail = "tail";
		
		System.out.println(t != (t = tail));
		System.out.println(t);
				
	}
	
}
