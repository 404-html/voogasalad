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
		myProjectile.getMyLocation().setLocation(myLauncher.getMyLocation().getXLocation()+myLauncher.getMyWidth()+1, myLauncher.getMyLocation().getYLocation()+1);
		myProjectile.setMyXVelocity(myXVelocity);
		myProjectile.setMyYVelocity(myYVelocity);
		myProjectile.setMyXAcceleration(0);
		myProjectile.setMyYAcceleration(0);
		myProjectile.setControllable(new Controllable(myProjectile));
		this.myProjectile=myProjectile.clone();
		this.myXVelocity=myXVelocity;
		this.myYVelocity=myYVelocity;
		this.myLevel=myLevel;
	}

	@Override
	public void act() {
		myProjectile=myProjectile.clone();
		myProjectile.getMyLocation().setLocation(myLauncher.getMyLocation().getXLocation()+myLauncher.getMyWidth()+1, myLauncher.getMyLocation().getYLocation()+1);
		myProjectile.setMyXVelocity(myXVelocity);
		myProjectile.setMyYVelocity(myYVelocity);
		myProjectile.setMyXAcceleration(0);
		myProjectile.setMyYAcceleration(0);
		myLevel.addNewSprite(myProjectile);
		
	}



}
