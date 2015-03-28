package ui.menu;

import ui.controller.ActionController;
import ui.model.ActionId;
import ui.model.MenuId;

/**
 * Menu object for query evaluation options
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public class QueryEvaluationMenu extends Menu {
	
	/** 
	 * Default constructor, sets the ID for the menu
	 */
	public QueryEvaluationMenu() {
		this.id = MenuId.QUERY_SELECTION;
	}
	
	/**
	 * Handles the input from the user.
	 * 
	 * @param in	int corresponding to the user's choice
	 * @return		Menu object corresponding to the next menu to display
	 */
	@Override
	public Menu executeInput(int in) {
		
		ActionController con = new ActionController();
		
		switch  (in) {
		case 1:
			con.run(ActionId.QUERY_1);
			return new QueryEvaluationMenu();
		case 2:
			con.run(ActionId.QUERY_2);
			return new QueryEvaluationMenu();
		case 3:
			con.run(ActionId.QUERY_3);
			return new QueryEvaluationMenu();
		case 4:
			con.run(ActionId.QUERY_4);
			return new QueryEvaluationMenu();
		case 5:
			con.run(ActionId.QUERY_5);
			return new QueryEvaluationMenu();
		case 6:
			return new HomeMenu();
		default:
			return this;
		}
	}

}
