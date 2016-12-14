package game_engine.actions;

import game_data.Controllable;
import game_data.Level;
import game_data.LevelSetter;
import game_data.Sprite;
/**
 * @author Katrina
 *
 */
public class LaunchSkeletonHorizontal implements Launch{
	private Sprite myLauncher;
	private Sprite myProjectile;
	private double myVelocity;
	public LaunchSkeletonHorizontal(Sprite myLauncher, Sprite myProjectile, double myVelocity){
		this.myLauncher=myLauncher;
		this.myProjectile=myProjectile.clone();
		this.myVelocity=myVelocity;
	}

	@Override
	public void act() {
		return;
	}
	public LaunchWithLevelHorizontal createLaunchWithLevel(Level aLevel){
		return new LaunchWithLevelHorizontal(myLauncher, myProjectile, myVelocity, aLevel);
	}

	@Override
	public double getVelocity() {
		return myVelocity;
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new LaunchSkeletonHorizontal(aSprite, myProjectile, myVelocity);
	}


}
