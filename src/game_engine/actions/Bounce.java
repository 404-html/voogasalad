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
            myPlayerSprite.setYVelocity(myBounceSpeedVertical);
=======
        if(mySide==Side.BOTTOM){
            myPlayerSprite.setYVelocity(myBounceSpeedVertical);
>>>>>>> 0a837dc1ca251d6f6d8186bb816fe2cc003bcaaa
        }            
        else if(mySide==Side.TOP){
        	 myPlayerSprite.setYVelocity(-myBounceSpeedVertical);
        }
        else if(mySide==Side.LEFT){ 
        	myPlayerSprite.setXVelocity(-myBounceSpeedHorizontal);
        }
        else if(mySide==Side.RIGHT){        	
<<<<<<< HEAD
            myPlayerSprite.setXVelocity(myBounceSpeedHorizontal);
        }*/

        
        //myPlayerSprite.setMyVelocity( getNewVelocity() );        
        //myPlayerSprite.getLocation().setMyHeading( getNewHeading() );
        
    }
    
    
}

