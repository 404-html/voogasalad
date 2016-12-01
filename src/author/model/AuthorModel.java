/**
 * 
 */
package author.model;


import java.io.File;

import util.XMLTranslator;
import author.controller.IAuthorController;
import game_data.Game;
import game_data.Level;
import game_data.Sprite;

/**
 * @author Cleveland Thompson V (ct168), Addison Howenstine
 *
 */
public abstract class AuthorModel implements IAuthorModel{

	private IAuthorController authorController;
	
	private Game activeGame;
	private Level activeLevel;
	
	
	public AuthorModel(IAuthorController aAuthorController) {
		this.authorController = aAuthorController;
	}
	
	@Deprecated
	public Level addLevel(int aWidth, int aHeight, String aBackgroundImageFilePath){
		this.activeLevel = new Level("Level 1", aWidth, aHeight, aBackgroundImageFilePath);
		this.activeGame.addNewLevel(this.activeLevel);
		return this.activeLevel;
	}
	
	@Deprecated
	public Sprite addSprite(Sprite aSpritePreset){
		Sprite createdSprite = aSpritePreset.clone();
		this.activeLevel.addNewSprite(createdSprite);
		return createdSprite;
	}
	
	@Override
	public void newGame(){
		this.activeGame = new Game("Mario");
	}
	
	@Override
	public Game getGame(){
		if (activeGame == null)
			newGame();
		return this.activeGame;
	}
	
	@Override
	public void loadGame(File aFile){
		XMLTranslator gameLoader = new XMLTranslator();
		this.activeGame = (Game) gameLoader.deserialize(aFile);
		this.authorController.reinitializeView();
		this.activeGame.setName(this.activeGame.getName());
	}
	
	@Override
	public void saveGame(String aFileName){
		XMLTranslator gameSaver = new XMLTranslator();
		gameSaver.saveToFile(activeGame, "XMLGameFiles/", activeGame.getName() + "_" + aFileName);
	}

}
