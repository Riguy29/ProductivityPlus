package modules.brainBreak;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class brainBreakController {

    @FXML
    private TextField breakTimeTextField;

    @FXML
    private Label displayLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Button startButton;

    @FXML
    private Button stopButton;

    @FXML
    private TextField workTimeTextField;
    
    @FXML
    private Label stateLabel;

	private boolean running;

	private timerTask task;

	public boolean work = true;

    @FXML
    void onStopButton(ActionEvent event) {
    	task.counting = false;
    }

    @FXML
    void onStartButton(ActionEvent event) {
    	if (!running) {
    		if (work) {
    			try {
    				createTask(Integer.parseInt(workTimeTextField.getText()));
    				running = true;
    			} catch (NumberFormatException e) {
        			workTimeTextField.setText("0");
        		}
    		} else {
    			try {
    				createTask(Integer.parseInt(breakTimeTextField.getText()));
    				running = true;
    			} catch (NumberFormatException e) {
        			breakTimeTextField.setText("0");
        		}
    		}
    	} else {
    		task.counting = true;
    	}
    }

    @FXML
    void onResetButton(ActionEvent event) {
    	reset();
    	work = true;
    	stateLabel();
    }
    
    public void createTask(int i) {
    	task = new timerTask((long) i);
		displayLabel.textProperty().bind(task.messageProperty());
		task.setOnSucceeded((succeededEvent) -> {
			reset();
			if (work) {
				work = false;
			} else {
				work = true;
			}
			stateLabel();
		});
		ExecutorService executorService = Executors.newFixedThreadPool(1);
		executorService.execute(task);
		executorService.shutdown();
    }
    
    public void reset() {
    	task.counting = false;
    	task.cancel();
    	running = false;
    	displayLabel.textProperty().unbind();
    	displayLabel.textProperty().set("00:00");
    }
    
    public void stateLabel() {
    	if (work) {
    		stateLabel.setText("Work");
    	} else {
    		stateLabel.setText("Break");
    	}
    }
}
