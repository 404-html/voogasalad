package gameplayer.application_controller;

import gameplayer.back_end.facebook.FacebookInformation;
import gameplayer.back_end.high_score.HighScore;
import gameplayer.back_end.user_information.UserInformation;

public class PlayerInformationController {
	
	private UserInformation myUserInformation; 
	private HighScore myHighScores;
	private FacebookInformation myFacebookInformation;
	
	public PlayerInformationController() {
		myUserInformation = new UserInformation();
		myHighScores = new HighScore();
		myFacebookInformation = new FacebookInformation();
	}
	
	public double getHighScores(String aUserName) {
		return myHighScores.getHighScore(aUserName);
	}
	
	public void setUserName(String aUserName) {
		myUserInformation = new UserInformation(aUserName);
	}
	
	public String getUser() {
		return myUserInformation.getUserName();
	}
	
	public void facebookLogin() {
		myFacebookInformation.authenticatePlayer();
		myUserInformation = new UserInformation(myFacebookInformation.getUserName());
		//myUserInformation.setUserImage(myFacebookInformation.getProfilePicture());
	}

}
