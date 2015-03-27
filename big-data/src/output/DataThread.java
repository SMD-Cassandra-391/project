package output;


import java.util.UUID;



public class DataThread implements Runnable{
	private String threadName;
	
	public DataThread(String tableDesc, String folder){
		threadName = new String(UUID.randomUUID().toString());
		
	}
	
	public void run(){
		
	}
	
	public void printInfo(){
		
	}
	

	
	public String getThreadName() {
		return threadName;
	}

	public void setThreadName(String threadName) {
		this.threadName = threadName;
	}
}