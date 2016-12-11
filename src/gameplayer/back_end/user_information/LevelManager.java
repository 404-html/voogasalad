package gameplayer.back_end.user_information;

public class LevelManager {

	private int myLevel;
	
	public LevelManager() {
		myLevel = 0;
	}
	
	public LevelManager(int aLevel) {
		myLevel = aLevel;
	}
	
	public void setLevel(int aLevel) {
		myLevel = aLevel;
	}
	
	public int getLevel() {
		return myLevel;
	}
}
