package game_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_data.Sprite;
import game_data.sprites.Player;
import javafx.geometry.Side;
import javafx.scene.image.ImageView;

public class ListOfCollidingSprites {
	
	private Sprite targetSprite;
	private List<Sprite> spriteList;
	private Map<Sprite, Side> collisionSprites;
	private Map<Sprite, ImageView> mySpriteImages;

	public ListOfCollidingSprites(Sprite targetSprite, List<Sprite> spriteList, Map<Sprite, ImageView> spriteImages) {
		this.targetSprite = targetSprite;
		this.spriteList = spriteList;
		this.mySpriteImages=spriteImages;
		//collisionSprites = new HashMap<Sprite, Side>();
		getListOfSpritesCollided();
	}
	
	private Map<Sprite, Side> getListOfSpritesCollided(){
		collisionSprites = new HashMap<Sprite, Side>();
		//System.out.println(spriteList.size());
		for(Sprite mySprite:spriteList){
			//System.out.println(mySprite.getName());
//			if(!mySprite.getName().equals(targetSprite.getName())){
//				System.out.println("target is " + targetSprite.getMyLocation().getYLocation());
//				System.out.println("other is " + mySprite.getMyLocation().getYLocation());
//			}
			//if(mySprite != targetSprite && mySpriteImages.get(mySprite).getBoundsInLocal().intersects(mySpriteImages.get(targetSprite).getBoundsInLocal())){
			//if(!mySprite.getName().equals(targetSprite.getName()) && mySpriteImages.get(mySprite).getBoundsInLocal().intersects(mySpriteImages.get(targetSprite).getBoundsInLocal())){
			if(!mySprite.getName().equals(targetSprite.getName()) && (mySpriteImages.get(mySprite).getBoundsInParent()).intersects(mySpriteImages.get(targetSprite).getBoundsInParent())) {
				//image.intersects(localBounds)
				//image.getBoundsInLocal().int
				collisionSprites.put(mySprite, findSideOfCollission(mySprite, targetSprite));
			}
			/*if(!mySprite.getName().equals(targetSprite.getName()) && mySprite.getMyLocation().getYLocation()<targetSprite.getMyLocation().getYLocation()+10
					&&mySprite.getMyLocation().getYLocation()>targetSprite.getMyLocation().getYLocation()-10){
				collisionSprites.put(mySprite, findSideOfCollission(mySprite, targetSprite));
			}*/
			//System.out.println(mySpriteImages.get(targetSprite).getLayoutY());
		}
		if(targetSprite instanceof Player){
			System.out.println(collisionSprites.size());
		}
		return collisionSprites;
	}

	private  Side findSideOfCollission(Sprite mySprite, Sprite targetSprite) {
		if(mySprite.getMyLocation().getYLocation() <= targetSprite.getMyLocation().getYLocation()){
			return Side.BOTTOM;
		}
		if(mySprite.getMyLocation().getYLocation() >= targetSprite.getMyLocation().getYLocation()){
			return Side.TOP;
		}
		if(mySprite.getMyLocation().getXLocation() < targetSprite.getMyLocation().getXLocation()){
			return Side.LEFT;
		}
		if(mySprite.getMyLocation().getXLocation() > targetSprite.getMyLocation().getXLocation()){
			return Side.RIGHT;
		}
		return null;
		
	}
	
	public Map<Sprite, Side> getCollisionSpriteMap(){
		return this.collisionSprites;
	}

}
