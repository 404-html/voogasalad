package author.view.pages.sprite.editor.character;

import game_data.Sprite;

public class EnemySpriteEditPage extends CharacterSpriteEditPage {

	public EnemySpriteEditPage() {
		super();
	}

	public EnemySpriteEditPage(Sprite aSprite){
		super(aSprite);
	}
	
	@Override
	public String getSpriteType() {
		return "Enemy Editor";
	}

	@Override
	public Sprite buildSprite() {
		return null;
	}

}
