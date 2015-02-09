package main.java.com.ualberta.cmput391.W15.slmyers.column;
import com.google.common.net.InetAddresses;
import java.util.Random;

public class IPgenerator extends ColumnType implements Generator{
	public IPgenerator(){

	}
	public String gen(){
        Random rnd = new Random();
        return InetAddresses.fromInteger(rnd.nextInt()).getHostAddress() + SEPERATOR;
    }

}