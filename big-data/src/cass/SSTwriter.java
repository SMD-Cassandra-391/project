package cass;

import java.io.IOException;

import org.apache.cassandra.config.Config;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;

public class SSTwriter {
	
	private ObjectListGenerator gen;
	private int numRows;
	private CQLSSTableWriter writer;
	private int id;
	
	
	public SSTwriter(int numRows, String folder, int threadId, int seed) {
		// magic
		Config.setClientMode(true);
		this.gen = null;
		this.numRows = numRows;
		gen = new ObjectListGenerator(threadId, seed);
		if(gen == null){
			System.out.println("gen is null");
		}
		String schemaToUse = null;
		this.id = threadId;
		String insertToUse = null;
		switch(threadId){
			case 0:
				if(Application.RUN_TYPE.equalsIgnoreCase(Application.DEMO)){
					schemaToUse = Schemas.SCHEMA_1_DEMO;
					insertToUse = Inserts.INSERT_1_DEMO;
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.PROJ)){
					schemaToUse = Schemas.SCHEMA_1_PROJ;
					insertToUse = Inserts.INSERT_1_PROJ;
				}
				break;
			case 1:
				if(Application.RUN_TYPE.equalsIgnoreCase(Application.DEMO)){
					schemaToUse = Schemas.SCHEMA_2_DEMO;
					insertToUse = Inserts.INSERT_2_DEMO;
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.PROJ)){
					schemaToUse = Schemas.SCHEMA_2_PROJ;
					insertToUse = Inserts.INSERT_2_PROJ;
				}
				break;
			case 2:
				if(Application.RUN_TYPE.equalsIgnoreCase(Application.DEMO)){
					schemaToUse = Schemas.SCHEMA_3_DEMO;
					insertToUse = Inserts.INSERT_3_DEMO;
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.PROJ)){
					schemaToUse = Schemas.SCHEMA_3_PROJ;
					insertToUse = Inserts.INSERT_3_PROJ;
				}
				break;
			default:
				System.out.println("unkown schema type");
				System.exit(-1);
	
		}
		
		System.out.println(insertToUse);
		CQLSSTableWriter.Builder builder = null;
		try{
		// Prepare SSTable writer
		builder = CQLSSTableWriter.builder();
		// set output directory
		builder.inDirectory(folder)
		// set target schema
				.forTable(schemaToUse)
				// set CQL statement to put data
				.using(insertToUse)
				// set partitioner if needed
				// default is Murmur3Partitioner so set if you use different
				// one.
				.withPartitioner(new Murmur3Partitioner());
		}catch(Exception e){
			System.out.println(threadId);
			System.out.println(folder);
		}
		writer = builder.build();

	}

	public void execute() {
		System.out.println("creating " + this.numRows + " rows for " + this.id);
		for (int i = 0; i < this.numRows; i++) {
			try {
				writer.addRow(gen.genRow());
			} catch (InvalidRequestException | IOException | NullPointerException e) {
				if(gen == null){
					System.out.println("generator is null");
				}
				return;
				
			} 
		}
	}
	
	public void close(){
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



}
