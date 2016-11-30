package game_data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import game_data.sprites.Player;
import javafx.scene.input.KeyCode;

/**
 * Represents a level of a Game. Has a List of Sprites
 * active on that Level as well as a background image and
 * Level specific KeyCommands
 * 
<<<<<<< HEAD
 * @author Addison, Cleveland Thompson
=======
 * @author Addison and Austin
>>>>>>> staging
 */
public class Level extends GameObject{
	
	private int width, height;
	private String backgroundImageFilePath;
	private Player myPlayerSprite;
	Set<Sprite> mySprites;
	Map<KeyCode, KeyCommand> myKeyCommands;
	private boolean isLevelLost;
	private boolean isLevelWon;
	
	public Level(String aName, int width, int height, String backgroundImageFilePath){
		setName(aName);
		this.width = width;
		this.height = height;
		isLevelLost=false;
		isLevelWon=false;
		this.backgroundImageFilePath = backgroundImageFilePath;
		mySprites = new HashSet<Sprite>();
		myKeyCommands = new HashMap<KeyCode, KeyCommand>();
		
	}
	
	public Player getMainPlayer(){
		return myPlayerSprite;
	}
	
	public void setPlayerSprite(Player aPlayer){
		myPlayerSprite = aPlayer;
		notifyListeners();
	}
	
	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
		this.notifyListeners();
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
		this.notifyListeners();
	}

	public void setBackgroundImageFilePath(String backgroundImageFilePath){
		this.backgroundImageFilePath = backgroundImageFilePath;
		this.notifyListeners();
	}
	
	public String getBackgroundImageFilePath(){
		return backgroundImageFilePath;
	}
	
	public void addNewSprite(Sprite  aSprite){
		mySprites.add(aSprite);
		this.notifyListeners();
	}
	
	public void setKeyCommand(KeyCode aKeyCode, KeyCommand aKeyCommand){
		myKeyCommands.put(aKeyCode, aKeyCommand);
		this.notifyListeners();
	}
	
	public void deleteKeyCommand(KeyCode aKeyCode){
		myKeyCommands.remove(aKeyCode);
		this.notifyListeners();
	}
	//Big Question: how do you know what is "currently selected"
	
	public void removeSprite(Sprite aSprite){
		if(mySprites.contains(aSprite)){
			mySprites.remove(aSprite);
			this.notifyListeners();
		}
	}

	public List<Sprite> getMySpriteList() {
		return new ArrayList<>(mySprites);
	}
	public void setLevelLost(boolean lost){
		isLevelLost=lost;
	}
	public void setLevelWon(boolean won){
		isLevelWon=won;
	}
	public boolean isLevelLost(){
		return isLevelLost;
	}
	public boolean isLevelWon(){
		return isLevelWon;
	}

}
