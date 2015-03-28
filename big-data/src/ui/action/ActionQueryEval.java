package ui.action;

import ui.dao.ConfigAccess;
import ui.model.ActionId;
import ui.model.Constants;

/**
 * Subclass of Action, provides the action behavior for executing 
 * queries against the Cassandra database. Requires a ActionId as some
 * queries require slightly different behaviors.
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public class ActionQueryEval extends Action {

	protected String query;
	
	/**
	 * Default constructor. Calls superclass constructor
	 * @param _id	Id of the action to instantiate
	 */
	public ActionQueryEval(ActionId _id) {
		super(_id);
	}

	/**
	 * Initializes the query execution process. Prompts the user for 
	 * confirmation. The return boolean is used to decide the outcome
	 * of executing the query.
	 * 
	 * @return		Return false if the user enters 'N'
	 * @return 		Return true if the user enters 'Y'
	 */
	@Override
	public boolean init() {
		System.out.println(Constants.getActionGreeting(id));
		return filterInput();
	}
	
	/**
	 * Handles input validation. Continues to prompt the user for input until
	 * one of 'N/n' or 'Y/y' is input.
	 *
	 * @return		false, if the user enters 'N/n'
	 * @return		true, if the user enters 'Y/y'
	 */
	private boolean filterInput() {
		String in = "";
		while (true) {
			System.out.print(Constants.getActionPrompts(id));
			in = recieveInput();
			if (in.toUpperCase().equals("Y"))
				return true;
			if (in.toUpperCase().equals("N"))
				return false;
		}
	}

	/**
	 * TODO: Implement
	 */
	@Override
	public void execute() {
		System.out.println("Executing Query: " + id);
		
		//Get connection strings and other connection information
		
		ConfigAccess ca = new ConfigAccess();
		
		//ca.setConfig("database", "hello world");
		System.out.println(ca.getConfig("ip"));
		
		//beginTimer();
		
		//execteQuery();
		
		//endTimer();
		
		System.out.println("The query has been executed successfully.");
		
	}

	/**
	 * TODO: Implement
	 */
	@Override
	public void tearDown() {
		System.out.println("Teardown processes successfully executed.");
	}

}
