package output;

import java.io.File;
import loader.JmxBulkLoader;
import cass.SSTwriter;

public class LoadThread implements Runnable {
	private String folder;
	SSTwriter writer; 
	public LoadThread(String folder){
		
		this.folder = folder;
	}
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
