package objectGenerator;

import java.util.Random;

public class ColumnObject {
	public Random rndm = new Random();
	
	protected final int randomInt(int min, int max){
        return rndm.nextInt((max - min) + 1) + min;
    }

    protected final long randomLong(long min, long max){
        return min +((long)(rndm.nextDouble()*(max-min)));
    }
 }
