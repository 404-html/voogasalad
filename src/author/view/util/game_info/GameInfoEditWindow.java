package author.view.util.game_info;

import java.io.File;

import util.RelativePathFinder;
import util.facades.ToolBarBuilder;
import util.filehelpers.FileLoader.FileLoader;
import util.filehelpers.FileLoader.FileType;
import game_data.Game;
import game_data.ScrollType;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class GameInfoEditWindow implements iGameInfoEditWindow {

	private Stage myStage;
	private Game myGame;
	private Pane myPane;
	private ToolBarBuilder myToolBarBuilder;
	
	private FileLoader myIconFileLoader;
	private FileLoader myAudioFileLoader;
	private TextField myNameField;
	private TextArea myDescriptionField;
	private String myIconFilePath;
	private ImageView myIconImageView;
	private String myAudioFilePath;
	private ToggleGroup scrollChoices;
	private RadioButton scrollCenter;
	private RadioButton scrollVertical;
	private RadioButton scrollHorizontal;
	
	private static final String STYLESHEET = "data/gui/author-style.css";


	public GameInfoEditWindow(Game aGame){
		myGame = aGame;
		myPane = new VBox();
		myToolBarBuilder = new ToolBarBuilder();
		Button saveButton = new Button("Save & Close");
		saveButton.setOnMouseClicked(e -> {
			saveGameInfo();
		});
		myToolBarBuilder.addBurst(new Label("Game Info"));
		myToolBarBuilder.addBurst(saveButton);
		myPane.getChildren().add(myToolBarBuilder.getToolBar());

		myIconFileLoader = new FileLoader(
				"data/images/game_icons/",
				FileType.RASTER_IMAGE
				);

		myAudioFileLoader = new FileLoader(
				"data/audio/",
				FileType.AUDIO
				);

		initializeInfoFields();
		myStage = new Stage();
		myStage.setScene(new Scene(myPane));
		myStage.getScene().getStylesheets().add(getStyleSheet());

	}

	private void initializeInfoFields() {
		myPane.getChildren().addAll(makeNameField(),
				makeDescriptionField(),
				makeIconSelect(),
				makeThemeSongSelect(),
				makeScrollTypeSelect());
	}

	private Node makeNameField() {
		Pane nameBox = new VBox();

		myNameField = new TextField(myGame.getName());
		nameBox.getChildren().addAll(new Label("Name: "), myNameField);
		return nameBox;	
	}

	private Node makeDescriptionField() {
		Pane descriptionBox = new VBox();

		myDescriptionField = new TextArea(myGame.getDescription());
		myDescriptionField.setPrefHeight(150);
		myDescriptionField.autosize();
		myDescriptionField.setWrapText(true);
		descriptionBox.getChildren().addAll(new Label("Description: "), myDescriptionField);
		return descriptionBox;	
	}

	private Node makeIconSelect() {
		Pane iconSelect = new HBox();
		myIconFilePath = myGame.getIconPath();

		Button iconButton = new Button();
		myIconImageView = new ImageView(new Image((new File(myGame.getIconPath()).toURI().toString())));
		iconButton.setGraphic(myIconImageView);
		myIconImageView.setFitWidth(50);
		myIconImageView.setFitHeight(50);


		iconSelect.getChildren().addAll(new Label("Choose icon: "),
				iconButton);

		iconButton.setOnMouseClicked(e -> {
			File aFile;
			try {
				aFile = myIconFileLoader.loadSingle();
				RelativePathFinder pf = new RelativePathFinder();
				myIconFilePath = pf.getPath(aFile);
				myIconImageView.setImage(new Image(aFile.toURI().toString()));
			} catch (Exception e1) {
				// TODO : Throw Error message if file not found
				e1.printStackTrace();
			}
		});

		return iconSelect;
	}

	private Node makeThemeSongSelect() {
		Pane themeSongBox = new HBox();

		Button songButton = new Button();
		myAudioFilePath = myGame.getAudioFilePath();
		songButton.setText(myAudioFilePath.substring(myAudioFilePath.lastIndexOf("/") + 1));
		songButton.setOnMouseClicked(e -> {
			File aFile;
			try {
				aFile = myAudioFileLoader.loadSingle();
				RelativePathFinder pf = new RelativePathFinder();
				myAudioFilePath = pf.getPath(aFile);
				songButton.setText(myAudioFilePath.substring(myAudioFilePath.lastIndexOf("/") + 1));
			} catch (Exception e1) {
				// TODO : Throw Error message if file not found
				e1.printStackTrace();
			}

		});

		themeSongBox.getChildren().addAll(new Label("Choose Theme Music: "), songButton);
		return themeSongBox;	
	}

	private Node makeScrollTypeSelect() {
		Pane scrollTypeBox = new VBox();

		scrollChoices = new ToggleGroup();
		scrollCenter = new RadioButton("Center");
		scrollVertical = new RadioButton("Vertical");
		scrollHorizontal = new RadioButton("Horizontal");
		scrollCenter.setToggleGroup(scrollChoices);
		scrollVertical.setToggleGroup(scrollChoices);
		scrollHorizontal.setToggleGroup(scrollChoices);
		if (myGame.getScrollType() == ScrollType.CENTER)
			scrollCenter.setSelected(true);
		if (myGame.getScrollType() == ScrollType.HORIZONTAL)
			scrollHorizontal.setSelected(true);
		if (myGame.getScrollType() == ScrollType.VERTICAL)
			scrollVertical.setSelected(true);
		
		
		scrollTypeBox.getChildren().addAll(new Label("Choose Scroll Type: "), 
				scrollCenter,
				scrollVertical,
				scrollHorizontal);
		return scrollTypeBox;	
	}

	@Override
	public void display() {
		myStage.show();
	}
	
	private void saveGameInfo() {
		myGame.setName(myNameField.getText());
		myGame.setDescription(myDescriptionField.getText());
		myGame.setIconPath(myIconFilePath);
		myGame.setAudioFilePath(myAudioFilePath);
		
		if (scrollChoices.getSelectedToggle() == scrollCenter)
			myGame.setScrollType(ScrollType.CENTER);
		if (scrollChoices.getSelectedToggle() == scrollVertical)
			myGame.setScrollType(ScrollType.VERTICAL);
		if (scrollChoices.getSelectedToggle() == scrollHorizontal)
			myGame.setScrollType(ScrollType.HORIZONTAL);
		
		myStage.close();
	}
	
	private String getStyleSheet(){
		File css = new File(STYLESHEET);
		return css.toURI().toString();
	}


}
