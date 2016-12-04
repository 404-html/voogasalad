package gameplayer.back_end.keycode_handler;

public class MovementHandler {
	
	private double myXMovement;
	private double myYMovement;
	
	public void setYMovement(double aYLocation, double aScreenHeight) {
		//System.out.println(aYLocation);
		double answer = aYLocation - (aScreenHeight * .5); 
		myYMovement = answer;
	}
	
	public double getYMovement() {
		return -myYMovement;
	}
	
	public void setXMovement(double aXLocation, double aScreenWidth) {
		//System.out.println(aXLocation);
		//System.out.println("velocity: " + aXVelocity + "\n");
		double answer = aXLocation - aScreenWidth *.5;
		myXMovement = answer;
	}
	
	public double getXMovement() {
		//System.out.println("key: " + aKey);
		return -myXMovement;
	}
	
}
