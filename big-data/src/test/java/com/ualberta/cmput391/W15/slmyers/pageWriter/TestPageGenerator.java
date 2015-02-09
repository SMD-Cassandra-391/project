package test.java.com.ualberta.cmput391.W15.slmyers.pageWriter;
import java.util.ArrayList;

import main.java.com.ualberta.cmput391.W15.slmyers.pageWriter.PageGenerator;

import org.junit.Assert;
import org.junit.Test;

public class TestPageGenerator {
	@Test
	public void testGeneratePage(){
		PageGenerator pg = new PageGenerator();
		ArrayList<String> output = new ArrayList<String>();
		output = pg.generatePage();
		
		Assert.assertTrue("expected " +  PageGenerator.PAGE_SIZE + "lines, found " + output.size() +
						  " lines", output.size() == PageGenerator.PAGE_SIZE);
	}
}
