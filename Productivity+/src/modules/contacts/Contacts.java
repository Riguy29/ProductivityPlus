package modules.contacts;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlElement;

public class Contacts {
   // @XmlElement specifies XML element name for each object in the List
   @XmlElement(name="contact") 
   private List<Contact> contacts = new ArrayList<>(); // stores Accounts

   // returns the List<Accounts>
   public List<Contact> getContacts() {return contacts;}
}