package game_data.states;

public class Solid implements State{

	private boolean isSolid;
	
	public Solid(){
		this.isSolid = true;
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	
	@Override
	public State copy() {
		return new Solid();
	}

	@Override
	public void updateState(double pain) {
		// TODO Auto-generated method stub
	}	
}
