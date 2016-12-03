package gameplayer.back_end.high_score;

import java.util.Collection;

public interface IViewableHighScore {
	
	public double getHighScore(String aUserName);
	
	public Collection<String> getUserNamesWithHighScores();

}
