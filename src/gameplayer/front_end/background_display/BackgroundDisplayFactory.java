package gameplayer.front_end.background_display;

import java.io.File;

import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;

public class BackgroundDisplayFactory {
	
	public Background buildBackgroundDisplay(String aFileName, double aWidth, double aHeight)  {//throws FileNotFoundException {
		if (aFileName == null) {
			return buildBackgroundDisplay(Color.WHITE, aWidth, aHeight);
		}
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
	
	public Background buildBackgroundDisplay(Color aColor, double aWidth, double aHeight) {
		Paint color = Paint.valueOf(aColor.toString());
		BackgroundFill fill = new BackgroundFill(color, null, null);
		return new Background(fill);
	}

}
