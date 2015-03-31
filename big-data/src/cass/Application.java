package cass;

import java.io.File;


public class Application {
	
	private static Application app = null;
	public static final String DEMO = "demo";
	public static final String PROJ = "project";
	public static int NUM_ROWS = 10000;
	public static final String PROJ_TABLE_ONE_PATH = "data/" + "thread0" + File.separator + "project" + File.separator + "t1";
	public static final String PROJ_TABLE_TWO_PATH =  "data/" +"thread1" + File.separator + "project" + File.separator+ "t2";
	public static final String PROJ_TABLE_THREE_PATH =  "data/" +"thread2" + File.separator + "project" + File.separator + "t3";
	public static final String PROJ_TABLE_FOUR_PATH =  "data/" +"thread3" + File.separator + "project" + File.separator + "t4";
	
	public static final String PRAC_TABLE_ONE_PATH = "thread0" + File.separator + "practice" + File.separator + "t1";
	public static final String PRAC_TABLE_TWO_PATH = "thread1" + File.separator + "practice" + File.separator+ "t2";
	public static final String PRAC_TABLE_THREE_PATH = "thread2" + File.separator + "practice" + File.separator + "t3";
	public static final String PRAC_TABLE_FOUR_PATH = "thread3" + File.separator + "practice" + File.separator + "t4";
	
	public static String RUN_TYPE = null;
	public static String TYPE_KEYSPACE = null;
	public static String TYPE_TABLE = null;
	protected Application(){
		// empty constructor
	}
	
	public static Application getApp(){
		if(app == null){
			app = new Application();
		}
		return app;
	}
	/*
	 * this is where the SST table folder structure is created need to change to allow 
	 * for uploading data to cassandra via sstableloader
	 */
	public void createOutputDirs(File[] files){
		// number of tables in keyspace
		// look at cass.Schemas for more information
		
		if(files == null){
			files = new File[4];
			files[0] = new File(PROJ_TABLE_ONE_PATH);
			files[1] = new File(PROJ_TABLE_TWO_PATH);
			files[2] = new File(PROJ_TABLE_THREE_PATH);
			files[3] = new File(PROJ_TABLE_FOUR_PATH);
		}
		for(File f : files){
			if(!f.exists() && !f.mkdirs()){
				throw new RuntimeException("Cannot create output directory: "
						+ f);
			}
		}
	}
}
