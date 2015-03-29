package cass;




public class RunShell {
	private static final int BUFFER_SIZE = 150;
	private static final String SPACE = " ";
	private static final String LOCAL_HOST = "127.0.0.1";
	
	
	public static void SSTcmd(String file){
		StringBuilder cmd = new StringBuilder(BUFFER_SIZE);
		cmd.append("./../../apache-cassandra-2.1.3/bin/sstableloader");
		cmd.append(SPACE);
		cmd.append("-d");
		cmd.append(SPACE);
		cmd.append(LOCAL_HOST);
		cmd.append(SPACE);
		cmd.append(file);
		System.out.println(cmd.toString());
		executeCommand(cmd.toString());
	}
	
	private static void executeCommand(String command) {
		Process p;
		try {
			p = Runtime.getRuntime().exec(command);
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
