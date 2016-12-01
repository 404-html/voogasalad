package author.view.pages.sprite.editor.inheritance.item;

import author.view.pages.sprite.editor.inheritance.base.BaseSpriteEditPage;
import game_data.Sprite;

public class ItemSpriteEditPage extends BaseSpriteEditPage {

	public ItemSpriteEditPage(Sprite aSprite){
		super(aSprite);
	}
	
	@Override
	public String getSpriteType() {
		return "item";
	}

}
