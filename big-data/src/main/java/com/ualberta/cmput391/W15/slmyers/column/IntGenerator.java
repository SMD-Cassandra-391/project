package main.java.com.ualberta.cmput391.W15.slmyers.column;
import java.lang.Integer;

public class IntGenerator extends ColumnType implements Generator{
    private int series;
    public IntGenerator(int series){
        this.series = series;
    }
    
    public IntGenerator(){
    	this.type = "INT";
    }

    public String gen(){
        String output = "";

        for(int i = 0; i < series; i++){
            output += Integer.toString(randomInt(1,1000));
            output += SEPERATOR; 
        }
        return output;
    }
    
    public String genTrue(){
    	return Integer.toString(randomInt(1,1000));
    }
}