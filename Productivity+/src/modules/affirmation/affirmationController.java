package modules.affirmation;

import java.io.IOException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class affirmationController{
	
	 @FXML
	 private Pane affPane;
	 Pane root = new Pane();

    @FXML
    private TextArea affTextArea;

    @FXML
    private Button newAffButton;

    @FXML
    private TextField dateTextField;
    
    private List<String> AffirmationList = new ArrayList<String>();
    
    Date date = new Date();
    DateFormat DFormat = DateFormat.getDateInstance(DateFormat.LONG);
    

    @FXML
    void selectNewAffButtonPressed(ActionEvent event) {
    	nextAffirmation();
    	randomBackgroundColor();
    	
    }

	public void initialize() throws IOException {
		
		dateTextField.setText(DFormat.format(date));
		
		AffirmationList.add("I’m enthusiastic and excited about my work.");
		AffirmationList.add("I love myself.");
		AffirmationList.add("Life is full of love and I find it everywhere I go.");
		AffirmationList.add("My power is unlimited.");
		AffirmationList.add("I'm wothy and value myself.");
		AffirmationList.add("I believe in myself.");
		AffirmationList.add("I see the beauty in everything.");
		AffirmationList.add("I'm enthusiastic about every second of my life.");
		AffirmationList.add("I am enough.");
		AffirmationList.add("I am in the right place, at the right time, doing the right thing.");
		AffirmationList.add("I can do hard things.");
		AffirmationList.add("I am the architect of my life; I build its foundation and chose its contents.");
		AffirmationList.add("I trust myself to make the right decision. I have the tools and abilities that I need ot do so.");
		AffirmationList.add("I am beautiful.");
		
    }
    
    private void nextAffirmation() {
    	Random randomClass = new Random();
    	int randomIndex = randomClass.nextInt(AffirmationList.size());
    	
    	affTextArea.setText(AffirmationList.get(randomIndex));
    	
    }
    
    private void randomBackgroundColor() {
    	Random random = new Random();
    	
        float redValue = random.nextInt(255);
    	float blueValue = random.nextInt(255);
    	float greenValue = random.nextInt(255);
   	
    	//affPane.getFills(Color.color(redValue, greenValue, blueValue, greenValue));
    	

    }
		
		
}
