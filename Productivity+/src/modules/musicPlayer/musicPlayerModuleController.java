package modules.musicPlayer;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class musicPlayerModuleController {

	final static Media[] playLists = new Media[2];
	final static String[] titlesOfPlaylists = {"Meditation","Study"};
	private boolean isPlaying = false;
	
	MediaPlayer mediaPlayer;
	
	private int playListIndex = 0;
    @FXML
    private Label titleOfCurrPlayList;
    
    @FXML
    private Slider volumeSlider;

    @FXML
    private VBox parentVBox;
    
    @FXML
    private Button playPauseButton;
	
	public void initialize() throws IOException {
		
		//Loading mp3 files into the media array
		playLists[0] = new Media(getClass().getResource("mediaFiles/MediationPlayList.mp3").toExternalForm());
		playLists[1] = new Media(getClass().getResource("mediaFiles/StudyPlayList.mp3").toExternalForm());
		
		mediaPlayer = new MediaPlayer(playLists[playListIndex]);
		setPlaylistTitle(titlesOfPlaylists[playListIndex]);
		
	}

    @FXML
    void onNextPlayListButton(ActionEvent event) {
    	mediaPlayer.stop();
    	playListIndex += 1;
    	if(playListIndex >= playLists.length) { //If we reach the end of the array, loop to the start
    		playListIndex = 0;
    	}
    	setPlaylistTitle(titlesOfPlaylists[playListIndex]);
    	mediaPlayer = new MediaPlayer(playLists[playListIndex]);
    	
    	if(isPlaying == true) {
    		mediaPlayer.play();
    	}
    }
    @FXML
    void onPlayMusicButton(ActionEvent event) {
    	if(isPlaying == true) { //If the music already playing clicking the button will pause it
    		mediaPlayer.pause();
    		playPauseButton.setText("Play");
    		System.out.print("TEST");
    		
    	}
    	else { //If music is not playing the button will play it
    		mediaPlayer.play();
    		playPauseButton.setText("Pause");
    	}
    	isPlaying = !isPlaying; //Sets the boolean to the opposite of its value
    	
    }

    @FXML
    void onPrevPlaylistButton(ActionEvent event) {
    	mediaPlayer.stop();
    	playListIndex -= 1;
    	if(playListIndex < 0) { //If we reach the end of the array, loop to the start
    		playListIndex = playLists.length-1;
    	}
    	setPlaylistTitle(titlesOfPlaylists[playListIndex]);
    	mediaPlayer = new MediaPlayer(playLists[playListIndex]);
    	//mediaPlayer.play();
    }

    @FXML
    void onStopMusicButton(ActionEvent event) {
    	mediaPlayer.stop();
    	playPauseButton.setText("Play");
    	isPlaying = false;
    }
    private void setPlaylistTitle(String title) {
    	titleOfCurrPlayList.setText(title);
    }

    @FXML
    void setNewVolume(MouseEvent event) {
    	double newVolume =volumeSlider.getValue()/100;
    	System.out.print(newVolume);
    	mediaPlayer.setVolume(newVolume);
    }
    
    //TODO: Currently music doesnt stop when you close the module, would like to fix that.


}
