package generic.factory;

public class Filter extends Part {
	
}
class FuelFilter extends Filter {
    public static class Factory1 implements Factory<FuelFilter> {
        public FuelFilter create() {
            return new FuelFilter();
        }
    }
}

class AirFilter extends Filter {
    public static class Factory1 implements Factory<AirFilter> {
        public AirFilter create() {
            return new AirFilter();
        }
    }
}

class Belt extends Part {}
class FanBelt extends Belt {
    public static class Factory1 implements Factory<FanBelt> {
        public FanBelt create() {
            return new FanBelt();
        }
    }
}

class GeneratorBelt extends Belt {
    public static class Factory1 implements Factory<GeneratorBelt> {
        public GeneratorBelt create() {
            return new GeneratorBelt();
        }
    }
}