package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;


import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import main.java.com.ualberta.cmput391.W15.slmyers.cass.Application;

public class Output{
	private static final int NUM_THREADS = 2;
	public static final String DATA_DIR = "/home/ubuntu/resources/data";
	public static final String SHELL_DIR = "/home/ubuntu/resources/shell";
    
	public static void main( String[] args ){
		String tableDesc = Application.getApp().getTableDesc();
		
		long startTime = 0;
    	long endTime = 0;
    	printStartMsg();
    	FileUtils.deleteDir(DATA_DIR);
    	FileUtils.createDir(DATA_DIR);
    	FileUtils.deleteDir(SHELL_DIR);
    	FileUtils.createDir(SHELL_DIR);
    	
    	startTime = System.currentTimeMillis();
    	ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    	for (int i = 0; i < 2; i++) {
    		  // filler is a placeholder for when I can actually implement DataThread
    	      Runnable worker = new DataThread(tableDesc, Application.DEMO);
    	      executor.execute(worker);
    	}

    	// This will make the executor accept no new threads
        // and finish all existing threads in the queue
        executor.shutdown();
        // Wait until all threads are finish
        try {
			executor.awaitTermination(120, TimeUnit.SECONDS);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        
        endTime = System.currentTimeMillis();
        printEndMsg(endTime - startTime);
        Application.getApp().closeCluster();
        
        //FileUtils.deleteDir("resources");
    }
    
   

    

    
    public static void printStartMsg(){
    	System.out.println("**********************************");
    	System.out.println("*  begining data generation      *");
    	System.out.println("**********************************");
    }
    
    /*
     * long size = FileUtils.sizeOfDirectory(new File("C:/Windows/folder"));

    	System.out.println("Folder Size: " + size + " bytes");
     */
    
    public static void printEndMsg(long totalTime){
    	System.out.println("**********************************");
    	System.out.println("*     ending data generation     *");
    	System.out.println("**********************************");
    }
    
    public static long folderSize(File directory) {
        long length = 0;
        for (File file : directory.listFiles()) {
            if (file.isFile())
                length += file.length();
            else
                length += folderSize(file);
        }
        return length;
    }
}

