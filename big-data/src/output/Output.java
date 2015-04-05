package output;


import java.io.File;
import cass.Application;


/**
 * this is the entry point of the application. It will infinitely output newly created tables
 * and then load them into the Cassandra cluster. 
 * invocation: sudo nohup java -jar DatabaseGenerator-1.0-SNAPSHOT.one-jar.jar
 * At this point one should record the pid of this process, because when you want to 
 * stop generation you're going to go sudo kill <pidof this program>.
 * http://stackoverflow.com/questions/2724725/best-way-to-daemonize-java-application-on-linux
 * http://www.cyberciti.biz/tips/nohup-execute-commands-after-you-exit-from-a-shell-prompt.html
 * @author slmyers
 *
 */
public class Output {
	/** Application stores information entered at runtime */
	public static Application app;
	/** The data creation and data loading is ran in four threads */
	public static final int NTHREDS = 4;
	public static File[] files;
	
	public static void main(String[] args) throws Exception {
		app = Application.getApp();
		if(args[0].equalsIgnoreCase("demo")){
			Application.RUN_TYPE = Application.DEMO;
		}

		else if(args[0].equalsIgnoreCase("project")){
			Application.RUN_TYPE = Application.PROJ;
		}
		else if(args[0].equalsIgnoreCase("mod_project")){
			Application.RUN_TYPE = Application.MOD_PROJ;
		}
		else if(args[0].equalsIgnoreCase("mod_demo")){
			Application.RUN_TYPE = Application.MOD_DEMO;
		}
		else{
			System.out.println("keyspace is neither demo nor project. System exiting.");
			System.exit(-1);
		}
		Application.NUM_ROWS = Integer.parseInt(args[1]);
		Stream stream = new Stream();
		// duration ranges from 0 to 6000s in model
		stream.createT4(6000);
		// two hundred thousand phone numbers in model
		stream.createT5(200000);
		stream.runStream();

		System.exit(0);
}

	

	
	
	
	

}
