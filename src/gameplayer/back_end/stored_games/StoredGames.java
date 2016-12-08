package gameplayer.back_end.stored_games;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;
import game_data.Game;
import util.XMLTranslator;

public class StoredGames {
	
	private Map<String, File> myStoredGameFiles;
	private Map<String, String> myStoredGameIcons;
	
	public StoredGames() {
		myStoredGameFiles = new HashMap<String, File>();
		myStoredGameIcons = new HashMap<String, String> ();
		getStoredGames();
	}
	
	private void getStoredGames() {
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
	
	public List<String> getGames() {
		return new ArrayList<>(myStoredGameFiles.keySet());
	}
	
	public List<String> getIcons(){
		List<String> list = new ArrayList<>(myStoredGameIcons.values());
		list.add("data/gui/clip_art_hawaiian_flower.png");
		return list;
	}
	
    private void addGame(Game aGame, File aGameFilePath) {
    	myStoredGameFiles.put(aGame.getName(), aGameFilePath);
//    	myStoredGameIcons.put(aGame.getName(), aGame.getIcon());
    }
    
    public File getGameFilePath(String aGamename) {
    	return myStoredGameFiles.get(aGamename);
    }
}
