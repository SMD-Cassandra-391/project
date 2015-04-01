package output;

import java.io.File;
import loader.JmxBulkLoader;


/**
 * instantiates a JmxBulkLoader to load SSTables to Cassandra cluster
 * @author slmyers
 */

public class LoadThread implements Runnable {
	/**
	 * folder containing SSTables to load
	 */
	private String folder; 
	public LoadThread(String folder){
		this.folder = folder;
	}
	
	/**
	 * JmxBulkLoader requires the absolute path to the file containing the SSTables
	 */
	@Override
	public void run() {
		File f = new File(this.folder);
		File path = f.getAbsoluteFile();
		JmxBulkLoader loader = null;
		try {
			loader = new JmxBulkLoader("localhost", 7199);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		loader.bulkLoad(path.toString());
	}

}
