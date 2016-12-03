package author.view.pages.sprite.editor.inheritance.character;

import game_data.Sprite;

public class EnemySpriteEditPage extends CharacterSpriteEditPage {

	public EnemySpriteEditPage(Sprite aSprite){
		super(aSprite);
	}
	
	@Override
	public String getSpriteType() {
		return "enemy";
	}

}
