package modules.contacts;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
import javafx.util.Callback;
import javafx.util.StringConverter;

public class contactModuleController {

    @FXML
    private ListView<Contact> contactListView;

    @FXML
    private ChoiceBox<String> sortByChoiceBox;

    @FXML
    private ImageView contactImage;

    @FXML
    private TextField contactName;

    @FXML
    private TextField contactPhoneNumber;

    @FXML
    private TextField contactEmail;

    @FXML
    private TextField contactGroup;
    @FXML
    private DatePicker contactDOB;
    
    private final ObservableList<Contact> contactsForListView = 
    	      FXCollections.observableArrayList();
    public void initialize() {
    	sortByChoiceBox.getItems().addAll("Name","Date Of Birth","Group");
        try(BufferedReader input = 
                Files.newBufferedReader(Paths.get("clients.xml"))) {
                // unmarshal the file's contents
                Contacts contacts = JAXB.unmarshal(input, Contacts.class);

                for (Contact contact : contacts.getContacts()) {
                	contactsForListView.add(contact);
                }
             } 
             catch (IOException ioException) {
                System.err.println("Error opening file.");
             }
        contactListView.setItems(contactsForListView); // bind booksListView to books

        // when ListView selection changes, show large cover in ImageView
        contactListView.getSelectionModel().selectedItemProperty().
           addListener(
              new ChangeListener<Contact>() {
				@Override
				public void changed(ObservableValue<? extends Contact> observable, Contact oldValue, Contact newValue) {
					contactImage.setImage(new Image(newValue.getPathToPicture()));
					contactName.setText(newValue.getName());
					contactPhoneNumber.setText(newValue.getPhoneNumber());
					contactEmail.setText(newValue.getEmail());
					contactGroup.setText(newValue.getGroup());
					DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
					LocalDate localDate = LocalDate.parse(newValue.getDOB(), formatter);
					contactDOB.setValue(localDate);
					
				}                                   
              }
           );        
           
        // set custom ListView cell factory
        contactListView.setCellFactory(
           new Callback<ListView<Contact>, ListCell<Contact>>() {
              @Override
              public ListCell<Contact> call(ListView<Contact> listView) {
                 return new ImageTextCell();
              }
           }
        );   
    }
    @FXML
    void addNewContact(ActionEvent event) {
    	contactsForListView.add(new Contact("/blankContactImage.png","Unnamed Contact",null,null,null,null));
    }

    @FXML
    void chooseNewContactImage(ActionEvent event) {

    }

    @FXML
    void deleteContact(ActionEvent event) {

    }

    @FXML
    void saveContact(ActionEvent event) {
    	
    	for(Contact c:contactsForListView){
    		try(BufferedWriter output = 
    		         Files.newBufferedWriter(Paths.get("clients.xml"))) {
    		         // stores the Accounts before XML serialization
    		         Contacts contacts = new Contacts(); 
    		         Contact savedContact = new Contact(c.getPathToPicture(),c.getName(),c.getPhoneNumber(),c.getEmail(),c.getGroup(),c.getDOB());
    		         contacts.getContacts().add(savedContact);

    		         // write AccountList's XML to output
    		         JAXB.marshal(contacts, output); 
    		      }
    		      catch (IOException ioException) {
    		         System.err.println("Error opening file. Terminating.");
    		      } 
    	}

    }

}
