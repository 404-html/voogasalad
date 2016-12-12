package game_data.characteristics;

import java.util.Map;
import game_engine.Side;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_data.states.LevelWon;
import game_data.states.Score;
import game_data.states.State;
import game_engine.actions.WinLevel;
//import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */
@NameAnnotation(name = "WinByScore")
public class WinByScore implements Characteristic{
	
	private Sprite mySprite;
	private double scoreToWin;
	
	@ParameterAnnotation(parameters={"Sprite", "Score needed"})
	public WinByScore(Sprite aSprite, double scoreToWin){
		mySprite = aSprite;
		this.scoreToWin = scoreToWin;
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO: make and execute win action
			if(mySprite instanceof Player ){
				double myScore = 0 ;
				for(State state:mySprite.getStates()){
					if(state instanceof Score){
						myScore = ((Score) state).getMyScore();
					}
				}
				if(myScore > scoreToWin){
					WinLevel winlevel=new WinLevel(mySprite);
					winlevel.act();
				}
				//System.out.println("poop if winning");
			
			}
		
	}

	@Override
	public Characteristic copy(Sprite aSprite) {
		return new WinByScore(aSprite, scoreToWin);
	}


}
