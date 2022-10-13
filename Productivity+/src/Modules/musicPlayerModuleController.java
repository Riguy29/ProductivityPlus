package Modules;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class musicPlayerModuleController implements Initializable{

	final static Media[] playLists = new Media[2];
	final static String[] titlesOfPlaylists = {"Meditation","Study"};
	
	MediaPlayer mediaPlayer;
	
	private int playListIndex = 0;
    @FXML
    private Label titleOfCurrPlayList;
    

    @FXML
    private VBox parentVBox;
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		playLists[0] = new Media(getClass().getResource("../mediaFiles/MediationPlayList.mp3").toExternalForm());
		playLists[1] = new Media(getClass().getResource("../mediaFiles/StudyPlayList.mp3").toExternalForm());
		mediaPlayer = new MediaPlayer(playLists[playListIndex]);
		setPlaylistTitle(titlesOfPlaylists[playListIndex]);
		mediaPlayer.setAutoPlay(true);
		
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
    	mediaPlayer.play();
    }

    @FXML
    void onPauseMusicButton(ActionEvent event) {
    	mediaPlayer.pause();
    }

    @FXML
    void onPlayMusicButton(ActionEvent event) {
    	mediaPlayer.play();
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
    	mediaPlayer.play();
    }

    @FXML
    void onStopMusicButton(ActionEvent event) {
    	mediaPlayer.stop();
    }
    private void setPlaylistTitle(String title) {
    	titleOfCurrPlayList.setText(title);
    }


}
