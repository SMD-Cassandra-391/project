package objectGenerator;

/**
 * generates a random Long number between:  10000000000000 and 50000000000000.
 * @author slmyers
 *
 */
public class BigIntGenerator extends ColumnObject implements ObjectGenerator{
	public final long MAX = 50000000000000L; 
    public final long MIN = 10000000000000L;
	@Override
	public Object gen() {
		// TODO Auto-generated method stub
		return randomLong(MIN, MAX);
	}
	
	public BigIntGenerator(int seed){
		super(seed);
	}

}
