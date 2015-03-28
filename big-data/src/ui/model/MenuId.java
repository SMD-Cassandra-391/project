package ui.model;

/**
 * Enumeration of menus. Reason for action IDs is that each
 * menu can have slightly different behaviors, although not 
 * different enough to justify a new subclass.
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public enum MenuId {
	MAIN_MENU,
	DATA_CREATION,
	QUERY_SELECTION,
	EXIT;
}
