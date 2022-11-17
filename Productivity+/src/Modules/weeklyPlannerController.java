package Modules;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.input.MouseDragEvent;
import javafx.scene.layout.StackPane;

public class weeklyPlannerController extends baseModuleInitalizer {

	    @FXML
	    private TitledPane mondayTitlePane;

	    @FXML
	    private TextArea mondayTextArea;

	    @FXML
	    private TitledPane tuesdayTitlePane;

	    @FXML
	    private TextArea tuesdayTextArea;

	    @FXML
	    private TitledPane wednesTitlePane;

	    @FXML
	    private TextArea wednesTextArea;

	    @FXML
	    private TitledPane thursTitlePane;

	    @FXML
	    private TextArea thursTextArea;

	    @FXML
	    private TitledPane fridayTitlePane;

	    @FXML
	    private TextArea fridayTextArea;

	
	

	@Override
	public void initialize() throws IOException  {
    	super.initialize(); //Must include this when you initalize you modules
		baseController.setTitle("Weekly Planner");	
		}
	
	@FXML
    private CheckBox addCheckBox;
	
	TextArea newTextArea = new TextArea();

    @FXML
    void addCheckBoxDraggable(ActionEvent event) {
    	CheckBox newCheckBox = new CheckBox();
    	TextField textF = new TextField();
    	String text = new String();
    	
    	
    	newCheckBox.setOnAction(Event -> {
    		
    	});
    	
    	if (newCheckBox.isSelected()) {
    		
    	}
    	
    }


	@FXML
    void checkBoxDragged(MouseDragEvent event) {
    	
    	
    }
}

