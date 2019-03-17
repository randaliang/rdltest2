package generic.twogeneric;

import java.util.ArrayList;
import java.util.List;

public class TwoImpl<T extends List,R extends List>  implements TwoGeneric<T,R> {

	@Override
	public R getResult(T t) {
		ArrayList l = new ArrayList();
		return  (R) l;
	}




	
}
