package application;

import java.beans.EventHandler;
import java.io.IOException;
import java.util.Properties;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.FadeTransition;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

public class startUpScreenController {

	@FXML
	private VBox loadingScreen;
	@FXML
	private Label greetingsLabel;

	@FXML
	private Label titleLabel;
	@FXML
	private Label nameLabel;

	@FXML
	private ProgressBar loadingBar;

	String pathToMainScreen = "./ProductivityPlus.fxml";

	public void initialize() throws IOException {
		Properties prop = ConfigReader.readConfig();
		String userName = prop.getProperty("user");
		nameLabel.setText(userName);

		FadeTransition fadeInWelcome = new FadeTransition(Duration.seconds(1), greetingsLabel);
		fadeInWelcome.setFromValue(0);
		fadeInWelcome.setToValue(1);
		fadeInWelcome.play();

		FadeTransition fadeInTitle = new FadeTransition(Duration.seconds(1), titleLabel);
		fadeInTitle.setFromValue(0);
		fadeInTitle.setToValue(1);
		FadeTransition fadeInName = new FadeTransition(Duration.seconds(1), nameLabel);
		fadeInName.setFromValue(0);
		fadeInName.setToValue(1);

		fadeInWelcome.setOnFinished(actionEvent -> {
			fadeInTitle.play();
		});
		fadeInTitle.setOnFinished(e -> {
			if (userName != null) {
				fadeInName.play();
			}

		});
		loadingAnimation();

	}

	private void loadingAnimation() {

		Timer timer = new Timer();
		Random r = new Random();

		loadingBar.progressProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				if (newValue.doubleValue() >= 1) { // When progress bar is filled, load the main screen
					// System.out.print("Done Loading");
					timer.cancel(); // Stops timer
					loadMainScreen();
				}
			}
		});
		TimerTask task = new TimerTask() {
			@Override
			public void run() {
				float randomLoadSpeed = r.nextInt(15 - 5) + 5; // Picking Number from 5 to 20
				randomLoadSpeed /= 100; // Dividing by 100 to give us .XX
				// System.out.println(randomLoadSpeed/100);
				loadingBar.setProgress(loadingBar.getProgress() + randomLoadSpeed);
			}
		};
		timer.schedule(task, 500, 500);
	}

	private void loadMainScreen() {

		Platform.runLater(() -> { // Switching to application thread or java yells at me
			VBox root;
			try {

				// Shuts down current stage
				root = FXMLLoader.load(getClass().getResource(pathToMainScreen));
				Stage stage = (Stage) loadingScreen.getScene().getWindow();
				stage.close();

				// Loading main screen stage
				Stage mainScreenStage = new Stage();
				Scene scene = new Scene(root);
				mainScreenStage.setTitle("Productivity Plus");
				mainScreenStage.setScene(scene);
				mainScreenStage.setMaximized(true);
				mainScreenStage.show();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		});

	}
}
