package gameplayer.back_end.keycode_handler;

import java.util.Set;

import game_data.ScrollType;
import game_data.states.ScreenRatio;
import game_data.states.State;

public class MovementHandlerFactory {
	
	public XYMovementHandler buildMovementHandler(double aXLocation, double aScreenWidth, double aYLocation, double aScreenHeight, 
			ScrollType scrollType, Set<State> set) {
		double XMovement = 0;
		double YMovement = 0;
		double screenRatioTop = .5;
		double screenRatioBottom = .5;
		for (State s : set) {
			if (s instanceof ScreenRatio) {
				screenRatioTop = ((ScreenRatio) s).getTopRatio();
				screenRatioBottom = ((ScreenRatio) s).getBottomRatio();
			}
		}
		if (scrollType.equals(ScrollType.HORIZONTAL_LEFT) || scrollType.equals(ScrollType.HORIZONTAL_RIGHT)) {
			XMovement = calculateMovement(aXLocation, aScreenWidth, screenRatioBottom);
		} else if (scrollType.equals(ScrollType.VERTICAL_DOWN) || scrollType.equals(ScrollType.VERTICAL_UP)) {
			YMovement = calculateMovement(aYLocation, aScreenHeight, screenRatioTop);
		} else if (scrollType.equals(ScrollType.CENTER)) {
			XMovement = calculateMovement(aXLocation, aScreenWidth, screenRatioBottom);
			YMovement = calculateMovement(aYLocation, aScreenHeight, screenRatioTop);
		}
		return new XYMovementHandler(XMovement, YMovement);
	}
	
	public double calculateMovement(double aLocation, double aScreenParam, double aScreenRatio) {
		double answer = aLocation - (aScreenParam * aScreenRatio); 
		return answer;
	}

}
