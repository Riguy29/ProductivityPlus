package modules.brainWarmup;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

public class brainWarmupController {


    @FXML
    private VBox parentVBox;

    @FXML
    private Label brainWarmupLabel;

    @FXML
    private Button startButton;
    
    @FXML
    private TextField answerTextField = new TextField();
    @FXML
    private Label questionLabel = new Label();
    @FXML
    private Button submitButton = new Button("Submit Answer");
    
    
    private List<String> QuizQuestions = new ArrayList<String>();
    private List<String>QuizAnswer = new ArrayList<String>();
    private String answer;
    private int amtCorrect; 
    private int totalQuestions = 5;
    @FXML
    void onStartButtonClick(ActionEvent event) {
    	parentVBox.getChildren().removeAll(startButton,brainWarmupLabel);
    	parentVBox.getChildren().addAll(questionLabel,answerTextField,submitButton);

    	
    	nextQuestion();
    	
    	submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent arg0) {
				
				if(submitButton.getText().equals("Next Question")){
					nextQuestion();
					submitButton.setText("Submit");
					answerTextField.setVisible(true); //Show textfield for user to enter answer
				}
				else { //If the button is in submit mode
					
					String userAnswer = answerTextField.getText();
					userAnswer.toLowerCase();
					if(userAnswer.equals(answer)) {
						System.out.print("Correct!");
						questionLabel.setText("Correct");
						amtCorrect += 1;
					}
					else {
						System.out.print("Correct!");
						questionLabel.setText("Incorrect");
					}
					
					
					if(QuizQuestions.isEmpty() == true) { //If the List is empty show end screen with score
						//TODO: Show a end screen with the users score
						parentVBox.getChildren().removeAll(answerTextField,submitButton);
						questionLabel.setText(String.format("You scored %d/%d", amtCorrect,totalQuestions));
					}
					else { //Else let user to go to next question
						submitButton.setText("Next Question");
						answerTextField.setVisible(false); //Hides answer text field
						answerTextField.setText(""); //Clears answer box
					}
				}

				
			}    		
    	});


    }
	public void initialize() throws IOException {
		
		
    	//TODO: Add pictures in 
    	//Fill in Quiz Arrays
    	QuizQuestions.add("What has cities, but no houses;\n forests, but no trees;\n and water, but no fish");
    	QuizAnswer.add("a map");
    	QuizQuestions.add("A bat and a ball cost $1.10 in total.\n The bat costs $1.00 more than the ball.\n How many cents does the ball cost?");
    	QuizAnswer.add("5");
    	QuizQuestions.add("Whats 3 + 3*3 /3");
    	QuizAnswer.add("6");
    	QuizQuestions.add("A Red House Is Made From Red Bricks.\n A Blue House Is Made From Blue Bricks.\n A Yellow House Is Made From Yellow Bricks.\n What Is A Green House Made From?");
    	QuizAnswer.add("glass");
    	QuizQuestions.add("David?s Father Has Three Sons: Snap, Crackle, And ________?");
    	QuizAnswer.add("david");
    	//Commented these out until we get a more complex detection system -Riley
//    	QuizQuestions.add("A girl fell off of a 20ft ladder. She wasn't hurt. How?");
//    	QuizAnswer.add("She was on the bottom step");
//    	QuizQuestions.add("There?s a one-story house where everything is yellow. The walls are yellow. The doors are yellow. All the furniture is yellow. The house has yellow beds and yellow couches. What color are the stairs?");
//    	QuizAnswer.add("There are no stairs?it?s a one-story house!");
	}
    
    private void nextQuestion() {
    	Random randomClass = new Random();
    	int randomIndex = randomClass.nextInt(QuizQuestions.size());
    	
    	questionLabel.setText(QuizQuestions.get(randomIndex));
    	answer = QuizAnswer.get(randomIndex);
    	QuizQuestions.remove(randomIndex);
    	QuizAnswer.remove(randomIndex);
    }
}
