package author.view.pages.sprite;

import author.view.pages.sprite.editor.inheritance.base.BaseSpriteEditPage;
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
		mySpriteEditPage = BaseSpriteEditPage.build(aSprite);
	}

	public void openWindow(){
		Stage stage = new Stage();
		stage.setTitle(mySpriteEditPage.getSpriteType());
		stage.setScene(new Scene(this.mySpriteEditPage.getPane()));
		stage.setResizable(true);
		stage.show();
	}
	
	public BaseSpriteEditPage getSpriteEdit(){
		return mySpriteEditPage;
	}
	
}
