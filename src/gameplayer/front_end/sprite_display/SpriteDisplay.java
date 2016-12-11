package gameplayer.front_end.sprite_display;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_data.Sprite;
import game_data.sprites.Player;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import javafx.scene.transform.Rotate;

public class SpriteDisplay {

	private Map<Sprite, ImageView> mySpriteViews;
	private List<ImageView> myAnimationSpriteImage;
	private int myCurrentImage; 
	
	public SpriteDisplay() {
		mySpriteViews = new HashMap<Sprite, ImageView>();
		myAnimationSpriteImage = new ArrayList<ImageView>();
	}
	
	private ImageView buildSpriteDisplay(Sprite aSprite) {
		ImageView image = new ImageView(new File(aSprite.getImagePath()).toURI().toString());
		return image;
		
	}
	
	public ImageView getUpdatedSpriteMap(Sprite aSprite) {
		ImageView image = checkAnimation(aSprite);
		if (image != null) {
			return image;
		}
		if (mySpriteViews.containsKey(aSprite)) {
			image = mySpriteViews.get(aSprite);
		} else {
			image = buildSpriteDisplay(aSprite);
			mySpriteViews.put(aSprite, image);
		}
		setImageProperties(aSprite, image);
		return image;
	}
	
	private ImageView checkAnimation(Sprite aSprite) {
		File fileOfAnimation = null;
		ImageView image = null;
		if (aSprite instanceof Player) {
			if (myAnimationSpriteImage.size() < 1) {
				String file = aSprite.getImagePath(); 
				image = buildSpriteDisplay(aSprite);
				myAnimationSpriteImage.add(image);
				String[] array = file.split("\\.");
				StringBuilder buildString = new StringBuilder();
				buildString.append(array[0]);
				buildString.append("_animation.");
				buildString.append(array[1]);
				String filePathOfAnimation = buildString.toString();
				fileOfAnimation = new File(filePathOfAnimation);
				myCurrentImage = 0;
				if (fileOfAnimation != null && fileOfAnimation.exists()) {
					image = new ImageView(fileOfAnimation.toURI().toString());
					myAnimationSpriteImage.add(image);
				} 
			} else if (myAnimationSpriteImage.size() == 1) {
				image = myAnimationSpriteImage.get(myCurrentImage);
			} else {
				image = myAnimationSpriteImage.get(myCurrentImage);
				if (myCurrentImage == 1) {
					myCurrentImage = 0;
				} else {
					myCurrentImage++;
				}
			}
			mySpriteViews.put(aSprite, image);
			setImageProperties(aSprite, image);
		}
		return image;
	}
	
	public Node get(Sprite aSprite) {
		return mySpriteViews.get(aSprite);
	}

	private void setImageProperties(Sprite aSprite, ImageView image) {
		image.setFitWidth(aSprite.getWidth());
		image.setFitHeight(aSprite.getHeight());
		image.setX(aSprite.getLocation().getXLocation());
		image.setY(aSprite.getLocation().getYLocation());
		image.setRotationAxis(Rotate.Y_AXIS);
	}

	public Map<Sprite, ImageView> getSpriteMap() {
		return mySpriteViews;
	}

}
