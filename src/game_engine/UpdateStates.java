package game_engine;

import java.awt.Image;

import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import game_data.Controllable;
import game_data.Level;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.InvincibilityPowerUpper;
import game_data.characteristics.SpeedPowerUpper;
import game_data.sprites.Enemy;
import game_data.sprites.Player;
import game_data.states.Health;
import game_data.states.LevelWon;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.actions.Action;
import game_engine.actions.Launch;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import game_engine.actions.MoveUpFly;
import game_engine.actions.MoveUpJump;
import game_engine.actions.SpeedBoost;
import game_engine.actions.StopLeftMovement;
import game_engine.actions.StopRightMovement;
import game_engine.actions.StopUpMovement;
import javafx.scene.Node;
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

	private Level myLevel;
	private List<Sprite> mySpriteList;
	private double timeElapsed;
	private KeyCode myKey;
	private Map<KeyCode, Action> myKeyPressedMap;
	private Set<KeyCode> myKeysPressed;
	private Set<KeyCode> myKeysReleased;
	private Map<Sprite, ImageView> mySpriteImages;
	private Map<KeyCode, Action> myKeyReleasedMap;
	private double myScreenWidth, myScreenHeight, myScreenXPosition, myScreenYPosition;

	private Map<Characteristic, Double> myCurrentPowerUps;
	private Controllable mainPlayerControllable;

	private List<Sprite> myControllableSpriteList;

	/*public UpdateStates(Level aLevel, double timeElapsed, Set<KeyCode> myKeysPressed, Set<KeyCode> myKeysReleased,
			Map<Sprite, ImageView> map, double aScreenHeight, double aScreenWidth, double aScreenXPosition, double aScreenYPosition) {
		this.myLevel = aLevel;
		this.myCurrentPowerUps = myLevel.getMainPlayer().getPowerUps();
		this.mySpriteList = myLevel.getMySpriteList();
		//System.out.println("mySpriteList is being updated"+mySpriteList.size());

		this.timeElapsed = timeElapsed;
		this.myKeysPressed = myKeysPressed;


		this.myKeysReleased=myKeysReleased;
		this.mySpriteImages=map;
		this.myKeyPressedMap = new HashMap<KeyCode, Action>();
		this.myKeyReleasedMap = new HashMap<KeyCode, Action>();

		this.myKeysReleased = myKeysReleased;
		this.mySpriteImages = mySpriteImages;
		this.myKeyPressedMap = new HashMap<KeyCode, Action>();
		this.myKeyReleasedMap = new HashMap<KeyCode, Action>();

		myControllableSpriteList = new ArrayList<Sprite>();
		this.myControllableSpriteList = myLevel.getMyControllableSpriteList();
		this.mainPlayerControllable = myLevel.getMainPlayer().getControllable();

		//generateDefaultKeyPressedMap();
		activatePowerUps();
		checkPowerUps();
		executeControls();
		executeCharacteristics();
		cleanGame();
		//updateSpritePositions();

//		System.out.println("xvel " + myLevel.getMainPlayer().getXVelocity());
//		System.out.println("yvel " + myLevel.getMainPlayer().getYVelocity());		
//		System.out.println("xtermvel " + myLevel.getMainPlayer().getTerminalXVel());
//		System.out.println("ytermvel " + myLevel.getMainPlayer().getTerminalYVel());
	}*/
	
	public UpdateStates(Level aLevel, double timeElapsed, Set<KeyCode> myKeysPressed, Set<KeyCode> myKeysReleased,
			Map<Sprite, ImageView> mySpriteImages, double aScreenHeight, double aScreenWidth, double aScreenXPosition, double aScreenYPosition) {
			this.myLevel = aLevel;
			this.myCurrentPowerUps = myLevel.getMainPlayer().getPowerUps();
			this.mySpriteList = myLevel.getMySpriteList();
			this.timeElapsed = timeElapsed;
			this.myKeysPressed = myKeysPressed;
			this.myKeysReleased = myKeysReleased;
			this.mySpriteImages = mySpriteImages;
			this.myKeyPressedMap = new HashMap<KeyCode, Action>();
			this.myKeyReleasedMap = new HashMap<KeyCode, Action>();
            this.myScreenWidth = aScreenWidth;
            this.myScreenHeight = aScreenHeight;
            this.myScreenXPosition = aScreenXPosition;
            this.myScreenYPosition = aScreenYPosition;
			myControllableSpriteList = new ArrayList<Sprite>();
			this.myControllableSpriteList = myLevel.getMyControllableSpriteList();
			this.mainPlayerControllable = myLevel.getMainPlayer().getControllable();

			//generateDefaultKeyPressedMap();
			activatePowerUps();
			checkPowerUps();
			executeControls();
			executeCharacteristics();
			cleanGame();
			//updateSpritePositions();

			//System.out.println("xvel " + myLevel.getMainPlayer().getXVelocity());
			//System.out.println("yvel " + myLevel.getMainPlayer().getYVelocity());
			//System.out.println("xtermvel " + myLevel.getMainPlayer().getTerminalXVel());
			//System.out.println("ytermvel " + myLevel.getMainPlayer().getTerminalYVel());
			}

	private void activatePowerUps() {
		for (Characteristic powerUp : myCurrentPowerUps.keySet()) {
			if (powerUp instanceof SpeedPowerUpper) {
				((SpeedPowerUpper) powerUp).activatePowerUp(myLevel.getMainPlayer(), this, myCurrentPowerUps.get(powerUp));
			}
			if(powerUp instanceof InvincibilityPowerUpper){
				((InvincibilityPowerUpper) powerUp).activatePowerUp(myLevel.getMainPlayer(), this, myCurrentPowerUps.get(powerUp));	
			}

		}
	}

	public void setKeyPressedMapWithBoosts() {
		mainPlayerControllable.setMyKeyPressedMap(generateBoostedKeyPressedMap());
	}

	private void checkPowerUps() {
		for (Characteristic powerUp : myCurrentPowerUps.keySet()) {
//			System.out.println("POWER UP SPEED " + myCurrentPowerUps.get(powerUp));
			myCurrentPowerUps.put(powerUp, myCurrentPowerUps.get(powerUp) - 1);
			if (myCurrentPowerUps.get(powerUp) <= 0) {
//				System.out.println("HELLOOOOOO");
				myCurrentPowerUps.remove(powerUp);
				powerUpHasBeenRemoved(powerUp);
			}
		}
	}

	//IS THERE A BETTER WAY TO DO THIS WITHOUT IFS?
	private void powerUpHasBeenRemoved(Characteristic powerUp) {
		if (powerUp instanceof SpeedPowerUpper) {
			((SpeedPowerUpper) powerUp).reversePowerUp(myLevel.getMainPlayer(), this);
		}
		if(powerUp instanceof InvincibilityPowerUpper){
			((InvincibilityPowerUpper) powerUp).reversePowerUp(myLevel.getMainPlayer(), this);
		}
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
			Controllable control;
			if(mySprite instanceof Player)
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
			if(mySprite.getMyRandomMoveHandler() != null) {
				mySprite.getMyRandomMoveHandler().move(mySprite,myScreenWidth,myScreenHeight,myScreenXPosition,myScreenYPosition);
			}
			if(mySprite.getMyRandomMoveConjointHandler() != null) {
				mySprite.getMyRandomMoveConjointHandler().move(mySprite,myScreenWidth,myScreenHeight,myScreenXPosition,myScreenYPosition);
			}
		}
	}
	
	private void checkForLoss() {
		for (State s : myLevel.getMainPlayer().getStates()) {
			if (s instanceof Health) {
				if (!(((Health) s).isAlive())) {
						//|| myLevel.getMainPlayer().getLocation().getYLocation() > myLevel.getHeight()) {
					myLevel.setLevelLost();
				}
			}
		}
	}

	//
	private void checkForWin() {
		for (State s : myLevel.getMainPlayer().getStates()) {
			if (s instanceof LevelWon) {
				if (((LevelWon) s).isHasWon()) {
					myLevel.setLevelWon();
				}
			}
		}
	}



	// keys will only control the main player rn
	
	private Map<KeyCode,Action> generateBoostedKeyPressedMap(){
		myKeyPressedMap.put(KeyCode.RIGHT,
				new MoveRight(myLevel.getMainPlayer(), GameResources.MOVE_RIGHT_SPEED.getDoubleResource()
						+ GameResources.SPEED_BOOST.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.LEFT,
				new MoveLeft(myLevel.getMainPlayer(), GameResources.MOVE_LEFT_SPEED.getDoubleResource()
						+ GameResources.SPEED_BOOST.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.UP, new MoveUpJump(myLevel.getMainPlayer(),
				GameResources.JUMP_SPEED.getDoubleResource() + GameResources.SPEED_BOOST.getDoubleResource()));
		return myKeyPressedMap;
	}
	
	public void generateDefaultKeyPressedMap() {
	
		myKeyPressedMap.put(KeyCode.RIGHT,
				new MoveRight(myLevel.getMainPlayer(), GameResources.MOVE_RIGHT_SPEED.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.LEFT,
				new MoveLeft(myLevel.getMainPlayer(), GameResources.MOVE_LEFT_SPEED.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.UP,
				new MoveUpJump(myLevel.getMainPlayer(), GameResources.JUMP_SPEED.getDoubleResource()));

	}

	private void generateDefaultKeyReleasedMap() {
		myKeyReleasedMap.put(KeyCode.RIGHT,
				new StopRightMovement(myLevel.getMainPlayer(), GameResources.MOVE_RIGHT_SPEED.getDoubleResource()));
		myKeyReleasedMap.put(KeyCode.LEFT,
				new StopLeftMovement(myLevel.getMainPlayer(), GameResources.MOVE_LEFT_SPEED.getDoubleResource()));
		// myKeyReleasedMap.put(KeyCode.UP, new
		// StopUpMovement(myLevel.getMainPlayer(),
		// GameResources.JUMP_SPEED.getDoubleResource()));
	}

	private void runKeyCalls() {
		for (KeyCode myKey : myKeysPressed) {
			if (myKeyPressedMap.containsKey(myKey)) {
				myKeyPressedMap.get(myKey).act();
			}
		}
	}

	private void runKeyReleased() {
		for (KeyCode myKey : myKeysReleased) {
			if (myKeyReleasedMap.containsKey(myKey)) {
				myKeyReleasedMap.get(myKey).act();
			}
		}
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
			UpdateLocation updateLocation = new UpdateLocation(sprite, timeElapsed);
			updateLocation.updateSpriteParameters();
		}
	}
}