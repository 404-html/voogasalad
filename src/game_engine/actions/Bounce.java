package game_engine.actions;
//import javafx.geometry.Side;
import game_data.Sprite;
import game_engine.Side;
/**
* @author Alex & James & Austin & Katrina
*
*/
public class Bounce implements Action {
    
    private double myBounceSpeedHorizontal;
    private double myBounceSpeedVertical;
    private Sprite myPlayerSprite;
    private Side mySide;
    
    public Bounce(double bouncerSpeedHorizontal, double bouncerSpeedVertical, Sprite player, Side aSide) {
        myBounceSpeedHorizontal = bouncerSpeedHorizontal;
        myBounceSpeedVertical = bouncerSpeedVertical;
        myPlayerSprite = player;
        mySide = aSide;
    }
    @Override
    public void act() {
    	mySide.bounce(myPlayerSprite, myBounceSpeedVertical);
        /*if(mySide==Side.BOTTOM){
            myPlayerSprite.setMyYVelocity(myBounceSpeedVertical);
        }            
        else if(mySide==Side.TOP){
        	 myPlayerSprite.setMyYVelocity(-myBounceSpeedVertical);
        }
        else if(mySide==Side.LEFT){ 
        	myPlayerSprite.setMyXVelocity(-myBounceSpeedHorizontal);
        }
        else if(mySide==Side.RIGHT){        	
            myPlayerSprite.setMyXVelocity(myBounceSpeedHorizontal);
        }*/
        //myPlayerSprite.setMyVelocity( getNewVelocity() );        
        //myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );
        
    }
    
    
}

