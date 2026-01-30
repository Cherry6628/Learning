package classes;
import java.lang.reflect.Field;


class Sample{
	public final Integer x = 10;
	private final Integer y = 10;
	
	public final int y() {return y;}
	
}

public class ReflectionSample {
	public static void main(String[]args) throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
//		Sample.x=30;
//		Sample.y=30;
		Sample s = new Sample();
		Field x = Sample.class.getField("x");
		System.out.println(x.get(s));
		x.setAccessible(true);
		x.set(s, 15);
		System.out.println(s.x);
		
		
		Field y = Sample.class.getDeclaredField("y");
		y.setAccessible(true);
		System.out.println(y.get(s));
		y.set(s, 15);
		System.out.println(s.y());
	}
}
