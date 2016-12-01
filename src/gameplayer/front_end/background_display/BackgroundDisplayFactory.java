package gameplayer.front_end.background_display;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;

public class BackgroundDisplayFactory {
	
	public Background buildBackgroundDisplay(String aFileName, double aWidth, double aHeight)  {//throws FileNotFoundException {
		System.out.println(new File(aFileName).toURI().toString());
		Image image = new Image(new File(aFileName).toURI().toString(), 
				aWidth, 
				aHeight, 
				true, 
				true);
		BackgroundImage backgroundImage = new BackgroundImage(image, 
				BackgroundRepeat.NO_REPEAT, 
				BackgroundRepeat.NO_REPEAT, 
				BackgroundPosition.DEFAULT, 
				new BackgroundSize(100, 100, true, true, true, true));
		return new Background(backgroundImage);
	}
}
