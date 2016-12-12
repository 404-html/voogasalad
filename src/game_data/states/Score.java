package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;

public class Score implements State{

	private double myScore;
	
	@ParameterAnnotation(parameters={})
	public Score(){
		myScore = 0;
	}
	
	@Override
	public State copy() {
		return new Score();
	}
	
	@ViewableMethodOutput(description="Score", type=int.class)
	public double getMyScore() {
		return myScore;
	}
	
	@Override
	public void updateState(double pointsUp) {
		myScore+=pointsUp;
	}	
	
}
