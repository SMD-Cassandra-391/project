package objectGenerator;

public class PhGenerator extends ColumnObject implements ObjectGenerator{

	@Override
	public Object gen() {
		return new Integer(randomInt(1000000, 9999999)).toString();
	}
	
	
}
