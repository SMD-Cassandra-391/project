package objectGenerator;

import java.util.Random;

public class ColumnObject {
	protected final int randomInt(int min, int max){
        Random rndm = new Random();
        return rndm.nextInt((max - min) + 1) + min;
    }

    protected final long randomLong(long min, long max){
        Random rndm = new Random();
        return min +((long)(rndm.nextDouble()*(max-min)));
    }
}
