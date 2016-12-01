package game_engine.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import game_data.Location;
import game_data.Sprite;
import game_engine.ListOfCollidingSprites;
import javafx.geometry.Side;
import javafx.scene.image.ImageView;

public class MoveRight extends Move {
	private List<Sprite>mySpriteList;
	private Map<Sprite, ImageView>mySpriteImages;
	public MoveRight(Sprite aSprite, double aVelocity, List<Sprite> aSpriteList, Map<Sprite, ImageView>aSpriteImages) {
		super(aSprite, aVelocity);
		mySpriteList=aSpriteList;
		mySpriteImages=aSpriteImages;
	}
/*	@Override
	public Location changeCoordinates(){
		//myNewLocation.setLocation(myOldLocation.getXLocation()+myVelocity, myOldLocation.getYLocation());
		//myNewLocation.setMyHeading(0);
		//return myNewLocation;
	}*/
	public void setVelocity(){
		ListOfCollidingSprites collidingSprites = new ListOfCollidingSprites(mySprite, mySpriteList, mySpriteImages);
		Map<Sprite, Side> myCollisionMap = collidingSprites.getCollisionSpriteMap();
		//Collection<Side> values =  myCollisionMap.values();
		for (Sprite name: myCollisionMap.keySet()){

            String value = myCollisionMap.get(name).toString();  
            System.out.println(" " + value);  


		} 
		//System.out.println(myCollisionMap.values());
		if(!myCollisionMap.containsValue(Side.LEFT)){
			mySprite.setMyXVelocity(myVelocity);
		}
		else{
			System.out.println("hitting the left side");
			StopRightMovement stop=new StopRightMovement(mySprite, 0);
			stop.act();
		}
		//mySprite.setMyXVelocity(myVelocity);
		//System.out.println("changing right velocity");
	}

}
