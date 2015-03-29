package cass;

import java.io.IOException;
import java.text.SimpleDateFormat;
import org.apache.cassandra.config.Config;
import org.apache.cassandra.dht.Murmur3Partitioner;
import org.apache.cassandra.exceptions.InvalidRequestException;
import org.apache.cassandra.io.sstable.CQLSSTableWriter;

public class SSTwriter {
	public final static String KEYSPACE = "project";
	public final static String TABLE1 = "t1";
	private ObjectListGenerator gen;
	private int numRows;
	private CQLSSTableWriter writer;
	public static  final String SCHEMA1 = 
			"CREATE TABLE " + KEYSPACE + "." + TABLE1 + " ( " 
			+ "num bigint, "
			+ "dateTimeOrigination int, "
			+ "origNodeId int, "
			+ "origSpan int, "
			+ "origIpAddr int, "
			+ "callingPartyNumber varchar, "
			+ "callingPartyUnicodeLoginUserID varchar, "
			+ "origCause_location float, "
			+ "origCause_value float, "
			+ "origPrecedenceLevel float, "
			+ "origMediaTransportAddress_IP int, "
			+ "origMediaTransportAddress_Port int, "
			+ "origMediaCap_payloadCapability int, "
			+ "origMediaCap_maxFramesPerPacket int, "
			+ "origVideoCap_Codec float, "
			+ "origVideoCap_Bandwidth int, "
			+ "origVideoCap_Resolution float, "
			+ "origVideoTransportAddress_IP int, "
			+ "currentRoutingReason int, "
			+ "origRoutingReason int, "
			+ "lastRedirectingRoutingReason int, "
			+ "huntPilotDN varchar, "
			+ "huntPilotPartition varchar, "
			+ "PRIMARY KEY(num, dateTimeOrigination))";
	
	public static final String SCHEMA2 = 
			"CREATE TABLE project.t2 ( "
			+ "num bigint, "
			+ "dateTimeOrigination int, "
			+ "cdrRecordType float, "
			+ "globalCallID_callManagerId int, "
			+ "globalCallID_callId int, "
			+ "origLegCallIdentifier int, "
			+ "origVideoTransportAddress_Port int, "
			+ "origRSVPAudioStat float, "
			+ "origRSVPVideoStat float, "
			+ "destLegCallIdentifier int, "
			+ "destNodeId int, "
			+ "destSpan int, "
			+ "destIpAddr int, "
			+ "originalCalledPartyNumber varchar, "
			+ "finalCalledPartyNumber varchar, "
			+ "finalCalledPartyUnicodeLoginUserID varchar, "
 			+ "destCause_location float, "
			+ "destCause_value float, "
			+ "destPrecedenceLevel float, "
			+ "destMediaTransportAddress_IP int, "
			+ "destMediaTransportAddress_Port int, "
			+ "destMediaCap_payloadCapability int, "
			+ "destMediaCap_maxFramesPerPacket int, "
			+ "PRIMARY KEY (num, dateTimeOrigination))";
	
	public static final String SCHEMA3 = 
			"CREATE TABLE project.t3 ( "
			+ "num bigint, "
			+ "dateTimeOrigination int, "
			+ "destVideoCap_Codec float, "
			+ "destVideoCap_Bandwidth int, "
			+ "destVideoCap_Resolution float, "
			+ "destVideoTransportAddress_IP int, "
			+ "destVideoTransportAddress_Port int, "
			+ "destRSVPAudioStat float, "
			+ "destRSVPVideoStat float, "
			+ "dateTimeConnect int, "
			+ "dateTimeDisconnect int, "
			+ "lastRedirectDn varchar, "
			+ "pkid varchar, "
			+ "originalCalledPartyNumberPartition varchar, "
			+ "NumberPartition varchar, "
			+ "finalCalledPartyNumberPartition varchar, "
			+ "lastRedirectDnPartition varchar, "
			+ "duration int, "
			+ "origDeviceName varchar, "
			+ "destDeviceName varchar, "
			+ "origCallTerminationOnBehalfOf int, "
			+ "destCallTerminationOnBehalfOf int, "
			+ "origCalledPartyRedirectOnBehalfOf int, "
			+ "finalCalledPartyNumber varchar, "
			+ "finalCalledPartyUnicodeLoginUserID varchar, "
			+ "destCause_location float, "
			+ "destCause_value float, "
			+ "destPrecedenceLevel float, "
			+ "destMediaTransportAddress_IP int, "
			+ "destMediaTransportAddress_Port int, "
			+ "destMediaCap_payloadCapability int, "
			+ "destMediaCap_maxFramesPerPacket int, "
			+ "IncomingProtocolID int, " 
			+ "IncomingProtocolCallRef varchar, "
			+ "OutgoingProtocolID int, "
			+ "OutgoingProtocolCallRef varchar, "
			+ "PRIMARY KEY (num, dateTimeOrigination))";
	
