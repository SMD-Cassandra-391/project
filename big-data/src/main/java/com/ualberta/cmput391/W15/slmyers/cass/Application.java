package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import main.java.com.ualberta.cmput391.W15.slmyers.pageWriter.FileUtils;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Application {
	private static Session session;
	private static Cluster cluster;
	private static Application app = null;
	public static final String DEMO = "DEMO";
	public static final String PROJ = "PROJ";
	public static final String DEMO_KEYSPACE = "DEMO_KEYSPACE";
	public static final String DEMO_TABLE = "DEMO_TABLE";
	public static final String PROJ_KEYSPACE = "PROJ_KEYSPACE";
	public static final String PROJ_TABLE = "PROJ_TABLE";
	// used in the COPY file basically describes the rows in the csv file
	private static String TABLE_DESCR = null;
	
	
	@SuppressWarnings("static-access")
	protected Application(){
		cluster = cluster.builder()
				  .addContactPoint("localhost")
				  .build();
		cluster.connect();
		session = cluster.connect();
	}
	
	protected Application(String test){
		// empty constructor
	}
	
	public static Application getApp(){
		if(app == null){
			app = new Application();
		}
		return app;
	}
	
	public static Application getTestApp(){
		if(app == null){
			app = new Application("test");
		}
		return app;
	}
	
	public Cluster getCluster(){
		return cluster;
	}
	
	public Session getSession(){
		return session;
	}
	
	public void closeCluster(){
		this.cluster.close();
	}
	
	public String getTableDesc(){
		return this.TABLE_DESCR;
	}
	
	public void setTableDesc(String tableDesc){
		this.TABLE_DESCR = FileUtils.getTableDesc();
	}
}
