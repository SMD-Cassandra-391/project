package output;

import java.io.File;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cass.Application;

/**
 * This class will execute the runnables required to generate and load the data
 * into the cluster.
 * @author slmyers
 *
 */
public class Stream {

	private static final int NTHREDS = 4;
	private File[] files;

	public Stream() {
		files = Application.getApp().createOutputDirs();
	}

	public void runStream() throws Exception {
		int seedValue = 1337;
		Random random = new Random(seedValue);
		int[] seeds = new int[NTHREDS];
		int i = 0;
		while (true) {
			for (int j = 0; j < NTHREDS; j++) {
				seeds[j] = random.nextInt();
			}
			stream(i, seeds);
			i++;
		}
	}

	/**
	 * creates multiple runnable to operate in four threads that each write data
	 * and then load the data. There is a bug that occurs only during the 2nd
	 * and 3rd iteration of runStream(). In this bug a null pointer exception is
	 * thrown for worker one on creating the writer. It is difficult to find the
	 * root of this bug and it's affect on the overall table structure in
	 * negligible.
	 * 
	 * @param i
	 *            this is the table type it is modded by the number of tables to
	 *            ensure that each table is written to an approximately equal
	 *            number of times.
	 * @throws Exception
	 */

	private void stream(int i, int[] seeds) throws Exception {

		ExecutorService dataExecutor = Executors.newFixedThreadPool(NTHREDS);
		/**
		 * which table we're currently working on
		 */
		int tableType = i % 3;
		Runnable worker1 = new RepeatDataThread(tableType,
				Application.THREAD_ONE_PATH + "t" + (tableType + 1), seeds[0]);
		Runnable worker2 = new RepeatDataThread(tableType,
				Application.THREAD_TWO_PATH + "t" + (tableType + 1), seeds[1]);
		Runnable worker3 = new RepeatDataThread(tableType,
				Application.THREAD_THREE_PATH + "t" + (tableType + 1), seeds[2]);
		Runnable worker4 = new RepeatDataThread(tableType,
				Application.THREAD_FOUR_PATH + "t" + (tableType + 1), seeds[3]);
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
		ExecutorService loadExecutor = Executors.newFixedThreadPool(NTHREDS);
		Runnable loadWorker1 = new LoadThread(Application.THREAD_ONE_PATH + "t" + (tableType + 1)); 
		Runnable loadWorker2 = new LoadThread(Application.THREAD_TWO_PATH + "t" + (tableType + 1));
		Runnable loadWorker3 = new LoadThread(Application.THREAD_THREE_PATH + "t" + (tableType + 1)); 
		Runnable loadWorker4 = new LoadThread(Application.THREAD_FOUR_PATH + "t" + (tableType + 1));
		loadExecutor.execute(loadWorker1); 
		loadExecutor.execute(loadWorker2);
		loadExecutor.execute(loadWorker3); 
		loadExecutor.execute(loadWorker4);
		loadExecutor.shutdown(); 
		loadExecutor.awaitTermination(40,TimeUnit.MINUTES);
		cleanDirs();

		System.out.println("Finished all threads");

	}
	
	/**
	 * creates table t4 and loads into cluster. Care must be taken with loading 
	 * data, because this method will throw a StaticInitializationException
	 * in one or more threads, but not all threads. This is why we must check 
	 * to see which file has data in it. 
	 * @param rows
	 */
	
	public void createT4(int rows) {
		ExecutorService dataExecutor = Executors.newFixedThreadPool(NTHREDS);
		Runnable worker1 = new RepeatDataThread(3, Application.THREAD_ONE_PATH
				+ "t4", 42);
		Runnable worker2 = new RepeatDataThread(3, Application.THREAD_TWO_PATH
				+ "t4", 42);
		Runnable worker3 = new RepeatDataThread(3,
				Application.THREAD_THREE_PATH + "t4", 42);
		Runnable worker4 = new RepeatDataThread(3, Application.THREAD_FOUR_PATH
				+ "t4", 42);
		dataExecutor.execute(worker1);
		dataExecutor.execute(worker2);
		dataExecutor.execute(worker3);
		dataExecutor.execute(worker4);
		dataExecutor.shutdown();
		try {
			dataExecutor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// find directory that contains data
		File[] files = new File[4];
		files[0] = new File(Application.THREAD_ONE_PATH + "t4");
		files[1] = new File(Application.THREAD_TWO_PATH + "t4");
		files[2] = new File(Application.THREAD_THREE_PATH + "t4");
		files[3] = new File(Application.THREAD_FOUR_PATH + "t4");
		File outputFile = null;

		for (File f : files) {
			if (FileUtils.folderSize(f) > 0) {
				outputFile = new File(f.toString());
			}
		}

		if (outputFile == null) {
			throw new RuntimeException(
					"unable to avoid initialization exception for table 4.");
		}
		
		ExecutorService loadExecutor = Executors.newFixedThreadPool(NTHREDS);
		Runnable loadWorker = new LoadThread(outputFile.toString());
		loadExecutor.execute(loadWorker);
		loadExecutor.shutdown();
		try {
			loadExecutor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cleanDirs();
	}

	/**
	 * creates table t5 and loads into cluster. Care must be taken with loading 
	 * data, because this method will throw a StaticInitializationException
	 * in one or more threads, but not all threads. This is why we must check 
	 * to see which file has data in it. 
	 * @param rows
	 */
	
	public void createT5(int rows) {
		ExecutorService dataExecutor = Executors.newFixedThreadPool(NTHREDS);
		Runnable worker1 = new RepeatDataThread(4, Application.THREAD_ONE_PATH
				+ "t5", 42);
		Runnable worker2 = new RepeatDataThread(4, Application.THREAD_TWO_PATH
				+ "t5", 42);
		Runnable worker3 = new RepeatDataThread(4,
				Application.THREAD_THREE_PATH + "t5", 42);
		Runnable worker4 = new RepeatDataThread(4, Application.THREAD_FOUR_PATH
				+ "t5", 42);
		dataExecutor.execute(worker1);
		dataExecutor.execute(worker2);
		dataExecutor.execute(worker3);
		dataExecutor.execute(worker4);
		dataExecutor.shutdown();
		try {
			dataExecutor.awaitTermination(1, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// find directory that contains data
		File[] files = new File[4];
		files[0] = new File(Application.THREAD_ONE_PATH + "t5");
		files[1] = new File(Application.THREAD_TWO_PATH + "t5");
		files[2] = new File(Application.THREAD_THREE_PATH + "t5");
		files[3] = new File(Application.THREAD_FOUR_PATH + "t5");
		File outputFile = null;

		for (File f : files) {
			if (FileUtils.folderSize(f) > 0) {
				outputFile = new File(f.toString());
			}
		}

		if (outputFile == null) {
			throw new RuntimeException(
					"unable to avoid initialization exception for table 5.");
		}
		
		ExecutorService loadExecutor = Executors.newFixedThreadPool(NTHREDS);
		Runnable loadWorker = new LoadThread(outputFile.toString());
		loadExecutor.execute(loadWorker);
		loadExecutor.shutdown();
		try {
			loadExecutor.awaitTermination(10, TimeUnit.MINUTES);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cleanDirs();
	}

	/**
	 * clears each applicable folder of SSTables. Called at the tail end of
	 * stream().
	 */

	public void cleanDirs() {
		for (File f : files) {
			FileUtils.deleteDir(f.toString());
		}
	}

}
