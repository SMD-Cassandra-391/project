package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

import main.java.com.ualberta.cmput391.W15.slmyers.pageWriter.Output;

public class RunShell {
	private static final String CASS_PATH = "/home/steve/cassandra/apache-cassandra-2.1.3/bin";
	// this is path from the folder holding the program
	// relative path from jar file for production
	private static final String CQLSH = "./../../../cassandra/apache-cassandra-2.1.3/bin/cqlsh";
	private static final int BUFFER_SIZE = 150;
	private static final String SPACE = " ";
	private static final String LOCAL_HOST = "127.0.0.1";
	
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
	
	
	public static String runcpy(String CQLSHFile){
		StringBuilder cmd = new StringBuilder(BUFFER_SIZE);
		cmd.append(CQLSH);
		cmd.append(SPACE);
		cmd.append("-f");
		cmd.append(SPACE);
		cmd.append(CQLSHFile);
		return executeCommand(cmd.toString());
	}
	
	public static String deletePair(String csvFile, String CQLSHFile){
		StringBuilder sb = new StringBuilder();
		sb.append(deleteCmd(Output.DATA_DIR + File.separatorChar + csvFile));
		sb.append(" ");
		sb.append(deleteCmd(Output.SHELL_DIR + File.separatorChar + CQLSHFile));
		return sb.toString();
	}
	
	private static String deleteCmd(String file){
		StringBuilder cmd = new StringBuilder(BUFFER_SIZE);
		cmd.append("rm");
		cmd.append(SPACE);
		cmd.append("-f");
		cmd.append(SPACE);
		cmd.append(file);
		return executeCommand(cmd.toString());
	}
	
	/*
	 * Then, load SSTables to Cassandra using sstableloader:
	 * sstableloader -d <ip address of the node> data/quote/historical_prices
	 */
	
	public static String SSTcmd(String file){
		StringBuilder cmd = new StringBuilder(BUFFER_SIZE);
		cmd.append("./../cassandra/apache-cassandra-2.0.5/bin/sstableloader");
		cmd.append(SPACE);
		cmd.append(LOCAL_HOST);
		cmd.append(SPACE);
		cmd.append("data/sstable");
		return executeCommand(cmd.toString());
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
		System.out.println(output.toString());
		return output.toString();
 
	}
}
