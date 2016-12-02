package gameplayer.back_end.high_score;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.thoughtworks.xstream.XStream;


public class HighScore implements Serializable, IViewableHighScore {
	
	private Map<String, Double> myHighScoresPerGame;
	
	public HighScore() {
		myHighScoresPerGame = new HashMap<String, Double>();
		//HighScore information = (HighScore) aSerializer.fromXML("");
		//init(information.map);
	}
	
	//private void init(Map<String, Double> aUserHighScores) {
		//myHighScoresPerGame = aUserHighScores;
	//}
	
	public double getHighScore(String aUserName) {
		return myHighScoresPerGame.get(aUserName);
	}
	
	public Collection<String> getUserNamesWithHighScores() {
		return myHighScoresPerGame.keySet();
	}

}
