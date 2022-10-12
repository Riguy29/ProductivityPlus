package Modules;

import java.util.ArrayList;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class taskListController extends baseModuleController{

	private int taskGoalNum = 1;
	private int tasksCompleted =0;
    @FXML
    private VBox taskListVBox;

    @FXML
    private ProgressBar taskProgressBar;

    @FXML
    private TextField taskGoalTextField;

    
    @FXML
    void createNewTaskButton(ActionEvent event) {
    	CheckBox taskCompleteCheckBox = new CheckBox();
		TextField textField = new TextField();
		HBox dailyTask = new HBox(0);
		
		dailyTask.setMaxSize(400, 10);
    	dailyTask.getChildren().add(taskCompleteCheckBox);
    	dailyTask.getChildren().add(textField);
    	
    	taskCompleteCheckBox.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				taskListVBox.getChildren().remove(dailyTask);
				tasksCompleted += 1;
				updateProgressBar();
				
			}    		
    	});
    	taskListVBox.getChildren().add(dailyTask);
    }

    @FXML
    void setDailyTaskGoal(KeyEvent event) {
    	try {
    		taskGoalNum = Integer.parseInt(taskGoalTextField.getText());
    		updateProgressBar();
    		
    	}
    	catch(java.lang.NumberFormatException E) {
    		
    	}

    }
    private void updateProgressBar() {
    	double percentageComplete = ((double)tasksCompleted/taskGoalNum);
    	System.out.print(percentageComplete);
    	taskProgressBar.setProgress(percentageComplete);
    }
    

}
