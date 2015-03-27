package objectGenerator;

import java.util.Random;

public class LongitudeGenerator extends ColumnObject implements ObjectGenerator {
	public static final double Y = 53.5333;
	private static double X = 113.5000;
    private static int RADIUS = 700000;
	
	@Override
	public Object gen() {
		Random random = new Random();
        
        // Convert radius from meters to degrees
        double radiusInDegrees = RADIUS / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(Y);

        double foundLongitude = new_x + X;
        
        
        
        return new Float(foundLongitude);
	}

}
