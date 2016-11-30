package author.view.pages.sprite.editor.character;

import game_data.Sprite;

public class PlayerSpriteEditPage extends CharacterSpriteEditPage {

	public PlayerSpriteEditPage() {
		super();
	}
	
	public PlayerSpriteEditPage(Sprite aSprite){
		super(aSprite);
	}

	@Override
	public String getSpriteType() {
		return "Player Editor";
	}

	@Override
	public Sprite buildSprite() {
		
		getSprite().setMyLocation(super.getLocation());
		getSprite().setMyImagePath(super.getImageFile().toString());
		getSprite().setMyWidth(super.getWidth());
		getSprite().setMyHeight(super.getHeight());
		getSprite().setName(super.getSpriteName());

		return getSprite();
	}

}
