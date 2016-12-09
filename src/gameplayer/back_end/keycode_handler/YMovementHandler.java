package gameplayer.back_end.keycode_handler;

public class YMovementHandler extends MovementHandler {
	
	private double myYMovement;
	
	public YMovementHandler() {
		myYMovement = 0;
	}
	
	public YMovementHandler(double aYLocation, double aYScreenHeight) {
		myYMovement = super.calculateMovement(aYLocation, aYScreenHeight);
	}
	
	public double getMovement() {
		return myYMovement;
	}

}
