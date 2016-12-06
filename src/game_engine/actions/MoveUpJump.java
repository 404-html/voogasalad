package game_engine.actions;

import java.util.List;
import java.util.Map;

import game_data.Location;
import game_data.Sprite;
import game_data.sprites.Terrain;
import game_engine.ListOfCollidingSprites;
import javafx.geometry.Side;
import javafx.scene.image.ImageView;
//this class needs to be edited so it won't fly
//aka check if it's colliding, if it isn't, then don't set the velocity
public class MoveUpJump extends Move {

	public MoveUpJump(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);

	}
	public void setVelocity(){
		if(mySprite.getMyYVelocity()>=0){
			mySprite.setMyYVelocity(-myVelocity);
		}
	}
/*	@Override
	public Location changeCoordinates(){
		myNewLocation.setLocation(myOldLocation.getXLocation(), myOldLocation.getYLocation()-myVelocity);
		return myNewLocation;
	}*/
	/*public void setVelocity(){
		ListOfCollidingSprites collidingSprites = new ListOfCollidingSprites(mySprite, mySpriteList, mySpriteImages);
		Map<Sprite, Side> myCollisionMap = collidingSprites.getCollisionSpriteMap();
		if(myCollisionMap.containsValue(Side.BOTTOM)){
			for(Sprite s: myCollisionMap.keySet()){
				if(s instanceof Terrain){
					if(myCollisionMap.get(s).equals(Side.BOTTOM)){
						mySprite.setMyYVelocity(-myVelocity);
					}
				}
			}
		}
	}*/
	public void stop(){
		return;
	}

}