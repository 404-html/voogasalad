package author.view.pages.sprite.editor.settings;

import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public abstract class SettingsViewColumn implements InvalidationListener {

	private Pane myPane;	
	
	public  SettingsViewColumn(Sprite aSprite) {
		aSprite.addListener(this);
		myPane = new VBox();
	}

	public Pane getPane() {
		return myPane;
	}
	
	@Override
	public void invalidated(Observable observable) {
		if(observable instanceof Sprite) updateList((Sprite) observable);
	}
	
	protected abstract void updateList(Sprite aSprite);
	
	
	
}
