package game_data.states;

public class Visible implements State{

	private boolean isVisible;
	
	public Visible(){
		this.isVisible = true;
	}
	
	public Visible(boolean visible){
		this.isVisible = visible;
	}
	
	public void setVisibility(boolean visible){
		isVisible = visible;
	}
	
	public boolean isVincibility(){
		return isVisible;
	}
	
	@Override
	public State copy() {
		return new Visible();
	}

	@Override
	public void updateState(int pain) {
		// TODO Auto-generated method stub
	}	
}
