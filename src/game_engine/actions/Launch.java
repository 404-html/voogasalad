package game_engine.actions;

import game_data.Controllable;
import game_data.Level;
import game_data.Sprite;
/**
 * @author Katrina
 *
 */
public class Launch implements Action{
	private Sprite myLauncher;
	private Sprite myProjectile;
	private double myXVelocity;
	private double myYVelocity;
	private Level myLevel;
	public Launch(Sprite myLauncher, Sprite myProjectile, double myXVelocity, double myYVelocity, Level aLevel){
		this.myLauncher=myLauncher;
		myProjectile.getLocation().setLocation(myLauncher.getLocation().getXLocation()+myLauncher.getWidth()+1, myLauncher.getLocation().getYLocation()+1);
		myProjectile.setXVelocity(myXVelocity);
		myProjectile.setYVelocity(myYVelocity);
		myProjectile.setXAcceleration(0);
		myProjectile.setYAcceleration(0);
		myProjectile.setControllable(new Controllable(myProjectile));
		this.myProjectile=myProjectile.clone();
		this.myXVelocity=myXVelocity;
		this.myYVelocity=myYVelocity;
		this.myLevel=aLevel;
	}

	@Override
	public void act() {
		//System.out.println("acting");
		myProjectile=myProjectile.clone();
		myProjectile.getLocation().setLocation(myLauncher.getLocation().getXLocation()+myLauncher.getWidth()+1, myLauncher.getLocation().getYLocation()+1);
		myProjectile.setXVelocity(myXVelocity);
		myProjectile.setYVelocity(myYVelocity);
		myProjectile.setXAcceleration(0);
		myProjectile.setYAcceleration(0);
		myLevel.addNewSprite(myProjectile);
		
	}

}
