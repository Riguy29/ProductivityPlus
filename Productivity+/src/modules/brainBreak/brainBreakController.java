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

	private boolean running;

	private timerTask task;

	private boolean work;

    @FXML
    void onStopButton(ActionEvent event) {
    	running = false;
    }


    @FXML
    void onStartButton(ActionEvent event) {
    	if (!running) {
    		running = true;
    		task = new timerTask((long) 100000);
    		displayLabel.textProperty().bind(task.messageProperty());
    		ExecutorService executorService = Executors.newFixedThreadPool(1);
    		executorService.execute(task);
    		executorService.shutdown();
    	}
    }

    @FXML
    void onResetButton(ActionEvent event) {
    	task.counting = false;
    	task.cancel();
    	running = false;
    	work = true;
    }

}
