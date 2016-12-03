package gameplayer.back_end.high_score;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;


public class HighScore {
	
	private Map<String, Double> myHighScoresPerGame;
	
	public HighScore() {
		myHighScoresPerGame = new HashMap<String, Double>();
	}
	
	public void setHighScore(String aUserName, double aValue) {
		myHighScoresPerGame.put(aUserName, aValue);
	}
	
	public double getHighScore(String aUserName) {
		return myHighScoresPerGame.get(aUserName);
	}
	
	public Collection<String> getUserNamesWithHighScores() {
		return myHighScoresPerGame.keySet();
	}

}
