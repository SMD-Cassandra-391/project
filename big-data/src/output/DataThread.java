package output;


import java.io.File;
import java.util.UUID;

import loader.JmxBulkLoader;
import cass.Application;
import cass.SSTwriter;



public class DataThread implements Runnable{
	private String threadName;
	private String folder;
	public DataThread(String threadName, String folder){
		this.threadName = threadName;
		this.folder = folder;
	}
	
	public void run(){
		final SSTwriter writer = new SSTwriter(Application.NUM_ROWS, folder); 
		
		File file = new File(folder);
		final String path = file.getAbsolutePath();
		writer.execute();
		writer.close();
		//JmxBulkLoader jmxLoader = new JmxBulkLoader("localhost", 7199);
	}
	
	public void printInfo(){
		
	}
	

	
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}