package application;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import Modules.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class ProductivityPlusController implements Initializable {
    
	DraggableMaker draggableMaker = new DraggableMaker();
    @FXML
    private  AnchorPane mainWorkspace;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) { //Runs this code block when the fxml loads
    	
    	String pathToFXML = "../FXML_Files/aboutModule.fxml";
		createModule(pathToFXML); //Creates the about module to tell users about our app
		
	}
    @FXML
    void onAboutButtonClick(ActionEvent event) {
    	String pathToFXML = "../FXML_Files/aboutModule.fxml";
    	createModule(pathToFXML);
    }
    
 
    @FXML
    void onDailyTaskListMenuButtonClick(ActionEvent event){
    	String pathToFXML = "../FXML_Files/taskListModule.fxml";
    	createModule(pathToFXML);

    }

    @FXML
    void onBrainBreakMenuItemClick(ActionEvent event) {
    	String pathToFXML = "../FXML_Files/brainBreakModule.fxml"; //Tells the create module function where the minimodule FXML file is
    	createModule(pathToFXML);
    }
    @FXML
    void onMusicPlayerMenuItemClicked(ActionEvent event){
    	String pathToFXML = "../FXML_Files/musicPlayerModule.fxml";
    	createModule(pathToFXML);
    }
    @FXML
    void onBrainWarmupMenuItemClicked(ActionEvent event) {
    	String pathToFXML = "../FXML_Files/brainWarmupModule.fxml";
    	createModule(pathToFXML);
    }


    @FXML
    void onHelpButtonClick(ActionEvent event) {

    }
    
    @FXML
    void setLayoutToStudyLayout(ActionEvent event) {

    }

    @FXML
    void setLayoutToWorkLayout(ActionEvent event){
    	String pathToFXML = "../FXML_Files/taskListModule.fxml";
    	createModule(pathToFXML,.5f,.5f);
    	

    }


    private void createModule(String Path){
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(Path)));
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
				System.out.println("Succesfully loaded " + miniModuleController.getBaseController().getTitleMenu() );
			}			
		}


    	
    	//return miniModuleController.baseVBox;
    }
    //Overloaded method to allow creation of modules at specfic percentage across the x and y of the main workspace
    //For example values of .5 and .5 would place the module in the center of the mainworkspace
    private void createModule(String Path, double percentageOfScreenWidth, double perctanageOfScreenHeight){
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(Path)));
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
    	
    	//return miniModuleController.baseVBox;
    }




    



}


