package application;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

public class dailyTaskMiniProgram extends MiniProgramBase {
	private final double width =200 ,height = 400;
	
	private Pane containerPane = new Pane();
	private VBox dailyTaskList = new VBox();
	private Button addNewTaskButton = new Button("Click here to add a new task");
	public dailyTaskMiniProgram(){
		
		
	}
	public Pane build() { 	
    	containerPane.getChildren().add(dailyTaskList);
    	dailyTaskList.setMaxSize(200, 400);
    	dailyTaskList.getChildren().add(new Label("Daily Task List"));
    	addNewTask();  	
    	dailyTaskList.getChildren().add(addNewTaskButton);
		return containerPane;
    	

	}
	public void addNewTask() {
		HBox dailyTask = new HBox(0);
		dailyTask.setMaxSize(400, 10);
    	dailyTask.getChildren().add(new CheckBox());
    	dailyTask.getChildren().add(new TextArea());
    	dailyTaskList.getChildren().add(dailyTask);
	}

}
