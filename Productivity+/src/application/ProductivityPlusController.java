package application;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.prefs.InvalidPreferencesFormatException;

import modules.*;
import javafx.animation.FadeTransition;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioMenuItem;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;

public class ProductivityPlusController {
    
	private List<Parent> styleableNodes = new ArrayList<Parent>();
	DraggableMaker draggableMaker = new DraggableMaker();
    @FXML
    private  AnchorPane mainWorkspace;
    
	/*
	 * IMPORTANT!! 
	 * The module enum and the modulePath must be in the exact same
	 * order IE: index 0 of both corresponds to the about module
	 */
    private static enum module{
    	about,
    	stopwatch

    	
    }
    private static String modulepath = "../FXML_Files/aboutModule.fxml";
    
    private static String[] modulePaths =
    {
    	"../FXML_Files/aboutModule.fxml",	
    	"../FXML_Files/stopwatchModule.fxml"

    	
    };
    
    
    @FXML
    private ToggleGroup themeGroup;
    @FXML
    private RadioMenuItem SunsetThemeButton;

    @FXML
    private RadioMenuItem DeepOceanTheme;
    
    @FXML
    private RadioMenuItem midnightThemeButton;
    @FXML
    private Label welcomeLabel;
    Properties  prop ;

	public void initialize() throws IOException { //Runs this code block when the fxml loads
		
		midnightThemeButton.setUserData("MidnightSkyTheme.css");
		SunsetThemeButton.setUserData("SunsetTheme.css");
		prop = ConfigReader.readConfig(); 
		Boolean showAbout = Boolean.valueOf(prop.getProperty("showAboutOnLaunch")); //Retrieving boolean value from config file
		
		//createModule(module.affirmation, .7f, .2f);
		if(showAbout == false) {//If property is false don't show module
			System.out.println("Not showing about module, as per config");
		}
		else { //If true, shows module		
			createModule(module.about,.5f,.5f); //Creates the about module to tell users about our app
		}

		
	}
    @FXML
    void onAboutButtonClick(ActionEvent event) {
    	createModule();
    }
    
    @FXML
    void onStopwatchMenuItemClick(ActionEvent event) { 
    	createModule("../FXML_Files/stopwatchModule.fxml");
    }
    
    //insert new modules here
    
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
    void onThemeRadioButtonSelected(ActionEvent event) {
    	String pathToStyleSheet = (String) themeGroup.getSelectedToggle().getUserData();
//    	Application.setUserAgentStylesheet(pathToStyleSheet);
//    	Scene scene = mainWorkspace.getScene();
//    	scene.getStylesheets().add(pathToStyleSheet);
//    	scene.
//    	mainWorkspace.getStylesheets().add(pathToStyleSheet);
//    	System.out.print(mainWorkspace.getStylesheets());
    	
    	for(Parent Module: styleableNodes) {
    		System.out.print(Module.getStylesheets());
    		if(!Module.getStylesheets().isEmpty()) {
    			Module.getStylesheets().clear();
    		}
    		
    		Module.getStylesheets().add(pathToStyleSheet);
    	}
    }
    
