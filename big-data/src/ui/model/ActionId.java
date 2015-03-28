package ui.model;

/**
 * Enumeration of actions. Reason for action IDs is that each
 * action can have slightly different behaviors, although not 
 * different enough to justify a new subclass. For example, each 
 * query action outputs different information to the user, and 
 * thus requires a way to differentiate itself from the others. 
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public enum ActionId {

	QUERY_1,
	QUERY_2,
	QUERY_3,
	QUERY_4,
	QUERY_5,
	GENERATE_DATA;
	
}
