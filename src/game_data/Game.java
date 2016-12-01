package game_data;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import util.XMLTranslator;

/**
 * A Game has a List of Levels. Each Level may contain Sprites and other
 * elements. A Game can be saved to a serialized XML file to be loaded
 * elsewhere.
 * 
 * @author Addison, Cleveland Thompson
 *
 */
public class Game extends GameObject {
	
	/*
	 * description
	 * icon
	 */

	List<Level> myLevels;
	Set<Sprite> spritePresets = new HashSet<>();
	Level currentLevel;

	public Game(String aName) {
		setName(aName);
		myLevels = new ArrayList<Level>();
	}

	/**
	 * Saves Game, Levels, and all states to a serialized XML File
	 * 
	 * @param filePath
	 * @param fileName
	 * @author Addison
	 */
	public void saveGameAs(String filePath, String fileName) {
		java.io.FileWriter fw;
		try {
			fw = new java.io.FileWriter(filePath + fileName + ".xml");
			fw.write(((new XMLTranslator()).serialize(this)));
			fw.close();
		} catch (IOException e) {
			System.out.println("Trouble printing XML to file");
		}
	}

	public void addNewLevel(Level aLevel) {
		this.currentLevel = aLevel;
		myLevels.add(aLevel);
		this.notifyListeners();
	}

	public List<Level> getLevels() {
		return myLevels;
	}

	public void setCurrentLevel(Level aLevel) {
		this.currentLevel = aLevel;
		this.notifyListeners();
	}

	public void addPreset(Sprite aPresetSprite) {
		this.spritePresets.add(aPresetSprite);
		this.notifyListeners();
	}

	public Set<Sprite> getPresets() {
		return Collections.unmodifiableSet(this.spritePresets);
	}

	public List<Level> getMyLevels() {
		return myLevels;
	}

	public void setCurrentLevel(int levelNumber) {
		currentLevel = myLevels.get(levelNumber);
		this.notifyListeners();
	}

	public Level getCurrentLevel() {
		return currentLevel;
	}

	public void removeLevel(Level aLevel){
		if(myLevels.contains(aLevel))
			myLevels.remove(aLevel);
		this.notifyListeners();
	}
	
	public void removeLevel(int levelNumber){
		if(myLevels.size() > levelNumber)
			myLevels.remove(levelNumber);
		this.notifyListeners();
	}


}
