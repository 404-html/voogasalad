package author.view.pages.sprite.editor.inheritance.projectile;

import author.view.pages.sprite.editor.inheritance.base.BaseSpriteEditPage;
import game_data.Sprite;

public class ProjectileSpriteEditPage extends BaseSpriteEditPage {

	public ProjectileSpriteEditPage(Sprite aSprite) {
		super(aSprite);
	}

	@Override
	public String getSpriteType() {
		return "projectile";
	}


}
