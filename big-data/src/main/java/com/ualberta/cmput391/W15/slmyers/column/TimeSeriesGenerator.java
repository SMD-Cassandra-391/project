package main.java.com.ualberta.cmput391.W15.slmyers.column;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.lang.Long;
import java.util.Random;

public class TimeSeriesGenerator extends ColumnType implements Generator {
    protected Date startDate;
    protected Date endDate;
    protected SimpleDateFormat formatter;
    private long startTime;
    private long requestTime;
    private long reportTime;

    public TimeSeriesGenerator(){
        formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try{
            startDate = formatter.parse("2013-00-01 00:00:00");
            endDate = formatter.parse("2014-00-01 00:00:00");
        }catch(ParseException pe){
            System.out.println("ERROR: parse error while generating random Epoch time.");
            pe.printStackTrace();
        }
    }

    public String gen(){
        String output = "";
        long startLong = startDate.getTime();
        long endLong = endDate.getTime();
        startTime = genDate(startLong, endLong);
        requestTime = startTime + (long)randomInt(2, 6);
        reportTime = requestTime + (long)randomInt(3, 10);
        output = Long.toString(startTime) + SEPERATOR +  
                 Long.toString(requestTime) + SEPERATOR +
                 Long.toString(reportTime) + SEPERATOR;
        return output;
    }

    protected long genDate(long min, long max){
        Random random = new Random();
        return min + (long)(random.nextDouble()*(max - min));
    }

    public Date getStartDate(){
        return this.startDate;
    }

    public Date getEndDate(){
        return this.endDate;
    }

	@Override
	public String genTrue() {
		// TODO Auto-generated method stub
		return null;
	}
}