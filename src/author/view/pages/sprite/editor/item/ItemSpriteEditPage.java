package author.view.pages.sprite.editor.item;

import author.view.pages.sprite.editor.BaseSpriteEditPage;
import game_data.Sprite;

public class ItemSpriteEditPage extends BaseSpriteEditPage {

	public ItemSpriteEditPage(Sprite aSprite){
		super(aSprite);
	}
	
	@Override
	public String getSpriteType() {
		return "Item";
	}

	@Override
	public Sprite editSprite() {
		// TODO Auto-generated method stub
		return null;
	}

}
