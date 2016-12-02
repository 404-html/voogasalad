package gameplayer.back_end.keycode_handler;

import javafx.scene.input.KeyCode;

public class KeyCodeHandler {
	
	private double myMovement;
	
	public KeyCodeHandler() {
		//myKeyCodeMap.put(KeyCode.RIGHT, -3.3);
		//myKeyCodeMap.put(KeyCode.LEFT, 3.3);
	}
	
	public void setMovement(double aXVelocity) {
		//System.out.println("velocity: " + aXVelocity + "\n");
		double answer = aXVelocity * .033;
		myMovement = answer;
	}
	
	public double getMovement() {
		//System.out.println("key: " + aKey);
		return -myMovement;
	}

}
