package objectGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class DateGenerator extends ColumnObject implements ObjectGenerator {
	private Date startDate;
    private Date endDate;
    private SimpleDateFormat formatter;
	public DateGenerator(){
        formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try{
            startDate = formatter.parse("2013-00-01 00:00:00");
            endDate = formatter.parse("2014-00-01 00:00:00");
        }catch(ParseException pe){
            System.out.println("ERROR: parse error while generating random Epoch time.");
            pe.printStackTrace();
        }
    }
	@Override
	public Object gen() {
		long startLong = startDate.getTime();
        long endLong = endDate.getTime();
        
		return new Date((genDate(startLong, endLong)));
	}
	
	private long genDate(long min, long max){
        Random random = new Random();
        return min + (long)(random.nextDouble()*(max - min));
    }

}
