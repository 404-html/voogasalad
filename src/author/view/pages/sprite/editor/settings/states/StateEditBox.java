package author.view.pages.sprite.editor.settings.states;

import author.view.pages.sprite.editor.settings.SettingsFactory;
import author.view.pages.sprite.editor.settings.SpriteSettingsEditBox;
import game_data.Sprite;
import game_data.states.State;

public class StateEditBox extends SpriteSettingsEditBox {

	public StateEditBox( Sprite aSprite, String aName) {
		super(aSprite, aName);
	}

	private State makeState() {
		return (State) getSettingFactory().getSettingInstance(makeTextToValueMap());
	}
	
	@Override
	protected SettingsFactory<?> buildSettingFactory() {
		return new StatesFactory<State>(getName(), getSprite());
	}

	@Override
	public void addSpriteSetting() {
		getSprite().addState(makeState());		
	}
}
