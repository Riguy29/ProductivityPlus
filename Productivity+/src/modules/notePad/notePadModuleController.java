package modules.notePad;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.xml.bind.JAXB;

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
	private Button clearButton;

	@FXML
	private Button listButton;

	@FXML
	private Button timeStamp;

	@FXML
	private ChoiceBox<String> instanceChoiceBox;

	private final String XML_FILE = "notePadInstances.xml";
	private ArrayList<NotePadInstance> instancesList = new ArrayList<>();
	ColorPicker c = new ColorPicker();
	Text text = new Text();
	boolean fontFlagI = false;
	boolean fontFlagB = false;
	NotePadInstance currentInstance;

	public void initialize() throws IOException {
		String[] instances = { "Notepad 1", "Notepad 2", "Notepad 3" };
		instanceChoiceBox.setItems(FXCollections.observableArrayList(instances));

		instanceChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				// First save all notepad data again
				saveCurrentInstance();
				saveAllData();
				// Then set the new notepad using data from the new value
				setNotepadInfo(newValue.intValue());
			}
		});

		try (BufferedReader input = Files.newBufferedReader(Paths.get(XML_FILE))) {
			// unmarshal the file's contents
			NotePadInstances notePads = JAXB.unmarshal(input, NotePadInstances.class);

			
			 for (NotePadInstance notepad : notePads.getInstances()) {
				 instancesList.add(notepad); 
			 }
			 
		} catch (IOException ioException) {
			System.err.println("Error opening file.");
		}
		instanceChoiceBox.setValue(instances[0]);

	}

	private void saveCurrentInstance() {
		try {
			currentInstance.setText(noteTextBox.getText());
			currentInstance.setItalics(fontFlagI);
			currentInstance.setBold(fontFlagB);
			currentInstance.setrValue(colorPicker.getValue().getRed());
			currentInstance.setbValue(colorPicker.getValue().getBlue());
			currentInstance.setgValue(colorPicker.getValue().getGreen());
		} catch (NullPointerException e) {
			System.err.print("This error is triggered when the notepad first loads because we manually set the instance but not everything has loaded\nThis can be safely ignored");
			//e.printStackTrace();
		}
	}

	private void saveAllData() {
		try {
			BufferedWriter output = Files.newBufferedWriter(Paths.get(XML_FILE));
			NotePadInstances instances = new NotePadInstances();
			for (NotePadInstance i : instancesList) {
				// Adds each contact to the the contacts class
				NotePadInstance savedInstance = new NotePadInstance(i.getText(), i.isItalics(), i.isBold(),i.getrValue(),i.getgValue(),i.getbValue());
				instances.getInstances().add(savedInstance);
			}
			// Writes contact class out to xml file
			JAXB.marshal(instances, output);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void setNotepadInfo(int notepadInstance) {
		currentInstance = instancesList.get(notepadInstance);
		noteTextBox.setText(currentInstance.getText());
		fontFlagI = currentInstance.isItalics();
		fontFlagB = currentInstance.isBold();
		Color noteColor = new Color(currentInstance.getrValue(),currentInstance.getgValue(),currentInstance.getbValue(),1);
		notePadVbox.setBackground(new Background(new BackgroundFill(noteColor, null, null)));
		noteTextBox.setBackground(new Background(new BackgroundFill(noteColor, null, null)));
		colorPicker.setValue(noteColor);
		FontPosture posture = FontPosture.REGULAR;
		FontWeight weight = FontWeight.NORMAL;
		if(fontFlagI) {
			posture = FontPosture.ITALIC;
		}
		if(fontFlagB) {
			weight = FontWeight.BOLD;
		}
		noteTextBox.setFont(Font.font("verdana", weight, posture, 12));

		
	}

	@FXML
	void addNewNote(ActionEvent event) { // duplicate an empty notepad
		VBox newPad = new VBox();
		TextArea textA = new TextArea();
		textA.setMaxSize(275, 200);
		newPad.setMaxSize(275, 200);

		notePadVbox.getChildren().addAll(newPad, textA);
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
	void italicizeText(ActionEvent event) { // italicize selected text

		FontPosture posture = FontPosture.REGULAR;
		FontWeight weight = FontWeight.NORMAL;
		
		fontFlagI = !fontFlagI; //Flips between italic and normal
		if(fontFlagB) {
			weight = FontWeight.BOLD;
		}
		if(fontFlagI) {
			posture = FontPosture.ITALIC;
		}
		noteTextBox.setFont(Font.font("verdana", weight, posture, 12));

//		System.out.printf("%n%nI CLICK -- BOLD " + fontFlagB + " ITALIC " + fontFlagI);
	}

	@FXML
	void clearNotePad(ActionEvent event) { // exit notepad/delete
		noteTextBox.clear();
		System.out.println("Successfully cleared Note Pad");
	}

	@FXML
	void boldText(ActionEvent event) { // bold selected text
		FontPosture posture = FontPosture.REGULAR;
		FontWeight weight = FontWeight.NORMAL;
		
		fontFlagB = !fontFlagB; //Flips between normal and bold
		if(fontFlagB) {
			weight = FontWeight.BOLD;
		}
		if(fontFlagI) {
			posture = FontPosture.ITALIC;
		}
		noteTextBox.setFont(Font.font("verdana", weight, posture, 12));

//			System.out.printf("%n%nBOLD CLICK -- BOLD " + fontFlagB + " ITALIC " + fontFlagI);
	}

	@FXML
	void timeStamp(ActionEvent event) { // insert current time and date-- when the note was made
		Date date = new Date();
		Timestamp ts = new Timestamp(date.getTime());
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		System.out.println(formatter.format(ts));
		noteTextBox.setText(formatter.format(ts) + noteTextBox.getText() + "\n\n");

	}

}