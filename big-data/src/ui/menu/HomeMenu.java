package ui.menu;

import ui.model.MenuId;

/**
 * Menu object for home options
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public class HomeMenu extends Menu {

	/**
	 * Default constructor, sets the id of the menu
	 */
	public HomeMenu() {
		this.id = MenuId.MAIN_MENU;
	}
	
	/**
	 * Instantiates the next menu level based on the user's choice
	 * 
	 * @param in 	int corresponding to the user's choice
	 */
	@Override
	public Menu executeInput(int in) {

		switch  (in) {
		case 1:
			return new DataManagementMenu();
		case 2:
			return new QueryEvaluationMenu();
		case 3:
			exit();
		default:
			return this;
		}
	}
}
