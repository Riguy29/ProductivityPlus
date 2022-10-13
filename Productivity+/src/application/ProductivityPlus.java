package application;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductivityPlus extends Application {
	
	@Override
	
	public void start(Stage stage) throws Exception{
		VBox root = FXMLLoader.load(getClass().getResource("../FXML_Files/ProductivityPlus.fxml"));
		
		
		Scene scene = new Scene(root);
		stage.setTitle("Productivity Plus");
		stage.setScene(scene);
		stage.setMaximized(true);
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
		//ProductivityPlusController mainController = new ProductivityPlusController();
		//mainController.popUpAboutMenuOnStart();
	}
}
