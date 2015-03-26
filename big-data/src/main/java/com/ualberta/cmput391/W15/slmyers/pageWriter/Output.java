package main.java.com.ualberta.cmput391.W15.slmyers.pageWriter;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import main.java.com.ualberta.cmput391.W15.slmyers.cass.Application;
import main.java.com.ualberta.cmput391.W15.slmyers.cass.Setup;

public class Output {
	
	public static final String DATA_DIR = "data";
	public static final String SHELL_DIR = "shell";

	public static void main(String[] args) {
		int numThreads = Runtime.getRuntime().availableProcessors();
		numThreads = 8;
		
		long startTime = 0;
		long endTime = 0;
		printStartMsg();
		System.out.println("spawning " + numThreads + " threads.");
		FileUtils.deleteDir(DATA_DIR);
		FileUtils.createDir(DATA_DIR);
		FileUtils.deleteDir(SHELL_DIR);
		FileUtils.createDir(SHELL_DIR);
		final Setup s = new Setup(Application.DEMO);
		Thread t2 = new Thread() {
			public void run() {
				Application.getApp().setTableDesc();
				final String tableDesc = Application.getApp().getTableDesc();
				s.execute();
			}
		};
		t2.start();
		try {
			t2.join();
		} catch (InterruptedException e1) {
			// dont know what error msg to print
			e1.printStackTrace();
		}
		String table = Application.getApp().getTableDesc();
		if ( table == null) {
			throw new RuntimeException(
					"error with resources/bigdata_setup1.sql");
		}

		startTime = System.currentTimeMillis();

		ExecutorService executor = Executors.newFixedThreadPool(numThreads);
		for (int i = 0; i < numThreads; i++) {
			// filler is a placeholder for when I can actually implement
			// DataThread
			Runnable worker = new DataThread(table, Application.DEMO);
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

		FileUtils.deleteDir(Output.DATA_DIR);
		FileUtils.deleteDir(Output.SHELL_DIR);

	}

	public static void printStartMsg() {
		System.out.println("**********************************");
		System.out.println("*  begining data generation      *");
		System.out.println("**********************************");
	}

	/*
	 * long size = FileUtils.sizeOfDirectory(new File("C:/Windows/folder"));
	 * 
	 * System.out.println("Folder Size: " + size + " bytes");
	 */

	public static void printEndMsg(long totalTime) {
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
