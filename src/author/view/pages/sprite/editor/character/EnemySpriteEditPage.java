package author.view.pages.sprite.editor.character;

import game_data.Sprite;

public class EnemySpriteEditPage extends CharacterSpriteEditPage {

	public EnemySpriteEditPage(Sprite aSprite){
		super(aSprite);
	}
	
	@Override
	public String getSpriteType() {
		return "enemy";
	}

	@Override
	public Sprite editSprite() {
		return null;
	}

}
