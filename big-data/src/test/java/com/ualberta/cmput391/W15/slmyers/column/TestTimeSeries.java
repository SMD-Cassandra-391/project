package com.ualberta.cmput391.W15.slmyers.column;
import org.junit.Assert;
import org.junit.Test;
import java.lang.String;
import java.util.Date;
import java.lang.Long;
import java.lang.NumberFormatException;

public class TestTimeSeries{
    @Test
    public void test(){
        TimeSeriesGenerator tsg = new TimeSeriesGenerator();
        String test = tsg.gen();
        String[] datesWithExtraField = test.split(";", -1);
        String[] datesExtracted = new String[3];
        for(int i = 0; i < datesWithExtraField.length - 1; i++){
            datesExtracted[i] = datesWithExtraField[i];
        }
        Assert.assertTrue("there are not three dates in time series. " +
                          datesExtracted.length + "\n" + datesExtracted[0] + "\n" + 
                          datesExtracted[1] + "\n"  + datesExtracted[2] + "\n"
                          , datesExtracted.length == 3);

        for(int i = 0; i < datesExtracted.length; i++){
            long date = 0;
            try{
                date = Long.parseLong(datesExtracted[i]);
            }catch(NumberFormatException nfe){
                nfe.printStackTrace();
            }
            Assert.assertTrue("date is not in proper range" +
                               "date = " + date + " startDate = " + tsg.getStartDate().getTime() +
                               "\n i = " + i,
                              (date >= tsg.getStartDate().getTime()
                                && date <= tsg.getEndDate().getTime()));
        }
    }
}
