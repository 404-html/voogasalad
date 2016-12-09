package gameplayer.back_end.keycode_handler;

public abstract class MovementHandler {
	
	public double calculateMovement(double aLocation, double aScreenHeight) {
		//System.out.println(aYLocation);
		double answer = aLocation - (aScreenHeight * .5); 
		return answer;
	}
	
}
