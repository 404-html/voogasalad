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
    	if(mySide.isVertical()){
    		mySide.bounce(myPlayerSprite, myBounceSpeedVertical);
    	}
    	else{
    		mySide.bounce(myPlayerSprite, myBounceSpeedHorizontal);
    	}
    }
    
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new Bounce(myBounceSpeedHorizontal, myBounceSpeedVertical, aSprite, mySide);
	}
    
    
}

