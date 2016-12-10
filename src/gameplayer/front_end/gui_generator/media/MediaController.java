package gameplayer.front_end.gui_generator.media;

import java.io.File;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class MediaController {
	
	private MediaPlayer myPlayer;
	
	public MediaController(String aFilePath) {
		Media media = new Media(new File(aFilePath).toURI().toString());
		myPlayer = new MediaPlayer(media); 
		myPlayer.play();
	}
	
	public void pauseMusic(){
		myPlayer.pause();
	}
	
	public void stopMusic() {
		myPlayer.stop();
	}

}
