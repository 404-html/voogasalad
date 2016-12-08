package gameplayer.front_end.sprite_display;

import java.util.HashMap;
import java.util.Map;

import game_data.Sprite;
import gameplayer.front_end.sprite_display.ISpriteDisplay.Shape;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class SpriteDisplay {

	private Map<Sprite, ImageView> mySpriteViews;
	
	public SpriteDisplay() {
		mySpriteViews = new HashMap<Sprite, ImageView>();
	}
	
	public ImageView buildSpriteDisplay(Sprite aSprite) {
		ImageView image = new ImageView(aSprite.getMyImagePath());
		setImageProperties(aSprite, image);
		return image;
		
	}
	
	public void getUpdatedSpriteMap(Sprite aSprite) {
		ImageView image;
		if (mySpriteViews.containsKey(aSprite)) {
			image = mySpriteViews.get(aSprite);
		} else {
			image = buildSpriteDisplay(aSprite);
			mySpriteViews.put(aSprite, image);
		}
	}
	
	public Node get(Sprite aSprite) {
		return mySpriteViews.get(aSprite);
	}

	private void setImageProperties(Sprite aSprite, ImageView image) {
		image.setFitWidth(aSprite.getMyWidth());
		image.setFitHeight(aSprite.getMyHeight());
		image.setX(aSprite.getMyLocation().getXLocation());
		image.setY(aSprite.getMyLocation().getYLocation());
		image.setTranslateZ(image.getBoundsInLocal().getWidth() / 2.0);
		image.setRotationAxis(Rotate.Y_AXIS);
	}

	public Map<Sprite, ImageView> getSpriteMap() {
		return mySpriteViews;
	}
	
	
}
