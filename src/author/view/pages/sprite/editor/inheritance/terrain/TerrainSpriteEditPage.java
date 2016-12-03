package author.view.pages.sprite.editor.inheritance.terrain;

import author.view.pages.sprite.editor.inheritance.base.BaseSpriteEditPage;
import game_data.Sprite;

public class TerrainSpriteEditPage extends BaseSpriteEditPage {

	public TerrainSpriteEditPage(Sprite aSprite) {
		super(aSprite);
	}

	@Override
	public String getSpriteType() {
		return "terrain";
	}
	
}
