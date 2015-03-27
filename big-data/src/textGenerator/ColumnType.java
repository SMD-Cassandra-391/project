package textGenerator;
import java.util.Random;

public abstract class ColumnType{
    public static final String SEPERATOR = ",";
    protected float freq;
    protected String type;
    
    protected final int randomInt(int min, int max){
        Random rndm = new Random();
        return rndm.nextInt((max - min) + 1) + min;
    }

    protected final long randomLong(long min, long max){
        Random rndm = new Random();
        return min +((long)(rndm.nextDouble()*(max-min)));
    }
}