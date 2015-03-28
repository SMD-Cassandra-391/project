package output;


import java.io.File;
import java.util.UUID;

import loader.JmxBulkLoader;
import cass.Application;
import cass.SSTwriter;



public class DataThread implements Runnable{
	private String threadName;
	
	public DataThread(String tableDesc, String folder){
		threadName = new String(UUID.randomUUID().toString());
		
	}
	
	public void run(){
		final SSTwriter writer = new SSTwriter(Application.NUM_ROWS); 
		File file = new File(threadName + File.separatorChar + Application.TYPE_KEYSPACE 
							+ File.separatorChar + Application.TYPE_TABLE);
		final String path = file.getAbsolutePath();
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