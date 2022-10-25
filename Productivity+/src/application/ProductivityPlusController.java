package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;
import java.util.ResourceBundle;

import Modules.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.*;

public class ProductivityPlusController {
    
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
    	tasklist,
    	brainBreak,
    	musicPlayer,
    	brainWarmup,
    	calculator,
    	notepad,
    	affirmation,
    	stopwatch
    	
    }
    private static String[] modulePaths =
    {
    	"../FXML_Files/aboutModule.fxml",	
    	"../FXML_Files/taskListModule.fxml",
    	"../FXML_Files/brainBreakModule.fxml",
    	"../FXML_Files/musicPlayerModule.fxml",
    	"../FXML_Files/brainWarmupModule.fxml",
    	"../FXML_Files/calculatorModule.fxml",
    	"../FXML_Files/notePadModule.fxml",
    	"../FXML_Files/affirmationModule.fxml",
    	"../FXML_Files/stopwatchModule.fxml" 
    	
    	
    };
    

	public void initialize() throws IOException { //Runs this code block when the fxml loads
		Properties  prop = ConfigReader.readConfig();
		//System.out.println(prop.getProperty("showAboutOnLauch"));
		Boolean showAbout = Boolean.valueOf(prop.getProperty("showAboutOnLaunch"));
		
		if(showAbout == false) {//If property is false don't show module
			System.out.println("Not showing about module, as per config");
		}
		else { //If true, shows module		
			createModule(module.about,.45,.45); //Creates the about module to tell users about our app
		}
		

		
		createModule(module.affirmation, .7f, .2f);
		
	}
    @FXML
    void onAboutButtonClick(ActionEvent event) {
    	createModule(module.about);
    }
    @FXML
    void onDailyTaskListMenuButtonClick(ActionEvent event){
    	createModule(module.tasklist);

    }
    @FXML
    void onBrainBreakMenuItemClick(ActionEvent event) {
    	createModule(module.brainBreak);
    }
    @FXML
    void onMusicPlayerMenuItemClicked(ActionEvent event){
    	createModule(module.musicPlayer);
    }
    @FXML
    void onBrainWarmupMenuItemClicked(ActionEvent event) {
    	createModule(module.brainWarmup);
    }
    @FXML
    void onCalculatorMenuClicked(ActionEvent event) {
    	createModule(module.calculator);
    }
    @FXML
    void onHelpButtonClick(ActionEvent event) {

    }    
    @FXML
    void onNotePadMenuItemClick(ActionEvent event) { //where you'd set a default load action
    	createModule(module.notepad);
    }
    
    @FXML
    void onAffirmationsMenuItemClick(ActionEvent event) { 
    	createModule(module.affirmation, .6f, .1f);
    }
    
    @FXML
    void onStopwatchMenuItemClick(ActionEvent event) { 
    	createModule(module.stopwatch);
    }
    
    //insert new modules here
    
    @FXML
    void setLayoutToStudyLayout(ActionEvent event) {
    	createModule(module.musicPlayer,.2f,.1f);
    	createModule(module.notepad, .5f,.1f);
    }

    @FXML
    void setLayoutToWorkLayout(ActionEvent event){
    	createModule(module.tasklist,.5f,.5f);
    	createModule(module.notepad, .2f,.2f);
    	createModule(module.calculator, .7f,.7f);
    	

    }


    private void createModule(module m){ //Passes in a module enum, and then converts it to the correct path from the modulePaths array. Very streamlined. Very Sexy
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(modulePaths[m.ordinal()])));
    	Node miniModule = null;
    	baseModuleInitalizer miniModuleController = null;
		try {
			miniModule = moduleLoader.load();
			miniModuleController  = (baseModuleInitalizer)moduleLoader.getController();
	    	miniModuleController.baseVBox.getChildren().add(miniModule); //Adds miniModule to the baseVBox from its parent
	    	mainWorkspace.getChildren().add(miniModuleController.baseVBox); //Adds the baseVBox(with the mini module) to the mainWorkspace;
		} 
		catch (IOException e) {
			System.err.print("Java yelled at me if I didnt put this in..No clue what it does");
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
    //Overloaded method to allow creation of modules at specfic percentage across the x and y of the main workspace
    //For example values of .5 and .5 would place the module in the center of the mainworkspace
    private void createModule(module m, double percentageOfScreenWidth, double perctanageOfScreenHeight){
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(modulePaths[m.ordinal()])));
    	Node miniModule = null;
    	baseModuleInitalizer miniModuleController = null;
		try {
			miniModule = moduleLoader.load();
			miniModuleController = (baseModuleInitalizer)moduleLoader.getController();
	    	miniModuleController.baseVBox.getChildren().add(miniModule); //Addes miniModule to the baseVBox from its parent
	    	mainWorkspace.getChildren().add(miniModuleController.baseVBox); //Adds the baseVBox(with the mini module) to the mainWorkspace;
	    	

	    	//TODO: Eventually try to center the module because right now the it spawns the module from its top left corner so things look offset
	    	miniModuleController.baseVBox.relocate( (mainWorkspace.getWidth() * percentageOfScreenWidth) ,mainWorkspace.getHeight() * perctanageOfScreenHeight);  
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