	public static final String SCHEMA4 =
			"CREATE TABLE project.t4 ( "
			+ "num bigint, "
			+ "dateTimeOrigination int, "
			+ "lastRedirectRedirectOnBehalfOf int, "
			+ "origCalledPartyRedirectReason int, "
			+ "lastRedirectRedirectReason int, "
			+ "destConversationID int, "
			+ "globalCallId_ClusterId varchar, "
			+ "joinOnBehalfOf  int, "
			+ "comment varchar, "
			+ "authCodeDescription varchar, "
			+ "authorizationLevel int, "
			+ "clientMatterCode varchar, "
			+ "origDTMFMethod int, "
			+ "destDTMFMethod int, "
			+ "callSecuredStatus int, "
			+ "origConversationID int, "
			+ "origMediaCap_Bandwidth int, "
			+ "destMediaCap_Bandwidth int, "
			+ "authorizationCodeValue varchar, "
			+ "outpulsedCallingPartyNumber varchar, " 
			+ "outpulsedCalledPartyNumber varchar, "
			+ "origIpv4v6Addr varchar, "
			+ "destIpv4v6Addr varchar, "
			+ "origVideoCap_Codec_Channel2 float, "
			+ "origVideoCap_Bandwidth_Channel2 int, "
			+ "origVideoCap_Resolution_Channel2 float, "
			+ "origVideoTransportAddress_IP_Channel2 int, "
			+ "origVideoTransportAddress_Port_Channel2 int, "
			+ "origVideoChannel_Role_Channel2 int, "
			+ "PRIMARY KEY (num, dateTimeOrigination))";
	
	public static  String INSERT1 =
		  "INSERT INTO project.t1 ("
		+ "num, "
		+ "dateTimeOrigination, "
		+ "origNodeId, "
		+ "origSpan, "
		+ "origIpAddr, "
		+ "callingPartyNumber, "
		+ "callingPartyUnicodeLoginUserID, "
		+ "origCause_location, "
		+ "origCause_value, "
		+ "origPrecedenceLevel, "
		+ "origMediaTransportAddress_IP, "
		+ "origMediaTransportAddress_Port, "
		+ "origMediaCap_payloadCapability, "
		+ "origMediaCap_maxFramesPerPacket, "
		+ "origVideoCap_Codec, "
		+ "origVideoCap_Bandwidth, "
		+ "origVideoCap_Resolution, "
		+ "origVideoTransportAddress_IP, "
		+ "currentRoutingReason, "
		+ "origRoutingReason, "
		+ "lastRedirectingRoutingReason, "
		+ "huntPilotDN, "
		+ "huntPilotPartition )"
		+ " VALUES ("
		+ "?, ?, ?, ?, ?, "
		+ "?, ?, ?, ?, ?, "
		+ "?, ?, ?, ?, ?, "
		+ "?, ?, ?, ?, ?, "
		+ "?, ?, ?)";
	
	public static  String INSERT2 = 
		  "INSERT INTO project.t2 ("
	    + "num, "
		+ "dateTimeOrigination, "
		+ "cdrRecordType, "
		+ "globalCallID_callManagerId, "
		+ "globalCallID_callId, "
		+ "origLegCallIdentifier, "
		+ "origVideoTransportAddress_Port, "
		+ "origRSVPAudioStat, "
		+ "origRSVPVideoStat, "
		+ "destLegCallIdentifier, "
		+ "destNodeId, "
		+ "destSpan, "
		+ "destIpAddr, "
		+ "originalCalledPartyNumber, "
		+ "finalCalledPartyNumber, "
		+ "finalCalledPartyUnicodeLoginUserID, "
		+ "destCause_location, "
		+ "destCause_value, "
		+ "destPrecedenceLevel, "
		+ "destMediaTransportAddress_IP, "
		+ "destMediaTransportAddress_Port, "
		+ "destMediaCap_payloadCapability, "
		+ "destMediaCap_maxFramesPerPacket) "
		+ " VALUES ( "
		+ "?, ?, ?, ?, ?, "
		+ "?, ?, ?, ?, ?, "
		+ "?, ?, ?, ?, ?, "
		+ "?, ?, ?, ?, ?, "
		+ "?, ?, ? )";
	
