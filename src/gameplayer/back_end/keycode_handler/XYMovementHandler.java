package gameplayer.back_end.keycode_handler;

import java.awt.Point;

public class XYMovementHandler {
	
	private double myXMovement;
	private double myYMovement;
	
	public XYMovementHandler(double xMovement, double yMovement) {
		myXMovement = xMovement;
		myYMovement = yMovement;
	}
	
	public Point getMovement() {
		Point point = new Point();
		point.setLocation(-myXMovement, -myYMovement);
		return point;
	}

}
