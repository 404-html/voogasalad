package game_data.states;

public class Shooting implements State{

	private boolean isShooting;
	
	public Shooting(){
		this.isShooting = true;
	}
	
	public boolean isShooting(){
		return isShooting;
	}
	
	public void setShooting(boolean shooting){
		isShooting = false;
	}
	
	@Override
	public State copy() {
		return new Shooting();
	}

	@Override
	public void updateState(int pain) {
		// TODO Auto-generated method stub
	}	
}
