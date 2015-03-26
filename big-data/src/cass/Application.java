package cass;

import output.FileUtils;

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
	protected Application(){}
	
	
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
		this.cluster.close();
	}
	
	public String getTableDesc(){
		return this.TABLE_DESCR;
	}
	
	public void setTableDesc(){
		this.TABLE_DESCR = FileUtils.getTableDesc();
	}
}
