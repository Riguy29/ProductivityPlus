package application;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class baseModuleController implements Initializable{
	
	
	@FXML
	private Node childNode;
	
    @FXML
    private VBox rootModuleVBox;


    @FXML
    protected Menu titleMenu;


	@FXML
    private MenuItem closeModuelMenuItem;
    @FXML
    private MenuItem minimizeMenuItem;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		 
		
	}
    @FXML
    void exitModule(ActionEvent event) {
		AnchorPane mainWorkspace = (AnchorPane) rootModuleVBox.getParent(); //Grabs the AnchorPane
		mainWorkspace.getChildren().remove(rootModuleVBox); //Removes module from the pane
		
    }
    @FXML
    void minimizeModule(ActionEvent event) {
    	
    	if(minimizeMenuItem.getText().equals("Minimize")){
    		childNode = rootModuleVBox.getChildren().get(1); //First gets the 2nd child node, 
    		rootModuleVBox.getChildren().remove(childNode);
    		minimizeMenuItem.setText("Maximize");
    	}
    	else {
    		rootModuleVBox.getChildren().add(childNode); //Because this can only trigger after getting the node, we don't have to worry about this being null
    		minimizeMenuItem.setText("Minimize");
    	}
    	
    }

    @FXML
    void renameModule(ActionEvent event) {
    	VBox popupVbox = new VBox();
    	popupVbox.setAlignment(Pos.CENTER);
    	TextField newNameTextField = new TextField("Enter new name for module here");
    	Button confirmRename = new Button("Rename");
    	final Stage renameWindow = new Stage();
    	renameWindow.initModality(Modality.APPLICATION_MODAL);
    	renameWindow.initOwner(null);
    	popupVbox.getChildren().addAll(newNameTextField,confirmRename);
    	Scene popupWindowScene = new Scene(popupVbox);
    	renameWindow.setScene(popupWindowScene);
    	renameWindow.show();
    	
    	confirmRename.setOnAction(Event ->{ //Renames module and then closes popup box
    		setTitle(newNameTextField.getText());
    		renameWindow.close();
    	});
    	
    }

    
    //Various functions that allow miniModules to change things about the base Module
    public void setTitle(String newTitle) { //Allows you to change the name of the menu at the top of the minimodules
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

