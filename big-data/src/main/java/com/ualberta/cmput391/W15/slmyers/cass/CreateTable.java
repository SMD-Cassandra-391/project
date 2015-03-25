package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class CreateTable {
	public static final String DEMO_KEYSPACE = "DEMO_KEYSPACE";
	public static final String DEMO_TABLE = "DEMO_TABLE";
	public static final String PROJ_KEYSPACE = "PROJ_KEYSPACE";
	public static final String PROJ_TABLE = "PROJ_TABLE";
	private String type;
	private Cluster cluster;
	private Session session;
	private static final String TABLE_STRING = "";

	
	public CreateTable(String type){
		this.type = new String(type);
		cluster.builder()
			   .addContactPoint("localhost")
			   .build();
		cluster.connect();
	}
	
	public void execute(){
		
	}
	
	public void readTableString(){
		
	}
	
}
