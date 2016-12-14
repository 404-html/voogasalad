package game_engine.actions;

import game_data.Level;
import game_data.LevelSetter;
import game_data.Sprite;

public class LaunchProxyHorizontal implements LevelSetter, Launch {
	private Launch myLaunch;
	private double myVelocity;
	
	public LaunchProxyHorizontal(Sprite myLauncher, Sprite myProjectile, double myVelocity) {
		myLaunch=new LaunchSkeletonHorizontal(myLauncher, myProjectile, myVelocity);
		this.myVelocity=myVelocity;
	}

	@Override
	public void act() {
		myLaunch.act();
	}
	public double getVelocity(){
		return myVelocity;
	}
	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return myLaunch.copyWithNewSprite(aSprite);
	}
	
	@Override
	public void setLevel(Level aLevel) {
		if(myLaunch instanceof LaunchSkeletonHorizontal){
			myLaunch=((LaunchSkeletonHorizontal) myLaunch).createLaunchWithLevel(aLevel);
		}		
	}

}
