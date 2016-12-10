package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_engine.GameResources;
import game_engine.SpritePhysics;

public class Physics implements State{

	//SpritePhysics myPhysics;
	private double verticalGravity;
	private double horizontalGravity;
	
//	public Physics(SpritePhysics aSpritePhysics){
//		myPhysics = aSpritePhysics;
//	}
	@ParameterAnnotation(parameters={"Vertical Gravity", "Horizontal Gravity"})
	public Physics(double verticalGravity, double horizontalGravity){
		this.verticalGravity = verticalGravity;
		this.horizontalGravity = horizontalGravity;
	}
	
//	private void setDefaults() {
//		this.verticalGravity = GameResources.DEFAULT_VERTICAL_GRAVITY.getDoubleResource();
//		this.horizontalGravity = GameResources.DEFAULT_HORIZONTAL_GRAVITY.getDoubleResource();
//	}

	@ViewableMethodOutput(type=double.class, description="Vertical Gravity")
	public double getVerticalGravity(){
		return verticalGravity;
	}
	
	@ViewableMethodOutput(type=double.class, description="Horizontal Gravity")
	public double getHorizontalGravity(){
		return horizontalGravity;
	}
	
//	public SpritePhysics getPhysics(){
//		return myPhysics;
//	}
	
	@Override
	public State copy() {
		// TODO Auto-generated method stub
		return new Physics(verticalGravity, horizontalGravity);
	}

	@Override
	public void updateState(int number) {
		// TODO Auto-generated method stub
	}

}
