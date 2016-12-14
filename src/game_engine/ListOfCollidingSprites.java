package game_engine;

import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.BouncerTop;
import game_data.characteristics.Characteristic;
import game_data.characteristics.TransparentBottomImpassable;
import game_data.sprites.Terrain;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.ImageView;

/**
 * @author Austin Gartside, Lucia, ALEX!!!!!112!1!!
 *
 */
public class ListOfCollidingSprites {
	
	private Sprite targetSprite;
	private List<Sprite> spriteList;
	private Map<Sprite, Side> collisionSprites;
	private Map<Sprite, ImageView> mySpriteImages;

	public ListOfCollidingSprites(Sprite targetSprite, List<Sprite> spriteList, Map<Sprite, ImageView> spriteImages) {
		this.targetSprite = targetSprite;
		this.spriteList = spriteList;
		this.mySpriteImages=spriteImages;
		getListOfSpritesCollided();
	}

	private void getListOfSpritesCollided() {
		collisionSprites = new HashMap<Sprite, Side>();
		for (Sprite mySprite : spriteList) {
			if ( mySprite != targetSprite && mySpriteImages.get(mySprite).getBoundsInParent()
					.intersects(mySpriteImages.get(targetSprite).getBoundsInParent()) ) 
			{
				collisionSprites.put(mySprite, findSideOfCollision(mySprite));
			}
		}
	}

	public Map<Sprite, Side> getCollisionSpriteMap() {
		return this.collisionSprites;
	}

	private Side findSideOfCollision(Sprite mySprite) {

		double myTopLeftX = mySprite.getLocation().getXLocation();
		double myTopLeftY = mySprite.getLocation().getYLocation();

		if (!(mySprite instanceof Terrain)) {

			double mySprite_bottom = mySprite.getLocation().getYLocation() + mySprite.getHeight();
			double targetSprite_bottom = targetSprite.getLocation().getYLocation() + targetSprite.getHeight();
			double mySprite_right = mySprite.getLocation().getXLocation() + mySprite.getWidth();
			double targetSprite_right = targetSprite.getLocation().getXLocation() + targetSprite.getWidth();

			double bottom_collision = targetSprite_bottom - mySprite.getLocation().getYLocation();
			double top_collision = mySprite_bottom - targetSprite.getLocation().getYLocation();
			double left_collision = mySprite_right - targetSprite.getLocation().getXLocation();
			double right_collision = targetSprite_right - mySprite.getLocation().getXLocation();

			if (top_collision < bottom_collision && top_collision < left_collision && top_collision < right_collision) {

				if((targetSprite instanceof Terrain && !isTransparent()) || (isTransparent() && pastPlatform(mySprite))){
					mySprite.setLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
				}

				return new Top();
				
			} else if (bottom_collision < top_collision && bottom_collision < left_collision
					&& bottom_collision < right_collision) {
				
				if(targetSprite instanceof Terrain && !isTransparent()){
					mySprite.setLocation(new Location(myTopLeftX, myTopLeftY + bottom_collision));
				}

				return new Bottom();
				
			} else if (left_collision < right_collision && left_collision < top_collision
					&& left_collision < bottom_collision) {
					if(targetSprite instanceof Terrain && !isTransparent()){
						mySprite.setLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
						mySprite.setLocation(new Location(myTopLeftX - left_collision, myTopLeftY));
					}

					return new Left();

			} else if (right_collision < left_collision && right_collision < top_collision
					&& right_collision < bottom_collision) {
				if(targetSprite instanceof Terrain && !isTransparent()){
					mySprite.setLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
					mySprite.setLocation(new Location(myTopLeftX + right_collision, myTopLeftY));
				}

					return new Right();

			}
		}
		return new Top();
	}

	private boolean pastPlatform(Sprite myPlayerSprite) {
		return myPlayerSprite.getLocation().getYLocation() + myPlayerSprite.getHeight() < targetSprite
				.getLocation().getYLocation()+.2*targetSprite.getHeight() && myPlayerSprite.getYVelocity() >= 0;
	}

	private boolean isTransparent() {
		for (Characteristic c : targetSprite.getCharacteristics()) {
			if (c instanceof TransparentBottomImpassable || c instanceof BouncerTop) {
				return true;
			}
		}
		return false;
	}
}