package game_engine.actions;

import java.util.List;
import java.util.Map;

import game_data.Sprite;
import game_engine.ListOfCollidingSprites;
import javafx.geometry.Side;
import javafx.scene.image.ImageView;

public class StopLeftMovement extends StopMovement {
	//private List<Sprite>mySpriteList;
	//private Map<Sprite, ImageView> mySpriteImages;
	//public StopLeftMovement(Sprite aSprite, double aVelocity, List<Sprite>aSpriteList, Map<Sprite, ImageView>aSpriteImages) {
	public StopLeftMovement(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
		//mySpriteList=aSpriteList;
		//mySpriteImages=aSpriteImages;
	}

	@Override
	public void act() {
		//this code below won't work because if mario's in the air, when he lands, he'll skate on the land with a 
		//constant horizontal velocity
		/*ListOfCollidingSprites collidingSprites = new ListOfCollidingSprites(mySprite, mySpriteList, mySpriteImages);
		Map<Sprite, Side> myCollisionMap = collidingSprites.getCollisionSpriteMap();
		if(myCollisionMap.isEmpty()){
			mySprite.setMyXVelocity(0);
		}*/
		if(mySprite.getXVelocity()<0){
			mySprite.setXVelocity(mySprite.getXVelocity()+myVelocity);
		}
		//mySprite.setMyXVelocity(mySprite.getMyXVelocity()+myVelocity);
	}

}
