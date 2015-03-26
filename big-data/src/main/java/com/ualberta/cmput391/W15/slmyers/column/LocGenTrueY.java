package main.java.com.ualberta.cmput391.W15.slmyers.column;

import java.util.Random;

public class LocGenTrueY extends LocationGenerator implements Generator {
	@Override
	public String gen() {
		return null;
	}

	public LocGenTrueY(){
		this.type = "DOUBLE";
	}
	
	public String genTrue() {
        Random random = new Random();
        
        // Convert radius from meters to degrees
        double radiusInDegrees = RADIUS / 111000f;

        double u = random.nextDouble();
        double v = random.nextDouble();
        double w = radiusInDegrees * Math.sqrt(u);
        double t = 2 * Math.PI * v;
        
        double y = w * Math.sin(t);
        double foundLatitude = y + Y;
        
        return Double.toString(foundLatitude);
	}
}
