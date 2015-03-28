package cass;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.datastax.driver.core.Session;

public class Setup {
	private static final String SQL_CREATE_TABLE = 
			"resources" + File.separatorChar +  "bigdata_setup1.sql";
	
	private String type;
	private Session session;
	private String createStatement;

	
	public Setup(String type){
		this.type = new String(type);
		this.readTableString();
	}
	
	public void execute(){
		Application.getApp().buildCluster();
		Application.getApp().connectSession();
		session = Application.getApp().getSession();
		createKeyspace();
    	createTable();
    	Application.getApp().closeCluster();
	}
	
	public void dropKeyspace(){
		if(type.equals(Application.DEMO)){
			session.execute("DROP KEYSPACE " + Application.DEMO_KEYSPACE + ";");
		}else{
			session.execute("DROP KEYSPACE " + Application.PROJ_KEYSPACE + ";");
		}
	}
	
	public void dropTable(){
		session.execute("DROP TABLE " + Application.TYPE_KEYSPACE + 
						"." + Application.TYPE_TABLE + ";");
		
	}
	
	public void createKeyspace(){
		String stmnt = "CREATE KEYSPACE IF NOT EXISTS " + Application.TYPE_KEYSPACE
				+ " WITH REPLICATION = { 'class' : 'SimpleStrategy', 'replication_factor' : 1 };";
		System.out.println("Keyspace: " + stmnt);
		session.execute(stmnt);
		
		
	}
	
	public void createTable(){
		String stmnt = "CREATE TABLE IF NOT EXISTS " + Application.TYPE_KEYSPACE + "." + Application.TYPE_TABLE + " ("
				+ this.createStatement + ");";
		System.out.println("Table: " + stmnt);
		session.execute(stmnt);
		
		
	}
	
	public void readTableString(){
		try {
			createStatement = new String(Files.readAllBytes(Paths.get(SQL_CREATE_TABLE)), StandardCharsets.UTF_8);
		} catch (IOException e) {
			System.out.println("unable to find required file: " + SQL_CREATE_TABLE);
		}
	}
	
}
