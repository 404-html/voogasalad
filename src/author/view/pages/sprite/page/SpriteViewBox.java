package author.view.pages.sprite.page;

import author.view.pages.sprite.SpriteEditWindow;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Button;
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

	public SpriteViewBox(Sprite aSprite){
		this();
		aSprite.addListener(this);
		
		myImageButton.setGraphic( new SpriteQuickView(aSprite).getNode() );
		myImageButton.minWidthProperty().bind(myPane.minWidthProperty());
		
		myImageButton.setOnMouseClicked( e -> {
			SpriteEditWindow sew = new SpriteEditWindow(aSprite);
			sew.openWindow();
		});				
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
