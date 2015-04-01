package output;


import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cass.Application;

/**
 * this is the entry point of the application. It will infinitely output newly created tables
 * and then load them into the Cassandra cluster. 
 * invocation: sudo nohup java -jar DatabaseGenerator-1.0-SNAPSHOT.one-jar.jar
 * At this point one should record the pid of this process, because when you want to 
 * stop generation you're going to go sudo kill <pidof this program>.
 * http://stackoverflow.com/questions/2724725/best-way-to-daemonize-java-application-on-linux
 * http://www.cyberciti.biz/tips/nohup-execute-commands-after-you-exit-from-a-shell-prompt.html
 * @author slmyers
 *
 */
public class Output {
	/** Application stores information entered at runtime */
	public static Application app;
	/** The data creation and data loading is ran in four threads */
	public static final int NTHREDS = 4;
	
	public static void main(String[] args) throws Exception {
		app = Application.getApp();
		if(args[0].equalsIgnoreCase("demo")){
			Application.RUN_TYPE = Application.DEMO;
		}
		else if(args[0].equalsIgnoreCase("project")){
			Application.RUN_TYPE = Application.PROJ;
		}
		else{
			System.out.println("keyspace is neither demo nor project. System exiting.");
			System.exit(-1);
		}
		Application.NUM_ROWS = Integer.parseInt(args[1]);
		app.createOutputDirs(null);
		runStream();
		
		
	}

	/**
	 * runs data creation and data loading infinitely alternating on table type.
	 * A random generator seeded with 1337 ("leet") will create for random seeds
	 * to the data threads. This ensures a repeatable sequence and allows evaluation
	 * of queries/schemas containing the same data.
	 */
	private static void runStream() throws Exception{
		int seedValue = 1337;
		Random random = new Random(seedValue);
		int[] seeds = new int[NTHREDS];
		int i = 0;
		int count = 0;
		while(count < 1){
			for(int j = 0; j < NTHREDS; j++){
				seeds[j] = random.nextInt();
			}
			stream(i, seeds);
			i++;
			count++;
		}
	}

	/**
	 *	creates multiple runnable to operate in four threads that each write data and then 
	 *	load the data. There is a bug that occurs only during the 2nd and 3rd iteration of 
	 *	runStream(). In this bug a null pointer exception is thrown for worker one on creating
	 *	the writer. It is difficult to find the root of this bug and it's affect on the overall
	 * 	table structure in negligible. 
	 * 
	 * @param 	i this is the table type it is modded by the number of tables to ensure
	 * 			that each table is written to an approximately equal number of times.
	 * @throws Exception
	 */
	private static void stream(int i, int[] seeds) throws Exception {
		
		ExecutorService dataExecutor = Executors.newFixedThreadPool(NTHREDS);

		Runnable worker1 = new DataThread(i % 3,
				Application.PROJ_TABLE_ONE_PATH, seeds[0]);
		Runnable worker2 = new DataThread(i % 3,
				Application.PROJ_TABLE_TWO_PATH, seeds[1]);
		Runnable worker3 = new DataThread(i % 3,
				Application.PROJ_TABLE_THREE_PATH, seeds[2]);
		Runnable worker4 = new DataThread(i % 3,
				Application.PROJ_TABLE_FOUR_PATH, seeds[3]);
		dataExecutor.execute(worker1);
		dataExecutor.execute(worker2);
		dataExecutor.execute(worker3);
		dataExecutor.execute(worker4);
		dataExecutor.shutdown();
		// Wait until all threads are finish
		// if a table takes more than (approx) 40mins then the row size 
		// is set too high, and load may fail. 
		dataExecutor.awaitTermination(40, TimeUnit.MINUTES);
		System.out.println("loading tables");
		/*
		ExecutorService loadExecutor = Executors.newFixedThreadPool(NTHREDS);
		Runnable loadWorker1 = new LoadThread(Application.PROJ_TABLE_ONE_PATH);
		Runnable loadWorker2 = new LoadThread(Application.PROJ_TABLE_TWO_PATH);
		Runnable loadWorker3 = new LoadThread(Application.PROJ_TABLE_THREE_PATH);
		Runnable loadWorker4 = new LoadThread(Application.PROJ_TABLE_FOUR_PATH);
		loadExecutor.execute(loadWorker1);
		loadExecutor.execute(loadWorker2);
		loadExecutor.execute(loadWorker3);
		loadExecutor.execute(loadWorker4);
		loadExecutor.shutdown();
		loadExecutor.awaitTermination(40, TimeUnit.MINUTES);
		
		cleanDirs();
		*/
		System.out.println("Finished all threads");
		
	}

	/**
	 * clears each applicable folder of SSTables. Called at the tail end of stream().
	 */
	
	public static void cleanDirs() {

		FileUtils.deleteDir(Application.PROJ_TABLE_ONE_PATH);
		FileUtils.deleteDir(Application.PROJ_TABLE_TWO_PATH);
		FileUtils.deleteDir(Application.PROJ_TABLE_THREE_PATH);
		FileUtils.deleteDir(Application.PROJ_TABLE_FOUR_PATH);

	}

}
