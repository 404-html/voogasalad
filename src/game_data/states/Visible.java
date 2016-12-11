package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;

public class Visible implements State{

	private boolean isVisible;
	
	@ParameterAnnotation(parameters={"Is Visible"})
	public Visible(boolean visible){
		this.isVisible = visible;
	}
	
	public void setVisibility(boolean visible){
		isVisible = visible;
	}
	
	@ViewableMethodOutput(description="Is Visible", type=boolean.class)
	public boolean isVisible(){
		return isVisible;
	}
	
	@Override
	public State copy() {
		return new Visible(this.isVisible);
	}

	@Override
	public void updateState(double pain) {
		// TODO Auto-generated method stub
	}

}
