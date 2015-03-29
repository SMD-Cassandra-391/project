package cass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import objectGenerator.ObjectGenerator;

import org.apache.cassandra.config.Config;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;

public class SSTwriter {
	private ObjectListGenerator gen;
	private int numRows;
	private CQLSSTableWriter writer;
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * INSERT statement to bulk load. It is like prepared statement. You fill in
	 * place holder for each data.
	 */
	

	public SSTwriter(int numRows, String folder, int threadId) {
		this.gen = null;
		this.numRows = numRows;
		gen = new ObjectListGenerator(threadId);
		String schemaToUse = null;
		String insertToUse = null;
		switch(threadId){
			case 1:
				schemaToUse = Schemas.SCHEMA1;
				insertToUse = Schemas.INSERT1;
				break;
			case 2:
				schemaToUse = Schemas.SCHEMA2;
				insertToUse = Schemas.INSERT2;
				break;
			case 3:
				schemaToUse = Schemas.SCHEMA3;
				insertToUse = Schemas.INSERT3;
				break;
			case 4:
				schemaToUse = Schemas.SCHEMA4;
				insertToUse = Schemas.INSERT4;
				break;
			default:
				System.out.println("unkown schema type");
	
		}
		System.out.println(schemaToUse);
		System.out.println(folder);
		// magic
		Config.setClientMode(true);
		// Prepare SSTable writer
		CQLSSTableWriter.Builder builder = CQLSSTableWriter.builder();
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
		writer = builder.build();

	}

	public void execute() {
		System.out.println("creating " + this.numRows + " rows");
		for (int i = 0; i < this.numRows; i++) {
			try {
				writer.addRow(gen.genRow());
			} catch (InvalidRequestException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClassCastException ce){
				ce.printStackTrace();
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
		System.out.println("writer is closed");
	}



}
