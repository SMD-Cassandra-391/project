package ObjectGenerator;

import java.util.Random;

import com.google.common.net.InetAddresses;

public class  IPGenerator extends ColumnObject implements ObjectGenerator{

	@Override
	public Object gen() {
		Random rnd = new Random();
        return InetAddresses.fromInteger(rnd.nextInt()).getHostAddress();
	}

}
