package output;


import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cass.Application;
import cass.RunShell;




public class Output {
	public static Application app;
	public static final int NTHREDS = 4;
	
	public static void main(String[] args) throws Exception {
		String type;
		type = args[0];
		Application.NUM_ROWS = Integer.parseInt(args[1]);
		initApp(type);
		
		
		
		long start = System.currentTimeMillis();
		ExecutorService executor = Executors.newFixedThreadPool(100);
		for(int i = 0; i < 10; i++)
			run(i, executor);
		executor.shutdown();
		// Wait until all threads are finish
		executor.awaitTermination(10000, TimeUnit.SECONDS);
		long end = System.currentTimeMillis();
		PrintWriter out = new PrintWriter("log.txt");
		out.println("took " +  (end - start)/1000 + " seconds.");
		out.close();
		System.out.println("took " + (end-start)/1000 + " seconds.");
		System.exit(0);
	}


	
	public static void initApp(String type){
		app = Application.getApp();
		app.createOutputDirs();
	}
	
	public static void run(int i, ExecutorService executor) throws Exception{
		
		Runnable worker1 = new DataThread(i % 4, Application.PRAC_TABLE_ONE_PATH);
		Runnable worker2 = new DataThread(i % 4, Application.PRAC_TABLE_TWO_PATH);
		Runnable worker3 = new DataThread(i % 4, Application.PRAC_TABLE_THREE_PATH);
		Runnable worker4 = new DataThread(i % 4, Application.PRAC_TABLE_FOUR_PATH);
		executor.execute(worker1);
		executor.execute(worker2);
		executor.execute(worker3);
		executor.execute(worker4);
		
	    
	    Thread t1 = new Thread(){
	    	public void run(){
	    		System.out.println("loading tables");
	    		RunShell.SSTcmd(Application.PRAC_TABLE_ONE_PATH);
	    		RunShell.SSTcmd(Application.PRAC_TABLE_TWO_PATH);
	    		RunShell.SSTcmd(Application.PRAC_TABLE_THREE_PATH);
	    		RunShell.SSTcmd(Application.PRAC_TABLE_FOUR_PATH);
	    	}
	    };
	    t1.start();
	    t1.join();
	    cleanDirs();
	    
		System.out.println("Finished all threads");
		
	}
	
	public static void cleanDirs(){
		
		FileUtils.deleteDir(Application.PRAC_TABLE_ONE_PATH);
		FileUtils.deleteDir(Application.PRAC_TABLE_TWO_PATH);
		FileUtils.deleteDir(Application.PRAC_TABLE_THREE_PATH);
		FileUtils.deleteDir(Application.PRAC_TABLE_FOUR_PATH);
		
	}
	
	

}
