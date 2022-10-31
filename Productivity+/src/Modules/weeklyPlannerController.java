package Modules;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TitledPane;

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
}

