package gameplayer.front_end.sprite_display;

import java.io.FileNotFoundException;

import gameplayer.front_end.sprite_display.ISpriteDisplay.Shape;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;

public class SpriteDisplayFactory {

	public Node buildSpriteDisplay(String aFileName) throws FileNotFoundException {
		ImageView image = new ImageView(aFileName);
		return new ImageView(aFileName);
		
	}

	public Node buildSpriteDisplay(Shape aShape) {
		if (aShape.equals(Shape.RECTANGLE)) {
			return new Rectangle(); 
		}
		if (aShape.equals(Shape.CIRCLE)) {
			return new Circle();
		}
		if (aShape.equals(Shape.TRIANGLE)) {
			return new Polygon(); 
		}
		return null;
	}
	
}
