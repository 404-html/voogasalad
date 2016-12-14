package game_engine;

import game_data.Controllable;
import game_data.Level;
import game_data.LevelSetter;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.TemporalPowerUpper;
import game_data.sprites.Item;
import game_data.sprites.Player;
import game_data.sprites.Projectile;
import game_data.states.Health;
import game_data.states.LevelWon;
import game_data.states.State;
import game_engine.actions.Action;
import game_engine.actions.Launch;
import game_engine.actions.LaunchProxyHorizontal;
import game_engine.actions.LaunchProxyVertical;
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
	private KeyCode launchCodeHorizontal;
	private KeyCode launchCodeVertical;
	private boolean horizontalLaunchWasPressed;
	private boolean verticalLaunchWasPressed;
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
		launchCodeHorizontal=null;
		launchCodeVertical=null;
		setHorizontalLaunchCode();
		setVerticalLaunchCode();
		horizontalLaunchWasPressed=false;
		verticalLaunchWasPressed=false;
	}
	public void update(double aTimeElapsed, Set<KeyCode> aKeysPressed, Set<KeyCode> aKeysReleased, Map<Sprite, ImageView> aSpriteImages, double aScreenHeight, double aScreenWidth, double aScreenXPosition, double aScreenYPosition){
		myScreenWidth = aScreenWidth;
        myScreenHeight = aScreenHeight;
        myScreenXPosition = aScreenXPosition;
        myScreenYPosition = aScreenYPosition;
		myTimeElapsed=aTimeElapsed;
		setKeysWithoutLaunchKeyHeldDown(aKeysPressed);
		setKeysReleased(aKeysReleased);
		mySpriteImages=aSpriteImages;
		myCurrentPowerUps = myLevel.getMainPlayer().getPowerUps();
		mySpriteList = myLevel.getMySpriteList();
		myControllableSpriteList = myLevel.getMyControllableSpriteList();
		mainPlayerControllable = myLevel.getMainPlayer().getControllable();	
		setLevel();
		activatePowerUps();
		checkPowerUps();
		executeControls();
		executeCharacteristics();
		cleanGame();
		setHorizontalLaunchWasPressed(aKeysPressed);
		setVerticalLaunchWasPressed(aKeysPressed);
	}
	private void setHorizontalLaunchCode(){
		Map<KeyCode, Action> actionMap = myLevel.getMainPlayer().getControllable().getMyKeyPressedMap();
		for(KeyCode key: actionMap.keySet()){
			if(actionMap.get(key) instanceof LaunchProxyHorizontal){
				launchCodeHorizontal=key;
			}
		}
	}
	private void setVerticalLaunchCode(){
		Map<KeyCode, Action> actionMap = myLevel.getMainPlayer().getControllable().getMyKeyPressedMap();
		for(KeyCode key: actionMap.keySet()){
			if(actionMap.get(key) instanceof LaunchProxyVertical){
				launchCodeVertical=key;
			}
		}
	}
	private void setHorizontalLaunchWasPressed(Set<KeyCode> aKeysPressed){
		horizontalLaunchWasPressed=false;
		for (KeyCode key: aKeysPressed){
			if(key==launchCodeHorizontal){
				horizontalLaunchWasPressed=true;
			}
		}
	}
	private void setVerticalLaunchWasPressed(Set<KeyCode> aKeysPressed){
		verticalLaunchWasPressed=false;
		for (KeyCode key: aKeysPressed){
			if(key==launchCodeVertical){
				verticalLaunchWasPressed=true;
			}
		}
	}
	private void setKeysWithoutLaunchKeyHeldDown(Set<KeyCode> currentKeysPressed){
		myKeysPressed=new HashSet<KeyCode>();
		if(launchCodeHorizontal==null && launchCodeVertical==null){
			myKeysPressed=currentKeysPressed;
		}	
		else{
			for (KeyCode key: currentKeysPressed){
				if(!(key==launchCodeHorizontal && horizontalLaunchWasPressed)){
					if(!(key==launchCodeVertical && verticalLaunchWasPressed)){
						myKeysPressed.add(key);
					}
				}
			}
		}
	}
	private void setLevel(){
		for(Sprite sprite: mySpriteList){
			for(KeyCode key: sprite.getControllable().getMyKeyPressedMap().keySet()){
				Action value = sprite.getControllable().getMyKeyPressedMap().get(key);
				if(value instanceof LevelSetter){
					((LevelSetter) value).setLevel(myLevel);
					
				}
			}
		}
	}
	private void setKeysReleased(Set<KeyCode> aKeysReleased){
		myKeysReleased=aKeysReleased;
	}
	public Level getLevel(){
		return myLevel;
	}
	private void activatePowerUps() {
		for (Characteristic powerUp : myCurrentPowerUps.keySet()) {
				((TemporalPowerUpper) powerUp).activatePowerUp(myLevel.getMainPlayer(), this,
						myCurrentPowerUps.get(powerUp));
		}
	}

	private void checkPowerUps() {
		ArrayList<Characteristic> toRemove = new ArrayList<Characteristic>();
		for (Characteristic powerUp : myCurrentPowerUps.keySet()) {
			myCurrentPowerUps.put(powerUp, myCurrentPowerUps.get(powerUp) - 1);
			if (myCurrentPowerUps.get(powerUp) <= 0) {
				toRemove.add(powerUp);
			}
		}
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
		for (State s : myLevel.getMainPlayer().getStates()) {
			if (s instanceof LevelWon) {
				if (((LevelWon) s).isHasWon()) {
					myLevel.setLevelWon();
				}
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

	private void updateSpritePositions() {
		for (Sprite sprite : mySpriteList) {
			UpdateLocation updateLocation = new UpdateLocation(sprite, myTimeElapsed);
			updateLocation.updateSpriteParameters();
		}
	}
	@Override
	public void generateDefaultKeyPressedMap() {
		// TODO Auto-generated method stub
		
	}
}