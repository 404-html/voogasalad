
package game_data.characteristics;

import java.util.Map;
import game_engine.Side;
import game_data.Level;
import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.NameAnnotation;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import game_data.sprites.Player;
import game_data.states.State;
import game_data.states.Vincibility;
import game_data.states.Visible;
import game_engine.IUpdateStatesAndPowerUps;
import game_engine.UpdateStates;
import game_engine.actions.Action;
import game_engine.actions.Invincibility;
import game_engine.actions.SpeedBoost;
//import javafx.geometry.Side;

/**
 * @author austingartside
 *
 */

@NameAnnotation(name = "Invincibility Power Up")
public class InvincibilityPowerUpper extends TemporalPowerUpper implements Characteristic {

	private double myTimeInEffect;
	private Action myAction;

	@ParameterAnnotation(parameters = { "Time In Effect", "Sprite" })
	public InvincibilityPowerUpper(double timeInEffect, Sprite aSprite) {
		super(aSprite);
		myTimeInEffect = timeInEffect;
	}
	
	@ViewableMethodOutput(description="Time In Effect", type=double.class)
	public double getTimeInEffect() {
		return myTimeInEffect;
	}
	
	@Override
	public Characteristic copy() {
		return new InvincibilityPowerUpper(myTimeInEffect, this.getSprite());
	}

	@Override
	public void execute(Map<Sprite, Side> myCollisionMap) {
		// TODO: make and execute speed Up action

		for (Sprite collidedSprite : myCollisionMap.keySet()) {
			// unless we want non players to be able to speed up upon hitting a
			// powerup
			if (collidedSprite instanceof Player) {
				addToPowerUpMap(collidedSprite, myTimeInEffect);
				myAction = new Invincibility(collidedSprite);
				// System.out.println("characteristic in");
				myAction.act();
			}
		}
	}

	@Override
	public void reversePowerUp(Sprite playerSprite, IUpdateStatesAndPowerUps  myInterface) {
		for (State state : playerSprite.getStates()) {
			if (state instanceof Vincibility) {
				((Vincibility) state).setVincibility(true);
			}
			if (state instanceof Visible) {
				((Visible) state).setVisibility(true);
			}
		}

	}

	@Override
	public void activatePowerUp(Sprite playerSprite, IUpdateStatesAndPowerUps myInterface, Double timeElapsed) {
		boolean hasVisibility = false;
		for (State state : playerSprite.getStates()) {
			if (state instanceof Visible) {
				hasVisibility = true;
				((Visible) state).setVisibility((timeElapsed.intValue()) % 3 == 0);

			}
		}
		if (!hasVisibility) {
			playerSprite.addState(new Visible((timeElapsed.intValue()) % 3 == 0));
		}
	}
}