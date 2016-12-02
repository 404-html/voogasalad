package author.view.pages.sprite.editor.inheritance.character;

import game_data.Sprite;

public class PlayerSpriteEditPage extends CharacterSpriteEditPage {

	public PlayerSpriteEditPage(Sprite aSprite){
		super(aSprite);
	}

	@Override
	public String getSpriteType() {
		return "player";
	}

}
