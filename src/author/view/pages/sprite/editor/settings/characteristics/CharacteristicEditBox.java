package author.view.pages.sprite.editor.settings.characteristics;

import java.util.Set;

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

	private Characteristic hasSettingAlready(Characteristic charr) {
		Set<Characteristic> spriteChars = getSprite().getCharacteristics();
		
		for (Characteristic c : spriteChars) {
			if(charr.getClass().getSimpleName().equals(c.getClass().getSimpleName()))
				return c;
		}
		
		return null;
	}

	@Override
	public void addSpriteSetting() {
		Characteristic c = makeCharacteristic();
		
		if(hasSettingAlready(c) == null){
			getSprite().addCharacteristic(c);
		}
		else {
			getSprite().getCharacteristics().remove(hasSettingAlready(c));
			getSprite().addCharacteristic(c);
		}
	}

	@Override
	protected SettingsFactory<?> buildSettingFactory() {
		return new CharacteristicsFactory<Characteristic>(getName(), getSprite());
	}


}
