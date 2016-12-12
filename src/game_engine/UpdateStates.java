package game_engine;

import game_data.Controllable;
import game_data.Level;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.TemporalPowerUpper;
import game_data.sprites.Player;
import game_data.states.Health;
import game_data.states.LevelWon;
import game_data.states.State;
import game_engine.actions.Action;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import game_engine.actions.MoveUpJump;

import java.util.ArrayList;
import java.util.HashMap;
import game_engine.properties.RandomMoveHandler.Orientation;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;

/**
 * TODO make sure that player doesnt run into walls or thigns NOTE: doing the
 * runKeyCalls and then updating sprite posistions separately might lead to an
 * issue with glitches CHECK: CALCULATING HEARDING FROM THE VERTICAL (NOON)
 * CHECK CORRECT CALC FOR VEL QUESTION who is going to keep track of the time of
 * the game? how are we going to provoke a win? through interface? TODO give
 * myLevel all the properties I want. CLARIFY: does the x and y loc represent
 * the middle or the left top corner or what? There are some things that I dont
 * know if I should be getting from level class of the engine itself Losses
 * should actually probably be integrated within characteristics so we dont
 * check for collision repeatedly. Do we have to deal with if the sprite hits a
 * block at multiple sides?
 * 
 * @author LuciaMartos, Austin Gartside, Katrina Zhu
 *
 */

public class UpdateStates implements IUpdateStatesAndPowerUps {
	//int count;
	private Level myLevel;
	private List<Sprite> mySpriteList;
	private double myTimeElapsed;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private Map<Sprite, ImageView> mySpriteImages;
	private double myScreenWidth, myScreenHeight, myScreenXPosition, myScreenYPosition;
	private Map<Characteristic, Double> myCurrentPowerUps;
	private Controllable mainPlayerControllable;
	private List<Sprite> myControllableSpriteList;

	public UpdateStates(Level aLevel) {
		//count=0;
		myLevel = aLevel;
		myCurrentPowerUps = new HashMap<Characteristic, Double>();
		mySpriteList = new ArrayList<Sprite>();
		myTimeElapsed = 0;
		myKeysPressed = new HashSet<KeyCode>();
		myKeysReleased = new HashSet<KeyCode>();
		mySpriteImages = new HashMap<Sprite, ImageView>();
		myControllableSpriteList = new ArrayList<Sprite>();

		mainPlayerControllable=new Controllable();
	}
	public void update(double aTimeElapsed, Set<KeyCode> aKeysPressed, Set<KeyCode> aKeysReleased, Map<Sprite, ImageView> aSpriteImages, double aScreenHeight, double aScreenWidth, double aScreenXPosition, double aScreenYPosition){
        myScreenWidth = aScreenWidth;
        myScreenHeight = aScreenHeight;
        myScreenXPosition = aScreenXPosition;
        myScreenYPosition = aScreenYPosition;
		myTimeElapsed=aTimeElapsed;
		setKeysPressed(aKeysPressed);
		setKeysReleased(aKeysReleased);
		mySpriteImages=aSpriteImages;
		myCurrentPowerUps = myLevel.getMainPlayer().getPowerUps();
//		System.out.println("number o fpower UP" + myCurrentPowerUps.size());
		mySpriteList = myLevel.getMySpriteList();
		myControllableSpriteList = myLevel.getMyControllableSpriteList();
		mainPlayerControllable = myLevel.getMainPlayer().getControllable();	
		activatePowerUps();
		checkPowerUps();
		executeControls();
		executeCharacteristics();
		cleanGame();
		
//		System.out.println( " X VEL :" + myLevel.getMainPlayer().getXVelocity());
//		for (State state : myLevel.getMainPlayer().getStates()) {
//			if (state instanceof Score) {
//					System.out.println("Score : "+ ((Score)state).getMyScore());
//				}
//			}	
	}

