package author.view.pages.sprite.page;

import author.view.pages.sprite.SpriteEditWindow;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SpriteViewBox implements InvalidationListener {

	private Pane myPane;
	private Button myImageButton;
	
	private static final String IMG_TEXT = "Click to Edit: ";
	
	private SpriteViewBox() {
		myPane = new VBox();
		myImageButton = new Button(IMG_TEXT);
		myImageButton.setContentDisplay(ContentDisplay.BOTTOM);
		myPane.getChildren().addAll(myImageButton);		
	}

	public SpriteViewBox(Sprite aSprite){
		this();
		aSprite.addListener(this);
		myImageButton.setText(IMG_TEXT + aSprite.getName());
		Image spriteImage = new Image( aSprite.getMyImagePath() );
		ImageView imageView = new ImageView( spriteImage );
		imageView.setFitWidth(100);
		imageView.setFitHeight(100);
		myImageButton.setGraphic( imageView );
		myImageButton.maxWidthProperty().bind(myPane.widthProperty());
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
			myImageButton.setText(IMG_TEXT + s.getName());
			ImageView iv = new ImageView (new Image(s.getMyImagePath()));
			iv.setFitWidth(100);
			iv.setFitHeight(100);
			myImageButton.setGraphic(iv );
		}
	}
	
}
