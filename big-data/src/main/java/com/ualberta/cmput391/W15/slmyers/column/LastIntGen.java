package main.java.com.ualberta.cmput391.W15.slmyers.column;
import java.lang.Integer;

public class LastIntGen extends ColumnType implements Generator{

    public LastIntGen(){

    }

    public String gen(){
        String output = "";
        output += Integer.toString(randomInt(1,1000));
        return output;
    }
}