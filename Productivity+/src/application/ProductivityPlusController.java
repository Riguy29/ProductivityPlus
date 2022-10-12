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
    void onDailyTaskListMenuButtonClick(ActionEvent event) {
    	createTaskListModule();
    }
    


    @FXML
    void onHelpButtonClick(ActionEvent event) {

    }
    
    public void popUpAboutMenuOnStart() {
//    	AboutModule aboutModule = new AboutModule();
//    	Pane aboutPane = aboutModule.build();
//    	mainWorkspace.getChildren().add(aboutPane);
    }
    
    @FXML
    void setLayoutToStudyLayout(ActionEvent event) {

    }

    @FXML
    void setLayoutToWorkLayout(ActionEvent event) {
    	VBox taskListModule = createTaskListModule();
    	taskListModule.setLayoutX(250);
    	taskListModule.setLayoutY(250);
    	

    }
    
    private VBox createTaskListModule() {
    	try {
    		baseModule = FXMLLoader.load(getClass().getResource("../FXML_Files/baseModule.fxml"));
			VBox taskListModule = FXMLLoader.load(getClass().getResource("../FXML_Files/taskListModule.fxml"));
			baseModule.getChildren().add(taskListModule);
			mainWorkspace.getChildren().add(baseModule);
			draggableMaker.makeDraggable(baseModule);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return baseModule;
    }


}
