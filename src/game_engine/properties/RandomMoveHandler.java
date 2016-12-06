package game_engine.properties;

//this class will be apart of Sprite to handle movement
public class RandomMoveHandler {

	private boolean verticalMove;
	
	public RandomMoveHandler(boolean aVerticalMove) {
		verticalMove = aVerticalMove;
	}
	
	public RandomMoveHandler(RandomMoveHandler aRandomMoveHandler) {
		verticalMove = aRandomMoveHandler.getVerticalMove();
	}
	
	public void move() {
		
	}
	
	public boolean getVerticalMove() {
		return verticalMove;
	}
	
}
