package objectGenerator;

import java.util.Random;

public class LatGenerator extends ColumnObject implements ObjectGenerator{
	public static final double Y = 53.5333;
	private static double X = 113.5000;
    private static int RADIUS = 700000;
	
	

	public Object gen() {
        Random random = new Random();
        
        // Convert radius from meters to degrees
        double radiusInDegrees = RADIUS / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double y = w * Math.sin(t);

        double foundLatitude = y + Y;
        return new Float(foundLatitude);
    }
}
