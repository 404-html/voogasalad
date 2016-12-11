package author.view.pages.sprite.editor.settings.characteristics;

import author.view.pages.sprite.editor.settings.SpriteSettingsEditBox;
import author.view.pages.sprite.editor.settings.SpriteSettingsEditor;
import author.view.pages.sprite.editor.settings.view.SettingsViewColumn;
import game_data.Sprite;
import util.InfoMap;

public class SpriteCharacteristicEditor extends SpriteSettingsEditor {
	
	public static final String ROOT = "sprite.characteristics.";
	
	public SpriteCharacteristicEditor(Sprite aSprite) {
		super(aSprite);
	}
	
	@Override
	protected SpriteSettingsEditBox makeEditBox(Sprite aSprite, String aName) {
		return new CharacteristicEditBox(aSprite, aName);
	}
	
	@Override
	protected String getDirectoryPath() {
		return ROOT;
	}

	@Override
	protected SettingsViewColumn makeViewColumn(Sprite aSprite) {
		CharacteristicsViewColumn cvc = new CharacteristicsViewColumn(aSprite);
		return cvc;
	}

	@Override
	public void updateSettings() {
		getSprite().getCharacteristics().forEach( c -> {
			getPropertySelector().getSelectedMap()
			.get(c.getClass().getSimpleName()).set(true);
			
			getEditBoxList().get(c.getClass().getSimpleName())
			.updateSettings(new InfoMap(c).getInfoMap());
		});
	}

	
}
