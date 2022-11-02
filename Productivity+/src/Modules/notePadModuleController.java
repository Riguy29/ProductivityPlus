package Modules;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.TextArea;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import java.sql.Timestamp;    
import java.util.Date;  
import java.text.SimpleDateFormat; 

public class notePadModuleController extends baseModuleInitalizer{
	
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
    
    Color c = colorPicker.getValue();
  	String text = noteTextBox.getText();
  	
  	

    @FXML
    void addNewNote(ActionEvent event){ //duplicate an empty notepad
    	
    }

    @FXML
    void changePadColor(ActionEvent event) { //change the background color of each note
//    	  colorPicker.setOnAction((EventHandler<ActionEvent>) event); 
//    		 c = (Color) colorPicker.getUserData();
    	  
    }
    
    @FXML
    void createList(ActionEvent event) {
    	
    }
    
    @FXML
    void italicizeText(ActionEvent event) { //italicize selected text
    	noteTextBox.setFont(Font.font( "verdana", FontWeight.NORMAL, FontPosture.ITALIC, 12));
    	
    }
  

	@FXML
    void exitNotePad(ActionEvent event) { //exit notepad/delete
			//noteTextBox.getChildren().clear();
    }
	
	@FXML
    void boldText(ActionEvent event) { //bold selected text				
    	noteTextBox.setFont(Font.font( "verdana", FontWeight.BOLD, FontPosture.REGULAR, 12));
    	
    }
		
	
	@FXML		
    void timeStamp(ActionEvent event) { //insert current time and date-- when the note was made
		Date date = new Date();  
	     Timestamp ts = new Timestamp(date.getTime());  
	     SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	       System.out.println(formatter.format(ts));
	       noteTextBox.setText(formatter.format(ts) + (noteTextBox.getText()));
	       
	}


    @Override
	public void initialize() throws IOException  {
    	super.initialize(); //Must include this when you initialize you modules
		baseController.setTitle("Note Pad");	
 	
	}
    

}
