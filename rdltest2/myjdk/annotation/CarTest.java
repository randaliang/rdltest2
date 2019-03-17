package annotation;

import java.lang.reflect.Method;

public class CarTest {
	@Car(color="red",shape="333")
	@Car(color="blue",shape="333")
	public String getCars() {
		return "122";
	}



	@CarShow( value= { @Car(color="red",shape="333"), 	@Car(color="blue",shape="333")} )
	public String getCars1() {
		return "122";
	}


	public static void main( String args[] ) {
		Method m;
		try {
			m = CarTest.class.getMethod("getCars", null);
			Car[] c = m.getAnnotationsByType(Car.class);
			for( Car car : c ) {
				System.out.println( car.color() + car.shape());
			}
			System.out.println( m.isAnnotationPresent(CarShow.class) );
			System.out.println( m.isAnnotationPresent(Car.class) );
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ;
	}
}
