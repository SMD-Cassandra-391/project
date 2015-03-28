package ui.model;

/**
 * Object representing a menu option. Contains an id and
 * a text representation of the option to be displayed to the user.
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public class MenuOption {

	private int id;
	private String description;
	
	/**
	 * Default constructor. Sets the id and description
	 * 
	 * @param _id		Option ID
	 * @param _descr	Option description
	 */
	public MenuOption(int _id, String _descr) {
		this.id = _id;
		this.description = _descr;
	}

	public int getId() {
		return id;
	}

	public String getDescription() {
		return description;
	}
	
}
