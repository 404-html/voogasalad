package gameplayer.back_end.stored_games;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

import game_data.Game;
import util.XMLTranslator;

public class StoredGames {
	
//	private Map<String, Game> myStoredGameNames;
	private Map<String, File> myStoredGameFiles;
	
	public StoredGames() {
//		myStoredGameNames = new HashMap<String, Game>(); 
		myStoredGameFiles = new HashMap<String, File>();
		getStoredGames();
	}
	
	private void getStoredGames(){
		try(Stream<Path> paths = Files.walk(Paths.get("XMLGameFiles"))) {
		    paths.forEach(filePath -> {
		        if (Files.isRegularFile(filePath) && filePath.toUri().toString().endsWith(".xml") && filePath.toUri().toString().contains("Working")) {
					XMLTranslator myTranslator = new XMLTranslator();
					Game myGame = (Game) myTranslator.loadFromFile(filePath.toFile());
		        	addGame(myGame, filePath.toFile());
		        }
		    });
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Collection<String> getGames() {
		return myStoredGameFiles.keySet();
	}
	
    private void addGame(Game aGame, File aGameFilePath) {
//    	myStoredGameNames.put(aGame.getName(), aGame);
    	myStoredGameFiles.put(aGame.getName(), aGameFilePath);
    }
    
    public File getGameFilePath(String aGamename) {
    	return myStoredGameFiles.get(aGamename);
    }
}
