package gameplayer.back_end.stored_games;

import java.util.ArrayList;
import java.util.List;

import game_data.Game;

public class StoredGames {
	
	private List<Game> myStoredGames;
	
	public StoredGames() {
		myStoredGames = new ArrayList<Game>(); 
	}
	
	public List<Game> getGames() {
		return myStoredGames;
	}
	
    public void addGame(Game aGame) {
    	myStoredGames.add(aGame);
    }

}
