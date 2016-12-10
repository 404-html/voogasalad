package author.view.pages.sprite.editor.settings.states;

import author.view.pages.sprite.editor.settings.SpriteSettingsEditBox;
import author.view.pages.sprite.editor.settings.SpriteSettingsEditor;
import author.view.pages.sprite.editor.settings.view.SettingsViewColumn;
import game_data.Sprite;
import util.InfoMap;

public class SpriteStatesEditor extends SpriteSettingsEditor {

	public static final String ROOT = "sprite.states.";
	
	public SpriteStatesEditor(Sprite aSprite) {
		super(aSprite);
	}

	@Override
	protected String getDirectoryPath() {
		return ROOT;
	}

	@Override
	protected SpriteSettingsEditBox makeEditBox(Sprite aSprite, String aName) {
		return new StateEditBox(aSprite, aName);
	}

	@Override
	protected SettingsViewColumn makeViewColumn(Sprite aSprite) {
		return new StatesViewColumn(aSprite);
	}

	@Override
	public void updateSettings() {
		getSprite().getStates().forEach( s -> {
			getPropertySelector().getSelectedMap()
			.get(s.getClass().getSimpleName()).set(true);
			
		getEditBoxList().get(s.getClass().getSimpleName())
			.updateSettings(new InfoMap(s).getInfoMap());
		});
		
	}
	
}
