package textGenerator;
import java.util.Random;
import java.lang.Double;
public class LocationGenerator extends ColumnType implements Generator{
	public static final double Y = 53.5333;
	private static double X = 113.5000;
    private static int RADIUS = 700000;

	public LocationGenerator(){

	}

	public String gen() {
        Random random = new Random();
        String output = "";
        // Convert radius from meters to degrees
        double radiusInDegrees = RADIUS / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        double x = w * Math.cos(t);
        double y = w * Math.sin(t);

        // Adjust the x-coordinate for the shrinking of the east-west distances
        double new_x = x / Math.cos(Y);

        double foundLongitude = new_x + X;
        double foundLatitude = y + Y;
        
        output = Double.toString(foundLongitude) + SEPERATOR + Double.toString(foundLatitude) + SEPERATOR;
        return output;
    }
}