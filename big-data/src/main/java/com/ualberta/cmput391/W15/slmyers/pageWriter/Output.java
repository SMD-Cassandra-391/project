package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;

import java.util.ArrayList;

public class Output{
	private static int NUMBER_PAGES = 1;
	
    public static void main( String[] args ){
    	ArrayList<String> output = new ArrayList<String>();
    	PageGenerator pg = new PageGenerator();
        for(int i = 0; i < NUMBER_PAGES; i++){
        	output = pg.generatePage();
        	FileWriter.outputPage(output);
        }
    }
    
    
}