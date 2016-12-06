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
		Characteristic c = makeCharacteristic();

		getSprite().getCharacteristics().removeIf( p -> { 
			return p.getClass().getSimpleName()
					.equals(c.getClass().getSimpleName());
			});
		getSprite().addCharacteristic(c);

	}

	@Override
	protected SettingsFactory<?> buildSettingFactory() {
		return new CharacteristicsFactory<Characteristic>(getName(), getSprite());
	}

	@Override
	public void removeSpriteSetting() {
		getSprite().getCharacteristics().removeIf( p -> {return p.getClass().getSimpleName().equals(getName());});
		getSprite().setName(getSprite().getName());
	}


}
