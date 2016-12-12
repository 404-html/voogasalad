package game_engine.actions;

import game_data.Controllable;
import game_data.Level;
import game_data.LevelSetter;
import game_data.Sprite;
/**
 * @author Katrina
 *
 */
public class LaunchWithLevel implements Launch{
	private Sprite myLauncher;
	private Sprite myProjectile;
	private double myXVelocity;
	private double myYVelocity;
	private Level myLevel;
	public LaunchWithLevel(Sprite myLauncher, Sprite myProjectile, double myXVelocity, double myYVelocity, Level myLevel){
		this.myLauncher=myLauncher;
		myProjectile.getLocation().setLocation(myLauncher.getLocation().getXLocation()+myLauncher.getWidth()+1, myLauncher.getLocation().getYLocation()+1);
		myProjectile.setXVelocity(myXVelocity);
		myProjectile.setYVelocity(myYVelocity);
		myProjectile.setXAcceleration(0);
		myProjectile.setYAcceleration(0);
		myProjectile.setControllable(new Controllable());
		this.myProjectile=myProjectile.clone();
		this.myXVelocity=myXVelocity;
		this.myYVelocity=myYVelocity;
		this.myLevel=myLevel;
	}

	@Override
	public void act() {
		myProjectile=myProjectile.clone();
		myProjectile.getLocation().setLocation(myLauncher.getLocation().getXLocation()+myLauncher.getWidth()+1, myLauncher.getLocation().getYLocation()+1);
		myProjectile.setXVelocity(myXVelocity);
		myProjectile.setYVelocity(myYVelocity);
		myProjectile.setXAcceleration(0);
		myProjectile.setYAcceleration(0);
		myLevel.addNewSprite(myProjectile);
		
	}
	
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new LaunchWithLevel(aSprite, myProjectile, myXVelocity, myYVelocity, myLevel);
	}



}
