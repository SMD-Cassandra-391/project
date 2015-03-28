package ui.menu;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

import ui.model.Constants;
import ui.model.MenuId;
import ui.model.MenuOption;


/**
 * Abstract class defining the behavior for all child menus. Also 
 * implements many of the shared functionalities needed by all menus
 * such as reading in input and displaying menu options.
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public abstract class Menu {

	// The id corresponding to the menu type instantiated
	MenuId id;
	
	/**
	 * Default constructor, does nothing right now.
	 */
	public Menu() {
	
	}
	
	/**
	 * Must execute input supplied to it by the user. Functionality must
	 * be implemented by each child menu as they may differ.
	 * 
	 * @param n		int corresponding to the choice input by the user
	 * @return		Menu object corresponding to the user's input
	 */
	public abstract Menu executeInput(int n);
	
	/**
	 * Parses and handles input given to it by the user. Ensures input 
	 * is supplied in the correct format.
	 * 
	 * @return		int representing the user's choices
	 */
	public int recieveInput() {
		
		InputStreamReader sr =new InputStreamReader(System.in);
		BufferedReader br=new BufferedReader(sr);
		
		int in;
		while (true) {
			try {
				in = Integer.valueOf(br.readLine());
				return in;
			}
			catch (Exception e) {
				System.out.print(Constants.getMenuPrompts(id));
			}
		}
	}
	
	/**
	 * Formats and outputs the menu's options.
	 */
	public void displayOptions() {
		ArrayList<MenuOption> options = Constants.getMenuOptions(id);
		for (MenuOption o : options) {
			System.out.println(o.getId() + ": " + o.getDescription());
		}
	}
	
	/**
	 * Formats and displays the menu's greeting messages, often
	 * just the title
	 */
	public void displayGreeting() {
		System.out.println();
		System.out.println(Constants.greetingHdrFtr);
		System.out.println(Constants.getMenuGreeting(id));
		System.out.println(Constants.greetingHdrFtr);
	}

	/**
	 * Displays the user prompt.
	 */
	public void displayPrompt() {
		System.out.print(Constants.getMenuPrompts(id));
	}
	
	/**
	 * Outputs a farewell message and kills the program.
	 */
	protected void exit() {
		System.out.println();
		System.out.println(Constants.goodbye);
		System.exit(0);
	}
}
