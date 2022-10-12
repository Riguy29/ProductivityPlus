package application;

import java.io.IOException;

import Modules.*;
import OLDMODULESSYSTEM.AboutModule;
import OLDMODULESSYSTEM.DailyTaskModule;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ProductivityPlusController {
    
	DraggableMaker draggableMaker = new DraggableMaker();
    @FXML
    private  AnchorPane mainWorkspace;

    @FXML
    void onAboutButtonClick(ActionEvent event) {
    	AboutModule aboutModule = new AboutModule();
    	Pane aboutPane = aboutModule.build();
    	mainWorkspace.getChildren().add(aboutPane);
    }
    
 
    @FXML
    void onDailyTaskListMenuButtonClick(ActionEvent event) {
    	DailyTaskModule DT = new DailyTaskModule();
    	Pane dailyTaskListPane = DT.build();
    	mainWorkspace.getChildren().addAll(dailyTaskListPane);
    	
    }
    

    @FXML
    void onHelpButtonClick(ActionEvent event) {
    	try {
    		VBox baseModule = FXMLLoader.load(getClass().getResource("../FXML_Files/baseModule.fxml"));
			VBox aboutModule = FXMLLoader.load(getClass().getResource("../FXML_Files/aboutModule.fxml"));
			baseModule.getChildren().add(aboutModule);
			mainWorkspace.getChildren().add(baseModule);
			draggableMaker.makeDraggable(baseModule);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void popUpAboutMenuOnStart() {
    	AboutModule aboutModule = new AboutModule();
    	Pane aboutPane = aboutModule.build();
    	mainWorkspace.getChildren().add(aboutPane);
    }

}
