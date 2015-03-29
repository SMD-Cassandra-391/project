package objectGenerator;

public class CharGenerator extends ColumnObject implements ObjectGenerator {

	@Override
	public Object gen() {
		char c = (char)randomInt(97, 122);
		return Character.toString(c);
	}

}
