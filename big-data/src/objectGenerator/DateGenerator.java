package objectGenerator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class DateGenerator extends ColumnObject implements ObjectGenerator {
	private Date startDate;
	private Date endDate;
	private SimpleDateFormat formatter;
	long startLong;
	long endLong;

	public DateGenerator(int seed) {
		super(seed);
		setDates();
	}

	private void setDates() {
		formatter = new SimpleDateFormat("yyyy-mm-dd HH:mm:ss");
		try {
			startDate = formatter.parse("2013-00-01 00:00:00");
			endDate = formatter.parse("2014-00-01 00:00:00");
		} catch (ParseException pe) {
			System.out
					.println("ERROR: parse error while generating random Epoch time.");
			pe.printStackTrace();
		}
		startLong = startDate.getTime();
		endLong = endDate.getTime();
	}

	@Override
	public Object gen() {
		return new Date((genDate(startLong, endLong)));
	}

	private long genDate(long min, long max) {

		return min + (long) (rndm.nextDouble() * (max - min));
	}

}
