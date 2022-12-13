package modules.wordle;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class wordleController {

	@FXML private Button startButton;
	@FXML private Button helpButton;
	@FXML private Label attemptLabel;
	@FXML private Label correctAnswerLabel;
	@FXML private VBox mainVBox;
	@FXML private Label Label0;
	@FXML private Label Label1;
	@FXML private Label Label2;
	@FXML private Label Label3;
	@FXML private Label Label4;
	@FXML private Label Label5;
	@FXML private TextField userInputTextField;
	
	@FXML private Label Label10;
	@FXML private Label Label11;
	@FXML private Label Label12;
	@FXML private Label Label13;
	@FXML private Label Label14;
	@FXML private Label Label15;
	
	private String iGuess = new String();
	
	private Scanner s = new Scanner(System.in);
	private int tries = 0;			//number of tries - max of 6
	private String[] possibleWords;  //array of all 5 letter words
	private String targetWord;		//target word - correct answer
	private boolean gameOver = false;	//game is not over until tries = 6 or target word was guessed
	
	
	@FXML
	void onEnter(ActionEvent event) {
		//when the enter button clicks - grab the word from textfield
		
		System.out.println("EnterPressed!!");
		
		iGuess = userInputTextField.getText();
		System.out.println("Text grabbed from textfield" + iGuess);
		
		if (isWordValid(iGuess, possibleWords) == true) {
			ContinueGame();
			userInputTextField.setText("");
		}
		else {
			correctAnswerLabel.setText("Invalid Word. Try Again!");	
		}
	}

	private void ContinueGame() {
		/**
		 * after validation of existing word and length
		 * 
		 * determine if the word is equal to target word
		 * determine when the game ends
		 * determine if value of characters == done value (2)
		 * set color values
		 * 
		 * set the labels to the guessed word with correct colors corresponding to their usefulness
		 */
		correctAnswerLabel.setText("");
		String guess = new String(userInputTextField.getText());
		System.out.println("ContinueGame() " + guess);
		tries++;
		attemptLabel.setText("Attempt: " + String.valueOf(tries));
		
		char [] guessArray = new char[5];
		for (int i = 0; i < 5; i++) {
			guessArray[i] = guess.charAt(i);
		}
		char[] targetWordArray = new char[5];
		for (int i = 0; i < 5; i++) {
			targetWordArray[i] = targetWord.charAt(i);
		}
		
		int [] colorOfLetter = ReturnColorOfLetter(guessArray, targetWordArray);
	
		String [] colorValue = new String[5];
		for (int i = 0; i < 5; i++) {
			if (colorOfLetter[i] == 0) {
				colorValue[i] = "black";
				//colorValue[i] = "#000000";
			}
			else if (colorOfLetter[i] == 1) {
				colorValue[i] = "yellow";
				//colorValue[i] = "#f9dc35";
			}
			else if (colorOfLetter[i] == 2) {
				colorValue[i] = "green";
				//colorValue[i] = "#0dca08";
			}
		}
		
		System.out.println("\nSet colors to " + colorValue[0] + " " 
				+ colorValue[1] + " " + colorValue[2] + " " + colorValue[3] 
				+ " " + colorValue[4]);

	
		StringBuilder cb = new StringBuilder();
		cb.append(colorValue[0].charAt(0)).append(colorValue[1].charAt(0)).append(colorValue[2].charAt(0)).append(colorValue[3].charAt(0)).append(colorValue[4].charAt(0));
		
		String colorLabel = cb.toString();
		String newLabel = guess;
		
		for (int i = 0; i < colorLabel.length(); i++) {
			if (colorLabel.contains("ggggg")) {
				gameOver = true;
				EndGame(2);
			}
			else if (colorLabel.contains("ggggg") && tries == 6) {
				gameOver = true;
				EndGame(2);
			}
			else if (!(colorLabel.contains("ggggg")) && tries == 6) {
				gameOver = true;
				EndGame(1);
			}
		}
		if (tries > 6 ) {
			gameOver = true;
			EndGame(1); 
		}
		
		
		setLabelText(newLabel, colorLabel);
		
	}

	private int[] ReturnColorOfLetter(char[] guess, char[] targetWord) {
		/**
		 * check the color to the letters
		 * green = 2 = correct position and letter
		 * yellow = 1 = correct letter wrong position
		 * black/reset = 0 letter not in word
		 */
		
		char[] tempTargetWord = targetWord;
		int[] color = new int[5];
		
		//check for green
		for (int i = 0; i < 5; i++) {
			if (guess[i] == tempTargetWord[i]) {
				color[i] = 2;
			}
		}
		
		//check for yellow
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				if (guess[i] == tempTargetWord[j] && color[i] != 2) {
					color[i] = 1;
				}
			}
		}
		//this just prints in console
		for (int m = 0; m < 5; m++) {
			if (color[m] == 0) {
				System.out.println(guess[m]);
			}
			if (color[m] == 1) {
				System.out.println(guess[m] + "[y]");
			}
            if (color[m] == 2) {
            	System.out.println(guess[m] + "[g]");
            }
       
		}
		
		return color;
	}

	private void setLabelText(String newLabel, String colorLabel) {
		//set the labels to the string with the guess colors and word 
		
		if (tries - 1 == 0) {
			Label0.setText(newLabel);
			Label10.setText(colorLabel);
		}
		else if (tries - 1 == 1) {
			Label1.setText(newLabel);
			Label11.setText(colorLabel);
		}
		else if (tries - 1 == 2) {
			Label2.setText(newLabel);
			Label12.setText(colorLabel);
		}
		else if (tries - 1 == 3) {
			Label3.setText(newLabel);
			Label13.setText(colorLabel);
		}
		else if (tries - 1 == 4) {
			Label4.setText(newLabel);
			Label14.setText(colorLabel);
		}
		else if (tries - 1 == 5) {
			Label5.setText(newLabel);
			Label15.setText(colorLabel);
		}
	}


	@FXML
	void onHelpButtonClick(ActionEvent event) {
		// directions on how to play the game popup
		Button b = new Button("Let's Play");
		Label txt = new Label();
		StackPane directions = new StackPane();
		Scene scene = new Scene(directions, 250, 300);
		Stage newStage = new Stage();

		
		txt.setWrapText(true);
		txt.setText("-How to Play- \n\nGuess the word in 5 tries.\n" + "Each guess must be a vaild 5-letter word.\n"
				+ "The color of the tiles change color to indicate how close your guess is.\n\n"
				+ "Green = correct letter in correct position.\n"
				+ "Yellow = correct letter but in the wrong position.\n"
				+ "Black = letter not found in the word anywhere. \n" + "Click Start to play!!");
		
		directions.setPadding(new Insets(15, 10, 10, 5));
		directions.setAlignment(txt, Pos.TOP_CENTER);
		directions.setAlignment(b, Pos.BOTTOM_CENTER);
		directions.getChildren().addAll(b, txt);
		newStage.setTitle("Help");
		newStage.setScene(scene);
		newStage.show();

		b.setOnAction((ActionEvent e) -> {
			newStage.close();
		});

	}

	@FXML
	void onStartButtonClick(ActionEvent event) {
		//start the game using the start button
		//every time the start button is clicked -> restart the game like normal
		RestartGame();
		StartGame();
	}
		
	
	private void RestartGame() {
		// TODO Auto-generated method stub
		tries = 0;
		userInputTextField.setDisable(false);
		correctAnswerLabel.setText("");
		attemptLabel.setText("Attempt: ");
		Label0.setText("-----");
		Label10.setText("-----");
		Label1.setText("-----");
		Label11.setText("-----");
		Label2.setText("-----");
		Label12.setText("-----");
		Label3.setText("-----");
		Label13.setText("-----");
		Label4.setText("-----");
		Label14.setText("-----");
		Label5.setText("-----");
		Label15.setText("-----");
		
	}

	private void StartGame() {
		/**
		 * Start the game
		 * pick a target word, read through file
		 */
		userInputTextField.setDisable(false);
		possibleWords = new String[12947];
		try {
			File file = new File("src/modules/wordle/wordList");
			Scanner read = new Scanner(file);
			int counter = 0;
			while(read.hasNextLine()) {
				String data = read.nextLine();
				possibleWords[counter] = data;
				counter++;
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. WordList File not found.");
			e.printStackTrace();
		}
		
		targetWord = ReturnTargetWord();
		char[] answer = new char[5];
		for (int i = 0; i < 5; i++) {
			answer[i] = targetWord.charAt(i);
		}
		
		System.out.println(targetWord);
		
	}
	
	private void EndGame(int Case) {
		/**
		 * end the game
		 * disable the textfield because the game is over
		 * display correct answer if the answer wasn't guessed
		 * if answer is guessed - say congrats
		 */
		userInputTextField.setDisable(true);
		if (Case == 1) { //ran out of tries before guessed word
			correctAnswerLabel.setText("Target word =   " + targetWord.toUpperCase());
			attemptLabel.setText("     Darn! " );
		}
		else if (Case == 2) { //guessed word
			correctAnswerLabel.setText("Correct!     " + targetWord.toUpperCase());
			attemptLabel.setText("You guessed it in " + tries);
		}
		else if (Case == 3) {
			correctAnswerLabel.setText("Correct!     " + targetWord.toUpperCase());
			attemptLabel.setText("You guessed it in " + tries);
		}
		
	}

	private String ReturnTargetWord() {
		//creates an array with the possible target words to guess and picks one 
		String[] answerList = new String[2315];
		try {
			File file = new File("src/modules/wordle/wordleAnswers");
			Scanner read = new Scanner(file);
			int counter = 0;
			while(read.hasNextLine()) {
				String data = read.nextLine();
				answerList[counter] = data;
				counter++;
			}
			read.close();
		} catch (FileNotFoundException e) {
			System.out.println("Error. Answers File not found.");
			e.printStackTrace();
		}
		
		return answerList[(int) (Math.random() * (answerList.length - 1))];
	}
	
	private boolean isWordValid(String input, String [] possibleWords) {
		/** 
		 * validates word 
		 * if word is less than 5 letters -- invalid -->false
		 * if word does not match a word from the possible answer list -- invalid --> false
		 * 
		 * loop through the possible words array
		 * if word does match -- valid --> true
		 */
		
		input.toLowerCase();
		if (input.length() < 5) {
			correctAnswerLabel.setText("Invalid Word. Not Long Enough. Try Again!");
			System.out.println("Invalid word. Not long enough");
			return false;
		}
		
		for (String str : possibleWords) {
			if (str.equals(input)) {
				return true;
			}
		}
		
		return false;
		
	}
	
	

	public void initialize() throws IOException {
		
		
	}

}
