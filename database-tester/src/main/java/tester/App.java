package main.java.tester;

import com.datastax.driver.core.Cluster;
import com.datastax.driver.core.Host;
import com.datastax.driver.core.Metadata;
import com.datastax.driver.core.Session;
import com.datastax.driver.core.exceptions.ReadTimeoutException;

/**
 * tests queries against appropriate keyspace TODO: refactor so that query
 * strings are less cumbersome
 * 
 * @author slmyers
 *
 */

public class App {
	static Session session;
	static Cluster cluster;
	static String MOD_DEMO = "modified_demo";
	static String MOD_PROJ = "modified_project";
	static String DEMO = "demo";
	static String PROJ = "project";
	static String TYPE;
/*************************************************************************************************/	
	static String QUERY_ONE_MOD_PROJ = "SELECT huntpilotdn FROM "
			+ MOD_PROJ
			+ ".t1 WHERE  num = 36681769924989 "
			+ "AND    (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination) "
			+ "       > (1,1,1,1,1) "
			+ "AND    (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination) "
			+ "       < (5000,5000,5000,5000,5000);";

	static String QUERY_TWO_MOD_PROJ = "SELECT  destcause_location, destipaddr "
			+ "FROM 	 "
			+ MOD_PROJ
			+ ".t2 "
			+ "WHERE 	 num = 17306447014892 "
			+ "AND     datetimeorigination > 1 "
			+ "AND 	 datetimeorigination < 5000 ";
	static String QUERY_THREE_MOD_PROJ = "SELECT  origdevicename, duration "
			+ "FROM 	" + MOD_PROJ + ".t3 " + "WHERE 	num = 17702306473948 "
			+ "AND 	destdevicename IN ('a','f', 'g', 'h');";
	static String QUERY_FOUR_MOD_PROJ = "SELECT 	count, duration " + "FROM 	"
			+ MOD_PROJ + ".t4 "
			+ "WHERE 	duration IN (10,20,30,40,50,60,70,80,90,100) "
			+ "ORDER BY count DESC " + "LIMIT 10; ";
	static String QUERY_FIVE_MOD_PROJ = "SELECT 	callingnumber, callcount "
			+ "FROM 	"
			+ MOD_PROJ
			+ ".t5 "
			+ "WHERE 	callingNumber IN ('6214789','7350819', '1255452', '9151967', '9657707', '9998888') "
			+ "ORDER BY callcount ASC " + "LIMIT 6;";
/************************************************************************************************/
	static String QUERY_ONE_MOD_DEMO = "SELECT huntpilotdn FROM "
			+ MOD_DEMO
			+ ".t1 WHERE  num = 36681769924989 "
			+ "AND    (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination) "
			+ "       > (1,1,1,1,1) "
			+ "AND    (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination) "
			+ "       < (5000,5000,5000,5000,5000);";

	static String QUERY_TWO_MOD_DEMO = "SELECT  destcause_location, destipaddr "
			+ "FROM 	 "
			+ MOD_DEMO
			+ ".t2 "
			+ "WHERE 	 num = 17306447014892 "
			+ "AND     datetimeorigination > 1 "
			+ "AND 	 datetimeorigination < 5000 ";
	static String QUERY_THREE_MOD_DEMO = "SELECT  origdevicename, duration "
			+ "FROM 	" + MOD_DEMO + ".t3 " + "WHERE 	num = 17702306473948 "
			+ "AND 	destdevicename IN ('a','f', 'g', 'h');";
	static String QUERY_FOUR_MOD_DEMO = "SELECT 	count, duration " + "FROM 	"
			+ MOD_DEMO + ".t4 "
			+ "WHERE 	duration IN (10,20,30,40,50,60,70,80,90,100) "
			+ "ORDER BY count DESC " + "LIMIT 10; ";
	static String QUERY_FIVE_MOD_DEMO = "SELECT 	callingnumber, callcount "
			+ "FROM 	"
			+ MOD_DEMO
			+ ".t5 "
			+ "WHERE 	callingNumber IN ('6214789','7350819', '1255452', '9151967', '9657707', '9998888') "
			+ "ORDER BY callcount ASC " + "LIMIT 6;";
/***********************************************************************************************************/
	static String QUERY_ONE_PROJ = "SELECT huntpilotdn FROM "
			+ PROJ
			+ ".t1 WHERE  num = 36681769924989 "
			+ "AND    (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination) "
			+ "       > (1,1,1,1,1) "
			+ "AND    (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination) "
			+ "       < (5000,5000,5000,5000,5000);";

