package gameplayer.back_end.user_information;

import java.util.HashMap;
import java.util.Map;
import gameplayer.back_end.exceptions.IncorrectPasswordException;
import gameplayer.back_end.exceptions.UsernameFieldEmptyException;
import gameplayer.back_end.exceptions.UsernameNotFoundException;
import gameplayer.back_end.exceptions.UsernameNotUniqueException;

public class UserInformationController implements IViewableUserInformation {

	private String myCurrentUser;
	private String myCurrentPassword;
	private Map<String, String> myUserInformation;

	public UserInformationController() {
//		deserializeTheXmlAndMakeMap(); 
		myUserInformation = new HashMap<String, String>();
	}

	@Override
	public void signIn(String aUserName, String aPassword) throws Exception {
		if(isValidToEnter(aUserName, aPassword)){
			assign(aUserName, aPassword);
		};
	}

	@Override
	public void signUp(String aUserName, String aPassword) throws Exception {
		if(isValidToSignUp(aUserName)){
			assign(aUserName, aPassword);
			saveToXML(aUserName, aPassword);
		};
	}
	
	private void assign(String aUserName, String aPassword){
		myUserInformation.put(aUserName, aPassword);
		setMyCurrentUser(aUserName);
		setMyCurrentPassword(aPassword);
	}

	private boolean isValidToEnter(String aUserName, String aPassword) throws Exception {
		if(aUserName.isEmpty() || aUserName.equals("Enter Username")){
			throw new UsernameFieldEmptyException();
//			return true;
		} else if (!myUserInformation.containsKey(aUserName)) {
			throw new UsernameNotFoundException(aUserName + " is not a valid username");
		} else if (!myUserInformation.get(aUserName).equals(aPassword)) {
			throw new IncorrectPasswordException();
		}
		return true;
	}
	
	private boolean isValidToSignUp(String aUserName) throws Exception {
		if (myUserInformation.containsKey(aUserName)) {
			throw new UsernameNotUniqueException(aUserName + " has already been taken");
		} else {
			return true;
		}
	}

	private void saveToXML(String aUserName, String aPassword) throws Exception {
		//Save
	}

	public String getMyCurrentPassword() {
		return myCurrentPassword;
	}

	public void setMyCurrentPassword(String aPassword) {
		myCurrentPassword = aPassword;
	}

	public String getMyCurrentUser() {
		return myCurrentUser;
	}

	public void setMyCurrentUser(String aCurrentUser) {
		myCurrentUser = aCurrentUser;
	}
}
