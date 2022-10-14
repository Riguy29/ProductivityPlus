package application;

import java.io.IOException;

import Modules.*;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.*;

public class ProductivityPlusController {
    
	DraggableMaker draggableMaker = new DraggableMaker();
    @FXML
    private  AnchorPane mainWorkspace;


    @FXML
    void onAboutButtonClick(ActionEvent event) throws IOException {
    	String pathToFXML = "../FXML_Files/aboutModule.fxml";
    	createModule(pathToFXML);
    }
    
 
    @FXML
    void onDailyTaskListMenuButtonClick(ActionEvent event) throws IOException {
    	String pathToFXML = "../FXML_Files/taskListModule.fxml";
    	createModule(pathToFXML);

    }

    @FXML
    void onBrainBreakMenuItemClick(ActionEvent event) throws IOException {
    	String pathToFXML = "../FXML_Files/brainBreakModule.fxml";
    	createModule(pathToFXML);
    }
    @FXML
    void onMusicPlayerMenuItemClicked(ActionEvent event) throws IOException {
    	String pathToFXML = "../FXML_Files/musicPlayerModule.fxml";
    	createModule(pathToFXML);
    }


    @FXML
    void onHelpButtonClick(ActionEvent event) {

    }
    
    @FXML
    void setLayoutToStudyLayout(ActionEvent event) {

    }

    @FXML
    void setLayoutToWorkLayout(ActionEvent event) throws IOException {
    	String pathToFXML = "../FXML_Files/taskListModule.fxml";
    	VBox module = createModule(pathToFXML);
    	module.setLayoutX(250);
    	module.setLayoutY(250);
    	

    }


    private VBox createModule(String Path) throws IOException{
    	FXMLLoader moduleLoader = new FXMLLoader((getClass().getResource(Path)));
    	VBox miniModule = moduleLoader.load();
    	baseModuleInitalizer miniModuleController = (baseModuleInitalizer)moduleLoader.getController();
    	miniModuleController.baseVBox.getChildren().add(miniModule); //Addes miniModule to the baseVBox from its parent
    	mainWorkspace.getChildren().add(miniModuleController.baseVBox); //Adds the baseVBox(with the mini module) to the mainWorkspace;
    	return miniModuleController.baseVBox;
    }
    



}