	private void setKeysPressed(Set<KeyCode> aKeysPressed){
		myKeysPressed=aKeysPressed;
	}
	private void setKeysReleased(Set<KeyCode> aKeysReleased){
		myKeysReleased=aKeysReleased;
	}
	public Level getLevel(){
		return myLevel;
	}
	private void activatePowerUps() {
		for (Characteristic powerUp : myCurrentPowerUps.keySet()) {
//			System.out.println("Number of power ups " + myCurrentPowerUps.size());
//			if (powerUp instanceof TemporalPowerUpper) {
				((TemporalPowerUpper) powerUp).activatePowerUp(myLevel.getMainPlayer(), this,
						myCurrentPowerUps.get(powerUp));
//				System.out.println("Time left " + myCurrentPowerUps.get(powerUp));
//			}
//			if(powerUp instanceof InvincibilityPowerUpper){
//				((InvincibilityPowerUpper) powerUp).activatePowerUp(myLevel.getMainPlayer(), this, myCurrentPowerUps.get(powerUp));
//			}

		}
	}

//	public static void activateSingularPowerUp(Characteristic powerUp, Sprite spriteToActOn, double timeRemaining) {
//		// BUGGY!!!!
////		System.out.println("Time left " + timeRemaining);
//
//		if (powerUp instanceof TemporalPowerUpper) {
//			// System.out.println("LUCIA");
//			// if(powerUp instanceof InvincibilityPowerUpper)
//			// System.out.println("Knows type");
//
//			((TemporalPowerUpper) powerUp).activatePowerUp(spriteToActOn, null, timeRemaining);
//		}
//	}

//	public void setKeyPressedMapWithBoosts() {
//		myLevel.getMainPlayer().getControllable().setMyKeyPressedMap(generateBoostedKeyPressedMap());
//	}

	private void checkPowerUps() {
		ArrayList<Characteristic> toRemove = new ArrayList<Characteristic>();
		for (Characteristic powerUp : myCurrentPowerUps.keySet()) {
//			 System.out.println("POWER UP TIM?E " + myCurrentPowerUps.get(powerUp));
			// myCurrentPowerUps.get(powerUp));
			myCurrentPowerUps.put(powerUp, myCurrentPowerUps.get(powerUp) - 1);
			if (myCurrentPowerUps.get(powerUp) <= 0) {
				toRemove.add(powerUp);
			}
		}

		// remove power ups
		for (Characteristic remove : toRemove) {
			myCurrentPowerUps.remove(remove);
			powerUpHasBeenRemoved(remove);
		}

	}

	private void powerUpHasBeenRemoved(Characteristic powerUp) {
		((TemporalPowerUpper) powerUp).reversePowerUp(myLevel.getMainPlayer(), this);
	}

	private void cleanGame() {
		ArrayList<Sprite> removeSprites = new ArrayList<Sprite>();
		for (Sprite mySprite : mySpriteList) {
			for (State state : mySprite.getStates()) {
				if (state instanceof Health) {
					if (!((Health) state).isAlive()) {
						// System.out.println("DEAD SPRITE");
						removeSprites.add(mySprite);
					}
				}
			}
		}
		for (Sprite mySprite : removeSprites) {
			myLevel.removeSprite(mySprite);
			mySpriteImages.remove(mySprite);
		}

		updateSpritePositions();
		moveRandomSprites();
		checkForWin();
		checkForLoss();

	}

	private void executeControls() {
		for (Sprite mySprite : myControllableSpriteList) {
			ListOfCollidingSprites collidingSprites = new ListOfCollidingSprites(mySprite, mySpriteList,
					mySpriteImages);
			Map<Sprite, Side> myCollisionMap = collidingSprites.getCollisionSpriteMap();
//			if(mySprite instanceof Player){
//				System.out.println("Colliding with : " + myCollisionMap.size());
//			}
			
			Controllable control;
			if (mySprite instanceof Player)
				control = mainPlayerControllable;
			else{
				control = mySprite.getControllable();
			}
			if (control.isControllable()) {
				control.sendCurrentKeys(myKeysPressed, myKeysReleased);
				control.execute(myCollisionMap);
			}
		}
	}

	private void moveRandomSprites() {
		for(Sprite mySprite : mySpriteList) {
			if( mySprite.getMyRandomMoveHandler() != null && mySprite.getMyRandomMoveHandler().getOrientation() != Orientation.NULL) {
				mySprite.getMyRandomMoveHandler().move(mySprite,myScreenWidth,myScreenHeight,myScreenXPosition,myScreenYPosition);
			}
		}
	}
	
	private void checkForLoss() {

		for (State s : myLevel.getMainPlayer().getStates()) {
			if (myLevel.getMainPlayer().getLocation().getYLocation() > myLevel.getHeight()) {
				myLevel.setLevelLost();
			}
			if (s instanceof Health) {
				if (!(((Health) s).isAlive())
						|| myLevel.getMainPlayer().getLocation().getYLocation() > myLevel.getHeight()) {
					myLevel.setLevelLost();
				}
			}
		}
	}

