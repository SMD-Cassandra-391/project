package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;


import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Output{
	private static final int NUM_THREADS = 2;
	public static final String DIR_NAME = "data";
    
	public static void main( String[] args ){
    	long startTime = 0;
    	long endTime = 0;
    	printStartMsg();
    	createDir(DIR_NAME);
    	startTime = System.currentTimeMillis();
    	ExecutorService executor = Executors.newFixedThreadPool(NUM_THREADS);
    	for (int i = 0; i < 2; i++) {
    	      Runnable worker = new DataThread();
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
        
    }
    
    

    public static void createDir(String dirName){
		File theDir = new File(dirName);
		
		// if the directory does not exist, create it
		if (!theDir.exists()) {
		    System.out.println("creating directory: " + dirName);
		    boolean result = false;
		
		    try{
		        theDir.mkdir();
		        result = true;
		    } 
		    catch(SecurityException se){
		        //handle it
		    }        
		    if(!result) {    
		        System.out.println("Unable to create directory: " + dirName);  
		    }
		}
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
    	long dirSizeBytes = folderSize(new File("data"));
    	
    	
    	System.out.println("data generation complete");
    	System.out.println("\tgenerated " + dirSizeBytes + " Bytes");
    	System.out.println("\tin folder: data/");
    	System.out.println("\ttook " + TimeUnit.MILLISECONDS.toSeconds(totalTime) + " seconds");
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

