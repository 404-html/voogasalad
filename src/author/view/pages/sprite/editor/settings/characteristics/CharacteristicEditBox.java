package author.view.pages.sprite.editor.settings.characteristics;

import author.view.pages.sprite.editor.settings.SettingsFactory;
import author.view.pages.sprite.editor.settings.SpriteSettingsEditBox;
import game_data.Sprite;
import game_data.characteristics.Characteristic;

final class CharacteristicEditBox extends SpriteSettingsEditBox {

	CharacteristicEditBox( Sprite aSprite, String aName) {
		super(aSprite, aName);
	}
	
	private Characteristic makeCharacteristic(){
		return (Characteristic) getSettingFactory().getSettingInstance(makeTextToValueMap());
	}

	@Override
	public void addSpriteSetting() {
		getSprite().addCharacteristic(makeCharacteristic());
	}


	@Override
	protected SettingsFactory<?> buildSettingFactory() {
		return new CharacteristicsFactory<Characteristic>(getName(), getSprite());
	}


}
