package game_data.characteristics;

import java.util.Map;
import game_engine.Side;

import game_data.Sprite;
import game_engine.actions.Launch;
//import javafx.geometry.Side;

public class LauncherWithKey implements Characteristic{
	private Sprite myLauncher;
	private Sprite myProjectile;
	private double myXVelocity;
	private double myYVelocity;
	public LauncherWithKey(Sprite launchingSprite, Sprite projectileSprite, double initialXVelocity, double initialYVelocity) {
		myLauncher=launchingSprite;
		myProjectile=projectileSprite;
		myXVelocity=initialXVelocity;
		myYVelocity=initialYVelocity;
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		if(isKeyPressed()){
			//Launch launch = new Launch(myLauncher, myProjectile, myXVelocity, myYVelocity);
			//launch.act();
		}
	}
		

	@Override
	public Characteristic copy(Sprite aSprite) {
		// TODO Auto-generated method stub
		return null;
	}
	private boolean isKeyPressed(){
		return false;
	}

}
