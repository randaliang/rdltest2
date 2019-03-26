package reg;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegTest {

	public static void main( String args[]  ) {
		test("11、中国 22、美国  11、北京 111、河北");
	}
	
	public static String test( String s ) {
		Set set = new HashSet();
		Pattern p = Pattern.compile("\\d+、");
		Matcher matcher = p.matcher(s);
		while (matcher.find()) {
		    if( set.contains( matcher.group() ) ) {
		    	continue;
		    }
		    set.add(matcher.group());
		    s = s.replace(matcher.group(), "#" + matcher.group() );
		}
		return s;
	}
}
