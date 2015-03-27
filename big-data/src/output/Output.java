package output;

import java.io.File;

import loader.JmxBulkLoader;
import cass.Application;
import cass.SSTwriter;
import cass.Setup;



public class Output {
	public static Application app;
	
	public static void main(String[] args) throws Exception {
		
		
		
		initApp();
		Thread tt = new Thread(){
			public void run(){
				Setup setup = new Setup(Application.DEMO);
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
		long start = System.currentTimeMillis();
		final SSTwriter writer = new SSTwriter(Application.NUM_ROWS); 
		Thread t = new Thread(){
			public void run(){
				writer.execute();
			}
		};
		t.start();
		try {
			t.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		File file = new File(Application.PATH_TO_DATA);
		final String path = file.getAbsolutePath();
		System.out.println("path is: " + path);
		JmxBulkLoader jmxLoader = new JmxBulkLoader("localhost", 7199);
		jmxLoader.bulkLoad(path);
		jmxLoader.close();
		long end = System.currentTimeMillis();
		//RunShell.SSTcmd(Application.DATA_FOLDER + File.separatorChar + Application.DEMO_KEYSPACE + File.separatorChar
		//		        + Application.DEMO_TABLE);
		
		
		System.out.println("Total time: " + (end - start)/1000 + " seconds.");
		System.exit(0);
	}

	public static void initApp(){
		app = Application.getApp();
		app.setTableDesc();
		app.setCreateStmnt();
		app.buildQuestionString();
		app.createOutputDir();
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
