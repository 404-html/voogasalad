package game_data.states;

import game_engine.SpritePhysics;

public class Physics implements State{

	SpritePhysics myPhysics;
	
	public Physics(SpritePhysics aSpritePhysics){
		myPhysics = aSpritePhysics;
	}
	
	public SpritePhysics getPhysics(){
		return myPhysics;
	}
	
	@Override
	public State copy() {
		// TODO Auto-generated method stub
		return new Physics(myPhysics.copy());
	}

	@Override
	public void updateState(double number) {
		// TODO Auto-generated method stub
	}

}
