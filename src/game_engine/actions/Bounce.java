package game_engine.actions;
import javafx.geometry.Side;
import game_data.Sprite;
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
        if(mySide==Side.BOTTOM){
            myPlayerSprite.setYVelocity(myBounceSpeedVertical);
        }            
        else if(mySide==Side.TOP){
        	 myPlayerSprite.setYVelocity(-myBounceSpeedVertical);
        }
        else if(mySide==Side.LEFT){ 
        	myPlayerSprite.setXVelocity(-myBounceSpeedHorizontal);
        }
        else if(mySide==Side.RIGHT){        	
            myPlayerSprite.setXVelocity(myBounceSpeedHorizontal);
        }
        //myPlayerSprite.setMyVelocity( getNewVelocity() );        
        //myPlayerSprite.getMyLocation().setMyHeading( getNewHeading() );
        
    }
    
    
}

