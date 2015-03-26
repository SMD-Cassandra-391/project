package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.cassandra.config.Config;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;
import org.supercsv.io.CsvListReader;
import org.supercsv.prefs.CsvPreference;

public class SSTloader {
	private static String createStatement;
	private static String tableDesc;
	private static String questionString;
	private String inputString;

	/** Default output directory */
	public static final String DEFAULT_OUTPUT_DIR = "./SSTdata";
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
	/** Keyspace name */
	public static final String KEYSPACE = "demo";
	/** Table name */
	public static final String TABLE = "demo_table";

	public static final String SCHEMA = "CREATE TABLE IF NOT EXISTS "
			+ Application.DEMO_KEYSPACE + "." + Application.DEMO_TABLE + " ("
			+ createStatement + ");";

	/**
	 * INSERT statement to bulk load. It is like prepared statement. You fill in
	 * place holder for each data.
	 */
	public static final String INSERT_STMT = String.format("INSERT INTO "
			+ Application.DEMO_KEYSPACE + "." + Application.DEMO_TABLE + " ("
			+ tableDesc + ") VALUES (" + questionString + ")");

	public SSTloader(String createStatement, String tableDesc,
			String questionString, String inputString) {
		this.createStatement = new String(createStatement);
		this.tableDesc = new String(tableDesc);
		this.questionString = new String(questionString);
		this.inputString = new String(inputString);
	}

	public void execute() {
		Config.setClientMode(true);

		File outputDir = new File(DEFAULT_OUTPUT_DIR + File.separator
				+ KEYSPACE + File.separator + TABLE);
		if (!outputDir.exists() && !outputDir.mkdirs()) {
			throw new RuntimeException("Cannot create output directory: "
					+ outputDir);
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
				// default is Murmur3Partitioner so set if you use different
				// one.
				.withPartitioner(new Murmur3Partitioner());
		CQLSSTableWriter writer = builder.build();
		BufferedReader reader = null;
		reader = new BufferedReader(new StringReader(inputString));

		CsvListReader csvReader = new CsvListReader(reader,
				CsvPreference.STANDARD_PREFERENCE);

		// Write to SSTable while reading data
		List<String> line;
		/*
		 * this is awful there must be a better way
		try {
			while ((line = csvReader.read()) != null) {
				writer.addRow(Integer.parseInt(line.get(0)),
							  Integer.parseInt(line.get(1)),
							  DATE_FORMAT.parse(line.get(2)),
							  DATE_FORMAT.parse(line.get(3)),
							  DATE_FORMAT.parse(line.get(4)),
							  line.get(5), line.get(6), line.get(7), line.get(8), line.get(9), line.get(10),
							  Float.parseFloat(line.get(11)), Float.parseFloat(line.get(12)), line.get(13), line.get(14),
							  Float.parseFloat(line.get(15)), Float.parseFloat(line.get(16)), line.get(17), line.get(18),
							  Integer.parseInt(line.get(19)), new BigInteger(line.get(20)), Integer.parseInt(line.get(21)),
							  Integer.parseInt(line.get(22)), Integer.parseInt(line.get(23)), line.get(24), line.get(25),
							  line.get(26), Integer.parseInt(line.get(27)), new BigInteger(line.get(28)), line.get(29),
							  Integer.parseInt(line.get(31)), Integer.parseInt(line.get(32)), Integer.parseInt(line.get(33)), Integer.parseInt(line.get(34)),
							  Integer.parseInt(line.get(35)), Integer.parseInt(line.get(36)), Integer.parseInt(line.get(37)), Integer.parseInt(line.get(38)),
							  Integer.parseInt(line.get(39)), Integer.parseInt(line.get(40)), Integer.parseInt(line.get(41)), Integer.parseInt(line.get(42)),
							  Integer.parseInt(line.get(43)), Integer.parseInt(line.get(44)), Integer.parseInt(line.get(45)), Integer.parseInt(line.get(46)),
							  line.get(47), line.get(48), Integer.parseInt(line.get(49)), Integer.parseInt(line.get(50)), Integer.parseInt(line.get(51)),
							  Integer.parseInt(line.get(52)), Integer.parseInt(line.get(53)), Integer.parseInt(line.get(54)), Integer.parseInt(line.get(55)),
							  Integer.parseInt(line.get(56)), Integer.parseInt(line.get(57)), Integer.parseInt(line.get(58)), Integer.parseInt(line.get(59)),
							  Integer.parseInt(line.get(60)), Integer.parseInt(line.get(61)), Integer.parseInt(line.get(62)), Integer.parseInt(line.get(63)),
							  Integer.parseInt(line.get(64)), Integer.parseInt(line.get(65)), Integer.parseInt(line.get(66)), Integer.parseInt(line.get(67)), 
							  Integer.parseInt(line.get(68)), Integer.parseInt(line.get(69)), Integer.parseInt(line.get(70)), Integer.parseInt(line.get(71)),
							  Integer.parseInt(line.get(72)), Integer.parseInt(line.get(73)), Integer.parseInt(line.get(74)), Integer.parseInt(line.get(75)), 
							  Integer.parseInt(line.get(76)), Integer.parseInt(line.get(77)), Integer.parseInt(line.get(78)), Integer.parseInt(line.get(79)),
							  
							  
							  
							  
						);
			}
		} catch (InvalidRequestException | IOException | NumberFormatException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	*/
	}

}
