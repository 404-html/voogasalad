package author.view.pages.sprite.editor.settings.characteristics;

import author.view.pages.sprite.editor.settings.SettingsViewColumn;
import game_data.Sprite;
import javafx.scene.control.Label;

public class CharacteristicsViewColumn extends SettingsViewColumn {

	public CharacteristicsViewColumn(Sprite aSprite) {
		super(aSprite);
	}

	@Override
	protected void updateList(Sprite aSprite) {
		getPane().getChildren().clear();
		
		aSprite.getCharacteristics().forEach( c -> {
			getPane().getChildren().add(new Label(c.getClass().getSimpleName()));
		});
	}

}
