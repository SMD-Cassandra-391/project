package main.java.com.ualberta.cmput391.W15.slmyers.column;
import java.lang.Long;

public class BigIntGenerator extends ColumnType implements Generator {
    public final long MAX = 50000000000000L; 
    public final long MIN = 10000000000000L;
    public BigIntGenerator(){

    }

    public String gen(){
        String output = "";
        output =  Long.toString(randomLong(MIN, MAX)) + SEPERATOR;
        return output;
    }

}