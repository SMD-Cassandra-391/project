package ui.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

/**
 * Class responsible for getting and setting configuration
 * values in the properties file.
 * 
 * @author Michael Kmicik
 * @version 1.0
 */
public class ConfigAccess {
	
	/**
	 * Return the config value specified by the key
	 * 
	 * @param property	The key value of the property to retrieve
	 * @return			The property specified by the key if it exists
	 * @return			null if the property key does not exist
	 */
	public String getConfig(String property) {
		
		Properties prop = new Properties();
		InputStream input = null;
	 
		try {
	 
			input = new FileInputStream("config.properties");
	 
			// Load the properties file
			prop.load(input);
	 
			// Return the property value
			return prop.getProperty(property);
	 
		} catch (IOException ex) {
			return null;
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	/**
	 * Set the config key and value specified by the args.
	 * 
	 * @param key	Key value
	 * @param value	Value associated with the key
	 * @return		true if the write was successful
	 * @return 		false if the write was unsuccessful
	 */
	public boolean setConfig(String key, String value) {
		
		Properties prop = new Properties();
		OutputStream output = null;
	 
		try {
	 
			output = new FileOutputStream("config.properties");
	 
			// Set the key value pair
			prop.setProperty(key, value);
			
			// Save properties to project root folder
			prop.store(output, null);
	 
			// Return success
			return true;
		} catch (IOException io) {
			// Return failure
			return false;
		} finally {
			if (output != null) {
				try {
					output.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	 
		}
	}
	
}
