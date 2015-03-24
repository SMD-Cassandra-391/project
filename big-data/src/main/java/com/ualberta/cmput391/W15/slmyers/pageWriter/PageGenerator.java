package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;
import main.java.com.ualberta.cmput391.W15.slmyers.column.*;

import java.util.ArrayList;

public class PageGenerator{
	
	public static final int PAGE_SIZE = 10000;
    private ArrayList<Generator> cols;

    public PageGenerator(){
    	this.initializeCols();
    }

    private void initializeCols(){
        cols = new ArrayList<Generator>();
        	cols.add(new IntGenerator(400));
    }

    public ArrayList<String> generatePage(){
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