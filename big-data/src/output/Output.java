package output;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import cass.Application;
import cass.SSTwriter;

//nohup java -jar MyJar &
//http://stackoverflow.com/questions/2724725/best-way-to-daemonize-java-application-on-linux
//http://www.cyberciti.biz/tips/nohup-execute-commands-after-you-exit-from-a-shell-prompt.html
public class Output {
	public static Application app;
	public static final int NTHREDS = 4;

	public static void main(String[] args) throws Exception {
		String type;
		type = args[0];
		Application.NUM_ROWS = Integer.parseInt(args[1]);
		initApp(type);
		runStream();
		
		
	}

	private static void initApp(String type) {
		app = Application.getApp();
		if(type.equalsIgnoreCase("stream")){
			app.createOutputDirs(null);
		}
	}
	
	private static void runStream() throws Exception{
		int i = 0;
		while(true){
			stream(i);
			i++;
		}
	}

	private static void stream(int i) throws Exception {
		
		ExecutorService dataExecutor = Executors.newFixedThreadPool(NTHREDS);

		Runnable worker1 = new DataThread(i % 4,
				Application.PROJ_TABLE_ONE_PATH);
		Runnable worker2 = new DataThread(i % 4,
				Application.PROJ_TABLE_TWO_PATH);
		Runnable worker3 = new DataThread(i % 4,
				Application.PROJ_TABLE_THREE_PATH);
		Runnable worker4 = new DataThread(i % 4,
				Application.PROJ_TABLE_FOUR_PATH);
		dataExecutor.execute(worker1);
		dataExecutor.execute(worker2);
		dataExecutor.execute(worker3);
		dataExecutor.execute(worker4);
		dataExecutor.shutdown();
		// Wait until all threads are finish
		// if a table takes more than (approx) 40mins to write then 
		// a 'pipe is broken' failure often occurs during transfer
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
		*/
		cleanDirs();
		System.out.println("Finished all threads");
		
	}

	public static void cleanDirs() {

		FileUtils.deleteDir(Application.PROJ_TABLE_ONE_PATH);
		FileUtils.deleteDir(Application.PROJ_TABLE_TWO_PATH);
		FileUtils.deleteDir(Application.PROJ_TABLE_THREE_PATH);
		FileUtils.deleteDir(Application.PROJ_TABLE_FOUR_PATH);

	}

}
