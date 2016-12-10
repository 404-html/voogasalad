package game_engine.actions;

import game_data.Controllable;
import game_data.Level;
import game_data.LevelSetter;
import game_data.Sprite;
/**
 * @author Katrina
 *
 */
public class LaunchSkeleton implements Launch{
	private Sprite myLauncher;
	private Sprite myProjectile;
	private double myXVelocity;
	private double myYVelocity;
	public LaunchSkeleton(Sprite myLauncher, Sprite myProjectile, double myXVelocity, double myYVelocity){
		this.myLauncher=myLauncher;
		this.myProjectile=myProjectile.clone();
		this.myXVelocity=myXVelocity;
		this.myYVelocity=myYVelocity;
	}

	@Override
	public void act() {
		return;
	}
	public LaunchWithLevel createLaunchWithLevel(Level aLevel){
		return new LaunchWithLevel(myLauncher, myProjectile, myXVelocity, myYVelocity, aLevel);
	}



}
