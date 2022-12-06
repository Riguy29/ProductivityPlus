package modules.contacts;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.text.TextAlignment;

public class ContactCell extends ListCell<Contact> {
	private HBox hbox = new HBox(8);
   private ImageView thumbImageView = new ImageView(); // initially empty
   private Label label = new Label();
   
   public ContactCell() {
	      hbox.setAlignment(Pos.CENTER); // center VBox contents horizontally

	      thumbImageView.setPreserveRatio(true);
	      thumbImageView.setFitHeight(100.0); // thumbnail 100 points tall
	      hbox.getChildren().add(thumbImageView); // attach to Vbox

	      label.setWrapText(true); // wrap if text too wide to fit in label
	      label.setTextAlignment(TextAlignment.CENTER); // center text
	      hbox.getChildren().add(label); // attach to VBox

	      setPrefWidth(USE_PREF_SIZE); // use preferred size for cell width
	   }

	   // called to configure each custom ListView cell
	   @Override 
	   protected void updateItem(Contact item, boolean empty) {
	      // required to ensure that cell displays properly
	      super.updateItem(item, empty);

	      if (empty || item == null) {
	         setGraphic(null); // don't display anything
	      }
	      else {
	         // set ImageView's thumbnail image
	         thumbImageView.setImage(new Image(item.getPathToPicture()));
	         label.setText(item.getName()); // configure Label's text
	         setGraphic(hbox); // attach custom layout to ListView cell
	      }
	   }
	}
