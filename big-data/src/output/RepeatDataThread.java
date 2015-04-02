package output;




import java.io.IOException;

import org.apache.cassandra.exceptions.InvalidRequestException;

import cass.Application;
import cass.SSTwriter;

/**
 * DataThread loads an SSTwriter with the information required to create an
 * SSTable, for an appropriate table schema output to a folder determined by 
 * the tableId.
 * @author slmyers
 */

public class RepeatDataThread implements Runnable{
	/**the table to be written */
	private int tableId;
	/**the folder to write the data */ 
	private String folder;
	/** the SSTwriter that writes the data */
	private SSTwriter writer = null; 
	/** the seed to pass the writer */
	private int seed;
	/**
	 * 
	 * @param tableId ID of table to write
	 * @param folder where to write the SSTable
	 */
	public RepeatDataThread(int tableId, String folder, int seed){
		this.tableId = tableId;
		this.folder = folder;
		this.seed = seed;
	}
	
	/**
	 * This will instantiate the writer, and pass a message to the writer to begin
	 * writing. The writer is then closed.
	 */
	public void run(){
		this.writer = new SSTwriter(Application.NUM_ROWS, this.folder, this.tableId, this.seed); 
		try {
			writer.execute();
		} catch (InvalidRequestException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		writer.close();
			
		
	}
}