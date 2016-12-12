package game_engine.properties;

public class RandomMoveDisjointHandler extends RandomMoveHandler {

	
	public RandomMoveDisjointHandler(Orientation aOrientation) {
		super(aOrientation);
	}
	
	protected void setSpritesNewLocation() {
		
		double newXLoc, newYLoc;
		if(getOrientation().equals(Orientation.VERTICAL)) {
			newXLoc = Math.random()*( getScreenWidth() - getSprite().getWidth() );
			newYLoc = getSprite().getLocation().getYLocation() - getScreenHeight() - getSprite().getHeight();
		} else {
			newXLoc = getSprite().getLocation().getXLocation() + getScreenWidth() + getSprite().getWidth();
			newYLoc = Math.random()*( getScreenHeight() - getSprite().getHeight() );
		}
		getSprite().getLocation().setLocation(newXLoc, newYLoc);
		
	}
	
	public RandomMoveHandler copy() {
		return new RandomMoveDisjointHandler(getOrientation());
	}
	
}