	public static String INSERT3 =
			  "INSERT INTO project.t3 ("
			+ "num, "
			+ "dateTimeOrigination, "
			+ "destVideoCap_Codec, "
			+ "destVideoCap_Bandwidth, "
			+ "destVideoCap_Resolution, "
			+ "destVideoTransportAddress_IP, "
			+ "destVideoTransportAddress_Port, "
			+ "destRSVPAudioStat, "
			+ "destRSVPVideoStat, "
			+ "dateTimeConnect, "
			+ "dateTimeDisconnect, "
			+ "lastRedirectDn, "
			+ "pkid, "
			+ "originalCalledPartyNumberPartition, "
			+ "NumberPartition, "
			+ "finalCalledPartyNumberPartition, "
			+ "lastRedirectDnPartition, "
			+ "duration, "
			+ "origDeviceName, "
			+ "destDeviceName, "
			+ "origCallTerminationOnBehalfOf, "
			+ "destCallTerminationOnBehalfOf, "
			+ "origCalledPartyRedirectOnBehalfOf, "
			+ "finalCalledPartyNumber, "
			+ "finalCalledPartyUnicodeLoginUserID, "
			+ "destCause_location, "
			+ "destCause_value, "
			+ "destPrecedenceLevel, "
			+ "destMediaTransportAddress_IP, "
			+ "destMediaTransportAddress_Port, "
			+ "destMediaCap_payloadCapability, "
			+ "destMediaCap_maxFramesPerPacket, "
			+ "IncomingProtocolID, " 
			+ "IncomingProtocolCallRef, "
			+ "OutgoingProtocolID, "
			+ "OutgoingProtocolCallRef) "	
			+ " VALUES ("
			+ "?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, "
			+ "?, ?, ?, ?, ?, ?)";
	
	public static final String INSERT4 =
			"INSERT INTO project.t4 ("
		  + "num,  "
		  + "dateTimeOrigination,  "
		  + "lastRedirectRedirectOnBehalfOf,  "
		  + "origCalledPartyRedirectReason,  "
	      + "lastRedirectRedirectReason,  "
		  + "destConversationID, "
		  + "globalCallId_ClusterId, "
		  + "joinOnBehalfOf,  "
		  + "comment, "
		  + "authCodeDescription, "
		  + "authorizationLevel, "
		  + "clientMatterCode, "
		  + "origDTMFMethod, "
		  + "destDTMFMethod, "
		  + "callSecuredStatus, "
	      + "origConversationID, "
		  + "origMediaCap_Bandwidth, "
		  + "destMediaCap_Bandwidth, "
		  + "authorizationCodeValue, "
		  + "outpulsedCallingPartyNumber, " 
		  + "outpulsedCalledPartyNumber, "
		  + "origIpv4v6Addr, "
		  + "destIpv4v6Addr, "
	      + "origVideoCap_Codec_Channel2, "
		  + "origVideoCap_Bandwidth_Channel2, "
		  + "origVideoCap_Resolution_Channel2, "
		  + "origVideoTransportAddress_IP_Channel2, "
		  + "origVideoTransportAddress_Port_Channel2, "
		  + "origVideoChannel_Role_Channel2) "
		  + " VALUES ("
		  + "?, ?, ?, ?, ?, "
		  + "?, ?, ?, ?, ?, "
		  + "?, ?, ?, ?, ?, "
		  + "?, ?, ?, ?, ?, "
		  + "?, ?, ?, ?, ?, "
		  + "?, ?, ?, ?)";
	
	
	public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");

	public SSTwriter(int numRows, String folder, int threadId) {
		// magic
		Config.setClientMode(true);
		this.gen = null;
		this.numRows = numRows;
		gen = new ObjectListGenerator(threadId);
		String schemaToUse = null;
	
		String insertToUse = null;
		
		
		
		switch(threadId){
			case 1:
				schemaToUse = SCHEMA1;
				insertToUse = INSERT1;
				break;
			case 2:
				schemaToUse = SCHEMA2;
				insertToUse = INSERT2;
				break;
			case 3:
				schemaToUse = SCHEMA3;
				insertToUse = INSERT3;
				break;
			case 4:
				schemaToUse = SCHEMA4;
				insertToUse = INSERT4;
				break;
			default:
				System.out.println("unkown schema type");
	
		}
		System.out.println("thread id = " + threadId);
		System.out.println(insertToUse);
		System.out.println(schemaToUse);
		System.out.println(folder);
		
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
				System.out.println(i);
				ce.printStackTrace();
				System.exit(-1);
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
