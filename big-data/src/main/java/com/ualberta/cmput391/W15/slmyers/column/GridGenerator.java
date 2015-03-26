package main.java.com.ualberta.cmput391.W15.slmyers.column;
import java.lang.Integer;

public class GridGenerator extends ColumnType implements Generator{
	public static final String TYPE = "INT";
    public GridGenerator(){

    }

    public String gen(){
        String output = "";
        int city = randomInt(0, 100);
        int province = randomInt(0, 1000);
        output = Integer.toString(city) + SEPERATOR + Integer.toString(province) + SEPERATOR;
        return output;
    }

	@Override
	public String genTrue() {
		// TODO Auto-generated method stub
		return null;
	}
}