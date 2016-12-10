package author.view.pages.sprite.page;

import java.io.File;
import java.util.Set;

import author.model.game_observables.draggable_sprite.context_menu.FunctionalMenuItemFactory;
import author.model.game_observables.draggable_sprite.context_menu.SpriteContextMenu;
import author.view.pages.sprite.SpriteEditWindow;
import game_data.Game;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Button;
import javafx.scene.control.ContextMenu;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SpriteViewBox implements InvalidationListener {

	private Pane myPane;
	private Button myImageButton;	


	private SpriteViewBox() {
		myPane = new VBox();
		myImageButton = new Button();
		myPane.getChildren().addAll(myImageButton);		
	}

	public SpriteViewBox(Sprite aSprite, Game aGame, SpriteScroller aSpriteScroller){
		this();
		aSprite.addListener(this);
		myImageButton.setGraphic( new SpriteQuickView(aSprite).getNode() );
		myImageButton.minWidthProperty().bind(myPane.minWidthProperty());

		myImageButton.setOnMouseClicked( e -> {
			if (((MouseEvent) e).getButton() == MouseButton.SECONDARY){
	 				ContextMenu myCM = new ContextMenu();
					myCM.getItems().add(new FunctionalMenuItemFactory().create("Delete", e2 -> { 
						aGame.removePreset(aSprite);
						myPane.setVisible(false);
						aSpriteScroller.removeInvisible();
					}).getItem());
					myCM.show(myPane, e.getSceneX(), e.getSceneY());
			}
			else{
				SpriteEditWindow sew = new SpriteEditWindow(aSprite);
				sew.openWindow();
			}
		});		
	}
	
	public boolean isVisible() {
		return myImageButton.isVisible();
	}

	public Pane getPane(){
		return myPane;
	}

	@Override
	public void invalidated(Observable observable) {
		if(observable instanceof Sprite){
			Sprite s = (Sprite) observable;
			myImageButton.setGraphic( new SpriteQuickView(s).getNode() );
		}
	}

}
