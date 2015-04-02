package cass;

import java.io.File;


public class Application {
	
	private static Application app = null;
	public static final String DEMO = "demo";
	public static final String PROJ = "project";
	public static int NUM_ROWS = 10000;
	public static final String PROJ_THREAD_ONE_PATH = "data/" + "thread0" + File.separator + "project" + File.separator;
	public static final String PROJ_THREAD_TWO_PATH =  "data/" +"thread1" + File.separator + "project" + File.separator;
	public static final String PROJ_THREAD_THREE_PATH =  "data/" +"thread2" + File.separator + "project" + File.separator;
	public static final String PROJ_THREAD_FOUR_PATH =  "data/" +"thread3" + File.separator + "project" + File.separator;
	
	public static final String DEMO_THREAD_ONE_PATH = "data/" + "thread0" + File.separator + "demo" + File.separator;
	public static final String DEMO_THREAD_TWO_PATH =  "data/" +"thread1" + File.separator + "demo" + File.separator;
	public static final String DEMO_THREAD_THREE_PATH =  "data/" +"thread2" + File.separator + "demo" + File.separator;
	public static final String DEMO_THREAD_FOUR_PATH =  "data/" +"thread3" + File.separator + "demo" + File.separator;
	
	public static String THREAD_ONE_PATH = null;
	public static String THREAD_TWO_PATH = null;
	public static String THREAD_THREE_PATH = null;
	public static String THREAD_FOUR_PATH = null;
	
	public static String RUN_TYPE = null;
	protected Application(){
		// empty constructor
	}
	/**
	 * singleton pattern
	 */
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
	public File[] createOutputDirs(){
		/**
		 * each thread needs an output folder for each distinct table
		 */
		File[] files = new File[20];
		if(RUN_TYPE == PROJ){
			/**
			 * each thread needs a specific folder for each SSTable
			 * the file format should adhere to ...../keyspace/tablename
			 * conventions. 
			 */
			files[0] = new File(PROJ_THREAD_ONE_PATH + "t1");
			files[1] = new File(PROJ_THREAD_ONE_PATH + "t2");
			files[2] = new File(PROJ_THREAD_ONE_PATH + "t3");
			files[3] = new File(PROJ_THREAD_ONE_PATH + "t4");
			files[4] = new File(PROJ_THREAD_ONE_PATH + "t5");
			
			files[5] = new File(PROJ_THREAD_TWO_PATH + "t1");
			files[6] = new File(PROJ_THREAD_TWO_PATH + "t2");
			files[7] = new File(PROJ_THREAD_TWO_PATH + "t3");
			files[8] = new File(PROJ_THREAD_TWO_PATH + "t4");
			files[9] = new File(PROJ_THREAD_TWO_PATH + "t5");
			
			files[10] = new File(PROJ_THREAD_THREE_PATH + "t1");
			files[11] = new File(PROJ_THREAD_THREE_PATH + "t2");
			files[12] = new File(PROJ_THREAD_THREE_PATH + "t3");
			files[13] = new File(PROJ_THREAD_THREE_PATH + "t4");
			files[14] = new File(PROJ_THREAD_THREE_PATH + "t5");
			
			files[15] = new File(PROJ_THREAD_FOUR_PATH + "t1");
			files[16] = new File(PROJ_THREAD_FOUR_PATH + "t2");
			files[17] = new File(PROJ_THREAD_FOUR_PATH + "t3");
			files[18] = new File(PROJ_THREAD_FOUR_PATH + "t4");
			files[19] = new File(PROJ_THREAD_FOUR_PATH + "t5");
			
			THREAD_ONE_PATH = PROJ_THREAD_ONE_PATH;
			THREAD_TWO_PATH = PROJ_THREAD_TWO_PATH;
			THREAD_THREE_PATH = PROJ_THREAD_THREE_PATH;
			THREAD_FOUR_PATH = PROJ_THREAD_FOUR_PATH;
			
			
		}
		else if (RUN_TYPE == DEMO){
			/**
			 * each thread needs a specific folder for each SSTable
			 * the file format should adhere to ...../keyspace/tablename
			 * conventions. 
			 */
			files[0] = new File(DEMO_THREAD_ONE_PATH + "t1");
			files[1] = new File(DEMO_THREAD_ONE_PATH + "t2");
			files[2] = new File(DEMO_THREAD_ONE_PATH + "t3");
			files[3] = new File(DEMO_THREAD_ONE_PATH + "t4");
			files[4] = new File(DEMO_THREAD_ONE_PATH + "t5");
			
			files[5] = new File(DEMO_THREAD_TWO_PATH + "t1");
			files[6] = new File(DEMO_THREAD_TWO_PATH + "t2");
			files[7] = new File(DEMO_THREAD_TWO_PATH + "t3");
			files[8] = new File(DEMO_THREAD_TWO_PATH + "t4");
			files[9] = new File(DEMO_THREAD_TWO_PATH + "t5");
			
			files[10] = new File(DEMO_THREAD_THREE_PATH + "t1");
			files[11] = new File(DEMO_THREAD_THREE_PATH + "t2");
			files[12] = new File(DEMO_THREAD_THREE_PATH + "t3");
			files[13] = new File(DEMO_THREAD_THREE_PATH + "t4");
			files[14] = new File(DEMO_THREAD_THREE_PATH + "t5");
			
			files[15] = new File(DEMO_THREAD_FOUR_PATH + "t1");
			files[16] = new File(DEMO_THREAD_FOUR_PATH + "t2");
			files[17] = new File(DEMO_THREAD_FOUR_PATH + "t3");
			files[18] = new File(DEMO_THREAD_FOUR_PATH + "t4");
			files[19] = new File(DEMO_THREAD_FOUR_PATH + "t5");
			
			
			THREAD_ONE_PATH = DEMO_THREAD_ONE_PATH;
			THREAD_TWO_PATH = DEMO_THREAD_TWO_PATH;
			THREAD_THREE_PATH = DEMO_THREAD_THREE_PATH;
			THREAD_FOUR_PATH = DEMO_THREAD_FOUR_PATH;
			
		}
		
		for(File f : files){
			if(!f.exists() && !f.mkdirs()){
				throw new RuntimeException("Cannot create output directory: "
						+ f);
			}
		}
		
		return files;
	}
}
