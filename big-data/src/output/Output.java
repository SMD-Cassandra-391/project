package output;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cass.Application;




public class Output {
	public static Application app;
	public static final int NTHREDS = 4;
	
	public static void main(String[] args) throws Exception {
		String type;
		if(args.length != 1){
			System.out.println("incorrect usage");
			System.out.println("correct usage:");
			System.out.println("java -jar DatabaseGenerator-1.0-SNAPSHOT.one-jar.jar <type>");
		}
		type = args[0];
		Application.NUM_ROWS = Integer.parseInt(args[1]);
		initApp(type);
		
		
		
		long start = System.currentTimeMillis();
		run();
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
	
	public static void run() throws Exception{
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		Runnable worker1 = new DataThread(1, Application.PROJ_TABLE_ONE_PATH);
		
		Runnable worker2 = new DataThread(2, Application.PROJ_TABLE_TWO_PATH);
		
		Runnable worker3 = new DataThread(3, Application.PROJ_TABLE_THREE_PATH);
		Runnable worker4 = new DataThread(4, Application.PROJ_TABLE_FOUR_PATH);
		executor.execute(worker1);
		executor.execute(worker2);
		executor.execute(worker3);
		executor.execute(worker4);
		executor.shutdown();
	    // Wait until all threads are finish
	    executor.awaitTermination(1000, TimeUnit.SECONDS);
	    System.out.println("Finished all threads");
		
	}
	
	public static void deleteThreadOutput() {
		for(int i = 0; i < NTHREDS; i++){
			FileUtils.deleteDir("thread" + i + File.separatorChar + Application.TYPE_KEYSPACE + File.separatorChar 
								+ Application.TYPE_TABLE);
		}
	}
	
	public static void cleanDirs(){
		for(int i = 0; i < NTHREDS; i++){
			FileUtils.deleteDir("thread" + i + File.separatorChar + Application.TYPE_KEYSPACE + File.separatorChar 
					+ Application.TYPE_TABLE);
		}
	}
	
	public static void printDirs(){
		
	}
	

}
