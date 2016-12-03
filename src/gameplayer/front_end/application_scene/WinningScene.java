package gameplayer.front_end.application_scene;

import javafx.scene.Scene;

public class WinningScene extends AbstractNavigationPlayerScene implements INavigationDisplay {

	public WinningScene(double aWidth, double aHeight) {
		super(aWidth, aHeight);
		
	}

	@Override
	public Scene init() {
		return myScene;
	}
	
	private void addButtons(){
		
	}

}
