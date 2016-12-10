package author.view.pages.sprite.page;

import java.io.File;

import game_data.Sprite;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.Separator;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

class SpriteQuickView {

	
	private Pane myPane;
	private ImageView myImageView;
	private Label myTypeLabel;
	private Label myNameLabel;
	private Label mySizeLabel;
	
	private SpriteQuickView(){
		myPane = new HBox();
		
		myImageView = new ImageView();
		myPane.getChildren().add(myImageView);
		myImageView.setFitHeight(100);
		myImageView.setFitWidth(100);
		
		myNameLabel = new Label();
		mySizeLabel = new Label();
		myTypeLabel = new Label();
		
		Pane infoBox = new VBox();
		infoBox.minWidthProperty().bind(myPane.minWidthProperty());
		HBox.setHgrow(infoBox, Priority.ALWAYS);
		
		infoBox.getChildren().addAll(
				myTypeLabel,
				new Separator(Orientation.HORIZONTAL),
				makeTextBox(myNameLabel, "Name: "),
				makeTextBox(mySizeLabel, "Size: ")
				);
		myPane.getChildren().add(infoBox);
	
	}
	
	SpriteQuickView(Sprite aSprite) {
		this();
		myImageView.setImage( new Image((new File(aSprite.getImagePath()).toURI().toString())) );
		myTypeLabel.setText(aSprite.getClass().getSimpleName());
		myNameLabel.setText(aSprite.getName());
		mySizeLabel.setText(aSprite.getWidth() + " x " + aSprite.getHeight());
	}

	public Node getNode(){
		return myPane;
	}
	
	private Pane makeTextBox(Label aTextLabel, String aTitle){
		Pane textBox = new HBox();
		Label titleLabel = new Label(aTitle);
		textBox.getChildren().addAll(titleLabel, aTextLabel);
		return textBox;
	}

}
