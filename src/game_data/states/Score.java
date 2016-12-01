package game_data.states;

public class Score implements State{

	private int myScore;
	
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
