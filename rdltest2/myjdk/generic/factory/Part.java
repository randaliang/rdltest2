package generic.factory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Part {
	static List<Factory<? extends Part>> partFactories = new ArrayList<Factory<? extends Part>>();
	static {
		partFactories.add(new FuelFilter.Factory1());
		partFactories.add(new AirFilter.Factory1());
		partFactories.add(new FanBelt.Factory1());
//        partFactories.add(new PowerSteeringBelt.Factory1());
	}
	private static Random rand = new Random(47);

	public static Part createRandom() {
		int n = rand.nextInt(partFactories.size());
		return partFactories.get(n).create();
	}

	public String toString() {
		return getClass().getSimpleName();
	}
}