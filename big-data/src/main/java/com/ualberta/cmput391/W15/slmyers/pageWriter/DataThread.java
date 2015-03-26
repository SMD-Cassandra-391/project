package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import main.java.com.ualberta.cmput391.W15.slmyers.cass.Application;
import main.java.com.ualberta.cmput391.W15.slmyers.cass.RunShell;

public class DataThread implements Runnable{
	private String threadName;
	private long runtime;
	private ArrayList<String> output;
	private PageGenerator pg;
	private String tableDesc;
	private String type;
	public DataThread(String tableDesc, String type){
		threadName = new String(UUID.randomUUID().toString());
		output = new ArrayList<String>();
		pg = new PageGenerator();
		this.tableDesc = tableDesc;
		this.type = type;
	}
	
	public void run(){
		String cpyOutput;
		String delOutput;
		long start = System.currentTimeMillis();
		ArrayList<String> shellOutput = new ArrayList<String>();
		shellOutput.add(this.shellText());
		output = pg.generatePage();
		
		FileUtils.outputPage(output, new String(threadName + ".csv"), Output.DATA_DIR);
		FileUtils.outputPage(shellOutput, new String(threadName + ".cql"), Output.SHELL_DIR);
		long end = System.currentTimeMillis();
		this.runtime = (end - start) / 1000L;
		RunShell.runcpy("shell/" + threadName + ".cql");
		
		
	}
	
	public void printInfo(){
		System.out.println("-----------------------------------------");
		System.out.println("\tThread Name: " + this.threadName);
		System.out.println("\tWrote to file " + this.threadName + ".csv");
		System.out.println("\tTook " + runtime + " seconds to complete.");
		System.out.println("-----------------------------------------");
	}
	
	public String shellText(){
		if(type.equals(Application.DEMO)){
			return "COPY " + Application.DEMO_KEYSPACE + "." + Application.DEMO_TABLE + " "
					+ tableDesc + " FROM " + "'" + "data" + File.separatorChar + threadName + ".csv" + "'" +  ";";
		}
		return "COPY " + Application.PROJ_KEYSPACE + "." + Application.PROJ_KEYSPACE + " "
				+ tableDesc + " FROM " + threadName + ".csv;";
	}
	
	
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}

	public long getRuntime() {
		return runtime;
	}

	public void setRuntime(long runtime) {
		this.runtime = runtime;
	}

	public ArrayList<String> getOutput() {
		return output;
	}

	public void setOutput(ArrayList<String> output) {
		this.output = output;
	}

	public PageGenerator getPg() {
		return pg;
	}

	public void setPg(PageGenerator pg) {
		this.pg = pg;
	}
	
}