package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import main.java.com.ualberta.cmput391.W15.slmyers.pageWriter.Output;

public class RunShell {
	private static final String CASS_PATH = "/home/ubuntu/cassandra/apache-cassandra-2.0.5/bin";
	private static final String RESOURCE_PATH = "/home/ubuntu/resources/";
	private static final String CQLSH = "./home/ubuntu/cassandra/apache-cassandra-2.0.5/bin/cqlsh";
	private static final int BUFFER_SIZE = 150;
	private static final String SPACE = " ";
	
	public static void movePair(String csvFile, String CQLSHFile){
		moveCommand(Output.DATA_DIR + csvFile);
		moveCommand(Output.SHELL_DIR + CQLSHFile);
	}
	
	private static void moveCommand(String file){
		StringBuilder moveCommand = new StringBuilder(BUFFER_SIZE);
		moveCommand.append("mv");
		moveCommand.append(SPACE);
		moveCommand.append(file);
		moveCommand.append(SPACE);
		moveCommand.append(CASS_PATH);
		executeCommand(moveCommand.toString());
	}
	
	
	public static void runcpy(String CQLSHFile){
		StringBuilder cmd = new StringBuilder(BUFFER_SIZE);
		cmd.append(CQLSH);
		cmd.append(SPACE);
		cmd.append("-f");
		cmd.append(SPACE);
		cmd.append(CQLSHFile);
		executeCommand(cmd.toString());
	}
	
	public static void deletePair(String csvFile, String CQLSHFile){
		deleteCmd(Output.DATA_DIR + csvFile);
		deleteCmd(Output.SHELL_DIR + CQLSHFile);
	}
	
	private static void deleteCmd(String file){
		StringBuilder cmd = new StringBuilder(BUFFER_SIZE);
		cmd.append("rm");
		cmd.append(SPACE);
		cmd.append("-f");
		cmd.append(SPACE);
		cmd.append(file);
		executeCommand(cmd.toString());
	}
	
	private static String executeCommand(String command) {
		 
		StringBuffer output = new StringBuffer();
 
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
			BufferedReader reader = 
                            new BufferedReader(new InputStreamReader(p.getInputStream()));
 
                        String line = "";			
			while ((line = reader.readLine())!= null) {
				output.append(line + "\n");
			}
 
		} catch (Exception e) {
			e.printStackTrace();
		}
 
		return output.toString();
 
	}
}
