package modules.notePad;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputControl;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.Timestamp;
import java.util.Date;
import java.text.SimpleDateFormat;

public class notePadModuleController {

	@FXML
	private VBox notePadVbox;

	@FXML
	private Button addNoteButton;

	@FXML
	private ColorPicker colorPicker;

	@FXML
	private TextArea noteTextBox;

	@FXML
	private Button boldButton;

	@FXML
	private Button italicButton;

	@FXML
	private Button exitButton;

	@FXML
	private Button listButton;

	@FXML
	private Button timeStamp;

	ColorPicker c = new ColorPicker();
	Text text = new Text();
	boolean fontFlagI = false;
	boolean fontFlagB = false;

	@FXML
	void addNewNote(ActionEvent event) { // duplicate an empty notepad
		
	}

	@FXML
	void changePadColor(ActionEvent event) { // change the background color of each note
		colorPicker.setOnAction(new EventHandler() {
			public void handle(Event t) {
				Color c = colorPicker.getValue();
				System.out.println("New Color's RGB = " + c.getRed() + " " + c.getGreen() + " " + c.getBlue());
				notePadVbox.setBackground(new Background(new BackgroundFill(c, null, null)));
				noteTextBox.setBackground(new Background(new BackgroundFill(c, null, null)));

			}
		});

	}

	@FXML
	void createList(ActionEvent event) {
		//do numbers 
	}

	@FXML
	void italicizeText(ActionEvent event) { // italicize selected text
		// noteTextBox.setFont(Font.font( "verdana", FontWeight.NORMAL,
		// FontPosture.ITALIC, 12));
		if (fontFlagI == false) {
			noteTextBox.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));

			fontFlagI = true;
		} else {
			noteTextBox.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
			fontFlagI = false;
		}
	}

	@FXML
	void exitNotePad(ActionEvent event) { // exit notepad/delete

	}

	@FXML
	void boldText(ActionEvent event) { // bold selected text
		// noteTextBox.setFont(Font.font( "verdana", FontWeight.BOLD,
		// FontPosture.REGULAR, 12));

		if (fontFlagB == false) {
			noteTextBox.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));

			fontFlagB = true;
		} else {
			noteTextBox.setFont(Font.font("verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
			fontFlagB = false;
		}
	}

	@FXML
	void timeStamp(ActionEvent event) { // insert current time and date-- when the note was made
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(formatter.format(ts));
		noteTextBox.setText(formatter.format(ts) + (noteTextBox.getText()));

	}

	// public ColorPicker (Color color) {}

	public void initialize() throws IOException {
	}

}
