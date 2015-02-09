package com.ualberta.cmput391.W15.slmyers.column;
import com.google.common.net.InetAddresses;
import org.junit.Assert;
import org.junit.Test;
import java.lang.String;
public class TestIPGenerator{
    @Test
    public void test(){
        IPgenerator ipg = new IPgenerator();
        String ipString = ipg.gen();
       	String[] term = ipString.split(";", -1);
        Assert.assertTrue("IP generated is not valid", 
                   InetAddresses.isInetAddress(term[0]));
    }
}