package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;
import main.java.com.ualberta.cmput391.W15.slmyers.column.*;

import java.util.ArrayList;

public class PageGenerator{
	
	private static final int PAGE_SIZE = 1000;
    private ArrayList<Generator> cols;

    public PageGenerator(){
    	this.initializeCols();
    }

    private void initializeCols(){
        cols = new ArrayList<Generator>();
        cols.add(new IntGenerator(2));
        cols.add(new TimeSeriesGenerator());
        cols.add(new PhoneGenerator());
        cols.add(new LocationGenerator());
        cols.add(new GridGenerator());
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
        cols.add(new IntGenerator(380));
        cols.add(new LastIntGen());
    }

    @SuppressWarnings("unused")
	private ArrayList<String> generatePage(){
        ArrayList<String> page = new ArrayList<String>();
        String line = "";
        for(int i = 0; i < PAGE_SIZE; i++){
            line = "";
            for(Generator column : cols){
                line += column.gen();
            }
            page.add(line);
        }
        return page;
    }

    @SuppressWarnings("unused")
	private ArrayList<Generator> getCols(){
    	return this.cols;
    }
}