package application;
import java.util.Properties;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ProductivityPlus extends Application {
	
	@Override
	
	public void start(Stage stage) throws Exception{

//		String pwd = System.getProperty("user.dir");
//
//        System.out.println(pwd);
        
		//String path = "../FXML_Files/ProductivityPlus.fxml";
		Properties prop = ConfigReader.readConfig(); 
		Boolean skipLoading = Boolean.valueOf(prop.getProperty("skipLoading"));
		String path;
		if(skipLoading) { //Allows us to skip loading for testing purposes
			path = "./ProductivityPlus.fxml";
			stage.setMaximized(true);
		}else {
			path = "startUpScreen.fxml";
		}
		
		VBox root = FXMLLoader.load(getClass().getResource(path));
		
		
		Scene scene = new Scene(root);
		stage.setTitle("Productivity Plus");
		stage.setScene(scene);
		//stage.setMaximized(true);
		
		stage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
