package gameplayer.front_end.popup;

public class PopUpFactory {
	
	public IPopUpDisplay buildPopUpDisplay(int aNumLevels) {
		return new LevelSelectionPopUp(aNumLevels);
	}
	
	public IPopUpDisplay buildPopUpDisplay() {
		return new PlayerOptionsPopUp();
	}

}
