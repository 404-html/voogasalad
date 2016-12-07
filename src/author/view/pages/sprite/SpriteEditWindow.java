package author.view.pages.sprite;

import java.io.File;

import author.view.pages.sprite.editor.inheritance.base.BaseSpriteEditPage;
import game_data.Sprite;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SpriteEditWindow {

	private BaseSpriteEditPage mySpriteEditPage;
	private static final String STYLESHEET = "data/GUI/author-style.css";

	
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
		stage.getScene().getStylesheets().add(getStyleSheet());
		stage.setResizable(false);
		stage.show();
	}
	
	public BaseSpriteEditPage getSpriteEdit(){
		return mySpriteEditPage;
	}
	
	private String getStyleSheet(){
		File css = new File(STYLESHEET);
		return css.toURI().toString();
	}
	
}
