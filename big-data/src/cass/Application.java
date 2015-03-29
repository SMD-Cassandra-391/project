package cass;

import java.io.File;


public class Application {
	
	private static Application app = null;
	public static final String DEMO = "demo";
	public static final String PROJ = "project";
	public static int NUM_ROWS = 10000;
	public static final String PROJ_TABLE_ONE_PATH = "thread0" + File.separator + "project" + File.separator + "t1";
	public static final String PROJ_TABLE_TWO_PATH = "thread1" + File.separator + "project" + File.separator+ "t2";
	public static final String PROJ_TABLE_THREE_PATH = "thread2" + File.separator + "project" + File.separator + "t3";
	public static final String PROJ_TABLE_FOUR_PATH = "thread3" + File.separator + "project" + File.separator + "t4";
	
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
	public void createOutputDirs(){
		// number of tables in keyspace
		// look at cass.Schemas for more information
		int numOfTables = 4;
		File[] files = new File[numOfTables];
		files[0] = new File(PRAC_TABLE_ONE_PATH);
		files[1] = new File(PRAC_TABLE_TWO_PATH);
		files[2] = new File(PRAC_TABLE_THREE_PATH);
		files[3] = new File(PRAC_TABLE_FOUR_PATH);
		
		for(int i = 0; i < numOfTables; i++){
			if(!files[i].exists() && !files[i].mkdirs()){
				throw new RuntimeException("Cannot create output directory: "
						+ files[i]);
			}
		}
	}
}
