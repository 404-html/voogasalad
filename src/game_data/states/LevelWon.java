package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

public class LevelWon implements State {

	private boolean hasWon;
	
	@ParameterAnnotation(parameters={})
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
