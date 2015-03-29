package objectGenerator;

import com.google.common.net.InetAddresses;

public class  IPGenerator extends ColumnObject implements ObjectGenerator{

	@Override
	public Object gen() {
        return InetAddresses.fromInteger(rndm.nextInt()).getHostAddress();
	}

}
