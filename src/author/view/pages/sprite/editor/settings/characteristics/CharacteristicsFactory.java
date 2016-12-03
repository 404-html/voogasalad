package author.view.pages.sprite.editor.settings.characteristics;

import author.view.pages.sprite.editor.settings.SettingsFactory;
import game_data.Sprite;
import game_data.characteristics.Characteristic;

public class CharacteristicsFactory<T extends Characteristic> extends SettingsFactory<T> {

	private static final String ROOT = "game_data.characteristics.";
	
	public CharacteristicsFactory(String aCharacteristicName, Sprite aSprite) {
			super(aCharacteristicName, aSprite);		
	}

	@Override
	protected String getRootDirectoryPath() {
		return ROOT;
	}
	
}
