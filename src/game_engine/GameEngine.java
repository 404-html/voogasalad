package game_engine;

import java.io.File;

import game_data.Game;
import util.XMLTranslator;

public class GameEngine {
	private Game myGame;
	private EnginePlayerController myEnginePlayerController;

	// public GameEngine(IEngineReceiverControllerInterface myInterface) {
	public GameEngine(File aFile) {
		makeGameFromXML(aFile);
		myEnginePlayerController = new EnginePlayerController(myGame);
	}

	public GameEngine(File aFile, int levelNumber) {
		makeGameFromXML(aFile);
		myGame.setCurrentLevel(levelNumber);
		myEnginePlayerController = new EnginePlayerController(myGame);
	}

	private void makeGameFromXML(File aFile) {
		XMLTranslator myTranslator = new XMLTranslator();
		myGame = (Game) myTranslator.loadFromFile(aFile);
	}

	public EnginePlayerController getMyEnginePlayerController() {
		return myEnginePlayerController;
	}

	public void setEnginePlayerController(EnginePlayerController enginePlayerController) {
		myEnginePlayerController = enginePlayerController;
	}
}
