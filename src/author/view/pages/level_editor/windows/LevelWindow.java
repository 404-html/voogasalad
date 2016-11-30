package author.view.pages.level_editor.windows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteMovableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.util.FileLoader;
import author.view.util.FileLoader.FileType;
import author.view.util.ToolBarBuilder;
import author.view.util.authoring_buttons.ButtonFactory;
import game_data.Level;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Pane;

/**
 * This window is the actual level editor, where sprites will be placed from the
 * EntityWindow
 * 
 * @author Jordan Frazier, Cleveland Thompson
 * @see EntityWindow
 * @see ../LevelEditor
 */
public class LevelWindow extends AbstractLevelEditorWindow {

	private ScrollPane myLevelScroller;
	private Pane currentContainer;
	private IAuthorController myController;
	
	private IntegerProperty horizontalPanes = new SimpleIntegerProperty(1);
	private IntegerProperty verticalPanes = new SimpleIntegerProperty(1);
	private Map<Level, Pane> levelPanes = new HashMap<>();
	
//	private static final int DEFAULT_LEVEL_WIDTH = 700;
//	private static final int DEFAULT_LEVEL_HEIGHT = 550;

	public LevelWindow(IAuthorController authorController) {
		super(authorController);
		myController = authorController;
		createLevelScroller();
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends Node> void addChildren(T... child) {
		for (T node : child) {
			currentContainer.getChildren().add(node);
		}
	}

	@Override
	protected void createToolBar() {
		ToolBarBuilder tbb = new ToolBarBuilder();
		tbb.addBurst(new Label("Level Window"));
		tbb.addFiller();
		tbb.addBurst(new ButtonFactory().createButton("Set Background", e -> {
			newBackgroundImage();
		}).getButton(), new ButtonFactory().createButton("Set Theme", e -> {
			// TODO: Jordan(vooga) - Add functionality to changing theme
			System.out.println("Change theme here");
		}).getButton(), new ButtonFactory().createButton("Extend Right", e -> {
			//currentContainer.setPrefWidth(myLevelScroller.getPrefViewportWidth() * horizontalPanes.get());
			horizontalPanes.set(horizontalPanes.get() + 1);
		}).getButton(), new ButtonFactory().createButton("Extend Down", e -> {
			//currentContainer.setPrefHeight(myLevelScroller.getPrefViewportHeight() * verticalPanes.get());
			verticalPanes.set(verticalPanes.get() + 1);
		}).getButton());

		super.getWindow().getChildren().add(tbb.getToolBar());
	}

	private void createLevelScroller() {
		myLevelScroller = new ScrollPane();
		

		myLevelScroller.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myLevelScroller.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);

		myLevelScroller.prefViewportWidthProperty().bind(this.getWindow().widthProperty());
		myLevelScroller.prefViewportHeightProperty().bind(this.getWindow().heightProperty());

		
		this.currentContainer = createLevelPane();
		myLevelScroller.setContent(currentContainer);
		acceptDraggableSprites();

		super.getWindow().getChildren().add(myLevelScroller);
	}
	
	private Pane createLevelPane(){
		Pane levelPane = new Pane();
		levelPane.setOnDragEntered(e -> {
			System.out.println("Drag entered level editor pane");
		});
		this.myLevelScroller.viewportBoundsProperty().addListener((listener) -> updatePane(levelPane));
		this.horizontalPanes.addListener((listener) -> updatePane(levelPane));
		this.verticalPanes.addListener((listener) -> updatePane(levelPane));
		return levelPane;
	}
	
	private void updatePane(Pane levelPane){
		levelPane.setPrefWidth(this.myLevelScroller.getViewportBounds().getWidth() * this.horizontalPanes.get());
		levelPane.setPrefHeight(this.myLevelScroller.getViewportBounds().getHeight() * this.verticalPanes.get());
	}

