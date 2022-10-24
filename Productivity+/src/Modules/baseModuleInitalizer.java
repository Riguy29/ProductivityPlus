package Modules;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.DraggableMaker;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.VBox;

public class baseModuleInitalizer{

	DraggableMaker draggableMaker = new DraggableMaker();
	public VBox baseVBox;
	protected baseModuleController baseController;

	public void initialize () throws IOException {
		FXMLLoader moduleLoader = new  FXMLLoader(getClass().getResource("../FXML_Files/baseModule.fxml"));
		baseVBox = moduleLoader.load(); //Placing base module FXML file into a vbox

		draggableMaker.makeDraggable(baseVBox); //Making the base module draggable
		baseController = moduleLoader.getController(); //Getting controller so that children of this class can call methods from the baseController
	}
	public baseModuleController getBaseController() {
		return baseController;
	}

}
