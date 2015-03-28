package ui.menu;

import ui.model.MenuId;

/**
 * Menu object for data management options
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public class DataManagementMenu extends Menu {

	/**
	 * Default constructor. Sets the id of the menu.
	 */
	public DataManagementMenu() {
		this.id = MenuId.DATA_CREATION;
	}
	
	/**
	 * Executes the correct action based on the user input
	 * 
	 * @param in 	int corresponding to the user's choice
	 */
	@Override
	public Menu executeInput(int in) {
		switch  (in) {
		case 1:
			return this;
		case 2:
			return this;
		case 3:
			return new HomeMenu();
		default:
			return this;
		}
	}

}
