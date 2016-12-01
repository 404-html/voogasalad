package author.view.pages.sprite.editor;

import java.io.File;

import author.view.util.FileLoader;
import author.view.util.FileLoader.FileType;
import author.view.util.NumberFieldBox;
import game_data.Location;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class BaseSpriteEditBox {

	private Pane myPane;
	private FileLoader myFileLoader;
	private NumberFieldBox myXPositionField;
	private NumberFieldBox myYPositionField;
	private NumberFieldBox myHeadingField;
	private TextField myNameField;
	private NumberFieldBox myWidthField;
	private NumberFieldBox myHeightField;
	private ImageView myImageView;
	private String myImagePath;
	
	private static final double CHAR_SIZE = 100;
	
	public BaseSpriteEditBox() {
		myPane = new VBox();
		myFileLoader = new FileLoader(FileType.PNG, FileType.GIF,FileType.JPG, FileType.JPEG);
		myPane.getChildren().addAll(
				makeNameField(), 
				makeLocationFields(),
				makeSizeFields(), 
				makeImageSelect()
				);	
	}

	public final Pane getPane(){
		return myPane;
	}

	public final String getName(){
		return myNameField.getText();
	}
	
	public final void setName(String aName){
		myNameField.setText(aName);		
	}
	
	public final void setSize(int aWidth, int aHeight){
		myWidthField.setValue(aWidth);
		myHeightField.setValue(aHeight);
	}
	
	public final int getWidth(){
		return myWidthField.getInteger();
	}
	
	public final int getHeight(){
		return myHeightField.getInteger();
	}
	
	public final Location getLocation(){
		return new Location(
				myXPositionField.getDouble(),
				myYPositionField.getDouble(),
				myHeadingField.getDouble()
				);

	}

	protected final void setLocation(Location aLocation){
		myXPositionField.setValue(aLocation.getXLocation());
		myYPositionField.setValue(aLocation.getYLocation());
		myHeadingField.setValue(aLocation.getMyHeading());
	}
	
	protected final String getImageFile(){
		return myImagePath;
	}
	
	protected final void setImageFile(String aImagePath){
		myImagePath = aImagePath;
		myImageView.setImage(new Image( myImagePath ));
	}

	private Node makeNameField(){
		Pane nameBox = new VBox();
		
		myNameField = new TextField();
		nameBox.getChildren().addAll(new Label("Name: "), myNameField);
		HBox.setHgrow(myNameField, Priority.ALWAYS);
		
		return nameBox;
	}
	
	private Node makeSizeFields(){
		Pane sizeBox = new VBox();
		
		myWidthField = new NumberFieldBox("Width: ");
		myHeightField = new NumberFieldBox("Height: ");
		
		sizeBox.getChildren().addAll(
				new Label("Size: "), 
				myWidthField.getPane(),
				myHeightField.getPane()
				);		
		
		return sizeBox;
	}
	
	private Node makeLocationFields(){
		Pane locationBox = new VBox();

		myXPositionField = new NumberFieldBox("X: ");
		myYPositionField = new NumberFieldBox("Y: ");
		myXPositionField.getTextField().setEditable(false);
		myYPositionField.getTextField().setEditable(false);
		
		myHeadingField = new NumberFieldBox("Angle: ");

		Pane coordinateBox = new VBox();
		coordinateBox.getChildren().addAll(
				myXPositionField.getPane(),
				myYPositionField.getPane(),
				myHeadingField.getPane());
		
		locationBox.getChildren().addAll(
				new Label("Location: "),
				coordinateBox
				);

		return locationBox;
	}

	private Node makeImageSelect(){
		Pane imageSelectBox = new HBox();
		
		myImageView = new ImageView();
		Button imageButton = new Button();
		imageButton.setText("Select Image:");
		imageButton.setContentDisplay(ContentDisplay.TOP);
		imageButton.setGraphic(myImageView);
		
		
		myImageView.setFitWidth(CHAR_SIZE);
		myImageView.setFitHeight(CHAR_SIZE);
		
		imageSelectBox.getChildren().addAll(imageButton, myImageView);
		
		imageButton.setOnMouseClicked(e -> {
			File file = myFileLoader.loadImage();
			if(file != null){
				myImagePath = file.toURI().toString();
				myImageView.setImage(new Image(myImagePath));
			}
		});
		
		return imageSelectBox;
	}
}
