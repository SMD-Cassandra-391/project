package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Session;

public class Setup {
	private static final String SQL_CREATE_TABLE = 
			"resources" + File.separatorChar +  "bigdata_setup1.sql";
	
	private String type;
	private Session session;
	private String createStatement;

	
	public Setup(String type){
		this.type = new String(type);
		if(!(type.equals(Application.DEMO) || type.equals(Application.PROJ))){
			throw new RuntimeException("Setup.type must equal " + Application.DEMO
										+" or " + Application.PROJ);
		}
		session = Application.getApp().getSession();
		
		this.readTableString();
	}
	
	public void execute(){
		createKeyspace();
    	createTable();
	}
	
	public void dropKeyspace(){
		if(type.equals(Application.DEMO)){
			session.execute("DROP KEYSPACE " + Application.DEMO_KEYSPACE + ";");
		}else{
			session.execute("DROP KEYSPACE " + Application.PROJ_KEYSPACE + ";");
		}
	}
	
	public void dropTable(){
		if(type.equals(Application.DEMO)){
			session.execute("DROP TABLE " + Application.DEMO + 
							"." + Application.DEMO_TABLE + ";");
		}else{
			session.execute("DROP KEYSPACE " + Application.PROJ +
							"." + Application.PROJ_TABLE + ";");
		}
	}
	
	public void createKeyspace(){
		
		if(type.equals(Application.DEMO)){
			session.execute("CREATE KEYSPACE IF NOT EXISTS " + Application.DEMO_KEYSPACE
							+ " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
		}else{
			session.execute("CREATE KEYSPACE IF NOT EXISTS " + Application.PROJ_KEYSPACE
							+ " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };");
		}
		
	}
	
	public void createTable(){
		
		if(type.equals(Application.DEMO)){
			session.execute("CREATE TABLE IF NOT EXISTS " + Application.DEMO_KEYSPACE + "." + Application.DEMO_TABLE + " ("
							+ this.createStatement + ");");
		}else{
			session.execute("CREATE TABLE IF NOT EXISTS " + Application.PROJ_KEYSPACE + "." + Application.PROJ_TABLE + " "
							+ this.createStatement + ");");
		}
		
	}
	
	public void readTableString(){
		try {
			createStatement = new String(Files.readAllBytes(Paths.get(SQL_CREATE_TABLE)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("unable to find required file: " + SQL_CREATE_TABLE);
		}
	}
	
}
