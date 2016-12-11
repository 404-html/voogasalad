package gameplayer.back_end.user_information;

import java.util.ArrayList;
import java.util.List;

import game_data.Game;

public class HighscoreManager {

	private List<Game> myGames;
	private List<String> myUsers;
	private List<Double> myScores;
	
	public HighscoreManager() {
		myGames = new ArrayList<Game>();
		myUsers = new ArrayList<String>();
		myScores = new ArrayList<Double>();
	}
	
	public void setHighscore(String aUsername, double aScore, Game aGame) {
		myUsers.add(aUsername);
		myScores.add(aScore);
		myGames.add(aGame);
	}
	
	public List<Double> getHighscores() {
		return myScores;
	}
	
	public List<String> getUsers() {
		return myUsers;
	}
	
	public List<Game> getGames() {
		return myGames;
	}
}
