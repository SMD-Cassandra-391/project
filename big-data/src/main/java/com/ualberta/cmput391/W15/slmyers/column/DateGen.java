package main.java.com.ualberta.cmput391.W15.slmyers.column;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateGen extends TimeSeriesGenerator implements Generator {
	public DateGen(){
		this.type = "TIMESTAMP";
		formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
        try{
            startDate = formatter.parse("2013-00-01 00:00:00");
            endDate = formatter.parse("2014-00-01 00:00:00");
        }catch(ParseException pe){
            System.out.println("ERROR: parse error while generating random Epoch time.");
            pe.printStackTrace();
        }
	}
	
	public String genTrue(){
		long startLong = startDate.getTime();
        long endLong = endDate.getTime();
		return Long.toString(genDate(startLong, endLong));
	}
}
