/*
 * Authors: Alex + Austin
 * 
 */

package game_data.characteristics;

import java.util.Map;

import com.sun.javafx.scene.traversal.Direction;

import game_data.IScoreBasedPositionSprite;
import game_data.ScrollType;
import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.sprites.Player;
import game_data.states.Score;
import game_data.states.State;
import game_engine.actions.Action;
import game_engine.actions.Break;
import game_engine.actions.ScoreAdder;
import game_engine.Side;

@NameAnnotation(name = "Score Based On Position")
public class ScoreBasedOnPosition implements Characteristic {

	private Sprite mySprite;
	private double initX;
	private double initY;
	private double pointsAdded;
	private double myScore;
	private double maxDist;
	private ScrollType scrollDirection;
	private double cur;
	private boolean curPositive;
	private boolean initPositive;
	private double magnitude;

	@ParameterAnnotation(parameters = { "Sprite"})
	public ScoreBasedOnPosition(Sprite aSprite) {
		mySprite = aSprite;
		this.initX = aSprite.getLocation().getXLocation();
		this.initY = aSprite.getLocation().getYLocation();
//		this.scrollDirection = scrollDirection;
		pointsAdded = 0;
		maxDist = 0;
		// updateMyScore();
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		if(scrollDirection == null || scrollDirection == ScrollType.CENTER )
			return;
		// updateMyScore();
//		System.out.println("executes");
		if (scrollDirection == ScrollType.HORIZONTAL_RIGHT || scrollDirection == ScrollType.HORIZONTAL_LEFT) {
			getHorizontalCoordinates();
			findMagnitude(initX);
		} else {
			getVerticalCoordinates();
			findMagnitude(initY);
		}

		if (maxDist < magnitude)
			maxDist = magnitude;

		double toAdd = maxDist - pointsAdded;
//		System.out.println("points to add " + toAdd);
		if (validAddition(scrollDirection)) {
			Action myAction = new ScoreAdder(toAdd, mySprite);
			myAction.act();
			pointsAdded += toAdd;
		}
		
//		updateMyScore();
//		System.out.println("score is : " + myScore);
	}

	private boolean validAddition(ScrollType scrollDirection2) {
		
		if((initX < cur && scrollDirection == ScrollType.HORIZONTAL_RIGHT) || (initX>cur && scrollDirection==ScrollType.HORIZONTAL_LEFT) || (initY < cur && scrollDirection == ScrollType.VERTICAL_DOWN) || (initY>cur && scrollDirection==ScrollType.VERTICAL_UP)){
			return true;
		}
		return false;
//		if(scrollDirection == Direction.RIGHT || scrollDirection == Direction.LEFT ){
//			if((initX < cur && scrollDirection == Direction.RIGHT) || (initX>cur && scrollDirection==Direction.LEFT)){
//				return true;
//			}
//			else {
//				return false;
//			}
//		}		
//		
//		if(scrollDirection == Direction.DOWN || scrollDirection == Direction.UP ){
//			if((initY < cur && scrollDirection == Direction.DOWN) || (initY>cur && scrollDirection==Direction.UP)){
//				return true;
//			}
//			else {
//				return false;
//			}
//		}
//		return false;
	}

	private void findMagnitude(double initVal) {
		if ((initPositive && !curPositive) || (!initPositive && curPositive)) {
			magnitude = Math.abs(initVal) + Math.abs(cur);
		} else {
			magnitude = Math.abs(initVal - cur);
		}

		return;
	}

	private void getHorizontalCoordinates() {
		cur = mySprite.getLocation().getXLocation();
		curPositive = cur > 0;
		initPositive = initX > 0;
	}

	private void getVerticalCoordinates() {
		cur = mySprite.getLocation().getYLocation();
		curPositive = cur > 0;
		initPositive = initY > 0;
	}

	private void updateMyScore() {
		for (State state : mySprite.getStates()) {
			if (state instanceof Score)
				myScore = ((Score) state).getMyScore();
		}
	}

	public void setScrollDirection(ScrollType myScrollType){
		this.scrollDirection = myScrollType;
	}
	@Override
	public Characteristic copy(Sprite aSprite) {
		return new ScoreBasedOnPosition(aSprite);
	}

}
