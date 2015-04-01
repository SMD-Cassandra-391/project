package objectGenerator;

import com.google.common.net.InetAddresses;

public class  IPGenerator extends ColumnObject implements ObjectGenerator{
	

	public IPGenerator(int seed) {
		super(seed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object gen() {
        return InetAddresses.fromInteger(rndm.nextInt()).getHostAddress();
	}

}
