package gameplayer.back_end.user_information;

import java.util.HashMap;
import java.util.Map;
import gameplayer.back_end.exceptions.IncorrectPasswordException;
import gameplayer.back_end.exceptions.UsernameFieldEmptyException;
import gameplayer.back_end.exceptions.UsernameNotFoundException;
import gameplayer.back_end.exceptions.UsernameNotUniqueException;

public class UserInformation {
	
	private String myUser;
	private String myImageFilePath;

	public UserInformation() {
		myUser = "RandomUser";
	}
	
	public UserInformation(String aName, String aPictureUrl) {
		myUser = aName;
		myImageFilePath = aPictureUrl;
	}
	
	public void setUserImage(String aFilePath) {
		myImageFilePath = aFilePath;
	}
	
	public String getUserName() {
		return myUser;
	}

	public String getPictureUrl() {
		return myImageFilePath;
	}

	
}