	static String QUERY_TWO_PROJ = "SELECT 	destcause_location, destipaddr "
			+ "FROM " + PROJ + ".t2 " + "WHERE 	datetimeorigination = 110 "
			+ "AND     num >= 10030531462782 "
			+ "AND     num <= 10043787914378;";
	static String QUERY_THREE_PROJ = "SELECT  origdevicename, duration "
			+ "FROM 	" + PROJ + ".t3 " + "WHERE 	num = 17702306473948 "
			+ "AND 	destdevicename IN ('a','f', 'g', 'h');";
	static String QUERY_FOUR_PROJ = "SELECT 	count, duration " + "FROM 	"
			+ PROJ + ".t4 "
			+ "WHERE 	duration IN (10,20,30,40,50,60,70,80,90,100) "
			+ "ORDER BY count DESC " + "LIMIT 10; ";
	static String QUERY_FIVE_PROJ = "SELECT 	callingnumber, callcount "
			+ "FROM 	"
			+ PROJ
			+ ".t5 "
			+ "WHERE 	callingNumber IN ('6214789','7350819', '1255452', '9151967', '9657707', '9998888') "
			+ "ORDER BY callcount ASC " + "LIMIT 6;";
/**********************************************************************************************************/
	static String QUERY_ONE_DEMO = "SELECT huntpilotdn FROM "
			+ DEMO
			+ ".t1 WHERE  num = 36681769924989 "
			+ "AND    (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination) "
			+ "       > (1,1,1,1,1) "
			+ "AND    (currentroutingreason, orignodeid, origspan, origvideocap_bandwidth, datetimeorigination) "
			+ "       < (5000,5000,5000,5000,5000);";

	static String QUERY_TWO_DEMO = "SELECT 	destcause_location, destipaddr "
			+ "FROM " + DEMO + ".t2 " + "WHERE 	datetimeorigination = 110 "
			+ "AND     num >= 10030531462782 "
			+ "AND     num <= 10043787914378;";
	static String QUERY_THREE_DEMO = "SELECT  origdevicename, duration "
			+ "FROM 	" + DEMO + ".t3 " + "WHERE 	num = 17702306473948 "
			+ "AND 	destdevicename IN ('a','f', 'g', 'h');";
	static String QUERY_FOUR_DEMO = "SELECT 	count, duration " + "FROM 	"
			+ DEMO + ".t4 "
			+ "WHERE 	duration IN (10,20,30,40,50,60,70,80,90,100) "
			+ "ORDER BY count DESC " + "LIMIT 10; ";
	static String QUERY_FIVE_DEMO = "SELECT 	callingnumber, callcount "
			+ "FROM 	"
			+ DEMO
			+ ".t5 "
			+ "WHERE 	callingNumber IN ('6214789','7350819', '1255452', '9151967', '9657707', '9998888') "
			+ "ORDER BY callcount ASC " + "LIMIT 6;";
/************************************************************************************************/
	
