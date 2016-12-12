package game_engine.actions;

//import javafx.geometry.Side;
import game_data.states.Physics;
import game_data.states.State;
import game_data.Sprite;
import game_engine.Side;
import game_engine.SpritePhysics;

/**
 * @author Katrina
 *
 */

public class Hit implements Action {
	
	private Sprite myPlayerSprite;
	private Sprite myNonPlayerSprite;
	private Side mySide;
	

	public Hit(Sprite player,  Side aSide, Sprite nonPlayerSprite) {
		myPlayerSprite = player;
		myNonPlayerSprite = nonPlayerSprite;
		mySide = aSide;
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new Hit(aSprite, mySide, myNonPlayerSprite);
	}
	
	@Override
	public void act() {
		
		Physics myPhysics = null;
		for(State s: myPlayerSprite.getStates()){
			if(s instanceof Physics){
				myPhysics = (Physics) s;
			}
		}
		
		
		//get new Velocity –– gets horizontal or vertical components to zero

		//setNewVelocity();
		//setNewAcceleration();
		mySide.hitImpassable(myPlayerSprite, myPhysics);

		//setNewVelocity();
		//setNewAcceleration();
	}
	
	private boolean pastPlatform(){
		return myPlayerSprite.getLocation().getYLocation()+myPlayerSprite.getHeight()>myNonPlayerSprite
				.getLocation().getYLocation() && myPlayerSprite.getYVelocity()>0;		

	}
	
	/*private void setNewVelocity() {
				
		if(mySide == Side.LEFT) {
			//System.out.println("hitting this");
			if(myPlayerSprite.getXVelocity()>0){
				
				myPlayerSprite.setXVelocity(0);
				//System.out.println("hittin left side of block");
			}
		}
		if(mySide==Side.RIGHT) {
			if(myPlayerSprite.getXVelocity()<0){
				//System.out.println("hittin right side of block");
				myPlayerSprite.setXVelocity(0);
			}
		}
		if(mySide==Side.TOP){
			//System.out.println("top");
			if(myPlayerSprite.getYVelocity()>0){
				//System.out.println("this should be a thing");
				myPlayerSprite.setYVelocity(0);
			}
		}
		if(mySide==Side.BOTTOM){
			if(myPlayerSprite.getYVelocity()<0){
				myPlayerSprite.setYVelocity(-.5*myPlayerSprite.getYVelocity());
			}
		}
	}
	private void setNewAcceleration(){
		SpritePhysics mySpritePhysics = null;
		for(State s: myPlayerSprite.getStates()){
			if(s instanceof Physics){
				mySpritePhysics = ((Physics) s).getPhysics();
			}
		}
		if((mySide == Side.LEFT && mySpritePhysics.getHorizontalGravity()>0)||(mySide == Side.RIGHT && mySpritePhysics.getHorizontalGravity()<0)){
			myPlayerSprite.setXAcceleration(-mySpritePhysics.getHorizontalGravity());
		}
		else if((mySide==Side.TOP && mySpritePhysics.getVerticalGravity()>0)||(mySide==Side.BOTTOM && mySpritePhysics.getVerticalGravity()<0)){
			//System.out.println("this should also be a thing");
			myPlayerSprite.setYAcceleration(-mySpritePhysics.getVerticalGravity());
		}
		
	}*/
	
		
}