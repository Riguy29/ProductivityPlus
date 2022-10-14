package Modules;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.DraggableMaker;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class taskListController extends baseModuleInitalizer{
	
	private int taskGoalNum = 1;
	public int tasksCompleted =0;
	
	
    @FXML
    private VBox taskListVBox;

    @FXML
    private ProgressBar taskProgressBar;

    @FXML
    private TextField taskGoalTextField;

    
    
    @FXML
    void createNewTaskButton(ActionEvent event)  {
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
    public void updateProgressBar() {
    	double percentageComplete = ((double)tasksCompleted/taskGoalNum);
    	System.out.print(percentageComplete);
    	taskProgressBar.setProgress(percentageComplete);
    }


    @Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
    	super.initialize(arg0, arg1); //Must include this when you initalize you modules
		baseController.setTitle("Task List");	
    	MenuItem resetCompletedTasks = new MenuItem("Reset Completed Tasks");
    	resetCompletedTasks.setOnAction(new EventHandler<ActionEvent>(){
			@Override
			public void handle(ActionEvent event) {
				tasksCompleted =0;
				updateProgressBar();
				
			}   		
    	});
    	baseController.addNodeToMenu(resetCompletedTasks,baseController.getModuleFunctionsMenu());
		
	}
    

}
