package gameplayer.back_end.stored_games;

import java.io.File;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import game_data.Game;

public class StoredGames {
	
	//private Map<String, Game> myStoredGameNames;
	private Map<String, File> myStoredGameFiles;
	
	public StoredGames() {
		//myStoredGameNames = new HashMap<String, Game>(); 
		myStoredGameFiles = new HashMap<String, File>();
	}
	
	public Collection<String> getGameNames() {
		return myStoredGameFiles.keySet();
	}
	
    public void addGame(String aGameName, File aGameFilePath) {
    	//myStoredGameNames.put(aGameName, aGame);
    	myStoredGameFiles.put(aGameName, aGameFilePath);
    }
    
    public File getGameFilePath(String aGameName) {
    	return myStoredGameFiles.get(aGameName);
    }
    
}
