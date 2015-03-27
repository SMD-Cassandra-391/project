package cass;

import java.io.IOException;
import java.text.SimpleDateFormat;

import org.apache.cassandra.config.Config;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;

public class SSTwriter {
	private ObjectListGenerator gen;
	private int numRows;
	private CQLSSTableWriter writer;

	/** Default output directory */
	public static final String DEFAULT_OUTPUT_DIR = Application.DATA_FOLDER;
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat(
			"yyyy-MM-dd");

	public static final String SCHEMA = "CREATE TABLE IF NOT EXISTS "
			+ Application.DEMO_KEYSPACE + "." + Application.DEMO_TABLE + " (" 
			+ Application.getApp().getCreateStmnt() + ")";

	/**
	 * INSERT statement to bulk load. It is like prepared statement. You fill in
	 * place holder for each data.
	 */
	public static final String INSERT_STMT = String.format("INSERT INTO "
			+ Application.DEMO_KEYSPACE + "." + Application.DEMO_TABLE 
			+ Application.getApp().getTableDesc() + " VALUES ("
			+ Application.getApp().getQuestionString() + ")");

	public SSTwriter(int numRows) {
		this.gen = new ObjectListGenerator();
		this.numRows = numRows;
	
		// magic
		Config.setClientMode(true);
		// Prepare SSTable writer
		CQLSSTableWriter.Builder builder = CQLSSTableWriter.builder();
		// set output directory
		builder.inDirectory(Application.DATA_FOLDER)
		// set target schema
				.forTable(SCHEMA)
				// set CQL statement to put data
				.using(INSERT_STMT)
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
		try {
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("writer is closed");
	}

	public void printSchema() {
		System.out.println(SSTwriter.SCHEMA);
	}

	public void printInsertStatement() {
		System.out.println(SSTwriter.INSERT_STMT);
	}

}
