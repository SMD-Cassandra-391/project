
CREATE TABLE IF NOT EXISTS project.t1  ( 
    num bigint, 
    datetimeorigination int, 
	callingpartynumber int, 
	callingpartyunicodeloginuserid text, 
	currentroutingreason int, 
	huntpilotdn text, 
	huntpilotpartition text, 
	lastredirectingroutingreason int, 
	origcause_location float, 
	origcause_value float, 
	origipaddr int, 
	origmediacap_maxframesperpacket int, 
	origmediacap_payloadcapability int, 
	origmediatransportaddress_ip text, 
	origmediatransportaddress_port int, 
	orignodeid int, 
	origprecedencelevel float,
	origroutingreason int, 
	origspan int, 
	origvideocap_bandwidth int, 
	origvideocap_codec float, 
	origvideocap_resolution float, 
	origvideotransportaddress_ip int, 
    PRIMARY KEY ((num), currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination)
);

CREATE TABLE IF NOT EXISTS project.t2 (
    num bigint, 
	datetimeorigination int, 
	cdrrecordtype float, 
	destcause_location float, 
	destcause_value float, 
	destipaddr text, 
	destlegcallidentifier int, 
	destmediacap_maxframesperpacket int, 
	destmediacap_payloadcapability int, 
	destmediatransportaddress_ip text, 
	destmediatransportaddress_port int, 
	destnodeid int, 
	destprecedencelevel float, 
	destspan int, 
	finalcalledpartynumber int, 
	finalcalledpartyunicodeloginuserid text, 
	globalcallid_callid int, 
	globalcallid_callmanagerid int, 
	incomingprotocolcallref text, 
	incomingprotocolid int, 
	originalcalledpartynumber text, 
	origlegcallidentifier int, 
	origrsvpaudiostat float, 
	origrsvpvideostat float, 
	origvideotransportaddress_port int, 
	outgoingprotocolcallref text, 
	outgoingprotocolid int, 
	PRIMARY KEY ((datetimeorigination), num))
);

CREATE TABLE IF NOT EXISTS project.t3 (
    num bigint,
    datetimeorigination timestamp, 
    datetimeconnect timestamp, 
	datetimedisconnect timestamp, 
	destcallterminationonbehalfof int, 
	destdevicename text, 
	destrsvpaudiostat float, 
	destrsvpvideostat float, 
	destvideocap_bandwidth int, 
	destvideocap_bandwidth_channel2 int, 
	destvideocap_codec float, 
	destvideocap_codec_channel2 float, 
	destvideocap_resolution float, 
	destvideocap_resolution_channel2 float, 
	destvideochannel_role_channel2 int, 
	destvideotransportaddress_ip text, 
	destvideotransportaddress_ip_channel2 text, 
	destvideotransportaddress_port int, 
	destvideotransportaddress_port_channel2 int, 
	duration int, 
	finalcalledpartynumberpartition text, 
	lastredirectdn text, 
	lastredirectdnpartition text, 
	numberpartition text, 
	origcalledpartyredirectonbehalfof int, 
	origcallterminationonbehalfof int, 
	origdevicename text, 
	originalcalledpartynumberpartition text, 
	pkid text, 
	PRIMARY KEY ((num), destdevicename))
);


CREATE TABLE IF NOT EXISTS project.t4 (
    duration int,
    count int,
    PRIMARY KEY (duration, count))
);

CREATE TABLE IF NOT EXISTS project.t5 (
    callingnumber text,
    callcount int,
    PRIMARY KEY (callingnumber, callcount))
);

