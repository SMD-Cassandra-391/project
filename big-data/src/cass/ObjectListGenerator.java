package cass;

import java.util.ArrayList;

import objectGenerator.BigIntGenerator;
import objectGenerator.CharGenerator;
import objectGenerator.DateGenerator;
import objectGenerator.DecimalGenerator;
import objectGenerator.IPGenerator;
import objectGenerator.IntGenerator;
import objectGenerator.LatGenerator;
import objectGenerator.LongitudeGenerator;
import objectGenerator.ObjectGenerator;
import objectGenerator.PhGenerator;
import objectGenerator.PhoneGenerator;


public class ObjectListGenerator {
	private ArrayList<ObjectGenerator> cols;
	private int threadId;

	public ObjectListGenerator(int threadId) {
		this.threadId = threadId;
		this.initializeCols();
	}
	
	private void initializeCols() {
		cols = null;
		switch(threadId){
			case 1:
				cols = getThreadOneGen();
				break;
			case 2:
				cols = getThreadTwoGen();
				break;
			case 3:
				cols = getThreadThreeGen();
				break;
			case 4:
				cols = getThreadFourGen();
				break;
		}
	}
	/*
	"num int, "
	"dateTimeOrigination int, "
	"origNodeId INT, "
	"origSpan INT, "
	"origIpAddr INT, "
	"callingPartyNumber STRING, "
	"callingPartyUnicodeLoginUserID STRING, "
	"origCause_location DECIMAL, "
	"origCause_value DECIMAL, "
	"origPrecedenceLevel DECIMAL, "
	"origMediaTransportAddress_IP INT, "
	"origMediaTransportAddress_Port INT, "
	"origMediaCap_payloadCapability INT, "
	"origMediaCap_maxFramesPerPacket INT, "
	"origVideoCap_Codec DECIMAL, "
	"origVideoCap_Bandwidth INT, "
	"origVideoCap_Resolution DECIMAL, "
	"origVideoTransportAddress_IP INT, "
	"currentRoutingReason INT, "
	"origRoutingReason INT, "
	"lastRedirectingRoutingReason INT, "
	"huntPilotDN VARCHAR, "
	"huntPilotPartition VARCHAR "
	*/
	public ArrayList<ObjectGenerator> getThreadOneGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		for(int i = 0; i < 5; i++){
			gens.add(new IntGenerator());
		}
		gens.add(new PhGenerator());
		gens.add(new PhGenerator());
		for(int i = 0; i < 3; i++)
			gens.add(new DecimalGenerator());
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new CharGenerator());
		return gens;
	}
	/*
	"num INT, "
	"dateTimeOrigination INT, "
	"cdrRecordType DECIMAL, "
	"globalCallID_callManagerId INT, "
	"globalCallID_callId INT, "
	"origLegCallIdentifier INT, "
	"origVideoTransportAddress_Port INT, "
	"origRSVPAudioStat DECIMAL, "
	"origRSVPVideoStat DECIMAL, "
	"destLegCallIdentifier INT, "
	"destNodeId INT, "
	"destSpan INT, "
	"destIpAddr INT, "
	"originalCalledPartyNumber STRING, "
	"finalCalledPartyNumber STRING, "
	"finalCalledPartyUnicodeLoginUserID STRING, "
	"destCause_location DECIMAL, "
	"destCause_value DECIMAL, "
	"destPrecedenceLevel DECIMAL, "
	"destMediaTransportAddress_IP INT, "
	"destMediaTransportAddress_Port INT, "
	"destMediaCap_payloadCapability INT, "
	"destMediaCap_maxFramesPerPacket INT, "
	*/
	public ArrayList<ObjectGenerator> getThreadTwoGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new IntGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new DecimalGenerator());
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		gens.add(new PhGenerator());
		gens.add(new PhGenerator());
		gens.add(new CharGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new DecimalGenerator());
		for(int i = 0; i < 4; i++)
			gens.add(new IntGenerator());
		return gens;
	}
	/*
	 * 	"num INT, "
	   	"dateTimeOrigination INT, "
		"destVideoCap_Codec DECIMAL, "
	 	"destVideoCap_Bandwidth INT, "
		"destVideoCap_Resolution DECIMAL, "
		"destVideoTransportAddress_IP INT, "
		"destVideoTransportAddress_Port INT, "
		"destRSVPAudioStat DECIMAL, "
		"destRSVPVideoStat DECIMAL, "
		"dateTimeConnect INT, "
		"dateTimeDisconnect INT, "
		"lastRedirectDn STRING, "
		"pkid STRING, "
		"originalCalledPartyNumberPartition STRING, "
		"NumberPartition STRING, "
		"finalCalledPartyNumberPartition STRING, "
		"lastRedirectDnPartition STRING, "
		"duration INT, "
		"origDeviceName STRING, "
		"destDeviceName STRING, "
		"origCallTerminationOnBehalfOf INT, "
		"destCallTerminationOnBehalfOf INT, "
		"origCalledPartyRedirectOnBehalfOf INT, "
		"finalCalledPartyNumber VARCHAR, "
		"finalCalledPartyUnicodeLoginUserID VARCHAR, "
		"destCause_location DECIMAL, "
		"destCause_value DECIMAL, "
		"destPrecedenceLevel DECIMAL, "
		"destMediaTransportAddress_IP INT, "
		"destMediaTransportAddress_Port INT, "
		"destMediaCap_payloadCapability INT, "
		"destMediaCap_maxFramesPerPacket INT, "
		"IncomingProtocolID INT, " 
		"IncomingProtocolCallRef VARCHAR, "
		"OutgoingProtocolID INT, "
		"OutgoingProtocolCallRef VARCHAR, "
	 */

	public ArrayList<ObjectGenerator> getThreadThreeGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new CharGenerator());
		gens.add(new PhGenerator());
		gens.add(new CharGenerator());
		gens.add(new PhGenerator());
		gens.add(new CharGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new CharGenerator());
		for(int i = 0 ; i < 3; i++){
			gens.add(new IntGenerator());
		}
		gens.add(new PhGenerator());
		gens.add(new CharGenerator());
		for(int i = 0; i < 3; i++){
			gens.add(new DecimalGenerator());
		}
		for(int i = 0; i< 5; i++){
			gens.add(new IntGenerator());
		}
		gens.add(new CharGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		return gens;
	}
	/*
	"num INT, "
	"dateTimeOrigination INT, "
	"lastRedirectRedirectOnBehalfOf INT, "
	"origCalledPartyRedirectReason INT, "
	"lastRedirectRedirectReason INT, "
	"destConversationID INT, "
	"globalCallId_ClusterId STRING, "
	"joinOnBehalfOf  INT, "
	"comment STRING, "
	"authCodeDescription STRING, "
	"authorizationLevel INT, "
	"clientMatterCode STRING, "
	"origDTMFMethod INT, "
	"destDTMFMethod INT, "
	"callSecuredStatus INT, "
	"origConversationID INT, "
	"origMediaCap_Bandwidth INT, "
	"destMediaCap_Bandwidth INT, "
	"authorizationCodeValue STRING, "
	"outpulsedCallingPartyNumber STRING, " 
	"outpulsedCalledPartyNumber STRING, "
	"origIpv4v6Addr STRING, "
	"destIpv4v6Addr STRING, "
	"origVideoCap_Codec_Channel2 DECIMAL, "
	"origVideoCap_Bandwidth_Channel2 INT, "
	"origVideoCap_Resolution_Channel2 DECIMAL, "
	"origVideoTransportAddress_IP_Channel2 INT, "
	"origVideoTransportAddress_Port_Channel2 INT, "
	"origVideoChannel_Role_Channel2 INT, "*/

	public ArrayList<ObjectGenerator> getThreadFourGen() {
		ArrayList<ObjectGenerator> gens = new ArrayList<ObjectGenerator>();
		for(int i = 0; i< 6; i++)
			gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new CharGenerator());
		gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		for(int i = 0; i< 6; i++)
			gens.add(new IntGenerator());
		gens.add(new CharGenerator());
		gens.add(new PhGenerator());
		gens.add(new IPGenerator());
		gens.add(new DecimalGenerator());
		gens.add(new IntGenerator());
		gens.add(new DecimalGenerator());
		for(int i = 0; i< 3; i++)
			gens.add(new IntGenerator());
		return gens;
	}

	public ArrayList<Object> genRow(){
		ArrayList<Object> row = new ArrayList<Object>();
		for(ObjectGenerator gen : cols){
			row.add(gen.gen());
		}
		return row;
	}
}
