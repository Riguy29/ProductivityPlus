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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
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

	@FXML private VBox notePadVbox;
	@FXML private Button addNoteButton;
	@FXML private ColorPicker colorPicker;
	@FXML private TextArea noteTextBox;
	@FXML private Button boldButton;
	@FXML private Button italicButton;
	@FXML private Button exitButton;
	@FXML private Button listButton;
	@FXML private Button timeStamp;
	@FXML private Button saveButton;

	
	ColorPicker c = new ColorPicker();
	Text text = new Text();
	boolean fontFlagI = false;
	boolean fontFlagB = false;
	int listNum = 0;
	int count = 1;
	TextArea textArea = new TextArea();

	@FXML
	void addNewNote(ActionEvent event) { // duplicate an empty notepad
		//addNoteButton
		VBox newPad = new VBox();
		TextArea textA = new TextArea();
		textA.setMaxSize(275, 200);
		newPad.setMaxSize(275, 200);
		
		baseVBox.getChildren().addAll(newPad, textA);
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
		int caretPos = noteTextBox.getCaretPosition();
		listButton.setOnAction(Event -> {count++;
		
		EventHandler<KeyEvent> enterListener = evt -> {
            if (evt.getCode() == KeyCode.ENTER) {
                evt.consume();
                noteTextBox.requestFocus();
                noteTextBox.end();
            }
        };
});
	}

	@FXML
	void italicizeText(ActionEvent event) { // italicize selected text
		// noteTextBox.setFont(Font.font( "verdana", FontWeight.NORMAL,
		// FontPosture.ITALIC, 12));
		
		 if (fontFlagI == false && fontFlagB == false) {
				noteTextBox.setFont(Font.font( "verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
				fontFlagI = true;	//text started not bold not i, flip italic not b
				fontFlagB = false;
			}
			else if (fontFlagI == false && fontFlagB == true) {
				noteTextBox.setFont(Font.font( "verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
				fontFlagI = true;	//text started bold not i, flip italic keep bold
				fontFlagB = true;
			}
			else if (fontFlagI == true && fontFlagB == false) {
				noteTextBox.setFont(Font.font( "verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
				fontFlagI = false;	//text started italic not b, flip italic not b
				fontFlagB = false;
			}
			else if (fontFlagI == true  && fontFlagB == true) {
				noteTextBox.setFont(Font.font( "verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
				fontFlagI = false;	//test started bold and i, flip italic keep b
				fontFlagB = true;
			}
				
//			System.out.printf("%n%nI CLICK -- BOLD " + fontFlagB + " ITALIC " + fontFlagI);
	}

	@FXML
	void exitNotePad(ActionEvent event) { // exit notepad/delete
		AnchorPane mainWorkspace = (AnchorPane) baseVBox.getParent(); //Grabs the AnchorPane
		mainWorkspace.getChildren().remove(baseVBox);
		System.out.println("Successfully closed Note Pad");
	}

	@FXML
	void boldText(ActionEvent event) { // bold selected text
		// noteTextBox.setFont(Font.font( "verdana", FontWeight.BOLD,
		// FontPosture.REGULAR, 12));

		if (fontFlagB == false && fontFlagI == false) {
			noteTextBox.setFont(Font.font( "verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
			fontFlagB = true;	
			fontFlagI = false;
		}
		else if (fontFlagB == false && fontFlagI == true) {
			noteTextBox.setFont(Font.font( "verdana", FontWeight.BOLD, FontPosture.ITALIC, 12));
			fontFlagB = true;	
			fontFlagI = true;
		}
		else if (fontFlagB == true && fontFlagI == false) {
			noteTextBox.setFont(Font.font( "verdana", FontWeight.NORMAL, FontPosture.REGULAR, 12));
			fontFlagB = false;	
			fontFlagI = false;
		}
		else if (fontFlagB == true && fontFlagI == true) {
			noteTextBox.setFont(Font.font( "verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
			fontFlagB = false;	
			fontFlagI = true;
		}
		
		
//		System.out.printf("%n%nBOLD CLICK -- BOLD " + fontFlagB + " ITALIC " + fontFlagI);
	}

	@FXML
	void timeStamp(ActionEvent event) { // insert current time and date-- when the note was made
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(formatter.format(ts));
		noteTextBox.setText(formatter.format(ts) + (noteTextBox.getText()));

	}

	@FXML
    void onSaveButtonClick(ActionEvent event) {

    }

	public void initialize() throws IOException {
	}

}
