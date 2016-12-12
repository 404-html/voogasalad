package game_engine.actions;

import game_data.Level;
import game_data.LevelSetter;
import game_data.Sprite;

public class LaunchProxy implements LevelSetter, Launch {
	private Launch myLaunch;
	private double myVelocity;
	
	public LaunchProxy(Sprite myLauncher, Sprite myProjectile, double myVelocity) {
		myLaunch=new LaunchSkeleton(myLauncher, myProjectile, myVelocity);
		this.myVelocity=myVelocity;
	}

	@Override
	public void act() {
		System.out.println("lanching");
		myLaunch.act();
	}
	public double getVelocity(){
		return myVelocity;
	}
	@Override
	public void setLevel(Level aLevel) {
		if(myLaunch instanceof LaunchSkeleton){
			myLaunch=((LaunchSkeleton) myLaunch).createLaunchWithLevel(aLevel);
		}		
	}

}
