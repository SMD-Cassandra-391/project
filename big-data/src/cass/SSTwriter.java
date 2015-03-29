package cass;

import java.io.File;
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
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public static final String SCHEMA1 = 
			"CREATE TABLE "
			+ "project.t1 " + " (" 
			+ "num int, "
			+ "dateTimeOrigination int, "
			+ "origNodeId INT, "
			+ "origSpan INT, "
			+ "origIpAddr INT, "
			+ "callingPartyNumber STRING, "
			+ "callingPartyUnicodeLoginUserID STRING, "
			+ "origCause_location DECIMAL, "
			+ "origCause_value DECIMAL, "
			+ "origPrecedenceLevel DECIMAL, "
			+ "origMediaTransportAddress_IP INT, "
			+ "origMediaTransportAddress_Port INT, "
			+ "origMediaCap_payloadCapability INT, "
			+ "origMediaCap_maxFramesPerPacket INT, "
			+ "origVideoCap_Codec DECIMAL, "
			+ "origVideoCap_Bandwidth INT, "
			+ "origVideoCap_Resolution DECIMAL, "
			+ "origVideoTransportAddress_IP INT, "
			+ "PRIMARY KEY (num, dateTimeOrigination));";
	
	public static final String SCHEMA2 = 
			"CREATE TABLE "
			+ "project.t2 " + " ("
			+ "num INT, "
			+ "dateTimeOrigination INT, "
			+ "cdrRecordType DECIMAL, "
			+ "globalCallID_callManagerId INT, "
			+ "globalCallID_callId INT, "
			+ "origLegCallIdentifier INT, "
			+ "origVideoTransportAddress_Port INT, "
			+ "origRSVPAudioStat DECIMAL, "
			+ "origRSVPVideoStat DECIMAL, "
			+ "destLegCallIdentifier INT, "
			+ "destNodeId INT, "
			+ "destSpan INT, "
			+ "destIpAddr INT, "
			+ "originalCalledPartyNumber STRING, "
			+ "finalCalledPartyNumber STRING, "
			+ "finalCalledPartyUnicodeLoginUserID STRING, "
 			+ "destCause_location DECIMAL, "
			+ "destCause_value DECIMAL, "
			+ "destPrecedenceLevel DECIMAL, "
			+ "destMediaTransportAddress_IP INT, "
			+ "destMediaTransportAddress_Port INT, "
			+ "destMediaCap_payloadCapability INT, "
			+ "destMediaCap_maxFramesPerPacket INT, "
			+ "PRIMARY KEY (num, dateTimeOrigination));";
	
	public static final String SCHEMA3 = 
			"CREATE TABLE project.t3( "
			+ "num INT, "
			+ "dateTimeOrigination INT, "
			+ "destVideoCap_Codec DECIMAL, "
			+ "destVideoCap_Bandwidth INT, "
			+ "destVideoCap_Resolution DECIMAL, "
			+ "destVideoTransportAddress_IP INT, "
			+ "destVideoTransportAddress_Port INT, "
			+ "destRSVPAudioStat DECIMAL, "
			+ "destRSVPVideoStat DECIMAL, "
			+ "dateTimeConnect INT, "
			+ "dateTimeDisconnect INT, "
			+ "lastRedirectDn STRING, "
			+ "pkid STRING, "
			+ "originalCalledPartyNumberPartition STRING, "
			+ "NumberPartition STRING, "
			+ "finalCalledPartyNumberPartition STRING, "
			+ "lastRedirectDnPartition STRING, "
			+ "duration INT, "
			+ "origDeviceName STRING, "
			+ "destDeviceName STRING, "
			+ "origCallTerminationOnBehalfOf INT, "
			+ "destCallTerminationOnBehalfOf INT, "
			+ "origCalledPartyRedirectOnBehalfOf INT, "
			+ "PRIMARY KEY (num, dateTimeOrigination));";
	
	public static final String SCHEMA4 =
			"CREATE TABLE project.t4( "
			+ "create table t4( "
			+ "num INT, "
			+ "dateTimeOrigination INT, "
			+ "lastRedirectRedirectOnBehalfOf INT, "
			+ "origCalledPartyRedirectReason INT, "
			+ "lastRedirectRedirectReason INT, "
			+ "destConversationID INT, "
			+ "globalCallId_ClusterId STRING, "
			+ "joinOnBehalfOf  INT, "
			+ "comment STRING, "
			+ "authCodeDescription STRING, "
			+ "authorizationLevel INT, "
			+ "clientMatterCode STRING, "
			+ "origDTMFMethod INT, "
			+ "destDTMFMethod INT, "
			+ "callSecuredStatus INT, "
			+ "origConversationID INT, "
			+ "origMediaCap_Bandwidth INT, "
			+ "destMediaCap_Bandwidth INT, "
			+ "authorizationCodeValue STRING, "
			+ "outpulsedCallingPartyNumber STRING, " 
			+ "outpulsedCalledPartyNumber STRING, "
			+ "origIpv4v6Addr STRING, "
			+ "destIpv4v6Addr STRING, "
			+ "PRIMARY KEY (num, dateTimeOrigination));";

	/**
	 * INSERT statement to bulk load. It is like prepared statement. You fill in
	 * place holder for each data.
	 */
	public static final String INSERT_STMT1 = String.format("INSERT INTO "
			+ Application.TYPE_KEYSPACE + "." + Application.TYPE_TABLE 
			+ Application.getApp().getTableDesc() + " VALUES ("
			+ Application.getApp().getQuestionString() + ")");

	public SSTwriter(int numRows, String folder, int tableNumber) {
		this.gen = new ObjectListGenerator();
		this.numRows = numRows;
		String schema_to_use;
		switch(tableNumber){
			case 1:
				schema_to_use = SCHEMA1;
				break;
			case 2:
				schema_to_use = SCHEMA2;
				break;
			case 3:
				schema_to_use = SCHEMA3;
				break;
			case 4:
				schema_to_use = SCHEMA4;
				break;
			default:
				System.out.println("unkown schema type");
	
		}
		
		// magic
		Config.setClientMode(true);
		// Prepare SSTable writer
		CQLSSTableWriter.Builder builder = CQLSSTableWriter.builder();
		// set output directory
		builder.inDirectory(folder)
		// set target schema
				.forTable(schema_to_use)
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

	public void printSchema() {
		System.out.println(SSTwriter.SCHEMA);
	}

	public void printInsertStatement() {
		System.out.println(SSTwriter.INSERT_STMT);
	}

}
