package main.java.com.ualberta.cmput391.W15.slmyers.cass;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import main.java.com.ualberta.cmput391.W15.slmyers.column.Generator;

import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;

public class SSTwriter {
	private String schema;
	private String insertStmnt;
	private CQLSSTableWriter writer;
	private File filePath;
	private static final int NUM_COLS = 470;
	
	private static final String keyspace = "demo_keyspace";
	private static final String tableName = "demo_table";
	
	
	public SSTwriter(String schema, String insertStmnt){
		this.schema = new String(schema);
		this.insertStmnt = new String(insertStmnt);
		this.writer = CQLSSTableWriter.builder()
				.forTable(schema)
				.using(this.insertStmnt)
				.inDirectory(getFilePath().getAbsolutePath())
				.build();
		
		createDirectories(keyspace, tableName);
	}

	public File getFilePath(){
		return this.filePath;
	}
	
	private void createDirectories(String keyspace, String tableName) {
		File directory = new File(keyspace);
		if (!directory.exists())
			directory.mkdir();
		
		filePath = new File(directory, tableName);
		if (!filePath.exists())
			filePath.mkdir();
	}
	
	public void writeTable(ArrayList<Generator> gens, ArrayList<String> colNames){
		if(gens.size() != colNames.size()){
			// must have a generator for each column name
			throw new RuntimeException("SSTwriter: gens.size() != colNames.size()");
		}
		if(gens.size() != NUM_COLS){
			// 470 columns in sample CDR table
			throw new RuntimeException("SSTwriter: gens.size() != " + NUM_COLS);
		}
		
		
		Map<String, Object> values = new HashMap<>();
		for(int i = 0; i < NUM_COLS; i++){
			
			try {
				if(gens.get(i).equals("")){}
				writer.addRow(colNames.get(i), gens.get(i).genTrue());
			} catch (InvalidRequestException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	/*
	cols.add(new IntGenerator(2));
	cols.add(new TimeSeriesGenerator());
	cols.add(new PhoneGenerator());
	cols.add(new LocationGenerator());
	cols.add(new GridGenerator());
	cols.add(new LocationGenerator());
	cols.add(new GridGenerator());
	cols.add(new IntGenerator(1));
	cols.add(new BigIntGenerator());
	cols.add(new IntGenerator(7));
	cols.add(new BigIntGenerator());
	cols.add(new IPgenerator());
	cols.add(new IntGenerator(16));
	cols.add(new IPgenerator());
	cols.add(new IPgenerator());
	cols.add(new IntGenerator(31));
	cols.add(new IPgenerator());
	cols.add(new IntGenerator(7));
	cols.add(new IPgenerator());
	cols.add(new IntGenerator(381));
	cols.add(new LastIntGen());
	
	@Test
public void testAddRow() throws Exception
{
	String KS = "cql_keyspace";
	String TABLE = "table1";
	
	File tempdir = Files.createTempDir();
	File dataDir = new File(tempdir.getAbsolutePath() + File.separator + KS + File.separator + TABLE);
	assertTrue( dataDir.mkdirs());
	
	String schema = "CREATE TABLE cql_keyspace.table1 ("
			+ "  k int PRIMARY KEY,"
			+ "  v1 text,"
			+ "  v2 int"
			+ ")";
	String insert = "INSERT INTO cql_keyspace.table1 (k, v1, v2) VALUES (?, ?, ?)";
	CQLSSTableWriter writer = CQLSSTableWriter.builder()
			.inDirectory(dataDir)
			.forTable(schema)
			.withPartitioner(StorageService.instance.getPartitioner())
			.using(insert).build();
	
	Map<String, Object> values = new HashMap<>();
	values.put( "k", 0 );
	values.put( "v1", "test1" );
	values.put( "v2", 24 );
	writer.addRow( values );
	
	values.clear();  
	values.put( "k", 1 );
	values.put( "v1", "test2" );
    values.put( "v2", null ); //commented intentionally so that v2 will get null by the writer.
	writer.addRow( values );
	
	values.clear();
	values.put( "k", 2 );
	values.put( "V1", "test3 - will not be found, since V1 is not lowercase" );
	values.put( "v2", 42 );
	values.put( "v3", "some ignored key" );
	writer.addRow( values );
	
	writer.close();
	
	SSTableLoader loader = new SSTableLoader(dataDir, new SSTableLoader.Client()
	{
		public void init(String keyspace)
		{
			for (Range<Token> range : StorageService.instance.getLocalRanges("cql_keyspace"))
				addRangeForEndpoint(range, FBUtilities.getBroadcastAddress());
			setPartitioner(StorageService.getPartitioner());
		}
		
		public CFMetaData getCFMetaData(String keyspace, String cfName)
		{
			return Schema.instance.getCFMetaData(keyspace, cfName);
		}
	}, new OutputHandler.SystemOutput(false, false));
	
	loader.stream().get();
	
	UntypedResultSet rs = QueryProcessor.processInternal("SELECT * FROM cql_keyspace.table1;");
	assertEquals(3, rs.size());
	
	Iterator<UntypedResultSet.Row> iter = rs.iterator();
	UntypedResultSet.Row row;
	
	row = iter.next();
	assertEquals(0, row.getInt("k"));
	assertEquals("test1", row.getString("v1"));
	assertEquals(24, row.getInt("v2"));
	
	row = iter.next();
	assertEquals(1, row.getInt("k"));
	assertEquals("test2", row.getString("v1"));
	assertFalse(row.has("v2"));
	
	row = iter.next();
	assertEquals(2, row.getInt("k"));
	assertFalse(row.has("v1"));
	assertEquals(42, row.getInt("v2"));
	assertFalse(row.has("v3"));
*/
}

	
	



