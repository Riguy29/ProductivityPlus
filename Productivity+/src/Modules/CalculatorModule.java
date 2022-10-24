package Modules;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class CalculatorModule extends baseModuleInitalizer {

    @FXML
    private Label previousOperation;

    @FXML
    private TextField currOpTextField;

    @FXML
    private GridPane simpleCalculator;
    
    
    private String currNum = "";
    private String prevNum;
    private Double prevResult;
    
    private enum operations{
    	add,
    	subtract,
    	multiply,
    	divide,  			
    }
    private operations currOperation;
    
    @Override
	public void initialize() throws IOException {
		super.initialize();
		baseController.setTitle("Calculator");
	}
    
    @FXML
    void add(ActionEvent event) {
    	currOperation = operations.add;
    	
    	if(prevResult != null) { //Uses last result if the user clicks on an operation without entering an new number
    		currNum = Double.toString(prevResult);
    		prevResult = null;
    	}
    	updateLastOperationDisplay(currNum + "+");    		
    }

    @FXML
    void subtract(ActionEvent event) {
    	currOperation = operations.subtract;
    	if(prevResult != null) { //Uses last result if the user clicks on an operation without entering an new number
    		currNum = Double.toString(prevResult);
    		prevResult = null;
    	}
    	updateLastOperationDisplay(currNum + "-");
    }

    @FXML
    void mutiply(ActionEvent event) {
    	currOperation = operations.multiply;
    	if(prevResult != null) { //Uses last result if the user clicks on an operation without entering an new number
    		currNum = Double.toString(prevResult);
    		prevResult = null;
    	}
    	updateLastOperationDisplay(currNum + "*");
    }

    @FXML
    void divide(ActionEvent event) {
    	currOperation = operations.divide;
    	if(prevResult != null) { //Uses last result if the user clicks on an operation without entering an new number
    		currNum = Double.toString(prevResult);
    		prevResult = null;
    	}
    	updateLastOperationDisplay(currNum + "/");
    }

    @FXML
    void equals(ActionEvent event) {
    	
    	double firstNum;		
    	double secondNum;
		
    	try {
			firstNum = Double.parseDouble(prevNum);
			secondNum = Double.parseDouble(currNum);
		} catch (NullPointerException e) {
			System.err.print("ERROR in equal function: One of the numbers was null, exiting function");
			return;
		}
    	catch(NumberFormatException e) {
			System.err.print("ERROR in equal function: One of the numbers is an empty string");
			return;
    	}
    	updateLastOperationDisplay(previousOperation.getText() + currNum +" =");
    	
    	double result = 128309582;
    	switch(currOperation) {
	    	case add:
	    		result = firstNum + secondNum;
	    		break;
	    	case subtract:
	    		result = firstNum - secondNum;
	    		break;
	    	case multiply:
	    		result = firstNum * secondNum;
	    		break;
	    	case divide:
	    		if(secondNum == 0) { //If users attempts to divide by zero, breaks out of function
	    			updateDisplay("ERR: Divide by 0");
	    			return;	    			
	    		}
	    		else {	    			
	    			result = firstNum / secondNum;
	    		}
		    	break;
    	}
    	prevResult = result;
    	currNum = "";
    	String displayResultString ="YOU SHOULD NOT BE SEEING THIS";
    	if(result - Math.floor(result) == 0) { //If the number is whole don't print decimal places   		
    		displayResultString = String.format("%.0f", result);    		
    	}
    	else { //Else prints to two decimal places
    		displayResultString = String.format("%.2f", result);    
    	}
    	updateDisplay(displayResultString);
    	
    }

    @FXML
    void onClear(ActionEvent event) { //Clears everything
    	currNum = "";
    	prevNum = "";
    	prevResult = 0.0;
    	updateDisplay("");
    	updateLastOperationDisplay("");
    }
    @FXML
    void onNum0(ActionEvent event) {insertNumber(0);}

    @FXML
    void onNum1(ActionEvent event) { insertNumber(1); }

    @FXML
    void onNum2(ActionEvent event) { insertNumber(2);}

    @FXML
    void onNum3(ActionEvent event) {insertNumber(3);}

    @FXML
    void onNum4(ActionEvent event) {insertNumber(4);}

    @FXML
    void onNum5(ActionEvent event) {insertNumber(5);}

    @FXML
    void onNum6(ActionEvent event) {insertNumber(6);}

    @FXML
    void onNum7(ActionEvent event) {insertNumber(7);}

    @FXML
    void onNum8(ActionEvent event) { insertNumber(8);}

    @FXML
    void onNum9(ActionEvent event) {insertNumber(9);}

    @FXML
    void decimal(ActionEvent event) {
    	if(!currNum.contains(".")) {//Only allows user to add a decimal if the current number doesnt have one
    		if(currNum.isEmpty()) { //IF the decimal is the first thing a users enters, add 0 infront
    			currNum = "0.";
    		}
    		else {    		
    			currNum += ".";
    		}
    		updateDisplay(currNum);    		
    	}
    }
    public void updateDisplay(String displayValue) {
    	currOpTextField.setText(displayValue);  	
    }
    private void insertNumber(int num) {
    	prevResult = null;
        if(currNum.equals("0")){ //If the value is 0, replaces the zero with the new number so we dont have leading zeros
            currNum = Integer.toString(num);
        }
        else{
            currNum = currNum + Integer.toString(num);
        }
    	updateDisplay(currNum);
    }
    public void updateLastOperationDisplay(String newInfo) {
    	previousOperation.setText(newInfo);
    	prevNum = currNum;
    	currNum = "";
    }



}

