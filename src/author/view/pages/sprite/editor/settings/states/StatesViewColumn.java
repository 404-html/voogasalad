package author.view.pages.sprite.editor.settings.states;

import author.view.pages.sprite.editor.settings.view.SettingsViewBox;
import author.view.pages.sprite.editor.settings.view.SettingsViewColumn;
import game_data.Sprite;

public class StatesViewColumn extends SettingsViewColumn {

	public StatesViewColumn(Sprite aSprite) {
		super(aSprite);
	}

	@Override
	protected void updateList(Sprite aSprite) {
		getPane().getChildren().clear();
		
		aSprite.getStates().forEach( c -> {
			getPane().getChildren().add(new SettingsViewBox(c).getPane());
		});
	}

}
