package gameplayer.front_end.application_scene;

public class SceneFactory {

	public IDisplay create(SceneIdentifier aIdentifier, double aWidth, double aHeight) {
		if(aIdentifier.equals(SceneIdentifier.LOGIN)){
			return new LoginScene(aWidth, aHeight);
		} else if (aIdentifier.equals(SceneIdentifier.MAINMENU)) {
			return new MainMenuScene(aWidth, aHeight);
		} else if (aIdentifier.equals(SceneIdentifier.GAMECHOICE)) {
			return new GameChoiceScene(aWidth, aHeight);
		} else if (aIdentifier.equals(SceneIdentifier.USERPROFILE)) {
			return new UserProfileScene(aWidth, aHeight);
		}
		return null;
	}
	
}
