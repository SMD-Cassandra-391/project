package objectGenerator;

public class PhGenerator extends ColumnObject implements ObjectGenerator{

	@Override
	public Object gen() {
		return Integer.toString(randomInt(1000000, 9999999));
	}
}
