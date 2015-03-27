package test.java.com.ualberta.cmput391.W15.slmyers.pageWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import main.java.com.ualberta.cmput391.W15.slmyers.pageWriter.FileWriter;

import org.junit.Assert;
import org.junit.Test;

public class TestFIleWriter {
	@Test
	public void testoutputPage(){
		String testString = "test";
		ArrayList<String> output = new ArrayList<String>();
		List<String> input = null;
		Path path = Paths.get(FileWriter.OUTPUT);
		output.add(testString);
		output.add(testString);
		
		FileWriter.outputPage(output, "test.txt");
		Assert.assertTrue("output file: " + FileWriter.OUTPUT + " does not exist", Files.exists(path));
		
		try {
			input = Files.readAllLines(path, StandardCharsets.UTF_8);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Assert.assertTrue("expected 2 lines, found " + input.size() + " lines", input.size() == 2);
		
		for(String s : input){
			Assert.assertTrue("expected string s to be test"
							  + " found: " + s, s.equals("test"));
		}
		
		try {
			Files.delete(path);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
