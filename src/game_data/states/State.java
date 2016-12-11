package game_data.states;

public interface State {
	
	public State copy();
	public void updateState(double pain);

}
