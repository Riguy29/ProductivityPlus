package modules.brainBreak;

import javafx.scene.input.KeyEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class brainBreakController {

	private float breakTime;
	private float workTime;
    @FXML
    private TextField breakTimeTextField;

    @FXML
    private TextField workTimeTextField;

    @FXML
    private Label timerLabel;

    @FXML
    private Label timer;

    @FXML
    void onPauseButton(ActionEvent event) {

    }

    @FXML
    void onStartButton(ActionEvent event) {

    }

    @FXML
    void onStopButton(ActionEvent event) {

    }

    @FXML
    void setBreakTime(KeyEvent event) {

    }

    @FXML
    void setWorkTime(KeyEvent event) {

    }
    
	public void initialize() throws IOException  {
	}


}
