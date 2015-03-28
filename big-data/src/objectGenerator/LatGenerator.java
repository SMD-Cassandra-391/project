package objectGenerator;

import java.util.Random;

public class LatGenerator extends ColumnObject implements ObjectGenerator{
	public static final double Y = 70.000;
	public static final double Min_Y = 50.000;
	private static double X = 113.5000;
    private static int RADIUS = 700000;
	
	

	public Object gen() {
        
		return new Float(rndm.nextFloat() * (Y - Min_Y) + Min_Y);
    }
}
