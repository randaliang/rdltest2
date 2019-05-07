import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class SortTest {
	
	public static void main(String ... a ) {
		List<Integer> list = new LinkedList<Integer>();
		Random r = new Random();
		for( int i = 0; i < 100000; i++ ) {
			list.add(r.nextInt());
		}
		
		Collections.sort(list);
		for( int i = 0; i <list.size(); i++ ) {
			System.out.println( list.get(i) );
		}
	}

}
