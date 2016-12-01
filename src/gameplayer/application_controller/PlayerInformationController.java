package gameplayer.application_controller;

import gameplayer.back_end.high_score.HighScore;
import gameplayer.back_end.high_score.IViewableHighScore;
import gameplayer.back_end.user_information.IViewableUserInformation;
import gameplayer.back_end.user_information.UserInformationController;
import gameplayer.front_end.game_display_information.GameDisplayInformation;
import gameplayer.front_end.game_display_information.IViewableGameDisplayInformation;

public class PlayerInformationController {
	
	private IViewableUserInformation myUserInformation; 
	private IViewableGameDisplayInformation myGameDisplayInformation;
	private IViewableHighScore myHighScores; 
	
	public PlayerInformationController() {
		myUserInformation = new UserInformationController();
		myGameDisplayInformation = new GameDisplayInformation();
		myHighScores = new HighScore();
	}
	
	public void userSignIn(String aUsername, String aPassword) throws Exception{
		try {
			myUserInformation.signIn(aUsername, aPassword);
		} catch (Exception e) {
			throw e;
		}
	}

	public void userSignUp(String aUsername, String aPassword) throws Exception {
		try {
			myUserInformation.signUp(aUsername, aPassword);
		} catch (Exception e) {
			throw e;
		}
	}
	
	public double getHighScoresForUser(String aUserName) {
		return myHighScores.getHighScore(aUserName);
	}
	
}
