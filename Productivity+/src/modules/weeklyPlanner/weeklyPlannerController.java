package modules.weeklyPlanner;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import javax.xml.bind.JAXB;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;


public class weeklyPlannerController {

	  @FXML
	    private TextArea MTextArea;

	    @FXML
	    private TextArea TTextArea;

	    @FXML
	    private TextArea WTextArea;

	    @FXML
	    private TextArea RTextArea;

	    @FXML
	    private TextArea FTextArea;

	    @FXML
	    private ChoiceBox<String> instanceChoiceBox;
    
	    private final String XML_FILE = "weeklyPlannerInstances.xml";
	    private ArrayList<WeeklyPlannerInstance> instancesList = new ArrayList<>();
    
    WeeklyPlannerInstance currentInstance;
    
   

	public void initialize() throws IOException  {
		String [] instances = {"Week 1", "Week 2", "Week 3"};
		instanceChoiceBox.setItems(FXCollections.observableArrayList(instances));
		
		instanceChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {
			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				saveCurrentInstance();
				saveData();
				setWeeklyPlannerInfo(newValue.intValue());
			}
		});
		
		try (BufferedReader input = Files.newBufferedReader(Paths.get(XML_FILE))) {
			WeeklyPlannerInstances planners = JAXB.unmarshal(input, WeeklyPlannerInstances.class);
			
			for (WeeklyPlannerInstance planner : planners.getInstances()) {
				instancesList.add(planner);
			}
		}
		catch (IOException ioE) {
			System.err.println("Error opening file.");
		}
		instanceChoiceBox.setValue(instances[0]);
	}
	
	private void saveCurrentInstance() {
		try {
			currentInstance.setText(MTextArea.getText());
			currentInstance.setText(TTextArea.getText());
			currentInstance.setText(WTextArea.getText());
			currentInstance.setText(RTextArea.getText());
			currentInstance.setText(FTextArea.getText());
			
		} 
		catch (NullPointerException e) {
			System.err.println("Triggered when manually open weeklyplanner. Safely Ignore :)");
		}
	}
	
	private void saveData() {
		try {
			BufferedWriter output = Files.newBufferedWriter(Paths.get(XML_FILE));
			WeeklyPlannerInstances instances = new WeeklyPlannerInstances();
			
			for (WeeklyPlannerInstance i : instancesList) {
				WeeklyPlannerInstance savedInstance = new WeeklyPlannerInstance(i.getText());
				instances.getInstances().add(savedInstance);
			}
				
			JAXB.marshal(instances, output);	
		}
		catch (IOException e) {
		e.printStackTrace();
		}
		
	}
	
	private void setWeeklyPlannerInfo(int weeklyPlannerInstance) {
		currentInstance = instancesList.get(weeklyPlannerInstance);
		
		MTextArea.setText(currentInstance.getText());
		TTextArea.setText(currentInstance.getText());
		WTextArea.setText(currentInstance.getText());
		RTextArea.setText(currentInstance.getText());
		FTextArea.setText(currentInstance.getText());
		
	}
	
	
}

