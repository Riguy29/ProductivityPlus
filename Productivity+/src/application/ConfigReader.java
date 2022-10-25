package application;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.prefs.InvalidPreferencesFormatException;
import java.util.prefs.Preferences;

public class ConfigReader {
	private static Properties BaseConfig;
	private final static String fileName = "config.properties";
    public static Properties readConfig() throws IOException {

        FileInputStream fis = null;
        Properties prop = null;
        fis = new FileInputStream(fileName);
        // create Properties class object
        prop = new Properties();
        // load properties file into it
        prop.load(fis);
        
        fis.close();
        
 
        return prop;
    }
    public static void changeValue(String propertyName, String value) throws IOException, InvalidPreferencesFormatException {
    	FileInputStream in = new FileInputStream(fileName);
        Properties config = new Properties();      
        config.load(in);
    	//System.out.println(config);
    	FileOutputStream out = new FileOutputStream(fileName);
    	//System.out.println(config);
        
        Enumeration<?> propertyList = config.propertyNames();
        
        /*
         * Loop through propety list and set each property again before changing one
         * This is needed because the store function ovverides the entire file
         * This is not a great solution but its what we got 
         */
        while(propertyList.hasMoreElements()) {
        	//System.out.println(propertyList.nextElement());
        	String key = (String) propertyList.nextElement();
        	//System.out.println(key);
        	config.setProperty(key, config.getProperty(key));
        }
        config.setProperty(propertyName, value);
        
        config.store(out, null);
        out.close();
        
    }
    private void setAllProperties() {

    	
    }
}
