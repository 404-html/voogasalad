package game_data.states;

public class Solid implements State{

	private boolean isSolid;
	
	public Solid(boolean isSolid){
		this.isSolid = isSolid;
	}
	
	public boolean isSolid(){
		return isSolid;
	}
	
	@Override
	public State copy() {
		return new Solid(isSolid);
	}

	@Override
	public void updateState(int pain) {
		// TODO Auto-generated method stub
	}	
}
