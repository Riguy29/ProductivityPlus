package Modules;

import application.DraggableMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class baseModuleController {

	DraggableMaker draggableMaker = new DraggableMaker();
			
    @FXML
    private VBox rootModuleVBox;

    @FXML
    private Menu moduleTitle;

    @FXML
    private MenuItem closeModuelMenuItem;


    @FXML
    void exitModule(ActionEvent event) {
		AnchorPane mainWorkspace = (AnchorPane) rootModuleVBox.getParent(); //Grabs the AnchorPane
		mainWorkspace.getChildren().remove(rootModuleVBox); //Removes module from the pane
    }
}

