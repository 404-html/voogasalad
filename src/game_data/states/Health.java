package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;

public class Health implements State{

	private int myHealth;
	private boolean isAlive;
	
	@ParameterAnnotation(parameters={"Health"})
	public Health(int health){
		myHealth = health;
		isAlive = true;
	}
	
	@ViewableMethodOutput(type=int.class, description="Health")
	public int getHealth() {
		return myHealth;
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
