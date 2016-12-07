package gameplayer.back_end.stored_games;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import game_data.Game;

public class StoredGames {
	
	private Map<Game, String> myStoredGameNames;
	private Map<Game, File> myStoredGameFiles;
	
	public StoredGames() {
		myStoredGameNames = new HashMap<Game, String>(); 
		myStoredGameFiles = new HashMap<Game, File>();
	}
	
	public Collection<Game> getGames() {
		return myStoredGameNames.keySet();
	}
	
    public void addGame(Game aGame, String aGameName, File aGameFilePath) {
    	myStoredGameNames.put(aGame, aGameName);
    	myStoredGameFiles.put(aGame, aGameFilePath);
    }
    
    public String getGameName(Game aGame) {
    	return myStoredGameNames.get(aGame);
    }
    
    public File getGameFilePath(Game aGame) {
    	return myStoredGameFiles.get(aGame);
    }
}
