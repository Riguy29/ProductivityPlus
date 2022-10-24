package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigReader {
	private static Properties config;
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
    public static void changeValue(String propertyName, String value) throws IOException {
    	FileOutputStream out = new FileOutputStream(fileName);
    	FileInputStream in = new FileInputStream(fileName);
        Properties config = new Properties();
        config.load(in);
        config.setProperty(propertyName, value);
        config.store(out, null);
        out.close();	
    }
}
