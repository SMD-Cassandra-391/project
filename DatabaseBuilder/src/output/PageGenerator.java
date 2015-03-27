package output;


import java.util.ArrayList;

import textGenerator.*;

public class PageGenerator {
	// around 1000 chars per line
	private static final int CHAR_CAP = 1000;
	// 10000 rows per csv file
	public static final int PAGE_SIZE = 1000;
	private ArrayList<Generator> cols;
	private StringBuilder sb;

	public PageGenerator() {
		this.initializeCols();
		sb = new StringBuilder(CHAR_CAP);
	}

	private void initializeCols() {
		cols = new ArrayList<Generator>();
		
		cols.add(new IntGenerator(2));
		
		cols.add(new TimeSeriesGenerator());
		cols.add(new PhoneGenerator());
		cols.add(new LocationGenerator());
		cols.add(new GridGenerator());
		cols.add(new LocationGenerator());
		cols.add(new GridGenerator());
		
		cols.add(new IntGenerator(1));
		cols.add(new BigIntGenerator());
		
		cols.add(new IntGenerator(7));
		
		cols.add(new BigIntGenerator());
		cols.add(new IPgenerator());
		
		cols.add(new IntGenerator(16));
		
		cols.add(new IPgenerator());
		cols.add(new IPgenerator());
		
		cols.add(new IntGenerator(31));
		
		cols.add(new IPgenerator());
		cols.add(new IntGenerator(7));
		
		cols.add(new IPgenerator());
		cols.add(new IntGenerator(381));
		cols.add(new LastIntGen());
	}

	public ArrayList<String> generatePage() {
		ArrayList<String> page = new ArrayList<String>();
		//String line = "";
		for (int i = 0; i < PAGE_SIZE; i++) {
			sb.delete(0, sb.length());
			for (Generator column : cols) {
				sb.append(column.gen());
			}
			page.add(sb.toString());
		}
		return page;
	}

	@SuppressWarnings("unused")
	private ArrayList<Generator> getCols() {
		return this.cols;
	}
}