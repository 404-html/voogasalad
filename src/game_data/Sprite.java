package game_data;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import game_data.characteristics.Characteristic;
import game_data.states.State;
import game_engine.GameResources;
import game_engine.actions.Action;

/**
 * Represents any viewable object in a Level including characters, items,
 * terrains, projectiles, etc.
 * 
 * @author Addison, Austin, Cleveland Thompson
 */
public abstract class Sprite extends GameObject {

	private Location myLocation;
	private Sprite preset;
	private int myWidth;
	private int myHeight;
	private String myImagePath;
	private double myXVelocity;
	private double myYVelocity;
	private double myXAcceleration;
	private double myYAcceleration;
	private double terminalXVel;
	private double terminalYVel;
	private CollisionHandler myCollisionHandler;
	private Set<Characteristic> myCharacteristics;
	private Controllable myControllable;
	private String id = "";
	private Map<Characteristic, Double> powerUps;

	private Set<State> myStates;
	
	public Sprite() {
		resetTerminalVelocities();
		myXVelocity = 0;
		myYVelocity = 0;
		myXAcceleration = 0;
		myYAcceleration = 0;
		myCollisionHandler = new CollisionHandler();
		myCharacteristics = new HashSet<Characteristic>();
		myStates = new HashSet<State>();
		myControllable= new Controllable(this);
	}
	

	public Sprite(Location aLocation, int aWidth, int aHeight, double xVelocity, double yVelocity, String aName, String aImagePath) {
		resetTerminalVelocities();
		myLocation = aLocation;
		myWidth = aWidth;
		myHeight = aHeight;
		setName(aName);
		myImagePath = aImagePath;
		myXVelocity = xVelocity;
		myYVelocity = yVelocity;
		myXAcceleration = 0;
		myYAcceleration = 0;
		myCollisionHandler = new CollisionHandler();
		myCharacteristics = new HashSet<Characteristic>();
		myStates = new HashSet<State>();
		myControllable=new Controllable(this);
	}

	// for copying sprites
	public Sprite(Sprite aSprite) {
		resetTerminalVelocities();
		preset = aSprite;
		myLocation = new Location(aSprite.getLocation().getXLocation(), aSprite.getLocation().getYLocation());
		myWidth = aSprite.getWidth();
		myHeight = aSprite.getHeight();
		setName(aSprite.getName());
		myImagePath = aSprite.getImagePath();
		myXVelocity = aSprite.getXVelocity();
		myYVelocity = aSprite.getYVelocity();
		myXAcceleration = aSprite.getXAcceleration();
		myYAcceleration = aSprite.getYAcceleration();
		myCollisionHandler = aSprite.getCollisionHandler(); // to change:
		myCharacteristics = copyCharacteristics(aSprite.getCharacteristics());
		myStates = copyStates(aSprite.getStates());
		myControllable=aSprite.getControllable();
	}

	/**
	 * should return a clone using the new Sprite(this) constructor
	 */
	public abstract Sprite clone();

	private Set<Characteristic> copyCharacteristics(Set<Characteristic> aCharacteristicSet) {
		if (aCharacteristicSet == null)
			return null;
		Set<Characteristic> characteristicCopies = new HashSet<Characteristic>();
		for (Characteristic c : aCharacteristicSet) {
			characteristicCopies.add(c.copy());
		}
		return characteristicCopies;
	}

	private Set<State> copyStates(Set<State> aStateSet) {
		if (aStateSet == null)
			return null;
		Set<State> stateCopies = new HashSet<State>();
		for (State c : aStateSet) {
			stateCopies.add(c.copy());
		}
		return stateCopies;
	}


	public void setControllable(Controllable control){
		myControllable=control;
	}

	public Controllable getControllable(){
		return myControllable;
	}

	public Set<Characteristic> getCharacteristics() {
		return myCharacteristics;
	}

	public void addCharacteristic(Characteristic aCharacteristic) {
		myCharacteristics.add(aCharacteristic);
		notifyListeners();
	}

	public void removeCharacteristic(Characteristic aCharacteristic) {
		if (myCharacteristics.contains(aCharacteristic))
			myCharacteristics.remove(aCharacteristic);
	}

	public Set<State> getStates() {
		return myStates;
	}

	public void addState(State aState) {
		myStates.add(aState);
		notifyListeners();
	}

	public void removeState(State aState) {
		if (myStates.contains(aState))
			myStates.remove(aState);
	}

	public Location getLocation() {
		return myLocation;
	}

	public void setLocation(Location myLocation) {
		this.myLocation = myLocation;
		notifyListeners();
	}

	public double getXVelocity() {
		return myXVelocity;
	}

	public double getYVelocity() {
		return myYVelocity;
	}

	public void setXVelocity(double myVelocity) {
		//		System.out.println("TERMINAL X " + terminalXVel);
		if (Math.abs(myVelocity) > terminalXVel) {
			this.myXVelocity = (myVelocity/Math.abs(myVelocity))*terminalXVel;
		}
		else{
			this.myXVelocity = myVelocity;
		}
		notifyListeners();
	}

	public void setYVelocity(double myVelocity) {
		if (Math.abs(myVelocity) > terminalYVel) {
			this.myYVelocity = (myVelocity/Math.abs(myVelocity))*terminalYVel;
		}
		else{
			this.myYVelocity = myVelocity;
		}
		notifyListeners();
	}

	public double getXAcceleration() {
		return myXAcceleration;
	}

	public void setXAcceleration(double myXAcceleration) {
		this.myXAcceleration = myXAcceleration;
	}

	public double getYAcceleration() {
		return myYAcceleration;
	}

	public void setYAcceleration(double myYAcceleration) {
		this.myYAcceleration = myYAcceleration;
	}

	public String getImagePath() {
		return myImagePath;
	}

	public void setImagePath(String myImagePath) {
		this.myImagePath = myImagePath;
		notifyListeners();
	}

	public CollisionHandler getCollisionHandler() {
		return myCollisionHandler;
	}

	public void setCollisionHandler(CollisionHandler myCollisionHandler) {
		this.myCollisionHandler = myCollisionHandler;
		notifyListeners();
	}

	public void setId(String id) {
		this.id = id;
		notifyListeners();
	}

	public String getId() {
		return id;
	}

	public int getWidth() {
		return myWidth;
	}

	public void setWidth(int myWidth) {
		this.myWidth = myWidth;
		notifyListeners();
	}

	public int getHeight() {
		return myHeight;
	}

	public void setHeight(int myHeight) {
		this.myHeight = myHeight;
		notifyListeners();
	}

	public Sprite getPreset() {
		return this.preset;
	}

	public void setPreset(Sprite aPreset){
		this.preset = aPreset;
		notifyListeners();
	}

	public double getTerminalXVel() {
		return terminalXVel;
	}
	public void setTerminalXVel(double terminalXVel) {
		this.terminalXVel = terminalXVel;
	}
	public double getTerminalYVel() {
		return terminalYVel;
	}
	public void setTerminalYVel(double terminalYVel) {
		this.terminalYVel = terminalYVel;
	}
	public void resetTerminalVelocities(){
		//		System.out.println("LUCIA");
		this.terminalXVel = GameResources.TERMINAL_X_VELOCITY.getDoubleResource();
		this.terminalYVel = GameResources.TERMINAL_Y_VELOCITY.getDoubleResource();
	}

	public Map<Characteristic, Double> getPowerUps() {
		if(powerUps == null) return new HashMap<Characteristic, Double>();
		return powerUps;
	}

	public void setPowerUps(Map<Characteristic, Double> powerUps){
		this.powerUps = powerUps;
	}
}
