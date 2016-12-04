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
 * @author Addison, Cleveland Thompson, Austin
 */
public class Level extends GameObject{
	
	private boolean didLose, didWin;
	private int width, height;
	private String backgroundImageFilePath;
	private Player myPlayerSprite;
	Set<Sprite> mySprites;
	private List<Sprite>myControllableSpriteList=new ArrayList<Sprite>();;
	
	Map<KeyCode, KeyCommand> myKeyCommands;
	
	public Level(String aName, int width, int height, String backgroundImageFilePath){
		System.out.println("instantiating level");
		setName(aName);
		didLose = false;
		didWin = false;
		this.width = width;
		this.height = height;
		this.backgroundImageFilePath = backgroundImageFilePath;
		mySprites = new HashSet<Sprite>();
		myKeyCommands = new HashMap<KeyCode, KeyCommand>();
		myControllableSpriteList=new ArrayList<Sprite>();
		setMyControllableSpriteList();
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
		if(aSprite.getControllable().isControllable()){
			myControllableSpriteList.add(aSprite);
		}
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
	
	public void removeSprite(Sprite aSprite){
		if(mySprites.contains(aSprite)){
			mySprites.remove(aSprite);
			if(aSprite.getControllable().isControllable()){
				myControllableSpriteList.remove(aSprite);
			}
			this.notifyListeners();
		}
	}

	public List<Sprite> getMySpriteList() {

		return new ArrayList<>(mySprites);
	}
	public void setMyControllableSpriteList(){
		System.out.println("setting sprite list");
		List<Sprite> myControllableSpriteList = new ArrayList<Sprite>();
		//List<Sprite> mySpriteList = getMySpriteList();
		for(Sprite s: mySprites){
			if(s.getControllable()!=null && s.getControllable().isControllable()){
				myControllableSpriteList.add(s);
				System.out.println("adding mars");
			}
		}
		this.myControllableSpriteList=myControllableSpriteList;
	}
	public List<Sprite> getMyControllableSpriteList(){
		System.out.println("gettng the shit" + myControllableSpriteList.size());
		return myControllableSpriteList;
	}
	public void setLevelLost(){
		didLose = true;
	}
	
	public boolean lostLevel(){
		return didLose;
	}
	
	public void setLevelWon(){
		didWin = true;
	}
	
	public boolean wonLevel(){
		return didWin;
	}
}
