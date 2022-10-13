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
    	FXMLLoader moduleLoader = new FXMLLoader(getClass().getResource("../FXML_Files/taskListModule.fxml"));
    	VBox miniModule = moduleLoader.load();
    	taskListController taskListCon = moduleLoader.getController();
    	BaseModuleReturnPackage basePackage = createModule(miniModule);
    	basePackage.baseController.setTitle("Task List");
    	
    	MenuItem resetCompletedTasks = new MenuItem("Reset Completed Tasks");
    	resetCompletedTasks.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				taskListCon.tasksCompleted =0;
				taskListCon.updateProgressBar();
				
			}   		
    	});
    	basePackage.baseController.addNodeToMenu(resetCompletedTasks, basePackage.baseController.getModuleFunctionsMenu());
    }

    @FXML
    void onBrainBreakMenuItemClick(ActionEvent event) throws IOException {
    	VBox brainBreakModule = FXMLLoader.load(getClass().getResource("../FXML_Files/brainBreakModule.fxml"));
    	BaseModuleReturnPackage basePackage = createModule(brainBreakModule);
    	basePackage.baseController.setTitle("Brain Break");
    }
    @FXML
    void onMusicPlayerMenuItemClicked(ActionEvent event) throws IOException {
    	VBox musicPlayerModule = FXMLLoader.load(getClass().getResource("../FXML_Files/musicPlayerModule.fxml"));
    	BaseModuleReturnPackage basePackage = createModule(musicPlayerModule);
    	basePackage.baseController.setTitle("Music Player");
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
    	BaseModuleReturnPackage basePackage = createModule(taskListModule);
    	VBox module = basePackage.baseModule;
    	module.setLayoutX(250);
    	module.setLayoutY(250);
    	

    }


    private BaseModuleReturnPackage createModule(VBox moduleVBox) throws IOException {
		FXMLLoader moduleLoader = new  FXMLLoader(getClass().getResource("../FXML_Files/baseModule.fxml"));
		baseModule = moduleLoader.load();
		baseModuleController baseController = moduleLoader.getController();
		baseModule.getChildren().add(moduleVBox);
		mainWorkspace.getChildren().add(baseModule);
		draggableMaker.makeDraggable(baseModule);

		return new BaseModuleReturnPackage(baseModule,baseController);
    }
    



}
class BaseModuleReturnPackage{
	VBox baseModule;
	baseModuleController baseController;
	
	BaseModuleReturnPackage(VBox _baseModule,baseModuleController _baseController){
		this.baseModule = _baseModule;
		this.baseController = _baseController;		
	}
}


