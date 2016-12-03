package gameplayer.back_end.keycode_handler;

public class MovementHandler {
	
	private double myMovement;
	
	public MovementHandler() {
		//myKeyCodeMap.put(KeyCode.RIGHT, -3.3);
		//myKeyCodeMap.put(KeyCode.LEFT, 3.3);
	}
	
	public void setMovement(double aXVelocity) {
		//System.out.println("velocity: " + aXVelocity + "\n");
		double answer = aXVelocity * .0166589;
		myMovement = answer;
	}
	
	public double getMovement() {
		//System.out.println("key: " + aKey);
		return -myMovement;
	}
}
