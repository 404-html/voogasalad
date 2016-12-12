/**
 * 
 */
package author.model;


import java.io.File;

import util.XMLTranslator;
import util.filehelpers.FolderListor;
import author.controller.IAuthorController;
import author.view.util.edit_window.GameEditWindowFactory;
import author.view.util.edit_window.IGameObjectEditWindowExternal;
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
	public void newGameWindow(){
		IGameObjectEditWindowExternal<Game> gameObjectEditWindowExternal = new GameEditWindowFactory().create();
		Game newGame = gameObjectEditWindowExternal.getResult();
		if(newGame != null){
			createNewGame(newGame.getName());
		}
	}

	@Override
	public Game getGame(){
		if (activeGame == null)
			this.activeGame = new Game("Unnamed_Game");
		return this.activeGame;
	}

	@Override
	public void createNewGame(String aName){
		this.activeGame = new Game(aName);
		this.authorController.reinitializeView();
		loadDefaultSprites();
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
		gameSaver.saveToFile(activeGame, "XMLGameFiles/", aFileName);
	}
	
	public void loadDefaultSprites() {
		try {
			FolderListor fl = new FolderListor("data/sprite/default_sprites/");
			for(String fileName : fl.getFilesWithExtension(".xml")) {
				if(fileName.contains(".DS_Store"))
					continue; // TODO: fix this temporary hack to avoid attempting to load this hidden mac generated file
				File aFile = new File(fileName + ".xml");
				XMLTranslator myLoader = new XMLTranslator();
				Sprite aSprite = (Sprite) myLoader.loadFromFile(aFile);
				this.authorController.getModel().getGame().addPreset(aSprite);
			}
		}
		catch (Exception exception){
			
		}
	}


}
