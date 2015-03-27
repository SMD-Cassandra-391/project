package textGenerator;
import java.lang.Integer;

public class GridGenerator extends ColumnType implements Generator{
    public GridGenerator(){

    }

    public String gen(){
        String output = "";
        int city = randomInt(0, 100);
        int province = randomInt(0, 1000);
        output = Integer.toString(city) + SEPERATOR + Integer.toString(province) + SEPERATOR;
        return output;
    }
}