package game_data.states;

public class Vincibility implements State{

	private boolean isVincible;
	
	public Vincibility(){
		this.isVincible = true;
	}
	
	public Vincibility(boolean vincible){
		this.isVincible = vincible;
	}
	
	public void setVincibility(boolean vincible){
		isVincible = vincible;
	}
	
	public boolean isVincibility(){
		return isVincible;
	}
	
	@Override
	public State copy() {
		return new Vincibility();
	}

	@Override
	public void updateState(int pain) {
		// TODO Auto-generated method stub
	}	
}