	public static void main(String[] args) {
		String queryOne;
		String queryTwo;
		String queryThree;
		String queryFour;
		String queryFive;
		if (args.length != 1) {
			System.out.println("incorrect usage");
			System.out
					.println("java -jar database-tester-0.0.1-SNAPSHOT.one-jar.jar <type>");
			System.out.println("where type is \"demo\", \" project \", \"modified_demo\" or \" modified_project \"");
		}
		System.out.println(args[0]);

		if (args[0].equalsIgnoreCase("modified_project")) {
			TYPE = new String(MOD_PROJ);
			queryOne = new String(QUERY_ONE_MOD_PROJ);
			queryTwo = new String(QUERY_TWO_MOD_PROJ);
			queryThree = new String(QUERY_THREE_MOD_PROJ);
			queryFour = new String(QUERY_FOUR_MOD_PROJ);
			queryFive = new String(QUERY_FIVE_MOD_PROJ);
		} else if (args[0].equalsIgnoreCase("modified_demo")) {
			TYPE = new String(MOD_DEMO);
			queryOne = new String(QUERY_ONE_MOD_DEMO);
			queryTwo = new String(QUERY_TWO_MOD_DEMO);
			queryThree = new String(QUERY_THREE_MOD_DEMO);
			queryFour = new String(QUERY_FOUR_MOD_DEMO);
			queryFive = new String(QUERY_FIVE_MOD_DEMO);
		} else if (args[0].equalsIgnoreCase("demo")) {
			TYPE = new String(DEMO);
			queryOne = new String(QUERY_ONE_DEMO);
			queryTwo = new String(QUERY_TWO_DEMO);
			queryThree = new String(QUERY_THREE_DEMO);
			queryFour = new String(QUERY_FOUR_DEMO);
			queryFive = new String(QUERY_FIVE_DEMO);
		} else if (args[0].equalsIgnoreCase("project")) {
			TYPE = new String(PROJ);
			queryOne = new String(QUERY_ONE_DEMO);
			queryTwo = new String(QUERY_TWO_DEMO);
			queryThree = new String(QUERY_THREE_DEMO);
			queryFour = new String(QUERY_FOUR_DEMO);
			queryFive = new String(QUERY_FIVE_DEMO);
		} else {
			throw new RuntimeException("type not recognized");
		}
		
		connect();
		
		try {
			testQuery(1, queryOne);
		} catch (ReadTimeoutException re) {
			System.out.println("timeout for query one.");
			System.out.println("----------------------");
		}
		try {
			testQuery(2, queryTwo);
		} catch (ReadTimeoutException re) {
			System.out.println("timeout for query two.");
			System.out.println("----------------------");
		}
		try {
			testQuery(3, queryThree);
		} catch (ReadTimeoutException re) {
			System.out.println("timeout for query three.");
			System.out.println("----------------------");
		}
		try {
			testQuery(4, queryFour);
		} catch (ReadTimeoutException re) {
			System.out.println("timeout for query four.");
			System.out.println("----------------------");
		}
		try {
			testQuery(5, queryFive);
		} catch (ReadTimeoutException re) {
			System.out.println("timeout for query five.");
			System.out.println("----------------------");
		}

		cluster.close();
	}

	private static void testQuery(int queryNum, String query)
			throws ReadTimeoutException {
		long start, end;
		long timeQueryOne, timeQueryTwo;
		System.out.println("QUERY " + queryNum);
		start = System.currentTimeMillis();
		session.execute(query);
		end = System.currentTimeMillis();
		timeQueryOne = end - start;

		start = System.currentTimeMillis();
		session.execute(query);
		end = System.currentTimeMillis();
		timeQueryTwo = end - start;

		System.out.println("First run = " + timeQueryOne + "ms");
		System.out.println("Second run = " + timeQueryTwo + "ms");
		System.out.println("----------------------------------------");
	}

	private static void connect() {
		cluster = Cluster.builder().addContactPoint("localhost").build();
		Metadata metadata = cluster.getMetadata();
		System.out.printf("Connected to cluster: %s\n",
				metadata.getClusterName());
		for (Host host : metadata.getAllHosts()) {
			System.out.printf("Datacenter: %s; Host: %s; Rack: %s\n",
					host.getDatacenter(), host.getAddress(), host.getRack());
		}

		session = cluster.connect();
	}
}
