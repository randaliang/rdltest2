package generic;

import java.util.ArrayList;
import java.util.List;

public class Season {

	public static void main(String args[]) {
		List<? extends Season> list1 = new ArrayList<>();
		// list1.add(new Spring());//这里编译不通过，因为编译器无法确定list所持有的类型。
		List<? extends Season> list2 = new ArrayList<Spring>();
//		list2.add(new Spring());//也是无法通过编译
		//通过上文，我们知道 ？extends Season表示可以接收的类型为 Seaon 或者其子类。
		//但是此处不行，因为可能传入进来的是spring,或者summer,编译器无法确定具体传递进来的是什么，
		//所以无法安全的向其中添加对象，但是它可以接收子类类型 的赋值。如下：
		//
		List<Spring> list3 = new ArrayList<Spring>();
		List<? extends Season> list4 = list3;
		// 这里和上面的list2做对比，无法直接add spring类型的对象
		// 但是可以直接将spring类型的list赋值。

		List<Season> seasons = new ArrayList<Season>();
		List<? super Spring> spring = seasons;
		spring.add(new Spring());// ok
		//spring.add(new Summer());//error
		//spring.add(new Season());//error
		//spring.add(new Object());//error
	}
}

class Spring extends Season {
	// ......
}

class Summer extends Season {
	// .......
}