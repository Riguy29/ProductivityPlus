package modules.weeklyPlanner;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.text.Text;

public class weeklyPlannerController {

    @FXML private TitledPane mondayTitlePane;
    @FXML private TextArea mondayTextArea;
    @FXML private TitledPane tuesdayTitlePane;
    @FXML private TextArea tuesdayTextArea;
    @FXML private TitledPane wednesTitlePane;
    @FXML private TextArea wednesTextArea;
    @FXML private TitledPane thursTitlePane;
    @FXML private TextArea thursTextArea;
    @FXML private TitledPane fridayTitlePane;
    @FXML private TextArea fridayTextArea;
    @FXML private CheckBox addCheckBox;
	 
	 TextArea newTextArea = new TextArea();
	 Text t = new Text();
	 final CheckBox source = new CheckBox("Drag Me");
	 final CheckBox target = new CheckBox("Drop ME");


    @FXML
    void addCheckBoxDraggable(ActionEvent event) {
    	CheckBox newCheckBox = new CheckBox();
    	TextField textF = new TextField();
    	String text = new String();
    	
    	
    	
    }


	@FXML
    void checkBoxDragged(MouseDragEvent event) {

    	
    }
}

