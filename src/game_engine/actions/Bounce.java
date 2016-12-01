package game_engine.actions;

import game_data.Sprite;

public class Bounce implements Action {
	
	private double myBounceSpeed;
	private Sprite myPlayerSprite;
	

	public Bounce(double bouncerSpeed, Sprite player) {
		myBounceSpeed = bouncerSpeed;
		myPlayerSprite = player;
	}

	@Override
	public void act() {
		//System.out.println("poop");
		//myPlayerSprite.setMyVelocity((myPlayerSprite.getMyVelocity()) + myBounceSpeed);
		//myPlayerSprite.getMyLocation().setMyHeading(myPlayerSprite.getMyLocation().getMyHeading()+Math.PI);
		myPlayerSprite.setMyYVelocity(0);
		}

	}


