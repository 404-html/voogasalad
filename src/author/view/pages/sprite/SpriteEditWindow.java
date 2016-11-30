package author.view.pages.sprite;

import author.view.pages.sprite.editor.BaseSpriteEditPage;
import author.view.pages.sprite.editor.character.PlayerSpriteEditPage;
import game_data.Sprite;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpriteEditWindow {

	private BaseSpriteEditPage mySpriteEditPage;
	
	
	
	private SpriteEditWindow() {
		// Does Nothing
	}
	
	public SpriteEditWindow(Sprite aSprite){
		this();
		mySpriteEditPage = new PlayerSpriteEditPage(aSprite);
	}

	public void openWindow(){
		Stage stage = new Stage();
		stage.setTitle(mySpriteEditPage.getSpriteType());
		stage.setScene(new Scene(this.mySpriteEditPage.getPane()));
		stage.setResizable(false);
		stage.show();
	}
	
	public BaseSpriteEditPage getSpriteEdit(){
		return mySpriteEditPage;
	}
	
}
