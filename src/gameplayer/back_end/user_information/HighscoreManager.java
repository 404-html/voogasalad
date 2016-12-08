package gameplayer.back_end.user_information;

import java.util.HashMap;
import java.util.Map;

import game_data.Game;

public class HighscoreManager {

	private Map<Game, HashMap<String, Double>> myScores;
	
	public HighscoreManager() {
		myScores = new HashMap<Game, HashMap<String, Double>>();
	}
	
	public void setHighscore(String aUsername, double aScore, Game aGame) {
		if(myScores.containsKey(aGame)){
			HashMap<String, Double> map = myScores.get(aGame);
			map.put(aUsername, aScore);
			myScores.put(aGame, map);
		} else {
			HashMap<String, Double> map = new HashMap<String, Double>();
			map.put(aUsername, aScore);
			myScores.put(aGame, map);
		}
	}
	
	public Map<Game, HashMap<String, Double>> getHighscores() {
		return myScores;
	}
}
