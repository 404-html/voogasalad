package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableVariable;

public class Score implements State{

	private int myScore;
	
	@ParameterAnnotation(parameters={"Score"})
	public Score(int score){
		myScore = score;
	}
	
	@Override
	public State copy() {
		return new Score(myScore);
	}
	
	@ViewableVariable(description="Score", type=int.class)
	public int getMyScore() {
		return myScore;
	}
	
	@Override
	public void updateState(int pointsUp) {
		myScore+=pointsUp;
	}	
	
}
