package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

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
	
	public int getMyScore() {
		return myScore;
	}
	
	@Override
	public void updateState(int pointsUp) {
		myScore+=pointsUp;
	}	
	
}
