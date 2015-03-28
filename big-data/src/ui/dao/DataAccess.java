package ui.dao;

/**
 * Singleton class that handles all interactions with the database server.
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public class DataAccess {

	private static DataAccess instance;
	
	/**
	 * Default constructor.
	 * 
	 * TODO: Initialize connection strings and ready for connection to 
	 * the server.
	 */
	private DataAccess() {
		
	}
	
	/**
	 * Returns an instance of the DAO object
	 * 
	 * @return		DataAccess object to handle interactions w/ server
	 */
	public static DataAccess getInstance() {
		if (instance == null) {
			instance = new DataAccess();
		}
		return instance;
	}
	
	/**
	 * Execute the query supplied in the args.
	 * 
	 * TODO: Implement.
	 * 
	 * @param query	The query to be executed by the database
	 * @return		The dataset returned by the database
	 */
	public boolean executeQuery(String query) {
		
		
		return true;
	}
	
	/**
	 * Loads the data from the supplied filename into the database.
	 * 
	 * @param filename	The name of the file to be loaded
	 * @return			True, if the load was successful
	 * @return			False, if the load was unsuccessful
	 */
	public boolean loadData(String filename) {
		return true;
	}
	
}
