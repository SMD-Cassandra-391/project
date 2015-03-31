package output;




import cass.Application;
import cass.SSTwriter;



public class DataThread implements Runnable{
	private int tableId;
	private String folder;
	SSTwriter writer; 
	public DataThread(int tableId, String folder){
		this.tableId = tableId;
		this.folder = folder;

	}
	
	public void run(){
		this.writer = new SSTwriter(Application.NUM_ROWS, this.folder, this.tableId); 
		writer.execute();
		writer.close();
			
		
	}
}