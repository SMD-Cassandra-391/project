package ui.controller;

import ui.action.Action;
import ui.action.ActionQueryEval;
import ui.model.ActionId;

/**
 * This class instantiates action objects and calls them to execute
 * their initialization, execution, and post-execution cleanup.
 *
 * @author  Michael Kmicik
 * @version   1.0
 */
public class ActionController {

	private Action act;

	/**
	 * Determines the action to instantiate and runs calls the execution
	 * cycle on it.
	 * 
	 * @param 	id	the id of the action to be instantiated
	 * @return		void
	 */
	public void run(ActionId id) {
		// Figure out what kind of action is to be executed
		act = getAction(id);
		
		// Initialization must be successful before execution can begin
		if (act.init()) {
			act.execute();
		}
		
		// Always execute in order to close connections and processes
		act.tearDown();
		return;
	}

	/**
	 * Return the correct action object based on the ID.
	 * 
	 * @param 	id	the id of the action type to instantiate
	 * @return 		the appropriate Action object
	 */
	private Action getAction(ActionId id) {
		switch  (id) {
		case GENERATE_DATA:
			return new ActionQueryEval(id);
		default:
			return new ActionQueryEval(id);
		}
	}
}
