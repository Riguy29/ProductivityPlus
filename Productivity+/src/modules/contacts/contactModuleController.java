package modules.contacts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Collections;

import javax.imageio.ImageIO;
import javax.xml.bind.JAXB;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.util.Callback;

public class contactModuleController {

    @FXML private ListView<Contact> contactListView;
    @FXML private ChoiceBox<String> sortByChoiceBox;
    @FXML private ImageView contactImage;
    @FXML private GridPane contactInfoGrid;
    @FXML private TextField contactName;
    @FXML private TextField contactPhoneNumber;
    @FXML private TextField contactEmail;
    @FXML private TextField contactGroup;
    @FXML private DatePicker contactDOB;
    
    
    private final String XML_FILE = "contacts.xml";
    private final ObservableList<Contact> contactsForListView = FXCollections.observableArrayList();
    public void initialize() {
    	String[] waysToSort = {"Name","DOB","Group"};
    	sortByChoiceBox.setItems(FXCollections.observableArrayList(waysToSort));
    	sortByChoiceBox.getSelectionModel().selectedIndexProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				ContactComparator contactCompare = new ContactComparator();
				contactCompare.SetCompareMethod(waysToSort[newValue.intValue()]);
				Collections.sort(contactsForListView,contactCompare);
			}
    		
    	});
    	
    	
        try(BufferedReader input = 
                Files.newBufferedReader(Paths.get(XML_FILE))) {
                // unmarshal the file's contents
                Contacts contacts = JAXB.unmarshal(input, Contacts.class);

                for (Contact contact : contacts.getContacts()) {
                	contactsForListView.add(contact);
                }
             } 
             catch (IOException ioException) {
                System.err.println("Error opening file.");
             }
        contactListView.setItems(contactsForListView);

        
        //When selecting different contacts loads their data into the FXML
        contactListView.getSelectionModel().selectedItemProperty().
           addListener(
              new ChangeListener<Contact>() {
				@Override
				public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
			    	contactInfoGrid.setDisable(false); //Enables the contact layout grid when a user selects a contact
					contactName.setText(newValue.getName());
					contactPhoneNumber.setText(newValue.getPhoneNumber());
					contactEmail.setText(newValue.getEmail());
					contactGroup.setText(newValue.getGroup());
					//System.out.print(newValue.getPathToPicture());
					contactImage.setImage(new Image(newValue.getPathToPicture()));
					try {
						DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
						LocalDate localDate = LocalDate.parse(newValue.getDOB(), formatter);
						contactDOB.setValue(localDate);
					}
					catch(Exception e) {
						contactDOB.setValue(null);
					}				
				}                                   
              }
           );        
           
        // set custom ListView cell factory
        contactListView.setCellFactory(
           new Callback<ListView<Contact>, ListCell<Contact>>() {
              @Override
              public ListCell<Contact> call(ListView<Contact> listView) {
                 return new ContactCell();
              }
           }
        );   
    }
    @FXML
    void addNewContact(ActionEvent event) {
    	contactsForListView.add(new Contact("/modules/contacts/default.jpg","Unnamed Contact",null,null,null,null));
    	
    	//When a new contact is added, resorts the list
		ContactComparator contactCompare = new ContactComparator();
		Collections.sort(contactsForListView,contactCompare);
    }

    @FXML
    void chooseNewContactImage(ActionEvent event) throws MalformedURLException, IOException,NullPointerException{
    	FileChooser fileChooser = new FileChooser();
    	fileChooser.setTitle("Choose a new contact image");
    	File imageFile = fileChooser.showOpenDialog(contactName.getScene().getWindow());
		if(ImageIO.read(imageFile) != null) {
			System.out.print(imageFile.toURI().toURL().toString());
			contactImage.setImage(new Image(imageFile.toURI().toURL().toString()));
		}
		Contact contact = contactListView.getSelectionModel().getSelectedItem();
		contact.setPathToPicture(imageFile.toURI().toURL().toString());
		saveAllContactsToXML();
    	
    }

    @FXML
    void deleteContact(ActionEvent event) {
    	contactsForListView.remove(contactListView.getSelectionModel().selectedItemProperty().get());
    	
    	if(contactsForListView.isEmpty()) {
    		contactInfoGrid.setDisable(true); //If there arent any contacts in the listview disable the contact layout info
    	}
    	saveAllContactsToXML();
    }

    @FXML
    void saveContact(ActionEvent event) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		Contact contact = contactListView.getSelectionModel().getSelectedItem();
		contact.setName(contactName.getText());
		try {
			contact.setDOB(contactDOB.getValue().format(formatter).toString());			
		}
		catch(NullPointerException e) {
			
		}
		contact.setPhoneNumber(contactPhoneNumber.getText());
		contact.setEmail(contactEmail.getText());
		contact.setGroup(contactGroup.getText());

		//Updates listView
		contactListView.setCellFactory(new Callback<ListView<Contact>, ListCell<Contact>>() {
			@Override
			public ListCell<Contact> call(ListView<Contact> param) {
				return new ContactCell();
			}
		});
		saveAllContactsToXML();
    }
    void saveAllContactsToXML() {
    	
    	//Whenever a contact is saved via the button, overwrite the entire xml file
    	//With all contacts in the ArrayList and the updated contact
    	try {
			BufferedWriter output = Files.newBufferedWriter(Paths.get(XML_FILE));
	    	Contacts contacts = new Contacts(); 
	    	for(Contact c:contactsForListView){
	            //Adds each contact to the the contacts class
	            Contact savedContact = new Contact(c.getPathToPicture(),c.getName(),c.getPhoneNumber(),c.getEmail(),c.getGroup(),c.getDOB());
	            contacts.getContacts().add(savedContact);	            
	    	}
	    	//Writes contact class out to xml file
	    	JAXB.marshal(contacts, output); 
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}


    }

}
