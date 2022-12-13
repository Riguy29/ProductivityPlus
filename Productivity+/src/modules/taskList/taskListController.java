package modules.taskList;

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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

public class taskListController {
	
	private int taskGoalNum = 1;
	public int tasksCompleted =0;
	
	
    @FXML
    private VBox taskListVBox;

    @FXML
    private ProgressBar taskProgressBar;

    @FXML
    private TextField taskGoalTextField;

    
	public void initialize() throws IOException  {

	}
    @FXML
    void createNewTaskButton(ActionEvent event)  {
    	CheckBox taskCompleteCheckBox = new CheckBox();
		TextField textField = new TextField();
		HBox dailyTask = new HBox(0);
		
		dailyTask.setMaxSize(400, 10);
    	dailyTask.getChildren().add(taskCompleteCheckBox);
    	dailyTask.getChildren().add(textField);
    	
    	taskCompleteCheckBox.setOnAction(Event ->{ //When the check box is checked, removes task from list
			taskListVBox.getChildren().remove(dailyTask);
			tasksCompleted += 1;
			updateProgressBar();
    	});
    	taskListVBox.getChildren().add(dailyTask); //Adds new task to list
    }

    @FXML
    void setDailyTaskGoal(MouseEvent event) {
    	try {
    		taskGoalNum = Integer.parseInt(taskGoalTextField.getText());
    		updateProgressBar();
    	}
    	catch(java.lang.NumberFormatException E) {	
    	}

    }
    @FXML
    void onResetGoalButtonClicked(ActionEvent event) {
		tasksCompleted =0;
		updateProgressBar();
    }
    public void updateProgressBar() { //Update progress bar to show correct percentage
    	double percentageComplete = ((double)tasksCompleted/taskGoalNum);
    	taskProgressBar.setProgress(percentageComplete);
    } 

}
