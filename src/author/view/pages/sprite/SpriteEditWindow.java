package author.view.pages.sprite;

import java.io.File;

import author.view.pages.sprite.editor.inheritance.base.BaseSpriteEditPage;
import game_data.Sprite;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class SpriteEditWindow {

	private BaseSpriteEditPage mySpriteEditPage;
	private static final String STYLESHEET = "data/GUI/author-style.css";
	private static final Stage SPRITE_EDIT_STAGE = makeStage();
	
	private static final Stage makeStage(){
		Stage s = new Stage();
		s.initModality(Modality.APPLICATION_MODAL);
		return s;
	}
	
	private SpriteEditWindow() {
		// Does Nothing
	}
	
	public SpriteEditWindow(Sprite aSprite){
		this();
		mySpriteEditPage = BaseSpriteEditPage.build(aSprite);
	}

	public void openWindow(){
		SPRITE_EDIT_STAGE.setScene(new Scene(this.mySpriteEditPage.getPane()));
		SPRITE_EDIT_STAGE.getScene().getStylesheets().add(getStyleSheet());
		SPRITE_EDIT_STAGE.setResizable(false);
		SPRITE_EDIT_STAGE.showAndWait();
	}
	
	public BaseSpriteEditPage getSpriteEdit(){
		return mySpriteEditPage;
	}
	
	private String getStyleSheet(){
		File css = new File(STYLESHEET);
		return css.toURI().toString();
	}
	
	public static final Stage getSpriteEditStage(){
		return SPRITE_EDIT_STAGE;
	}
}
