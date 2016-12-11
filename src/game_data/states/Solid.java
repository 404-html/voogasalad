package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

public class Solid implements State{

	private boolean isSolid;
	
	@ParameterAnnotation(parameters={})
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
