package Modules;

import application.DraggableMaker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class baseModuleController {


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
    
    //Various functions that allow miniModules to change things about the base Module
    protected void setTitle(String newTitle) {
    	moduleFunctionsMenu.setText(newTitle);
    }
    protected void addNodeToMenu(MenuItem itemToAdd, Menu menuToAddToo) {
    	menuToAddToo.getItems().add(itemToAdd);
    }
    protected void addNodeToMenu(Menu menuToAdd, Menu menuToAddToo) {
    	menuToAddToo.getItems().add(menuToAdd);
    }
    
    
    public Menu getModuleFunctionsMenu() {
		return moduleFunctionsMenu;
	}
}

