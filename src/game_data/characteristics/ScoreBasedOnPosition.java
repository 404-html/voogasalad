/*
 * Authors: Alex + Austin
 * 
 */

package game_data.characteristics;

import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_engine.actions.Action;
import game_engine.actions.Break;
import game_engine.actions.ScoreAdder;
import javafx.geometry.Side;

@NameAnnotation(name = "Score Based On Position")
public class ScoreBasedOnPosition implements Characteristic {

	private Sprite mySprite;
	private double initX;
	private double initY;

	@ParameterAnnotation(parameters = { "Sprite" })
	public ScoreBasedOnPosition(Sprite aSprite, double initX, double initY) {
		mySprite = aSprite;
		this.initX = initX;
		this.initY = initY;
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		//TODO NEED TO KNOW WHETHER ITS HORIZONTAL OR VERTICAL SCOLLER THIS IS SHITTY WAY OF TELLING MAX SCORE.
		Action myAction = new ScoreAdder((int) Math.max(Math.abs(mySprite.getLocation().getXLocation()-initX), Math.abs(mySprite.getLocation().getYLocation()-initY)), mySprite);
		myAction.act();
	}

	@Override
	public Characteristic copy() {
		return new ScoreBasedOnPosition(mySprite, initX, initY);
	}

}
