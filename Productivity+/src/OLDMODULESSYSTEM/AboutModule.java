package OLDMODULESSYSTEM;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

public class AboutModule extends ModuleBase{

	protected AboutModule() throws IOException {
		
		// TODO Auto-generated constructor stub
	}


	private double width = 200, height = 100;
	private Text infoText = new Text(
			"Welcome to ProductivityPlus, your all in one suite to help with your productivity\n"
			+ "ProductivityPlus offers a wide variety of small programs, called modules that will assist you in many different settings\n"
			+ "Need to study for school and need some relaxing music, open up the Music Player Module!\n"
			+ "Need to organize a task list for work, we got you covered simply open up the Task List Module!\n"
			+ "To exit any module, including this one, click the menu at the top and select exit\n"
			+ "Created by:"
			+ "Riley Arneson - Programmer, Project Manager, huge nerd\n"
			+ "Avery Jensen - Programmer, GUI Designer\n"
			+ "Maddie Jones - Programmer, Artist\n");
	
	
	public StackPane build() {
		super.build();
		super.setTitleMenuText("About ProductivityPlus");
		VBox parentVbox = super.getVerticalOrganizer();
		parentVbox.setMaxSize(width, height);
		parentVbox.getChildren().add(infoText);
		parentVbox.getChildren().add(new CheckBox("Don't show this again"));
		return basePane;
	}

}
