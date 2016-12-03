package game_data;

import java.util.HashSet;
import java.util.Set;

import game_data.characteristics.Characteristic;
import game_data.states.State;

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
	private CollisionHandler myCollisionHandler;
	private Set<Characteristic> myCharacteristics;
	private String id = "";
	
	private Set<State> myStates;
	
	public Sprite(Location aLocation, int aWidth, int aHeight, String aName, String aImagePath){
		myLocation = aLocation;
		myWidth = aWidth;
		myHeight = aHeight;
		setName(aName);
		myImagePath = aImagePath;
		myXVelocity = 0;
		myYVelocity = 0;
		myXAcceleration = 0;
		myYAcceleration = 0;
		myCollisionHandler = new CollisionHandler();
		myCharacteristics = new HashSet<Characteristic>();
		myStates = new HashSet<State>();
	}
	
	//for copying sprites
	public Sprite(Sprite aSprite){
		preset = aSprite;
		myLocation = new Location(aSprite.getMyLocation().getXLocation(),
				aSprite.getMyLocation().getYLocation(), aSprite.getMyLocation().getMyHeading());
		myWidth = aSprite.getMyWidth();
		myHeight = aSprite.getMyHeight();
		setName(aSprite.getName());
		myImagePath = aSprite.getMyImagePath();
		myXVelocity = aSprite.getMyXVelocity();
		myYVelocity = aSprite.getMyYVelocity();
		myXAcceleration = aSprite.getMyXAcceleration();
		myYAcceleration = aSprite.getMyYAcceleration();
		myCollisionHandler = aSprite.getMyCollisionHandler(); //to change: would need to have the same collision handler but we don't know what that is yet
		myCharacteristics = copyCharacteristics(aSprite.getCharacteristics());
		myStates = copyStates(aSprite.getStates());
	}

	/**
	 * should return a clone using the new Sprite(this) constructor
	 */
	public abstract Sprite clone();
	private Set<Characteristic> copyCharacteristics(Set<Characteristic> aCharacteristicSet){
		if (aCharacteristicSet == null)
			return null;
		Set<Characteristic> characteristicCopies = new HashSet<Characteristic>();
		for(Characteristic c: aCharacteristicSet){
			characteristicCopies.add(c.copy());
		}
		return characteristicCopies;
	}
	
	private Set<State> copyStates(Set<State> aStateSet){
		if (aStateSet == null)
			return null;
		Set<State> stateCopies = new HashSet<State>();
		for(State c: aStateSet){
			stateCopies.add(c.copy());
		}
		return stateCopies;	
	}
	
	public Set<Characteristic> getCharacteristics(){
		return myCharacteristics;
	}

	public void addCharacteristic(Characteristic aCharacteristic) {
		myCharacteristics.add(aCharacteristic);
		notifyListeners();
	}
	
	public void removeCharacteristic(Characteristic aCharacteristic){
		if (myCharacteristics.contains(aCharacteristic))
			myCharacteristics.remove(aCharacteristic);
	}
	
	public Set<State> getStates(){
		return myStates;
	}
	
	public void addState(State aState){
		myStates.add(aState);
		notifyListeners();
	}
	
	public void removeState(State aState){
		if (myStates.contains(aState))
			myStates.remove(aState);
	}
	
	public Location getMyLocation() {
		return myLocation;
	}

	public void setMyLocation(Location myLocation) {
		this.myLocation = myLocation;
		notifyListeners();
	}
	public double getMyXVelocity() {
		return myXVelocity;
	}
	public double getMyYVelocity() {
		return myYVelocity;
	}
	public void setMyXVelocity(double myVelocity) {
		this.myXVelocity = myVelocity;
		notifyListeners();
	}
	public void setMyYVelocity(double myVelocity){
		this.myYVelocity = myVelocity;
		notifyListeners();
	}
	
	public double getMyXAcceleration() {
		return myXAcceleration;
	}

	public void setMyXAcceleration(double myXAcceleration) {
		this.myXAcceleration = myXAcceleration;
	}

	public double getMyYAcceleration() {
		return myYAcceleration;
	}

	public void setMyYAcceleration(double myYAcceleration) {
		this.myYAcceleration = myYAcceleration;
	}


	public String getMyImagePath() {
		return myImagePath;
	}

	public void setMyImagePath(String myImagePath) {
		this.myImagePath = myImagePath;
		notifyListeners();
	}

	public CollisionHandler getMyCollisionHandler() {
		return myCollisionHandler;
	}

	public void setMyCollisionHandler(CollisionHandler myCollisionHandler) {
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

	public int getMyWidth() {
		return myWidth;
	}

	public void setMyWidth(int myWidth) {
		this.myWidth = myWidth;
		notifyListeners();
	}

	public int getMyHeight() {
		return myHeight;
	}

	public void setMyHeight(int myHeight) {
		this.myHeight = myHeight;
		notifyListeners();
	}
	
	public Sprite getPreset(){
		return this.preset;
	}
	
	public void setPreset(Sprite aPreset){
		this.preset = aPreset;
		notifyListeners();
	}

}
