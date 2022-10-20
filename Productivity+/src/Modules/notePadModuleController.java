package Modules;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Labeled;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class notePadModuleController extends baseModuleInitalizer{
	
	//creating text object
	Text text = new Text();
	String type = text.getText();
	

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
    private Button underlineButton;

    @FXML
    private Button listButton;
    
    @FXML
    private Button timeStamp;

    @FXML
    void addNewNote(ActionEvent event) { //duplicate an empty notepad

    	
    }

    @FXML
    void changePadColor(ActionEvent event) { //change the background color of each note
    	
    	
    }
    
    @FXML
    void createList(ActionEvent event) {
    	
    }
    
    @FXML
    void italicizeText(ActionEvent event) { //italicize selected text
    	noteTextBox.setFont(Font.font( "verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
    }

    

	@FXML
    void underlineText(ActionEvent event) { //underline selected text
    	//noteTextBox.setUnderline(true);
    }
    
    @FXML
    void boldText(ActionEvent event) { //bold selected text
    	
    	noteTextBox.setFont(Font.font( "verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    	
    }


	@FXML		
    void timeStamp(ActionEvent event) { //insert current time and date-- when the note was made
		
	}


    @Override
	public void initialize(URL arg0, ResourceBundle arg1)  {
    	super.initialize(arg0, arg1); //Must include this when you initialize you modules
		baseController.setTitle("Note Pad");	
 	
	}
    

}
