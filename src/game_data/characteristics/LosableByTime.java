package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import javafx.geometry.Side;

@NameAnnotation(name = "Losable By Time")
public class LosableByTime extends Losable implements Characteristic{

	private double myTime;
	private Sprite mySprite;
	
	@ParameterAnnotation(parameters = {"Time", "Sprite"})
	public LosableByTime(double time, Sprite aSprite){
		super(aSprite);
		myTime = time;
		mySprite = aSprite;
	}
	
	public double getTime(){
		return myTime;
	}
	
	public boolean outOfTime(){
		return myTime<=0;
	}
	
	@Override
	public Characteristic copy() {
		return new LosableByTime(myTime, mySprite.clone());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: make and execute action
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			//myAction = new ProjectilePowerUp();
			//myAction.act();
		}
	}

}
