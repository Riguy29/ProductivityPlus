package OLDMODULESSYSTEM;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;

public class DailyTaskModule extends ModuleBase {
	private final double width =200 ,height = 400;
	
	@FXML
	private VBox dailyTaskList = new VBox(5);
	
	@FXML
	private Button addNewTaskButton = new Button("+");
	
	private MenuItem saveTask = new MenuItem("Save Task List");
	private MenuItem loadTask = new MenuItem("Load Task List");

	
	public DailyTaskModule(){
		
		
	}
	@Override
	public StackPane build() {
		super.build(); //Performs build functionality from the ModuleBase class
		super.addMenuItemToTitleMenu(saveTask);
		super.addMenuItemToTitleMenu(loadTask);
		
		super.setTitleMenuText("Task List");
    	getVerticalOrganizer().getChildren().add(dailyTaskList); //Add a vbox to the basePane
    	
    	dailyTaskList.setMaxSize(width, height);
    	dailyTaskList.setAlignment(Pos.TOP_CENTER);
    	dailyTaskList.getChildren().add(addNewTaskButton); //Button that allows user to add more tasks
    	
    	addNewTask();  	
    	
    	addNewTaskButton.setOnAction(event ->{ //When the button is clicked, adds new task
    		addNewTask();
    	});
		return basePane;
    	

	}

	public void addNewTask() {
		CheckBox taskCompleteCheckBox = new CheckBox();
		TextField textField = new TextField();
		HBox dailyTask = new HBox(0);
		
		dailyTask.setMaxSize(400, 10);
    	dailyTask.getChildren().add(taskCompleteCheckBox);
    	dailyTask.getChildren().add(textField);
    	
    	taskCompleteCheckBox.setOnAction(event ->{ //When the checkBox is marked, removes parent Hbox node
    		dailyTaskList.getChildren().remove(dailyTask);
    	});
    	
    	dailyTaskList.getChildren().add(dailyTask);
	}


}
