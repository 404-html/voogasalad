package author.view.pages.sprite.editor.settings.states;

import author.view.pages.sprite.editor.settings.SettingsFactory;
import game_data.Sprite;
import game_data.states.State;

public class StatesFactory<T extends State> extends SettingsFactory<T> {

	public static final String ROOT = "game_data.states.";
	
	public StatesFactory(String aName, Sprite aSprite) {
		super(aName, aSprite);
	}

	@Override
	protected String getRootDirectoryPath() {
		return ROOT;
	}

}
