package ui.controller;

import ui.menu.HomeMenu;
import ui.menu.Menu;
import ui.model.Constants;

/**
 * This class instantiates menu objects and calls them to display 
 * menu options and handle input from users. Subclasses different Menu 
 * objects to handle different menu levels.
 *
 * @author  Michael Kmicik
 * @version   1.0
 */
public class MenuController {

	// The current menu that the user is on. Handles prompts and
	// input accordingly
	private Menu menu;

	/**
	 * Main method. Handles initialization, execution, and cleanup
	 * 
	 * @param args	Command line arguments
	 */
	public static void main(String[] args) {
		MenuController m = new MenuController();
		
		m.init();
		m.run();
		m.end();
	}
	
	/**
	 * Handles initialization. Initializes constants and outputs greeting
	 * to the user.
	 */
	private void init() {
		Constants.init();
		System.out.println(Constants.greeting);
		System.out.println(Constants.section);
		System.out.println(Constants.members);
	}

	/**
	 * Execution of the menu interface. For each level of the 
	 * menu, it displays a greeting, options, and a prompt. It then
	 * recieves input and handles it accordingly.
	 */
	private void run() {

		int in;
		menu = new HomeMenu();
		
		while (true) {

			menu.displayGreeting();
			menu.displayOptions();
			menu.displayPrompt();
			in = menu.recieveInput();
			menu = menu.executeInput(in);
			clearConsole();
		}
	}
	
	/**
	 * Is supposed to clear all text from the console. Currently NOT 
	 * tested, and may not work properly.
	 * 
	 * TODO: Fix this method and ensure it works properly.
	 */
	private void clearConsole() {
		{
		    try
		    {
		        final String os = System.getProperty("os.name");
		        
		        if (os.contains("Windows"))
		        {
		            Runtime.getRuntime().exec("cls");
		        }
		        else
		        {
		            Runtime.getRuntime().exec("clear");
		        }
		    }
		    catch (final Exception e)
		    {
		        
		    }
		}
	}
	
	/**
	 * Handle any cleanup actions and kill the application.
	 */
	private void end() {
		System.exit(0);
	}
}
