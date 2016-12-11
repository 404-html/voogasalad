package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

public class Shooting implements State{

	private boolean isShooting;
	
	@ParameterAnnotation(parameters={})
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
	public void updateState(double pain) {
		// TODO Auto-generated method stub
	}	
}
