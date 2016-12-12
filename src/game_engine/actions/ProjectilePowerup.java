package game_engine.actions;

import game_data.Sprite;

public class ProjectilePowerup implements Action{

	public ProjectilePowerup(){
		
	}
	@Override
	public void act() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new ProjectilePowerup();
	}
	
}
