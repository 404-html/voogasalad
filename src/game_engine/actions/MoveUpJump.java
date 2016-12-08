package game_engine.actions;

import java.util.List;
import java.util.Map;

import game_data.Location;
import game_data.Sprite;
import game_engine.ListOfCollidingSprites;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
//this class needs to be edited so it won't fly
//aka check if it's colliding, if it isn't, then don't set the velocity
public class MoveUpJump extends Move {
	private List<Sprite>mySpriteList;
	private Map<Sprite, ImageView>mySpriteImages;
	private double myTimeElapsed;
	public MoveUpJump(Sprite aSprite, double aVelocity, List<Sprite> aSpriteList, Map<Sprite, ImageView> mySpriteImages2, double timeElapsed) {
		super(aSprite, aVelocity);
		mySpriteList=aSpriteList;
		mySpriteImages=mySpriteImages2;
		myTimeElapsed=timeElapsed;
	}
/*	@Override
	public Location changeCoordinates(){
		myNewLocation.setLocation(myOldLocation.getXLocation(), myOldLocation.getYLocation()-myVelocity);
		return myNewLocation;
	}*/
	public void setVelocity(){
		ListOfCollidingSprites collidingSprites = new ListOfCollidingSprites(mySprite, mySpriteList, mySpriteImages);
		Map<Sprite, Side> myCollisionMap = collidingSprites.getCollisionSpriteMap();
		if(myCollisionMap.containsValue(Side.BOTTOM)){
			mySprite.setMyYVelocity(-myVelocity);
		}
	}

}