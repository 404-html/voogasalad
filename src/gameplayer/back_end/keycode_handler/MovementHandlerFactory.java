package gameplayer.back_end.keycode_handler;

public class MovementHandlerFactory {
	
	public XYMovementHandler buildMovementHandler(double aXLocation, double aScreenWidth, double aYLocation, double aScreenHeight, int aTypeOfMovement) {
		double XMovement = 0;
		double YMovement = 0;
		if (aTypeOfMovement==0) {
			XMovement = calculateMovement(aXLocation, aScreenWidth);
		} else if (aTypeOfMovement==1) {
			YMovement = calculateMovement(aYLocation, aScreenHeight);
		} else {
			XMovement = calculateMovement(aXLocation, aScreenWidth);
			YMovement = calculateMovement(aYLocation, aScreenHeight);
		}
		return new XYMovementHandler(XMovement, YMovement);
	}
	
	public double calculateMovement(double aLocation, double aScreenParam) {
		double answer = aLocation - (aScreenParam * .5); 
		return answer;
	}

}
