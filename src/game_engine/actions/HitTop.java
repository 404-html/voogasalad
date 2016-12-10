package game_engine.actions;

//import javafx.geometry.Side;
import game_data.states.Physics;
import game_data.states.State;
import game_data.Sprite;
import game_engine.Side;
import game_engine.SpritePhysics;
import game_engine.Top;

/**
 * @author Austn
 *
 */

public class HitTop implements Action {
	
	private double collisionBuffer;
	private Sprite myPlayerSprite;
	private Sprite myNonPlayerSprite;
	private Side mySide;

	public HitTop(Sprite player, Side aSide, Sprite nonPlayerSprite) {
		myPlayerSprite = player;
		myNonPlayerSprite = nonPlayerSprite;
		mySide = aSide;
		collisionBuffer = nonPlayerSprite.getHeight()*.2;
	}

	@Override
	public void act() {
		
		
		//get new Velocity –– gets horizontal or vertical components to zero
		//setNewVelocity();
		//setNewAcceleration();
		SpritePhysics mySpritePhysics = null;
		for(State s: myPlayerSprite.getStates()){
			if(s instanceof Physics){
				mySpritePhysics = ((Physics) s).getPhysics();
			}
		}
		
		if(mySide instanceof Top && pastPlatform()){
			mySide.hitImpassable(myPlayerSprite, mySpritePhysics);
		}
	}
	
//	private void setNewVelocity() {


//		if(mySide==Side.TOP){
//			if(myPlayerSprite.getYVelocity()>0){
//				if(pastPlatform()){
//					myPlayerSprite.setYVelocity(0);
//				}
//			}
//		}
//	}
	
	private boolean pastPlatform(){
		return myPlayerSprite.getLocation().getYLocation()+myPlayerSprite.getHeight()<myNonPlayerSprite
				.getLocation().getYLocation()+(myNonPlayerSprite.getHeight()*.5) && myPlayerSprite.getYVelocity()>=0;
	}
//		if(mySide==Side.TOP){
//			if(myPlayerSprite.getYVelocity()>0){
//				if(pastPlatform()){
//					myPlayerSprite.setYVelocity(0);
//				}
//			}
//		}
	
	
//	private boolean pastPlatform(){
////		return myPlayerSprite.getLocation().getYLocation()+myPlayerSprite.getHeight()>myNonPlayerSprite
////				.getLocation().getYLocation()+collisionBuffer && myPlayerSprite.getYVelocity()>0;
//		return myPlayerSprite.getLocation().getYLocation()+myPlayerSprite.getHeight()<myNonPlayerSprite
//				.getLocation().getYLocation()+(myNonPlayerSprite.getHeight()*.5) && myPlayerSprite.getYVelocity()>0;
//	}

//	private void setNewAcceleration(){
//		SpritePhysics mySpritePhysics = null;
//		for(State s: myPlayerSprite.getStates()){
//			if(s instanceof Physics){
//				mySpritePhysics = ((Physics) s).getPhysics();
//			}
//		}
//	
//		if((mySide==Side.TOP && mySpritePhysics.getVerticalGravity()>0)&&pastPlatform()){
//			myPlayerSprite.setYAcceleration(-mySpritePhysics.getVerticalGravity());
//		}
//		if((mySide==Side.TOP && mySpritePhysics.getVerticalGravity()>0)&&pastPlatform()){
//			myPlayerSprite.setYAcceleration(-mySpritePhysics.getVerticalGravity());
//		}
		
	
	
	
		
}