	private void acceptDraggableSprites() {

		currentContainer.setOnDragDropped((DragEvent event) -> {
			if (checkGameHasLevel()) {
				System.out.println("New Sprite on Screen");
				Dragboard db = event.getDragboard();
				boolean success = false;
				if (db.hasString()) {
					Sprite sprite = findSprite(db.getString());

					Sprite clone = sprite.clone();
					clone.getMyLocation().setLocation(event.getX(), event.getY());
					this.myController.getModel().getGame().getCurrentLevel().addNewSprite(clone);
					DraggableSprite newSprite;
					try {
						newSprite = new ConcreteMovableSprite(clone);
					} catch (NullPointerException e) {
						System.out.println(e.getMessage());
						e.printStackTrace();
						throw new NullPointerException();
					}

					ImageView image = newSprite.getImageView();
					if (image != null) {
						image.setLayoutX(event.getX());
						image.setLayoutY(event.getY());
						success = true;
					}
					
				}
				event.setDropCompleted(success);
				event.consume();
			}
		});

		currentContainer.setOnDragOver((DragEvent event) -> {
			if (event.getDragboard().hasString()) {
				event.acceptTransferModes(TransferMode.MOVE);
			}
			event.consume();
		});
	}

	private boolean checkGameHasLevel() {
		if (this.myController.getModel().getGame().getCurrentLevel() == null) {
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("No Level");
			alert.setHeaderText("No Level created yet.");
			alert.setContentText("Select New -> New Level to create a new Level before dragging sprites");
			alert.showAndWait();
			return false;
		}
		return true;
	}

	private void newBackgroundImage() {
		File file = new FileLoader(FileType.GIF, FileType.JPEG, FileType.PNG, FileType.JPG).loadImage();

		if (file != null)
			this.myController.getModel().getGame().getCurrentLevel()
					.setBackgroundImageFilePath(file.toURI().toString());
	}

	private void setBackgroundImage(String filePath) {
		Level currentLevel = this.myController.getModel().getGame().getCurrentLevel();
		Image image = new Image(filePath, currentLevel.getWidth(), currentLevel.getHeight(), false, false);
		BackgroundImage backIm = new BackgroundImage(image, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(currentLevel.getWidth(), currentLevel.getHeight(), false, false, false, true));

		currentContainer.setBackground(new Background(backIm));
		currentContainer.setMinSize(image.getWidth(), image.getHeight());
	}

	private Sprite findSprite(String nodeId) {
		for (Sprite s : myController.getModel().getGame().getPresets()) {
			if (nodeId.equals(s.getId())) {
				return s;
			}
		}
		return null;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see author.view.pages.level_editor.windows.AbstractLevelEditorWindow#
	 * initListener(author.controller.IAuthorController, game_data.Level)
	 */
	@Override
	protected void initListener() {
		getController().getModel().getGame().addListener((game) -> {
			Level currentLevel = getController().getModel().getGame().getCurrentLevel();
			if (currentLevel != null)
				updateLevel(currentLevel);
		});
	}

	private void updateLevel(Level aLevel) {
		updatePane(aLevel);
		aLevel.addListener((level) -> {
			updatePane(aLevel);
		});
	}

	private void updatePane(Level aLevel) {
		if (!this.levelPanes.containsKey(aLevel)){
			Pane newLevelPane = createLevelPane();
			this.levelPanes.put(aLevel, newLevelPane);
		}
		
		this.currentContainer = this.levelPanes.get(aLevel);
		this.myLevelScroller.setContent(this.currentContainer);
		acceptDraggableSprites();
		
		if (aLevel.getBackgroundImageFilePath() != null)
			setBackgroundImage(aLevel.getBackgroundImageFilePath());	
		
		Set<Sprite> levelSprites = this.getNewSprites(this.getDraggableSprites(), aLevel.getMySpriteList());
		
		levelSprites.forEach((sprite) -> {
			initPresetListener(sprite, sprite.getPreset());
			DraggableSprite draggableSprite = new ConcreteMovableSprite(sprite);
			this.addDraggableSprite(draggableSprite);
			styleSpriteImageView(sprite, draggableSprite);
			currentContainer.getChildren().add(draggableSprite.getImageView());
		});
	}
	
	private void initPresetListener(Sprite instanceSprite, Sprite spritePreset){
		InvalidationListener invalidationListener = (sprite) -> {
			instanceSprite.setMyImagePath(spritePreset.getMyImagePath());
		};
		spritePreset.addListener(invalidationListener);
	}

	private void styleSpriteImageView(Sprite sprite, DraggableSprite draggableSprite) {
		draggableSprite.getImageView().setLayoutX(sprite.getMyLocation().getXLocation());
		draggableSprite.getImageView().setLayoutY(sprite.getMyLocation().getYLocation());
		draggableSprite.getImageView().setFitWidth(sprite.getMyWidth());
		draggableSprite.getImageView().setFitHeight(sprite.getMyHeight());
	}

}
