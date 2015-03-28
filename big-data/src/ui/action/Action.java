package ui.action;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import ui.model.ActionId;


/**
 * Abstract class that defines the behavior for all action
 * objects. An action object is one that performs a certain behavior,
 * such as performing queries against the database, generating data,
 * or loading data.
 *
 * @author  Michael Kmicik
 * @version   1.0
 */
public abstract class Action {

	// Some actions can have minor differences in behavior, but not enough to 
	// justify a new subclass
	protected ActionId id;				
	
	public abstract boolean init();
	public abstract void execute();
	public abstract void tearDown();
	
	/**
	 * Default constructor; sets the action identifier
	 * @param _id	The id of the action type
	 */
	public Action(ActionId _id) {
		this.id = _id;
	}
	
	/**
	 * Reads data from standard in and returns it as a String
	 * 
	 * @return		String representation of the user's input
	 */
	public String recieveInput() {

		InputStreamReader sr =new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(sr);

		String in;
		while (true) {
			try {
				in = br.readLine();
				return in;
			}
			catch (Exception e) {
				
			}
		}
	}
}
