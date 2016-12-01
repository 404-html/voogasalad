package gameplayer.front_end.background_display;

import java.io.File;

import javafx.geometry.Side;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
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
		Image image = new Image(new File(aFileName).toURI().toString(), 
				aWidth, 
				aHeight, 
				true, 
				true);
		BackgroundImage backgroundImage = new BackgroundImage(image, 
				BackgroundRepeat.REPEAT, 
				BackgroundRepeat.NO_REPEAT, 
				new BackgroundPosition(Side.LEFT, 100, true, Side.TOP, 100, true),
				new BackgroundSize(100, 100, true, true, true, true));
		return new Background(backgroundImage);
	}
	
	public Background buildBackgroundDisplay(Color aColor, double aWidth, double aHeight) {
		Paint color = Paint.valueOf(aColor.toString());
		BackgroundFill fill = new BackgroundFill(color, null, null);
		return new Background(fill);
	}
	
	
}
