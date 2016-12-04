package game_engine.actions;

import game_data.Level;
import game_data.Sprite;

public class Launch implements Action{
	private Sprite myLauncher;
	private Sprite myProjectile;
	private double myXVelocity;
	private double myYVelocity;
	private Level myLevel;
	public Launch(Sprite myLauncher, Sprite myProjectile, double myXVelocity, double myYVelocity, Level aLevel){
		this.myLauncher=myLauncher;
		this.myProjectile=myProjectile;
		this.myXVelocity=myXVelocity;
		this.myYVelocity=myYVelocity;
		this.myLevel=aLevel;
	}

	@Override
	public void act() {
		myProjectile.setMyXVelocity(myXVelocity);
		myProjectile.setMyYVelocity(myYVelocity);
		myLevel.addNewSprite(myProjectile);
		
	}

}
