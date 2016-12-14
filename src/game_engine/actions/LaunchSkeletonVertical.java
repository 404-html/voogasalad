package game_engine.actions;

import game_data.Controllable;
import game_data.Level;
import game_data.LevelSetter;
import game_data.Sprite;
/**
 * @author Katrina
 *
 */
public class LaunchSkeletonVertical implements Launch{
	private Sprite myLauncher;
	private Sprite myProjectile;
	private double myVelocity;
	public LaunchSkeletonVertical(Sprite myLauncher, Sprite myProjectile, double myVelocity){
		this.myLauncher=myLauncher;
		this.myProjectile=myProjectile.clone();
		this.myVelocity=myVelocity;
	}

	@Override
	public void act() {
		return;
	}
	public LaunchWithLevelVertical createLaunchWithLevel(Level aLevel){
		return new LaunchWithLevelVertical(myLauncher, myProjectile, myVelocity, aLevel);
	}

	@Override
	public double getVelocity() {
		return myVelocity;
	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new LaunchSkeletonVertical(aSprite, myProjectile, myVelocity);
	}


}
