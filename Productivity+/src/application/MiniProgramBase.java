package application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public abstract class MiniProgramBase extends ProductivityPlusController{
	
	@FXML
	protected StackPane basePane = new StackPane();
	private final BorderStroke borderStroke = new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(2));
	private final Insets defaultInsets = new Insets(10);
	protected Border defaultBorder= new Border(borderStroke);
	

	
	DraggableMaker draggableMaker = new DraggableMaker();
	
	public  abstract StackPane build();
	public void exit() {
		
	}
	protected final void makePaneDraggable(){
		draggableMaker.makeDraggable(basePane);
	}

	protected final void setToDefaults() {
		basePane.setBorder(defaultBorder);
		basePane.setPadding(defaultInsets);
		basePane.setAlignment(Pos.TOP_CENTER);
		
	}
	protected MenuBar addMenu(String moduleTitle, StackPane parentPane) {
		MenuItem minimizeModule = new MenuItem("Minimize");
		MenuItem exitModule = new MenuItem("Exit");
		
		exitModule.setOnAction(event ->{ //When the exit button is clicked the node exits
			AnchorPane mainWorkspace = (AnchorPane) basePane.getParent();
			mainWorkspace.getChildren().remove(basePane);
			
			
		});
		MenuBar menuBar = new MenuBar();
		Menu titleMenu = new Menu(moduleTitle);
		//TODO: Try and get the titleMenu centered
		
		menuBar.getMenus().add(titleMenu);
		
		titleMenu.getItems().addAll(minimizeModule,exitModule);
		
		return menuBar;
	}
}
