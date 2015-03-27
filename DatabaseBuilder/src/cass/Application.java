package cass;

import java.io.File;

import output.FileUtils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Application {
	private static Session session;
	private static Cluster cluster;
	private static Application app = null;
	public static final String DEMO = "DEMO";
	public static final String PROJ = "PROJ";
	public static final String DEMO_KEYSPACE = "demo_keyspace";
	public static final String DEMO_TABLE = "demo_table";
	public static final String PROJ_KEYSPACE = "proj_keyspace";
	public static final String PROJ_TABLE = "proj_table";
	public static final int NUM_COLS = 470;
	public static final int NUM_ROWS = 100;
	public static final String DATA_FOLDER = "data/";
	// used in the COPY file basically describes the rows in the csv file
	private static String TABLE_DESCR = null;
	private static String CREATE_STATMENT = null;
	private static String QUESTION_STRING = null;
	public static final String PATH_TO_DATA = DATA_FOLDER +  DEMO_KEYSPACE 
											  + File.separatorChar + DEMO_TABLE;
	public static File OUTPUT_DIR = null;
	
	protected Application(){
		// empty constructor
	}
	
	public static Application getApp(){
		if(app == null){
			app = new Application();
		}
		return app;
	}
	
	public void buildCluster(){
		cluster = Cluster.builder()
				  .addContactPoint("localhost")
				  .build();
		cluster.connect();
	}
	
	public void connectSession(){
		session = cluster.connect();
	}
	
	public Cluster getCluster(){
		return cluster;
	}
	
	public Session getSession(){
		return session;
	}
	
	public void closeCluster(){
		cluster.close();
	}
	
	public String getTableDesc(){
		return TABLE_DESCR;
	}
	
	public void setTableDesc(){
		TABLE_DESCR = FileUtils.getTableDesc();
	}


	public void setCreateStmnt() {
		CREATE_STATMENT = FileUtils.getCreateStmnt();
		
	}

	public String getCreateStmnt() {
		return CREATE_STATMENT;
	}
	
	public void buildQuestionString(){
		// 470 ?
		// 469 ,
		// 469 " "
		// means 1408 chars going to 1420 for extra safety
		StringBuilder sb = new StringBuilder(1420);
		for(int i = 0; i < Application.NUM_COLS - 1; i++){
			sb.append("?");
			sb.append(",");
			sb.append(" ");
		}
		sb.append("?");
		QUESTION_STRING = new String(sb.toString());
	}
	
	public String getQuestionString(){
		return QUESTION_STRING;
	}
	
	/*
	 * this is where the SST table folder structure is created need to change to allow 
	 * for uploading data to cassandra via sstableloader
	 */
	public void createOutputDir(){
		File outputDir = new File(Application.DATA_FOLDER + File.separator
				+ DEMO_KEYSPACE + File.separator + DEMO_TABLE);
		if (!outputDir.exists() && !outputDir.mkdirs()) {
			throw new RuntimeException("Cannot create output directory: "
					+ outputDir);
		}
	}
	public static String getProperty(String name, String defaultValue){		
		return System.getProperty(name) == null ? defaultValue : System.getProperty(name); 
	}	
}
