package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.apache.cassandra.config.Config;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

public class SSTloader {
	private static String createStatement;
	private static String tableDesc;
	private static String questionString;
	
	/** Default output directory */
    public static final String DEFAULT_OUTPUT_DIR = "./SSTdata";
    
    /** Keyspace name */
    public static final String KEYSPACE = "demo";
    /** Table name */
    public static final String TABLE = "demo_table";
    
    public static final String SCHEMA = "CREATE TABLE IF NOT EXISTS " + Application.DEMO_KEYSPACE + "." + Application.DEMO_TABLE + " ("
										+ createStatement + ");";

	/**
	* INSERT statement to bulk load.
	* It is like prepared statement. You fill in place holder for each data.
	*/
    public static final String INSERT_STMT = String.format("INSERT INTO " +  Application.DEMO_KEYSPACE + 
    										"." + Application.DEMO_TABLE +" (" 
    										+ tableDesc + ") VALUES (" + questionString + ")");
    
    public SSTloader(String createStatement, String tableDesc, String questionString){
    	this.createStatement = new String(createStatement);
    	this.tableDesc = new String(tableDesc);
    	this.questionString = new String(questionString);
    }

    public void execute(){
    	Config.setClientMode(true);
    	
    	File outputDir = new File(DEFAULT_OUTPUT_DIR + File.separator + KEYSPACE + File.separator + TABLE);
        if (!outputDir.exists() && !outputDir.mkdirs())
        {
            throw new RuntimeException("Cannot create output directory: " + outputDir);
        }
        
        // Prepare SSTable writer
        CQLSSTableWriter.Builder builder = CQLSSTableWriter.builder();
        // set output directory
        builder.inDirectory(outputDir)
               // set target schema
               .forTable(SCHEMA)
               // set CQL statement to put data
               .using(INSERT_STMT)
               // set partitioner if needed
               // default is Murmur3Partitioner so set if you use different one.
               .withPartitioner(new Murmur3Partitioner());
        CQLSSTableWriter writer = builder.build();
        BufferedReader reader = null;
		try {
			reader = new BufferedReader(new FileReader("data" + File.separatorChar + "data.csv"));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        CsvListReader csvReader = new CsvListReader(reader, CsvPreference.STANDARD_PREFERENCE);
        
        // Write to SSTable while reading data
        List<String> line;
        try {
			while ((line = csvReader.read()) != null){
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

}
