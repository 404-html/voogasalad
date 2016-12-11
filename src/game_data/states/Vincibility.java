package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;

public class Vincibility implements State{

	private boolean isVincible;
	
	@ParameterAnnotation(parameters={"Is Vincible"})
	public Vincibility(boolean vincible){
		this.isVincible = vincible;
	}
	
	public void setVincibility(boolean vincible){
		isVincible = vincible;
	}
	
	@ViewableMethodOutput(description="Is Vincible", type=boolean.class)
	public boolean isVincibility(){
		return isVincible;
	}
	
	@Override
	public State copy() {
		return new Vincibility(this.isVincible);
	}

	@Override
	public void updateState(int pain) {
		// TODO Auto-generated method stub
	}	
}
