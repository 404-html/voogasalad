package author.view.pages.sprite.editor.settings.states;

import java.util.Map;

import author.view.pages.sprite.editor.settings.SpriteSettingsEditBox;
import author.view.pages.sprite.editor.settings.SpriteSettingsEditor;
import author.view.pages.sprite.editor.settings.view.SettingsViewColumn;
import game_data.Sprite;
import javafx.beans.property.BooleanProperty;
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
		Map<String, BooleanProperty> selectedMap = getPropertySelector().getSelectedMap();
		String name = s.getClass().getSimpleName();
		
		if(selectedMap.containsKey(name)){
			selectedMap.get(name).set(true);
			getEditBoxList().get(s.getClass().getSimpleName()).updateSettings(new InfoMap(s).getInfoMap());
		}
		});
		
	}
	
}
