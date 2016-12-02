package game_data.states;

public class LevelWon implements State {

	private boolean hasWon;
	
	public LevelWon() {
		hasWon = false;
	}
	
	public boolean isHasWon() {
		return hasWon;
	}

	public void setHasWon(boolean hasWon) {
		this.hasWon = hasWon;
	}

	@Override
	public State copy() {
		return new LevelWon();
	}

	@Override
	public void updateState(int pain) {
		// TODO Auto-generated method stub
	}
	

}
