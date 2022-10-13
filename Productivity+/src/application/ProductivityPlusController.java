package application;

import java.io.IOException;

import OLDMODULESSYSTEM.AboutModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

public class ProductivityPlusController {
    
	DraggableMaker draggableMaker = new DraggableMaker();
    @FXML
    private  AnchorPane mainWorkspace;
    VBox baseModule;


    @FXML
    void onAboutButtonClick(ActionEvent event) {
    	try {
    		baseModule = FXMLLoader.load(getClass().getResource("../FXML_Files/baseModule.fxml"));
			VBox aboutModule = FXMLLoader.load(getClass().getResource("../FXML_Files/aboutModule.fxml"));
			baseModule.getChildren().add(aboutModule);
			mainWorkspace.getChildren().add(baseModule);
			draggableMaker.makeDraggable(baseModule);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
 
    @FXML
    void onDailyTaskListMenuButtonClick(ActionEvent event) throws IOException {
    	VBox taskListModule = FXMLLoader.load(getClass().getResource("../FXML_Files/taskListModule.fxml"));
    	createModule(taskListModule);
    }

    @FXML
    void onBrainBreakMenuItemClick(ActionEvent event) throws IOException {
    	VBox brainBreakModule = FXMLLoader.load(getClass().getResource("../FXML_Files/brainBreakModule.fxml"));
    	createModule(brainBreakModule);
    }


    @FXML
    void onHelpButtonClick(ActionEvent event) {

    }
    
    @FXML
    void setLayoutToStudyLayout(ActionEvent event) {

    }

    @FXML
    void setLayoutToWorkLayout(ActionEvent event) throws IOException {
    	VBox taskListModule = FXMLLoader.load(getClass().getResource("../FXML_Files/taskListModule.fxml"));
    	VBox module = createModule(taskListModule);
    	module.setLayoutX(250);
    	module.setLayoutY(250);
    	

    }
    
//    private VBox createTaskListModule() {
//    	try {
//    		baseModule = FXMLLoader.load(getClass().getResource("../FXML_Files/baseModule.fxml"));
//			
//			baseModule.getChildren().add(taskListModule);
//			mainWorkspace.getChildren().add(baseModule);
//			draggableMaker.makeDraggable(baseModule);
//			
//			
//			
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return baseModule;
//    }
    private VBox createModule(VBox moduleVBox) {
    	try {
    		baseModule = FXMLLoader.load(getClass().getResource("../FXML_Files/baseModule.fxml"));
			baseModule.getChildren().add(moduleVBox);
			mainWorkspace.getChildren().add(baseModule);
			draggableMaker.makeDraggable(baseModule);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baseModule;
    }


}
