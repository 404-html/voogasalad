package game_engine.actions;

import game_data.Sprite;

public class Launch implements Action{
	private Sprite myLauncher;
	private Sprite myProjectile;
	private double myXVelocity;
	private double myYVelocity;
	public Launch(Sprite myLauncher, Sprite myProjectile, double myXVelocity, double myYVelocity){
		this.myLauncher=myLauncher;
		this.myProjectile=myProjectile;
		this.myXVelocity=myXVelocity;
		this.myYVelocity=myYVelocity;
	}

	@Override
	public void act() {
		
		// TODO Auto-generated method stub
		
	}

}
