package modules.stopwatch;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class stopwatchModuleController{
	
	private boolean running;
	private clockTask task;
	
	
    @FXML
    private Label displayLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Button startButton;
    
    @FXML
    private Button stopButton;
    
    @FXML
    void onStart(ActionEvent event) {
    	if (!running) {
	    	try {
	    		running = true;
	    		task = new clockTask();
	    		task.on = true;
	    		displayLabel.textProperty().bind(task.messageProperty());
	    		ExecutorService executorService = Executors.newFixedThreadPool(1);
	    		executorService.execute(task);
	    		executorService.shutdown();
	    	} catch (NumberFormatException e){
	    	}
    	} else {
    		task.on = true;
    	}
    }

    @FXML
    void onReset(ActionEvent event) {
    	running = false;
    	task.on = false;
    	task.cancel();
    	try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	displayLabel.textProperty().unbind();
    	displayLabel.setText(String.format("%04d.%03d",0,0));
    }
    
    @FXML
    void onStop(ActionEvent event) {
    	task.on = false;
    }

	public void initialize() throws IOException {
		
	}
}
