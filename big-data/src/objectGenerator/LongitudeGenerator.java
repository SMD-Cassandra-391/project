package objectGenerator;



public class LongitudeGenerator extends ColumnObject implements ObjectGenerator {
	private static double X = 113.5000;
	private static double Min_X = 100.000;
    @Override
	public Object gen() {
		
		return new Float(rndm.nextFloat() * (X - Min_X) + Min_X);
	}

}
