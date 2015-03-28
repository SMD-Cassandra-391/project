package objectGenerator;

import java.util.Random;

public class LongitudeGenerator extends ColumnObject implements ObjectGenerator {
	public static final double Y = 53.333;
	private static double X = 113.5000;
	private static double Min_X = 100.000;
    private static int RADIUS = 700000;
	
	@Override
	public Object gen() {
		
		return new Float(rndm.nextFloat() * (X - Min_X) + Min_X);
	}

}
