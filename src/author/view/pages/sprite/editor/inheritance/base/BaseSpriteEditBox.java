package author.view.pages.sprite.editor.inheritance.base;

import java.io.File;
import java.io.FileNotFoundException;

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
import util.RelativePathFinder;
import util.filehelpers.FileLoader.FileLoader;
import util.filehelpers.FileLoader.FileType;
import util.inputfields.NumberFieldBox;

public class BaseSpriteEditBox {

	private Pane myPane;
	private FileLoader myFileLoader;
	private NumberFieldBox myXVelocityField;
	private NumberFieldBox myYVelocityField;
	private TextField myNameField;
	private NumberFieldBox myWidthField;
	private NumberFieldBox myHeightField;
	private ImageView myImageView;
	private String myImagePath;

	private static final double CHAR_SIZE = 100;

	public BaseSpriteEditBox() {
		myPane = new VBox();

		myFileLoader = new FileLoader(
				"data/images/sprite_images/",
				FileType.RASTER_IMAGE
				);

		myPane.getChildren().addAll(
				makeNameField(), 
				makeVelocityFields(),
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

	public final double getXVelocity(){
		return myXVelocityField.getDouble();
	}

	public final double getYVelocity(){
		return myYVelocityField.getDouble();
	}

	protected final void setXVelocity(double aXVelocity){
		myXVelocityField.setValue(aXVelocity);
	}

	protected final void setYVelocity(double aYVelocity){
		myYVelocityField.setValue(aYVelocity);
	}

	protected final String getImageFile(){
		return myImagePath;
	}

	protected final void setImageFile(String aImagePath){
		RelativePathFinder pf = new RelativePathFinder();
		File file = new File(aImagePath);
		myImagePath = pf.getPath(file);
		myImageView.setImage(new Image(file.toURI().toString()));
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

	private Node makeVelocityFields(){
		Pane velocityBox = new VBox();

		myXVelocityField = new NumberFieldBox("X: ");
		myYVelocityField = new NumberFieldBox("Y: ");

		Pane coordinateBox = new VBox();
		coordinateBox.getChildren().addAll(
				myXVelocityField.getPane(),
				myYVelocityField.getPane());

		velocityBox.getChildren().addAll(
				new Label("Initial Velocity: "),
				coordinateBox
				);

		return velocityBox;
	}

	private Node makeImageSelect(){
		Pane imageSelectBox = new HBox();

		myImageView = new ImageView();
		Button imageButton = new Button();
		imageButton.minWidthProperty().bind(myPane.widthProperty());
		imageButton.setText("Select Image:");
		imageButton.setContentDisplay(ContentDisplay.TOP);
		imageButton.setGraphic(myImageView);


		myImageView.setFitWidth(CHAR_SIZE);
		myImageView.setFitHeight(CHAR_SIZE);

		imageSelectBox.getChildren().addAll(imageButton, myImageView);

		imageButton.setOnMouseClicked(event -> {
			File file;
			try {
				file = myFileLoader.loadSingle();
				RelativePathFinder pf = new RelativePathFinder();
				myImagePath = pf.getPath(file);
				myImageView.setImage(new Image(file.toURI().toString()));
			} catch (FileNotFoundException e) {
				// Do Nothing
				return;
			}
		});

		return imageSelectBox;
	}
}
