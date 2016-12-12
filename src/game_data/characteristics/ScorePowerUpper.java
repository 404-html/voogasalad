package game_data.characteristics;
import game_engine.IUpdateStatesAndPowerUps;
import game_engine.Side;
import game_engine.actions.*;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_engine.actions.Damage;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_engine.actions.Damage;
//import javafx.geometry.Side;

/**
 * @author Lucia Martos
 *
 */
@NameAnnotation(name = "Score Power Up")
public class ScorePowerUpper extends PowerUpper implements Characteristic{

	private int myScoreToGain;
	private Action myAction;
	
	@ParameterAnnotation(parameters = {"Score Gained", "Sprite"})
	public ScorePowerUpper(int scoreToGain, Sprite aSprite){
		super(aSprite);
		myScoreToGain = scoreToGain;
	}
	
	@ViewableMethodOutput(description="Score Gained", type=int.class)
	public int getScoreToGain(){
		return myScoreToGain;
	}
	
	@Override
	public Characteristic copy(Sprite aSprite) {
		return new ScorePowerUpper(myScoreToGain, aSprite);
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		for(Sprite collidedSprite:myCollisionMap.keySet()){
			if (collidedSprite instanceof Player){
				myAction = new ScoreAdder(getScoreToGain(), collidedSprite);
				myAction.act();
			}		
		}

	}


	}

