package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;

import java.util.ArrayList;
import java.util.UUID;

public class DataThread implements Runnable{
	private String threadName;
	private long runtime;
	private ArrayList<String> output;
	private PageGenerator pg;
	private String tableDesc;
	public DataThread(String tableDesc){
		threadName = new String(UUID.randomUUID().toString());
		output = new ArrayList<String>();
		pg = new PageGenerator();
		this.tableDesc = new String(tableDesc);
	}
	
	public void run(){
		long start = System.currentTimeMillis();
		output = pg.generatePage();
		FileUtils.outputPage(output, new String(threadName + ".csv"));
		long end = System.currentTimeMillis();
		this.runtime = (end - start) / 1000;
	}
	
	public void printInfo(){
		System.out.println("-----------------------------------------");
		System.out.println("\tThread Name: " + this.threadName);
		System.out.println("\tWrote to file " + this.threadName + ".csv");
		System.out.println("\tTook " + runtime + " seconds to complete.");
		System.out.println("-----------------------------------------");
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