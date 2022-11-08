package modules.stopwatch;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class stopwatchModuleController{
	
	static long begin = 0;
	static long stop = 0;
	
    @FXML
    private Label displayLabel;

    @FXML
    private Button resetButton;

    @FXML
    private Button startButton;
    
    @FXML
    void onStart(ActionEvent event) {
    	if (begin == 0) {
    		begin = System.currentTimeMillis();
    	} else {
    		stop = System.currentTimeMillis();
    	}
		updateUI();
    }

    @FXML
    void onReset(ActionEvent event) {
    	begin = 0;
    	stop = 0;
    	startButton.setText("Start");
    	updateUI();
    }
    
    void updateUI() {
    	long ms = (stop - begin) % 1000;
    	long s = (stop - begin) / 1000;
    	startButton.setText((begin > 0) ? "Lap" : "Start");
    	displayLabel.setText((stop > 0) ? String.format("%04d.%03d",s,ms): String.format("%04d.%03d",0,0));
    }
    

	public void initialize() throws IOException {

	}
}
