package application;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.MenuItem;
import javafx.scene.text.Text;

public class ProductivityPlusController {

    @FXML
    private MenuItem workPresetLayout;

    @FXML
    private MenuItem studyPresetLayoutMenu;

    @FXML
    private MenuItem dailyTaskListCreation;

    @FXML
    private MenuItem aboutMenuItem;

    @FXML
    private Text test;

    @FXML
    void onAboutButtonClick(ActionEvent event) {
    	test.setText("This is working dumb dumb");
    }

}
