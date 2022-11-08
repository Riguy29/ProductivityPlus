package modules.about;

import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.prefs.InvalidPreferencesFormatException;

import application.ConfigReader;
import application.baseModuleInitalizer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;

public class aboutModuleController extends baseModuleInitalizer {
	
	String loadedProperty= null;
	@Override
	public void initialize() throws IOException  {
		super.initialize();
		baseController.setTitle("About");
		Properties prop;
		Boolean showAbout = false;
		prop = ConfigReader.readConfig();
		loadedProperty = "showAboutOnLaunch";
		showAbout = Boolean.valueOf(prop.getProperty(loadedProperty));

		showAboutCheckBox.setSelected(!showAbout);

	}
	
    @FXML
    private CheckBox showAboutCheckBox;
    @FXML
    void dontShowAgainClicked(ActionEvent event) throws IOException, InvalidPreferencesFormatException {
    	
		Properties  prop = ConfigReader.readConfig(); 
		Boolean showAbout = Boolean.valueOf(prop.getProperty(loadedProperty));
    	showAbout = !showAbout; //Sets the boolean to be the opposite of whatever it was, so if it was false it will be true 
    	//System.out.printf("%s %s", loadedProperty, Boolean.toString(showAbout));
    	ConfigReader.changeValue(loadedProperty, Boolean.toString(showAbout));
    }
    
}
