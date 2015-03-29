package objectGenerator;

public class DecimalGenerator extends ColumnObject implements ObjectGenerator {

	@Override
	public Object gen() {
		return new Double(randomInt(1,100)/100.0);
	}

}
