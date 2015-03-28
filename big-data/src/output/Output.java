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
	public static final int NTHREDS = 10;
	
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
		
		File f1 = new File("thread1/test_keyspace/test_table");
		System.out.println("Thread 1 size: " + f1.length()/1048576.00);

		File f2 = new File("thread2/test_keyspace/test_table");
		System.out.println("Thread 2 size: " + f2.length()/1048576.00);

		File f3 = new File("thread3/test_keyspace/test_table");
		System.out.println("Thread 3 size: " + f3.length()/1048576.00);

		File f4 = new File("thread4/test_keyspace/test_table");
		System.out.println("Thread 4 size: " + f4.length()/1048576.00);

		System.out.println("Grand Total :" + (f1.length() + f2.length() + f3.length() + f4.length())/1048576.00);
		
		FileUtils.deleteDir("thread1/test_keyspace/test_table");
		FileUtils.deleteDir("thread2/test_keyspace/test_table");
		FileUtils.deleteDir("thread3/test_keyspace/test_table");
		FileUtils.deleteDir("thread4/test_keyspace/test_table");
		
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
		Runnable worker1 = new DataThread("thread1", "thread1/" + File.separator
				+ Application.TYPE_KEYSPACE + File.separator + Application.TYPE_TABLE);
		Runnable worker2 = new DataThread("thread2", "thread2/" + File.separator
				+ Application.TYPE_KEYSPACE + File.separator + Application.TYPE_TABLE);
		Runnable worker3 = new DataThread("thread3", "thread3/" + File.separator
				+ Application.TYPE_KEYSPACE + File.separator + Application.TYPE_TABLE);
		Runnable worker4 = new DataThread("thread4", "thread4/" + File.separator
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
	
	
	private static void purgeDirectory(String dataFolder) {
		FileUtils.deleteDir(dataFolder);
	}

	public static void printStartMsg() {
		
	}

	/*
	 * long size = FileUtils.sizeOfDirectory(new File("C:/Windows/folder"));
	 * 
	 * System.out.println("Folder Size: " + size + " bytes");
	 */
	public static void printEndMsg(long totalTime) {
		
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
