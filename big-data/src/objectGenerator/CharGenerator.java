package objectGenerator;

/**
 * generates a random lower case character
 * @author slmyers
 *
 */


public class CharGenerator extends ColumnObject implements ObjectGenerator {

	@Override
	public Object gen() {
		char c = (char)randomInt(97, 122);
		return Character.toString(c);
	}

	public CharGenerator(int seed){
		super(seed);
	}
}
