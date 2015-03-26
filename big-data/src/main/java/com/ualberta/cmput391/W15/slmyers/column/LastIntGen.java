package main.java.com.ualberta.cmput391.W15.slmyers.column;
import java.lang.Integer;

public class LastIntGen extends ColumnType implements Generator{

    public LastIntGen(){
    	this.type = "INT";
    }

    public String gen(){
        String output = "";
        output += Integer.toString(randomInt(1,1000));
        return output;
    }

	@Override
	public String genTrue() {
		// TODO Auto-generated method stub
		return null;
	}
}