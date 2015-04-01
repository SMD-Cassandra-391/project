package objectGenerator;

public class PhGenerator extends ColumnObject implements ObjectGenerator{

	public PhGenerator(int seed) {
		super(seed);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Object gen() {
		return Integer.toString(randomInt(1000000, 9999999));
	}
}
