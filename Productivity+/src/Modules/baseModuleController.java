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
    private Menu moduleFunctionsMenu;


	@FXML
    private MenuItem closeModuelMenuItem;


    @FXML
    void exitModule(ActionEvent event) {
		AnchorPane mainWorkspace = (AnchorPane) rootModuleVBox.getParent(); //Grabs the AnchorPane
		mainWorkspace.getChildren().remove(rootModuleVBox); //Removes module from the pane
    }
    
    public void setTitle(String newTitle) {
    	moduleFunctionsMenu.setText(newTitle);
    }
    public void addNodeToMenu(MenuItem itemToAdd, Menu menuToAddToo) {
    	menuToAddToo.getItems().add(itemToAdd);
    }
    public void addNodeToMenu(Menu menuToAdd, Menu menuToAddToo) {
    	menuToAddToo.getItems().add(menuToAdd);
    }
    public Menu getModuleFunctionsMenu() {
		return moduleFunctionsMenu;
	}
}

