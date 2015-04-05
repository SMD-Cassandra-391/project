package cass;

import java.io.IOException;

import objectGenerator.IntGenerator;
import objectGenerator.PhGenerator;

import org.apache.cassandra.config.Config;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;

public class SSTwriter {
	
	private ObjectListGenerator gen;
	private int numRows;
	private CQLSSTableWriter writer;
	private int tableId;
	private int seed;
	
	public SSTwriter(int numRows, String folder, int tableId, int seed) {

		// magic
		Config.setClientMode(true);
		this.gen = null;
		this.numRows = numRows;
		this.seed = seed;
		gen = new ObjectListGenerator(tableId, seed);
		if(gen == null){
			System.out.println("gen is null");
		}
		String schemaToUse = null;
		String insertToUse = null;
		this.tableId = tableId;
		switch(tableId){
			case 0:
				if(Application.RUN_TYPE.equalsIgnoreCase(Application.DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_1_DEMO);
					insertToUse = new String(Inserts.INSERT_1_DEMO);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_1_PROJ);
					insertToUse = new String(Inserts.INSERT_1_PROJ);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_1_MOD_PROJ);
					insertToUse = new String(Inserts.INSERT_1_MOD_PROJ);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_1_MOD_DEMO);
					insertToUse = new String(Inserts.INSERT_1_MOD_DEMO);
				}
				break;
			case 1:
				if(Application.RUN_TYPE.equalsIgnoreCase(Application.DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_2_DEMO);
					insertToUse = new String(Inserts.INSERT_2_DEMO);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_2_PROJ);
					insertToUse = new String(Inserts.INSERT_2_PROJ);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_2_MOD_PROJ);
					insertToUse = new String(Inserts.INSERT_2_MOD_PROJ);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_2_MOD_DEMO);
					insertToUse = new String(Inserts.INSERT_2_MOD_DEMO);
				}
				break;
			case 2:
				if(Application.RUN_TYPE.equalsIgnoreCase(Application.DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_3_DEMO);
					insertToUse = new String(Inserts.INSERT_3_DEMO);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_3_PROJ);
					insertToUse = new String(Inserts.INSERT_3_PROJ);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_3_MOD_PROJ);
					insertToUse = new String(Inserts.INSERT_3_MOD_PROJ);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_3_MOD_DEMO);
					insertToUse = new String(Inserts.INSERT_3_MOD_DEMO);
				}
				break;
			case 3:
				if(Application.RUN_TYPE.equalsIgnoreCase(Application.DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_4_DEMO);
					insertToUse = new String(Inserts.INSERT_4_DEMO);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_4_PROJ);
					insertToUse = new String(Inserts.INSERT_4_PROJ);
				}else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_4_MOD_PROJ);
					insertToUse = new String(Inserts.INSERT_4_MOD_PROJ);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_4_MOD_DEMO);
					insertToUse = new String(Inserts.INSERT_4_MOD_DEMO);
				}
				break;
			case 4:
				if(Application.RUN_TYPE.equalsIgnoreCase(Application.DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_5_DEMO);
					insertToUse = new String(Inserts.INSERT_5_DEMO);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.PROJ)){
					schemaToUse = Schemas.SCHEMA_5_PROJ;
					insertToUse = Inserts.INSERT_5_PROJ;
				}else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_PROJ)){
					schemaToUse = new String(Schemas.SCHEMA_5_MOD_PROJ);
					insertToUse = new String(Inserts.INSERT_5_MOD_PROJ);
				}
				else if(Application.RUN_TYPE.equalsIgnoreCase(Application.MOD_DEMO)){
					schemaToUse = new String(Schemas.SCHEMA_5_MOD_DEMO);
					insertToUse = new String(Inserts.INSERT_5_MOD_DEMO);
				}
				break;
				
			default:
				System.out.println("unkown schema type");
				System.exit(-1);
	
		}
		while(schemaToUse == null);
		
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
			System.out.println(tableId);
			System.out.println(folder);
		}
		System.out.println(folder);
		System.out.println(insertToUse);
		System.out.println(schemaToUse);
		writer = builder.build();

	}

	public void execute() throws InvalidRequestException, IOException {
		if(this.tableId == 3){
			this.executeTableFour();
			return;
		}
		else if(this.tableId == 4){
			this.executeTableFive();
			return;
		}
		System.out.println("creating " + this.numRows + " rows for " + this.tableId);
		for (int i = 0; i < this.numRows; i++) {
			writer.addRow(gen.genRow());
		}
	}
	
	public void executeTableFour() {
		
		int count = 1;
		IntGenerator gen = new IntGenerator(this.seed);
		while(count <= 6000){
			try {
				writer.addRow(new Integer(count), gen.gen());
			} catch (InvalidRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
		}
	}
	
	public void executeTableFive() {
		
		int count = 1;
		IntGenerator intGen = new IntGenerator(this.seed);
		PhGenerator phGen = new PhGenerator(this.seed);
		while(count <= 200000){
			try {
				writer.addRow(phGen.gen(), intGen.gen());
			} catch (InvalidRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			count++;
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
