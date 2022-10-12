package OLDMODULESSYSTEM;

import java.io.IOException;

import application.DraggableMaker;
import application.ProductivityPlusController;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public abstract class ModuleBase extends ProductivityPlusController{
	
	@FXML
	protected StackPane basePane = new StackPane();
	private VBox verticalOrganizer = new VBox();
	private final BorderStroke borderStroke = new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(2));
	private final Insets defaultInsets = new Insets(2);
	protected Border defaultBorder= new Border(borderStroke);
	private Background defaultBackground = new Background(new BackgroundFill(Color.WHITE, null, defaultInsets));
	
	private MenuBar menuBar = new MenuBar();
	private Menu titleMenu = new Menu();
	
	DraggableMaker draggableMaker = new DraggableMaker();
	
	public StackPane build() {
		makePaneDraggable();
		setToDefaults();
		createTopMenu();
		basePane.getChildren().add(verticalOrganizer);
		return basePane;
		
	}
	private final void makePaneDraggable(){
		draggableMaker.makeDraggable(basePane);
	}

	private final void setToDefaults() {
		basePane.setBorder(defaultBorder);
		basePane.setPadding(defaultInsets);
		basePane.setAlignment(Pos.TOP_CENTER);
		basePane.setBackground(defaultBackground);
		
	}
	protected void createTopMenu() {
		MenuItem minimizeModule = new MenuItem("Minimize");
		MenuItem exitModule = new MenuItem("Exit");
		MenuItem renameModule = new MenuItem("Rename");
		
		exitModule.setOnAction(event ->{ //When the exit button is clicked the node exits
			AnchorPane mainWorkspace = (AnchorPane) basePane.getParent(); //Grabs the AnchorPane
			mainWorkspace.getChildren().remove(basePane); //Removes module from the pane
			
			
		});
		minimizeModule.setOnAction(event ->{
			//TODO: Get Minimize functionality working
			
		});
		renameModule.setOnAction(event ->{
			//TODO: Allow users to rename the modules
		});
		
		
		//TODO: Try and get the titleMenu centered
		
		menuBar.getMenus().add(titleMenu);
		
		titleMenu.getItems().addAll(minimizeModule,exitModule,renameModule);
		
		verticalOrganizer.getChildren().add(menuBar);
	}
	public VBox getVerticalOrganizer() {
		return verticalOrganizer;
	}
	
	public void addMenuItemToTitleMenu(MenuItem newMenuItem) {
		titleMenu.getItems().add(newMenuItem);
		
	}
	public void setTitleMenuText(String titleText) {
		titleMenu.setText(titleText);
	}
	
}
