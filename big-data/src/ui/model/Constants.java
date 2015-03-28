package ui.model;

import java.util.ArrayList;
import java.util.HashMap;

import ui.model.MenuId;


/**
 * Contains all the UI constants. This includes String constants,
 * greetings, prompts, menu options, etc. Also defines methods for
 * retrieving these constants.
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public class Constants {

	/* User prompts */
	public static String greeting = "Welcome to the CDR Retrieval System Developed by Group 4!";
	public static String section = "Cmput 391 Section H1";
	public static String members = "Michael Kmicik, Steven Meyers, Divyank";
	public static String goodbye = "Application Ended.";
	public static String greetingHdrFtr = "---------------------------------------------";
	
	/* Queries */
	public static String SELECT = String.format("this {0} {1}", "hello", "world");
	
	/* Menu Constants */
	private static HashMap<MenuId, String> menuPrompts;
	private static HashMap<MenuId, String> menuGreetings;
	private static HashMap<MenuId, ArrayList<MenuOption>> menuOptions;
	
	/* Action Constants */
	private static HashMap<ActionId, String> actionPrompts;
	private static HashMap<ActionId, String> actionGreetings;
	
	/* Getters */
	public static ArrayList<MenuOption> getMenuOptions(MenuId id) {
		return menuOptions.get(id);
	}
	
	public static String getMenuGreeting(MenuId id) {
		return menuGreetings.get(id);
	}
	
	public static String getMenuPrompts(MenuId id) {
		return menuPrompts.get(id);
	}
	
	public static String getActionGreeting(ActionId id) {
		return actionGreetings.get(id);
	}
	
	public static String getActionPrompts(ActionId id) {
		return actionPrompts.get(id);
	}
	
	/**
	 * Initializes all of the data
	 */
	public static void init() {
		initMenuPrompts();
		initMenuGreetings();
		initMenuOptions();
		
		initActionPrompts();
		initActionGreetings();
	}
	
	/**
	 * Initializes menu prompts
	 */
	private static void initMenuPrompts() {
		menuPrompts = new HashMap<MenuId, String>();
		
		menuPrompts.put(MenuId.MAIN_MENU, "Input the id of the corresponding menu item > ");
		menuPrompts.put(MenuId.DATA_CREATION, "Clear existing database data or generate new data > ");
		menuPrompts.put(MenuId.QUERY_SELECTION, "The following queries can be executed on the dataset > ");
	}
	
	/**
	 * Initializes menu greetings
	 */
	private static void initMenuGreetings() {
		menuGreetings = new HashMap<MenuId, String>();
		
		menuGreetings.put(MenuId.MAIN_MENU, "Main Menu");
		menuGreetings.put(MenuId.DATA_CREATION, "Data Management");
		menuGreetings.put(MenuId.QUERY_SELECTION, "Query Selection");
	}
	
	/**
	 * Initializes menu options
	 */
	private static void initMenuOptions() {
		
		menuOptions = new HashMap<MenuId, ArrayList<MenuOption>>();
		ArrayList<MenuOption> menu;
		int i;
		
		// Main Menu
		menu = new ArrayList<MenuOption>();
		i = 0;
		menu.add(new MenuOption(++i, "Data Management"));
		menu.add(new MenuOption(++i, "Execute Queries"));
		menu.add(new MenuOption(++i, "Exit"));
		menuOptions.put(MenuId.MAIN_MENU, menu);
		
		// Query Selection Menu
		menu = new ArrayList<MenuOption>();
		i = 0;
		menu.add(new MenuOption(++i, "Execute Query 1"));
		menu.add(new MenuOption(++i, "Execute Query 2"));
		menu.add(new MenuOption(++i, "Execute Query 3"));
		menu.add(new MenuOption(++i, "Execute Query 4"));
		menu.add(new MenuOption(++i, "Execute Query 5"));
		menu.add(new MenuOption(++i, "Back"));
		menuOptions.put(MenuId.QUERY_SELECTION, menu);
		
		// Data Creation
		menu = new ArrayList<MenuOption>();
		i = 0;
		menu.add(new MenuOption(++i, "Generate Data"));
		menu.add(new MenuOption(++i, "Clear Data"));
		menu.add(new MenuOption(++i, "Back"));
		menuOptions.put(MenuId.DATA_CREATION, menu);
	}
	
	/**
	 * Initializes action prompts
	 */
	private static void initActionPrompts() {
		actionPrompts = new HashMap<ActionId, String>();
		
		actionPrompts.put(ActionId.QUERY_1, "Continue? (y/n) > ");
		actionPrompts.put(ActionId.QUERY_2, "Continue? (y/n) > ");
		actionPrompts.put(ActionId.QUERY_3, "Continue? (y/n) > ");
		actionPrompts.put(ActionId.QUERY_4, "Continue? (y/n) > ");
		actionPrompts.put(ActionId.QUERY_5, "Continue? (y/n) > ");
		actionPrompts.put(ActionId.GENERATE_DATA, "Size (Gb) > ");
	}
	
	/**
	 * Initializes action greetings
	 */
	private static void initActionGreetings() {
		actionGreetings = new HashMap<ActionId, String>();
		
		actionGreetings.put(ActionId.QUERY_1, "Query to be evalutated: \n");
		actionGreetings.put(ActionId.QUERY_2, "Query to be evalutated: \n");
		actionGreetings.put(ActionId.QUERY_3, "Query to be evalutated: \n");
		actionGreetings.put(ActionId.QUERY_4, "Query to be evalutated: \n");
		actionGreetings.put(ActionId.QUERY_5, "Query to be evalutated: \n");
		actionGreetings.put(ActionId.GENERATE_DATA, "Enter the amount of data you would like to generate.");
	}
}
