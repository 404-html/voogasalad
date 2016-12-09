package gameplayer.back_end.keycode_handler;

public class XMovementHandler extends MovementHandler {
	
	private double myXMovement;
	
	public XMovementHandler() {
		myXMovement = 0;
	}
	
	public XMovementHandler(double aXLocation, double aXScreenHeight) {
		myXMovement = super.calculateMovement(aXLocation, aXScreenHeight);
	}

	public double getMovement() {
		return myXMovement;
	}
}
