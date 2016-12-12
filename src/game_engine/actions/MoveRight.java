package game_engine.actions;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import game_data.Location;
import game_data.Sprite;
import game_data.states.Solid;
import game_data.states.State;
import game_engine.ListOfCollidingSprites;
import javafx.geometry.Side;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
/**
 * @author Katrina
 *
 */
public class MoveRight extends Move {
	private List<Sprite>mySpriteList;
	private Map<Sprite, ImageView>mySpriteImages;


	public MoveRight(Sprite aSprite, double aVelocity) {
		super(aSprite, aVelocity);
	}

	/*	@Override
	public Location changeCoordinates(){
		//myNewLocation.setLocation(myOldLocation.getXLocation()+myVelocity, myOldLocation.getYLocation());
		//myNewLocation.setMyHeading(0);
		//return myNewLocation;
	}*/
	public void setVelocity(){
		//mySprite.setXVelocity(myVelocity);
		//System.out.println(myVelocity);
		mySprite.setXVelocity(myVelocity);
	}
	/*private boolean checkSpriteSolidity() {
	boolean solid = false;
	for(State myState:mySprite.getStates()){
		if(myState instanceof Solid){ solid = true;
		}
	}
	return solid;
}*/
	public void stop(){
		if(mySprite.getXVelocity()>0){
			//mySprite.setXVelocity(0);\
			//System.out.println("stopping" + mySprite.getXVelocity());
			mySprite.setXVelocity(mySprite.getXVelocity()-myVelocity);
		}
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new MoveRight(aSprite, getVelocity());
	}


}
