package application;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.prefs.InvalidPreferencesFormatException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ProductivityPlusController {
    
	private List<Parent> styleableNodes = new ArrayList<Parent>();
	DraggableMaker draggableMaker = new DraggableMaker();
	
    @FXML
    private  AnchorPane mainWorkspace;
    @FXML
    private ToggleGroup themeGroup;
    @FXML
    private RadioMenuItem modernaThemeButton;
    @FXML
    private RadioMenuItem caspianThemeButton;
    @FXML
    private RadioMenuItem SunsetThemeButton;
    @FXML
    private RadioMenuItem DeepOceanThemeButton;
    @FXML
    private RadioMenuItem midnightThemeButton;
    @FXML
    private Label welcomeLabel;
    @FXML
    private Menu menuModules;
    
    Properties  prop ;
    
    @FXML
    void onSetNameClick(ActionEvent event) {
    	VBox popupVbox = new VBox();
    	popupVbox.setAlignment(Pos.CENTER);
    	TextField newUsernameTextField = new TextField("Enter desired username");
    	Button confirmRename = new Button("Set username");
    	final Stage renameUserWindow = new Stage();
    	renameUserWindow.initModality(Modality.APPLICATION_MODAL);
    	renameUserWindow.initOwner(null);
    	popupVbox.getChildren().addAll(newUsernameTextField,confirmRename);
    	Scene popupWindowScene = new Scene(popupVbox);
    	renameUserWindow.setScene(popupWindowScene);
    	renameUserWindow.show();
    	
    	confirmRename.setOnAction(Event ->{ //Renames module and then closes popup box
    		String newUsername = newUsernameTextField.getText();
    		try {
				ConfigReader.changeValue("user",  newUsername);
			} catch (IOException | InvalidPreferencesFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    		renameUserWindow.close();
    	});
    }
    
    @FXML
    void onImportModuleClick(ActionEvent event) {
    }
    
	@FXML
	void onThemeRadioButtonSelected(ActionEvent event) throws IOException, InvalidPreferencesFormatException {
		String pathToStyleSheet = (String) themeGroup.getSelectedToggle().getUserData();
		ConfigReader.changeValue("theme", pathToStyleSheet);
		System.out.print(pathToStyleSheet);
		setUserTheme(pathToStyleSheet);
	}
    
    public void initialize() throws IOException { //Runs this code block when the fxml loads
		
    	String pathToCssFiles = "/css/";
		modernaThemeButton.setUserData(pathToCssFiles +"modena.css");
		caspianThemeButton.setUserData(pathToCssFiles +"caspian.css");
		midnightThemeButton.setUserData(pathToCssFiles +"MidnightSkyTheme.css");
		SunsetThemeButton.setUserData(pathToCssFiles +"SunsetTheme.css");
		DeepOceanThemeButton.setUserData(pathToCssFiles +"DeepOceanTheme.css");
		prop = ConfigReader.readConfig(); 
		Boolean showAbout = Boolean.valueOf(prop.getProperty("showAboutOnLaunch")); //Retrieving boolean value from config file
		
		generateModuleMenuItemInfo();
		
		String userTheme = prop.getProperty("theme");
		if (userTheme != null) {
			setUserTheme(userTheme);
		}
		if(showAbout == false) {//If property is false don't show module
			System.out.println("Not showing about module, as per config");
		}
		else { //If true, shows module		
			createModule("../modules/about/aboutModule.fxml",.5f,.5f); //Creates the about module to tell users about our app
		}

	}
    
	private void setUserTheme(String pathToStyleSheet) {
		//Have to add a delay because otherwise it cant find the mainworkspace during initlization
		Timer delay = new Timer();
		TimerTask loadUserStyleSheet = new TimerTask() {
			@Override
			public void run() {
				Scene scene = mainWorkspace.getScene();
				scene.getStylesheets().clear();
				scene.getStylesheets().add(pathToStyleSheet);
			}
		};
		delay.schedule(loadUserStyleSheet, 300);
	}
    private void generateModuleMenuItems(String name, String path) { //use to create a menu Item with a createModule action on start
    	
		MenuItem menuItem = new MenuItem();
		menuItem.setText(name);
		menuItem.setOnAction(Event ->{ createModule(path);});
		menuModules.getItems().add(menuItem);
    }
    
    private void generateModuleMenuItemInfo() {
	    File dir = new File("./src/modules");
		File[] modules = dir.listFiles();
		for (File module : modules) {
			String name = module.getName();
			String path = "../modules/";
			File[] files = module.listFiles();
			for (File file : files) {
				if (file.getName().endsWith(".fxml")) {
					path = path + name + "/" + file.getName();
				}
			}
			System.out.println(name);
			System.out.println(path);
			generateModuleMenuItems(name,path);
		}
    }
    private void createModule(String m){  // uses a file path written as a string to locate the fxml file
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(m)));
    	Node miniModule = null;
		try {
			miniModule = moduleLoader.load();		
			FXMLLoader baseModuleLoader  = new FXMLLoader(getClass().getResource("baseModule.FXML"));
			VBox baseModuleVBox =  baseModuleLoader.load();
			baseModuleController baseController =  baseModuleLoader.getController();
			baseController.setTitle(ConvertFilePathToTitle(m));
			baseModuleVBox.getChildren().add(miniModule);
			DraggableMaker.makeDraggable(baseModuleVBox);
	    	mainWorkspace.getChildren().add(baseModuleVBox); //Adds the baseVBox(with the mini module) to the mainWorkspace;
		} 
		catch (IOException e) {
			//System.err.print("Java yelled at me if I didnt put this in..No clue what it does");
			//e.getStackTrace();
			e.printStackTrace();
		}
		catch(IllegalStateException e) {
			System.err.println("File could not be found, make sure file path is correct");
		}
		finally {
			if(miniModule == null) {
				System.err.print("FXML module could not find controller\n Make sure controller path in the FXML file is formatted like this\n Modules.NAMEOFCONTROLLERCLASS");
			}
			else {
				System.out.println("Succesfully loaded ");
			}			
		}
    }
    
    //Overloaded method to allow creation of modules at specific percentage across the x and y of the main workspace
    //For example values of .5 and .5 would place the module in the center of the mainworkspace
    private void createModule(String m, double percentageOfScreenWidth, double perctanageOfScreenHeight){
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(m)));
    	Node miniModule = null;
		try {
			miniModule = moduleLoader.load();		
			FXMLLoader baseModuleLoader  = new FXMLLoader(getClass().getResource("baseModule.FXML"));
			VBox baseModuleVBox =  baseModuleLoader.load();
			baseModuleController baseController =  baseModuleLoader.getController();
			baseController.setTitle(ConvertFilePathToTitle(m));
			baseModuleVBox.getChildren().add(miniModule);
			DraggableMaker.makeDraggable(baseModuleVBox);
	    	mainWorkspace.getChildren().add(baseModuleVBox); //Adds the baseVBox(with the mini module) to the mainWorkspace;
	    	

	    	//TODO: Eventually try to center the module because right now the it spawns the module from its top left corner so things look offset
	    	baseModuleVBox.relocate( (mainWorkspace.getWidth() * percentageOfScreenWidth) ,mainWorkspace.getHeight() * perctanageOfScreenHeight);  
		} 
		catch (IOException e) {
			System.err.println("Java yelled at me if I didnt put this in..No clue what it does");
		}
		catch(IllegalStateException e) {
			System.err.println("File could not be found, make sure file path is correct");
		}
		finally {
			if(miniModule == null) {
				System.err.println("FXML module could not find controller\n Make sure controller path in the FXML file is formatted like this\n Modules.NAMEOFCONTROLLERCLASS");
			}
			else {
				System.out.println("Succesfully loaded " );
			}
			
		}
    }
    
    private String ConvertFilePathToTitle(String filePath) {
    	StringBuilder title = new StringBuilder(filePath);
    	title.delete(title.indexOf("Module.fxml"),title.length()); //Removes Module.fxml from the path
    	title.delete(0,title.lastIndexOf("/")+1); //Deletes everything before and including the last /
    	title.replace(0, 1, title.substring(0, 1).toUpperCase()); //Capitilizes the first letter
    	System.out.print(title.toString());
    	
    	return title.toString();
    }




    



}


