package gameplayer.front_end.popup;

public enum PlayerOptions {
	
	DEFAULT("default", "#fdbe3b"), RED("default", "red"), GREEN("default", "green"), BLUE("default", "blue"), 
	LEFT("left", "#fdbe3b");
	private String myKeyChoice;
	private String myColorChoice;

	PlayerOptions(String aKeyChoice, String aColorChoice) {
		myKeyChoice = aKeyChoice;
		myColorChoice = aColorChoice;
	}
	
	public String getColorChoice() {
		return myColorChoice;
	}
	
	public String getKeyChoice() {
		return myKeyChoice;
	}
	
	public void setColorChoice(String aColorChoice) {
		myColorChoice = aColorChoice;
	}
	
	public void setKeyChoice(String aKeyChoice) {
		myKeyChoice = aKeyChoice;
	}
}