    private void createModule(String m){ //Passes in a module enum, and then converts it to the correct path from the modulePaths array. Very streamlined. Very Sexy
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(m)));
    	Node miniModule = null;
    	baseModuleInitalizer miniModuleController = null;
		try {
			miniModule = moduleLoader.load();
			miniModuleController  = (baseModuleInitalizer)moduleLoader.getController();
	    	miniModuleController.getBaseVBox().getChildren().add(miniModule); //Adds miniModule to the baseVBox from its parent
	    	mainWorkspace.getChildren().add(miniModuleController.getBaseVBox()); //Adds the baseVBox(with the mini module) to the mainWorkspace;
	    	styleableNodes.add((Parent)miniModuleController.getBaseVBox());
		} 
		catch (IOException e) {
			System.err.print("Java yelled at me if I didnt put this in..No clue what it does");
			e.getStackTrace();
		}
		catch(IllegalStateException e) {
			System.err.println("File could not be found, make sure file path is correct");
		}
		finally {
			if(miniModule == null) {
				System.err.print("FXML module could not find controller\n Make sure controller path in the FXML file is formatted like this\n Modules.NAMEOFCONTROLLERCLASS");
			}
			else {
				System.out.println("Succesfully loaded " + miniModuleController.getBaseController().getTitleMenu().getText() );
			}			
		}
    }
    
    private void createModule(){ //Passes in a module enum, and then converts it to the correct path from the modulePaths array. Very streamlined. Very Sexy
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(modulepath)));
    	Node miniModule = null;
    	baseModuleInitalizer miniModuleController = null;
		try {
			miniModule = moduleLoader.load();
			miniModuleController  = (baseModuleInitalizer)moduleLoader.getController();
	    	miniModuleController.getBaseVBox().getChildren().add(miniModule); //Adds miniModule to the baseVBox from its parent
	    	mainWorkspace.getChildren().add(miniModuleController.getBaseVBox()); //Adds the baseVBox(with the mini module) to the mainWorkspace;
	    	styleableNodes.add((Parent)miniModuleController.getBaseVBox());
		} 
		catch (IOException e) {
			System.err.print("Java yelled at me if I didnt put this in..No clue what it does");
			e.getStackTrace();
		}
		catch(IllegalStateException e) {
			System.err.println("File could not be found, make sure file path is correct");
		}
		finally {
			if(miniModule == null) {
				System.err.print("FXML module could not find controller\n Make sure controller path in the FXML file is formatted like this\n Modules.NAMEOFCONTROLLERCLASS");
			}
			else {
				System.out.println("Succesfully loaded " + miniModuleController.getBaseController().getTitleMenu().getText() );
			}			
		}
    }

    private void createModule(module m){ //Passes in a module enum, and then converts it to the correct path from the modulePaths array. Very streamlined. Very Sexy
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(modulePaths[m.ordinal()])));
    	Node miniModule = null;
    	baseModuleInitalizer miniModuleController = null;
		try {
			miniModule = moduleLoader.load();
			miniModuleController  = (baseModuleInitalizer)moduleLoader.getController();
	    	miniModuleController.getBaseVBox().getChildren().add(miniModule); //Adds miniModule to the baseVBox from its parent
	    	mainWorkspace.getChildren().add(miniModuleController.getBaseVBox()); //Adds the baseVBox(with the mini module) to the mainWorkspace;
	    	styleableNodes.add((Parent)miniModuleController.getBaseVBox());
		} 
		catch (IOException e) {
			System.err.print("Java yelled at me if I didnt put this in..No clue what it does");
			e.getStackTrace();
		}
		catch(IllegalStateException e) {
			System.err.println("File could not be found, make sure file path is correct");
		}
		finally {
			if(miniModule == null) {
				System.err.print("FXML module could not find controller\n Make sure controller path in the FXML file is formatted like this\n Modules.NAMEOFCONTROLLERCLASS");
			}
			else {
				System.out.println("Succesfully loaded " + miniModuleController.getBaseController().getTitleMenu().getText() );
			}			
		}
    }
    //Overloaded method to allow creation of modules at specific percentage across the x and y of the main workspace
    //For example values of .5 and .5 would place the module in the center of the mainworkspace
    private void createModule(module m, double percentageOfScreenWidth, double perctanageOfScreenHeight){
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(modulePaths[m.ordinal()])));
    	Node miniModule = null;
    	baseModuleInitalizer miniModuleController = null;
		try {
			miniModule = moduleLoader.load();
			miniModuleController = (baseModuleInitalizer)moduleLoader.getController();
	    	miniModuleController.getBaseVBox().getChildren().add(miniModule); //Adds miniModule to the baseVBox from its parent
	    	mainWorkspace.getChildren().add(miniModuleController.getBaseVBox()); //Adds the baseVBox(with the mini module) to the mainWorkspace;
	    	

	    	//TODO: Eventually try to center the module because right now the it spawns the module from its top left corner so things look offset
	    	miniModuleController.getBaseVBox().relocate( (mainWorkspace.getWidth() * percentageOfScreenWidth) ,mainWorkspace.getHeight() * perctanageOfScreenHeight);  
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
				System.out.println("Succesfully loaded " + miniModuleController.getBaseController().getTitleMenu().getText() );
			}
			
		}
    	
    	//return miniModuleController.baseVBox;
    }




    



}


