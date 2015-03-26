package main.java.com.ualberta.cmput391.W15.slmyers.column;

import java.util.Random;

public class LocGenTrueX extends LocationGenerator implements Generator {

	@Override
	public String gen() {
		return null;
	}

	public LocGenTrueX(){
		this.type = "DOUBLE";
	}
	
	public String genTrue() {
        Random random = new Random();
        String output = "";
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
        return Double.toString(foundLongitude);
    }

}
