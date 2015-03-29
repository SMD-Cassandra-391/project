package output;

import java.io.File;
import java.io.PrintWriter;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cass.Application;
import cass.Setup;



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
		/*
		Thread tt = new Thread(){
			public void run(){
				Setup setup = new Setup(Application.RUN_TYPE);
				setup.execute();
			}
		};
		tt.start();
		try {
			tt.join();
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		*/
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
		app.setTableDesc();
		app.setCreateStmnt();
		app.buildQuestionString();
		app.setType(type);
		app.createOutputDirs();
	}
	
	public static void run() throws Exception{
		ExecutorService executor = Executors.newFixedThreadPool(NTHREDS);
		Runnable worker1 = new DataThread("thread0", "thread0/" + File.separator
				+ Application.TYPE_KEYSPACE + File.separator + Application.TYPE_TABLE);
		Runnable worker2 = new DataThread("thread1", "thread1/" + File.separator
				+ Application.TYPE_KEYSPACE + File.separator + Application.TYPE_TABLE);
		Runnable worker3 = new DataThread("thread2", "thread2/" + File.separator
				+ Application.TYPE_KEYSPACE + File.separator + Application.TYPE_TABLE);
		Runnable worker4 = new DataThread("thread3", "thread3/" + File.separator
				+ Application.TYPE_KEYSPACE + File.separator + Application.TYPE_TABLE);
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
