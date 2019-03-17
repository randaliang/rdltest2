package generic;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ScheduledFuture;
import java.util.function.Function;

public class Compare {
	
	//<T extends  Comparable<? super T>> T
	public static <T extends  Comparable<? super T>> T max(Collection<? extends T> coll) {
		Iterator<? extends T> i = coll.iterator();
		T candidate = i.next();

		while (i.hasNext()) {
			T next = i.next();
			if (next.compareTo(candidate) > 0)
				candidate = next;
		}
		return candidate;
	}
	
	
    public static <T, U extends Comparable<? super U>> Comparator<T> comparing(
            Function<? super T, ? extends U> keyExtractor)
    {
        Objects.requireNonNull(keyExtractor);
        return (Comparator<T> & Serializable)
            (c1, c2) -> keyExtractor.apply(c1).compareTo(keyExtractor.apply(c2));
    }

	
    
	
	//<T extends  Comparable<? super T>> T
	public static <T extends  Comparable<T>> T max0(Collection<? extends T> coll) {
		Iterator<? extends T> i = coll.iterator();
		T candidate = i.next();

		while (i.hasNext()) {
			T next = i.next();
			if (next.compareTo(candidate) > 0)
				candidate = next;
		}
		return candidate;
	}
	
    public static <T extends Object & Comparable<? super T>> T max2(Collection<? extends T> coll) {
        Iterator<? extends T> i = coll.iterator();
        T candidate = i.next();

        while (i.hasNext()) {
            T next = i.next();
            if (next.compareTo(candidate) > 0)
                candidate = next;
        }
        return candidate;
    }

    


	public static void main( String args[] ) {
		List<BigDecimal> list = new ArrayList<>();
		Comparable b  = max(list);
		List<ScheduledFuture<?>> l = null;
		ScheduledFuture s = max0(l);
	}
	
}
