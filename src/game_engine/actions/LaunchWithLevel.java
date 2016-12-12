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
	private double myVelocity;
	private Level myLevel;
	public LaunchWithLevel(Sprite myLauncher, Sprite myProjectile, double myVelocity, Level myLevel){
		this.myLauncher=myLauncher;
		myProjectile.setXAcceleration(0);
		myProjectile.setYAcceleration(0);
		myProjectile.setControllable(new Controllable());
		this.myProjectile=myProjectile.clone();
		this.myVelocity=myVelocity;
		this.myLevel=myLevel;
	}

	@Override
	public void act() {
		boolean right=true;
		myProjectile=myProjectile.clone();
		if(myLauncher.getXVelocity()<0){
			right=false;
		}
		
/*		System.out.println(myLauncher.getName());
		System.out.println(myLauncher.getLocation().getXLocation());
		System.out.println(myLauncher.getLocation().getYLocation());*/
		if(right){
			myProjectile.getLocation().setLocation(myLauncher.getLocation().getXLocation()+myLauncher.getWidth()+20, myLauncher.getLocation().getYLocation()+myLauncher.getHeight()/2);
			myProjectile.setXVelocity(myVelocity);
		}
		else{
			myProjectile.getLocation().setLocation(myLauncher.getLocation().getXLocation()-20, myLauncher.getLocation().getYLocation() + myLauncher.getHeight()/2);
			myProjectile.setXVelocity(-myVelocity);
		}
		myProjectile.setYVelocity(0);
		myLevel.addNewSprite(myProjectile);		
	}

	@Override
	public double getVelocity() {
		return myVelocity;
	}
	
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return new LaunchWithLevel(aSprite, myProjectile, myVelocity, myLevel);
	}



}
