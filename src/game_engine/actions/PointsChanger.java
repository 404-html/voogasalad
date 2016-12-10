package game_engine.actions;

import game_data.Sprite;
import game_data.states.*;


public abstract class PointsChanger implements Action {
	
	protected double pointsToGive;
	protected Sprite spriteAffected;
	
	public PointsChanger(double damage, Sprite sprite){
		pointsToGive = damage;
		spriteAffected = sprite;
	}
	
	@Override
	public abstract void act();

}
