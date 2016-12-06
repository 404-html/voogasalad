package author.view.pages.sprite.editor.settings.states;

import java.util.Set;

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

	private State hasStateAlready(State state) {
		Set<State> spriteStates = getSprite().getStates();

		for (State s : spriteStates) {
			if(state.getClass().getSimpleName().equals(s.getClass().getSimpleName()))
				return s;
		}

		return null;
	}

	@Override
	public void addSpriteSetting() {
		State s = makeState();
		if(hasStateAlready(s) == null) // Does not have state
			getSprite().addState(s);
		else {						   // If it does, replace the old one
			getSprite().getStates().remove(hasStateAlready(s));
			getSprite().getStates().remove(s);
		}
	}
}
