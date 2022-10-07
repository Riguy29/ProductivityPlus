package application;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public abstract class MiniProgramBase {
	
	@FXML
	protected StackPane basePane = new StackPane();
	private final BorderStroke borderStroke = new BorderStroke(Color.BLACK,BorderStrokeStyle.SOLID,null,new BorderWidths(2));
	private final Insets defaultInsets = new Insets(10);
	protected Border defaultBorder= new Border(borderStroke);
	
	DraggableMaker draggableMaker = new DraggableMaker();
	
	public abstract StackPane build();
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
}
