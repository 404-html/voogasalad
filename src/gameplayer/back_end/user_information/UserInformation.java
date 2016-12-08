package gameplayer.back_end.user_information;

public class UserInformation {
	
	private String myUser;
	private String myImageFilePath;

	public UserInformation() {
		myUser = "RandomUser";
	}
	
	public UserInformation(String aName) {
		myUser = aName;
	}
	
	public void setUserImage(String aFilePath) {
		myImageFilePath = aFilePath;
	}
	
	public String getUserName() {
		return myUser;
	}
	
}
