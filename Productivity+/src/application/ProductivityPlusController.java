package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class ProductivityPlusController {

    @FXML
    private MenuItem workPresetLayout;

    @FXML
    private MenuItem studyPresetLayoutMenu;

    @FXML
    private MenuItem dailyTaskListCreation;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Text test;
    
    @FXML
    private  AnchorPane mainWorkspace;

    @FXML
    void onAboutButtonClick(ActionEvent event) {
    	//test.setText("This is working dumb dumb");
    }
    
 
    @FXML
    void onDailyTaskListMenuButtonClick(ActionEvent event) {
    	dailyTaskMiniProgram DT = new dailyTaskMiniProgram();
    	Pane dailyTaskListPane = DT.build();
    	mainWorkspace.getChildren().addAll(dailyTaskListPane);
    	
    }
    
    public AnchorPane getMainWorkspace() {
    	
    	System.out.print(mainWorkspace);
    	return mainWorkspace;
    }
    public void exitModule(Pane module) {
    	mainWorkspace.getChildren().remove(module);
    }

}
