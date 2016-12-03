package game_data.states;

public class Health implements State{

	private int myHealth;
	private boolean isAlive;
	
	public Health(int health){
		myHealth = health;
		isAlive = true;
	}
	
	public boolean isAlive(){
		return isAlive;
	}
	
	@Override
	public State copy() {
		return new Health(myHealth);
	}
	
	public void kill(){
		isAlive = false;
	}

	@Override
	public void updateState(int pain) {
		myHealth-=pain;
	}
	
	public int getMyHealth() {
		return myHealth;
	}

	
}
