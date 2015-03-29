package output;


import java.io.File;
import java.util.UUID;

import loader.JmxBulkLoader;
import cass.Application;
import cass.SSTwriter;



public class DataThread implements Runnable{
	private int threadId;
	private String folder;
	public DataThread(int threadId, String folder){
		this.threadId = threadId;
		this.folder = folder;
	}
	
	public void run(){
		final SSTwriter writer = new SSTwriter(Application.NUM_ROWS, this.folder, this.threadId); 
		
		File file = new File(folder);
		final String path = file.getAbsolutePath();
		Thread t1 = new Thread(){
			public void run(){
				writer.execute();
				writer.close();
			}
		};
		t1.start();
		try {
			t1.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}
	
	public void printInfo(){
		
	}
}