package author.view.pages.sprite.editor.character;

import game_data.Sprite;

public class PlayerSpriteEditPage extends CharacterSpriteEditPage {

	public PlayerSpriteEditPage(Sprite aSprite){
		super(aSprite);
	}

	@Override
	public String getSpriteType() {
		return "player";
	}

	@Override
	public Sprite editSprite() {
		
		getSprite().setMyLocation(super.getLocation());
		getSprite().setMyImagePath(super.getImageFile().toString());
		getSprite().setMyWidth(super.getWidth());
		getSprite().setMyHeight(super.getHeight());
		getSprite().setName(super.getSpriteName());

		return getSprite();
	}

}
