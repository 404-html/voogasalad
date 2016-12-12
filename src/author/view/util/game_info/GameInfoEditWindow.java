package author.view.util.game_info;

import java.io.File;
import java.io.FileNotFoundException;

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
import javafx.stage.Modality;
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
	private RadioButton scrollVerticalUP;
	private RadioButton scrollVerticalDOWN;
	private RadioButton scrollHorizontalRIGHT;
	private RadioButton scrollHorizontalLEFT;
	
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
		myStage.initModality(Modality.APPLICATION_MODAL);
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

		iconButton.setOnMouseClicked(event -> {
			File aFile;
			try {
				aFile = myIconFileLoader.loadSingle();
				RelativePathFinder pf = new RelativePathFinder();
				myIconFilePath = pf.getPath(aFile);
				myIconImageView.setImage(new Image(aFile.toURI().toString()));
			} catch (FileNotFoundException e) {
				// Do Nothing, no file loaded
				return;
			}
		});

		return iconSelect;
	}

	private Node makeThemeSongSelect() {
		Pane themeSongBox = new HBox();

		Button songButton = new Button();
		myAudioFilePath = myGame.getAudioFilePath();
		songButton.setText(myAudioFilePath.substring(myAudioFilePath.lastIndexOf("/") + 1));
		songButton.setOnMouseClicked(event -> {
			File aFile;
			try {
				aFile = myAudioFileLoader.loadSingle();
				RelativePathFinder pf = new RelativePathFinder();
				myAudioFilePath = pf.getPath(aFile);
				songButton.setText(myAudioFilePath.substring(myAudioFilePath.lastIndexOf("/") + 1));
			} catch (FileNotFoundException e) {
				// Do Nothing, no file loaded
				return;
			}

		});

		themeSongBox.getChildren().addAll(new Label("Choose Theme Music: "), songButton);
		return themeSongBox;	
	}

	private Node makeScrollTypeSelect() {
		Pane scrollTypeBox = new VBox();

		scrollChoices = new ToggleGroup();
		scrollCenter = new RadioButton("Center");
		scrollVerticalUP = new RadioButton("Vertical (Traveling Up)");
		scrollVerticalDOWN = new RadioButton("Vertical (Traveling Down)");
		scrollHorizontalRIGHT = new RadioButton("Horizontal (Traveling Right)");
		scrollHorizontalLEFT = new RadioButton("Horizontal (Traveling Left)");
		scrollCenter.setToggleGroup(scrollChoices);
		scrollVerticalUP.setToggleGroup(scrollChoices);
		scrollVerticalDOWN.setToggleGroup(scrollChoices);
		scrollHorizontalRIGHT.setToggleGroup(scrollChoices);
		scrollHorizontalLEFT.setToggleGroup(scrollChoices);
		if (myGame.getScrollType() == ScrollType.CENTER)
			scrollCenter.setSelected(true);
		if (myGame.getScrollType() == ScrollType.HORIZONTAL_RIGHT)
			scrollHorizontalRIGHT.setSelected(true);
		if (myGame.getScrollType() == ScrollType.VERTICAL_UP)
			scrollVerticalUP.setSelected(true);
		if (myGame.getScrollType() == ScrollType.HORIZONTAL_LEFT)
			scrollHorizontalLEFT.setSelected(true);
		if (myGame.getScrollType() == ScrollType.VERTICAL_DOWN)
			scrollVerticalDOWN.setSelected(true);

		
		
		scrollTypeBox.getChildren().addAll(new Label("Choose Scroll Type: "), 
				scrollCenter,
				scrollVerticalUP,
				scrollVerticalDOWN,
				scrollHorizontalRIGHT,
				scrollHorizontalLEFT);
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
		if (scrollChoices.getSelectedToggle() == scrollVerticalUP)
			myGame.setScrollType(ScrollType.VERTICAL_UP);
		if (scrollChoices.getSelectedToggle() == scrollVerticalDOWN)
				myGame.setScrollType(ScrollType.VERTICAL_DOWN);
		if (scrollChoices.getSelectedToggle() == scrollHorizontalRIGHT)
			myGame.setScrollType(ScrollType.HORIZONTAL_RIGHT);
		if (scrollChoices.getSelectedToggle() == scrollHorizontalLEFT)
			myGame.setScrollType(ScrollType.HORIZONTAL_LEFT);
		
		myStage.close();
	}
	
	private String getStyleSheet(){
		File css = new File(STYLESHEET);
		return css.toURI().toString();
	}


}