	//
	private void checkForWin() {
/*		count++;
		if(count==100){
			myLevel.setLevelWon();
		}*/
		for (State s : myLevel.getMainPlayer().getStates()) {
			if (s instanceof LevelWon) {
				if (((LevelWon) s).isHasWon()) {
					myLevel.setLevelWon();
				}
			}
		}
	}



	// keys will only control the main player rn

	private Map<KeyCode, Action> generateBoostedKeyPressedMap() {
		Map<KeyCode, Action> myKeyPressedMap = new HashMap<KeyCode, Action>();
		myKeyPressedMap.put(KeyCode.RIGHT, new MoveRight(myLevel.getMainPlayer(),
				GameResources.MOVE_RIGHT_SPEED.getDoubleResource() + GameResources.SPEED_BOOST.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.LEFT, new MoveLeft(myLevel.getMainPlayer(),
				GameResources.MOVE_LEFT_SPEED.getDoubleResource() + GameResources.SPEED_BOOST.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.UP, new MoveUpJump(myLevel.getMainPlayer(),
				GameResources.JUMP_SPEED.getDoubleResource() + GameResources.SPEED_BOOST.getDoubleResource()));
		return myKeyPressedMap;
	}

	public void generateDefaultKeyPressedMap() {
		
		Map<KeyCode, Action> myKeyPressedMap = new HashMap<KeyCode, Action>();
		myKeyPressedMap.put(KeyCode.RIGHT,
				new MoveRight(myLevel.getMainPlayer(), GameResources.MOVE_RIGHT_SPEED.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.LEFT,
				new MoveLeft(myLevel.getMainPlayer(), GameResources.MOVE_LEFT_SPEED.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.UP,
				new MoveUpJump(myLevel.getMainPlayer(), GameResources.JUMP_SPEED.getDoubleResource()));

	}

	private void executeCharacteristics() {
		for (Sprite mySprite : mySpriteList) {

			ListOfCollidingSprites collidingSprites = new ListOfCollidingSprites(mySprite, mySpriteList,
					mySpriteImages);
			Map<Sprite, Side> myCollisionMap = collidingSprites.getCollisionSpriteMap();
			Set<Characteristic> characteristics = mySprite.getCharacteristics();
			for (Characteristic myCharacteristic : characteristics) {
				myCharacteristic.execute(myCollisionMap);
			}
		}
	}

	// not the best design in the world but works for the time being

	/**
	 * Checking for win should just happen naturally while checking collisions
	 * (i.e. once something collides with winning object)
	 */
	/*
	 * private void checkForWin() { Set<String> type =
	 * enginePlayerController.getMyLevel().getWinType();
	 * if(type.contains("time")&& enginePlayerController.getMyLevel().getTime()
	 * > enginePlayerController.getMyLevel().getTimeToWin()){
	 * System.out.print("YOU WIN"); }
	 * 
	 * if(type.contains("score") &&
	 * enginePlayerController.getMyLevel().getMainPlayerSprite().getPoints() >
	 * enginePlaterController.getMyLevel().getPointsToWin()){
	 * System.out.println("YOU WIN"); }
	 * 
	 * if(type.contains("object") &&
	 * enginePlayerController.getMyLevel().getWinningSprite().getBoundsInLocal()
	 * .interects(enginePlayerController.getMyLevel().getMainPlayerSprite())){
	 * System.out.println("YOU WIN"); } }
	 */

	/*
	 * private void checkForWin(Sprite aSprite){ if(aSprite instanceof
	 * WinningObject){ System.out.println("Do win action???"); //Do somethin? }
	 * }
	 */

	/**
	 * Checking for loss also should happen naturally (or just have to check if
	 * the players health characteristic has health<0)
	 */
	/*
	 * private void checkForLoss() { Set<String> type =
	 * enginePlayerController.getMyLevel().getLossType(); Sprite mainPlayer =
	 * enginePlayerController.getMainPlayer(); if(type.contains("object")){
	 * List<Sprite> deathProvokingObj =
	 * enginePlayerController.getMyObjectSpriteList(); for(Sprite myObj :
	 * deathProvokingObj){
	 * if(enginePlayerController.getImageView(myObj).getBoundsInLocal().
	 * intersects(enginePlayerController.getImageView(mainPlayer).
	 * getBoundsInLocal())){ System.out.println("DEATH"); } } } }
	 */

	private void updateSpritePositions() {
		for (Sprite sprite : mySpriteList) {
			UpdateLocation updateLocation = new UpdateLocation(sprite, myTimeElapsed);
			updateLocation.updateSpriteParameters();
		}
	}
}