package gameplayer.front_end.popup;

import gameplayer.back_end.keycode_handler.KeyCodeHandler;

public class UserOptions {

	private String myFontColor;
	private String myKeyInput;
	
	public UserOptions(String aFontColor, String aKeyInput) {
		setMyFontColor(aFontColor);
		setMyKeyInput(aKeyInput);
	}
	
	public UserOptions(){
		setMyKeyInput("Default");
	}

	public String getMyKeyInput() {
		return myKeyInput;
	}

	public void setMyKeyInput(String aKeyInput) {
		myKeyInput = aKeyInput;
	}

	public String getMyFontColor() {
		return myFontColor;
	}

	public void setMyFontColor(String aFontColor) {
		myFontColor = aFontColor;
	}
}
