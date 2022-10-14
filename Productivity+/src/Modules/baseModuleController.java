package Modules;

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
    protected Menu titleMenu;


	@FXML
    private MenuItem closeModuelMenuItem;


    @FXML
    void exitModule(ActionEvent event) {
		AnchorPane mainWorkspace = (AnchorPane) rootModuleVBox.getParent(); //Grabs the AnchorPane
		mainWorkspace.getChildren().remove(rootModuleVBox); //Removes module from the pane
		
    }
    
    //Various functions that allow miniModules to change things about the base Module
    protected void setTitle(String newTitle) { //Allows you to change the name of the menu at the top of the minimodules
    	titleMenu.setText(newTitle);
    }
    protected void addNodeToMenu(MenuItem itemToAdd, Menu menuToAddToo) { //Call this function from a mini module to add a menu item to a menu
    	menuToAddToo.getItems().add(itemToAdd);
    }
    protected void addNodeToMenu(Menu menuToAdd, Menu menuToAddToo) { //Adds a Menu to a Menu
    	menuToAddToo.getItems().add(menuToAdd);
    }
    

    public Menu getTitleMenu() {
		return titleMenu;
	}
}

