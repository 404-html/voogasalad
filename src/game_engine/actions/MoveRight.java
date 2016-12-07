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
import javafx.scene.image.ImageView;
/**
 * @author Katrina
 *
 */
public class MoveRight extends Move {
	
	
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
		//mySprite.setMyXVelocity(myVelocity);
		//System.out.println("changing right velocity");
		mySprite.setMyXVelocity(myVelocity);
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
		if(mySprite.getMyXVelocity()>0){
			mySprite.setMyXVelocity(mySprite.getMyXVelocity()-myVelocity);
		}
	}

}
