package application;

import Modules.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ProductivityPlusController {
    
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

    }

}
