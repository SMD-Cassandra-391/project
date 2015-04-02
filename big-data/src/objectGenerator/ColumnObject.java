package objectGenerator;

import java.util.Random;

/**
 * this is a class which is extended by every generator of a specific type. 
 * @author slmyers
 *
 */

public class ColumnObject {
	public Random rndm;
	
	public ColumnObject(int seed){
		rndm = new Random(seed);
	}
	
	public void setSeed(int seed){
		rndm = new Random(seed);
	}
	
	
	protected final int randomInt(int min, int max){
        return rndm.nextInt((max - min) + 1) + min;
    }

    protected final long randomLong(long min, long max){
        return min +((long)(rndm.nextDouble()*(max-min)));
    }
 }
