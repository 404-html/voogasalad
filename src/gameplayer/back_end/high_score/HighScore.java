package gameplayer.back_end.high_score;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

import com.thoughtworks.xstream.XStream;


public class HighScore implements Serializable {
	
	private String myCurrentUser;
	private Map<String, String> myHighScoresPerGame;
	
	public HighScore(String aUserName) {
		myCurrentUser = aUserName;
		//HighScore information = (HighScore) aSerializer.fromXML("");
		//init(information.map);
	}
	
	private void init(Map<String, String> aUserHighScores) {
		myHighScoresPerGame = aUserHighScores;
	}
	
	public double getHighScore(String aUserName) {
		//return Double.parseDouble(myHighScoreData.getString(aUserName));
		return 0;
	}
	
	public Collection<String> getUserNamesWithHighScores() {
		//return myHighScoreData.keySet();
		return null;
	}

}
