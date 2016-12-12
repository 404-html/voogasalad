package game_engine.actions;

import game_data.Level;
import game_data.LevelSetter;
import game_data.Sprite;

public class LaunchProxy implements LevelSetter, Launch {
	private Launch myLaunch;
	public LaunchProxy(Sprite myLauncher, Sprite myProjectile, double myXVelocity, double myYVelocity) {
		myLaunch=new LaunchSkeleton(myLauncher, myProjectile, myXVelocity, myYVelocity);
	}

	@Override
	public void act() {
		myLaunch.act();

	}

	@Override
	public Action copyWithNewSprite(Sprite aSprite) {
		return myLaunch.copyWithNewSprite(aSprite);
	}
	
	@Override
	public void setLevel(Level aLevel) {
		if(myLaunch instanceof LaunchSkeleton){
			myLaunch=((LaunchSkeleton) myLaunch).createLaunchWithLevel(aLevel);
		}		
	}

}
