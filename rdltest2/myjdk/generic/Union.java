package generic;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Union {

	public static <E> Set<E> union(Set< ? extends E> s1, Set<? extends E> s2) {
		Set<E> result = new HashSet<>(s1);
		return result;
	}

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

	public static void main(String args[]) {
		Set<Integer> i = new HashSet<>();
		Set<Double> d = new HashSet<>();
		Set<Number> n = Union.<Number>union( i,d );		
		n.toArray();
	}

}
