package author.view.pages.level_editor.windows;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteMovableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.pages.level_editor.windows.level_window.LevelWindowPaneFactory;
import author.view.pages.level_editor.windows.level_window.LevelWindowScrollerFactory;
import author.view.util.FileLoader;
import author.view.util.FileLoader.FileType;
import author.view.util.ToolBarBuilder;
import author.view.util.authoring_buttons.ButtonFactory;
import game_data.Level;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
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
public class LevelWindow extends AbstractLevelEditorWindow{

	private ScrollPane levelScroller;
	private Pane levelPane;
	private IAuthorController authorController;
	
	private IntegerProperty horizontalPanes = new SimpleIntegerProperty(1);
	private IntegerProperty verticalPanes = new SimpleIntegerProperty(1);
	private Map<Level, Pane> levelPanes = new HashMap<>();
	
	private LevelWindowPaneFactory levelWindowPaneFactory;
	private LevelWindowScrollerFactory levelWindowScrollerFactory;
	

	public LevelWindow(IAuthorController authorController) {
		super(authorController);
		this.authorController = authorController;
		this.levelWindowPaneFactory = new LevelWindowPaneFactory((ILevelEditorWindowInternal) this , this.authorController);
		this.levelWindowScrollerFactory = new LevelWindowScrollerFactory((ILevelEditorWindowInternal) this);
		initScrollPane();
	}
	
	private void initScrollPane(){
		this.levelScroller = levelWindowScrollerFactory.create();
		this.getWindow().getChildren().add(levelScroller);
	}

	@Override
	protected void createToolBar() {
		ToolBarBuilder tbb = new ToolBarBuilder();
		tbb.addBurst(new Label("Level Window"));
		tbb.addFiller();
		tbb.addBurst(new ButtonFactory().createButton("Set Background", e -> {
			newBackgroundImage();
		}).getButton(), new ButtonFactory().createButton("Set Theme", e -> {
			//TODO: Set Theme Here
		}).getButton(), new ButtonFactory().createButton("Extend Right", e -> {
			horizontalPanes.set(horizontalPanes.get() + 1);
		}).getButton(), new ButtonFactory().createButton("Extend Down", e -> {
			verticalPanes.set(verticalPanes.get() + 1);
		}).getButton());

		super.getWindow().getChildren().add(tbb.getToolBar());
	}

	private void newBackgroundImage() {
		File file = new FileLoader(FileType.GIF, FileType.JPEG, FileType.PNG, FileType.JPG).loadImage();

		if (file != null)
			this.authorController.getModel().getGame().getCurrentLevel()
					.setBackgroundImageFilePath(file.toURI().toString());
	}

	private void setBackgroundImage(String filePath) {
		Image image = new Image(filePath);
		BackgroundImage backIm = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT,
				new BackgroundSize(100, 100, true, true, true, true));
		this.levelPane.setBackground(new Background(backIm));
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
		if (!this.levelPanes.containsKey(aLevel)){
			
			Pane newLevelPane = this.levelWindowPaneFactory.create();
			this.levelPanes.put(aLevel, newLevelPane);
			this.levelPane = newLevelPane;
			this.levelScroller.setContent(this.levelPane);
			
			this.levelScroller.boundsInLocalProperty().addListener((listener) -> updateLevelSize(newLevelPane, aLevel));
			this.horizontalPanes.addListener((listener) -> updateLevelSize(newLevelPane, aLevel));
			this.verticalPanes.addListener((listener) -> updateLevelSize(newLevelPane, aLevel));
			
			aLevel.addListener((level) -> {
				updatePane(aLevel);
			});
		}
		updatePane(aLevel);
		
	}
	
	private void updateLevelSize(Pane aLevelPane, Level aLevel){
		aLevel.setWidth((int) this.levelScroller.getViewportBounds().getWidth() * this.horizontalPanes.get());
		aLevel.setHeight((int) this.levelScroller.getViewportBounds().getHeight() * this.verticalPanes.get());
		updatePaneSize(aLevelPane, aLevel);
	}
	
	private void updatePaneSize(Pane levelPane, Level aLevel){
		levelPane.setPrefWidth(aLevel.getWidth());
		levelPane.setPrefHeight(aLevel.getHeight());
	}

	private void updatePane(Level aLevel) {
		this.levelPane = this.levelPanes.get(aLevel);
		
		if (aLevel.getBackgroundImageFilePath() != null)
			setBackgroundImage(aLevel.getBackgroundImageFilePath());	
		
		Set<Sprite> levelSprites = this.getNewSprites(this.getDraggableSprites(), aLevel.getMySpriteList());
		
		levelSprites.forEach((sprite) -> {
			initPresetListener(sprite, sprite.getPreset());
			DraggableSprite draggableSprite = new ConcreteMovableSprite(sprite);
			this.addDraggableSprite(draggableSprite);
			styleSpriteImageView(sprite, draggableSprite);
			this.levelPane.getChildren().add(draggableSprite.getImageView());
		});
	}
	
	private void initPresetListener(Sprite instanceSprite, Sprite spritePreset){
		InvalidationListener invalidationListener = (sprite) -> {
			instanceSprite.setMyImagePath(spritePreset.getMyImagePath());
			instanceSprite.setMyWidth(spritePreset.getMyWidth());
			instanceSprite.setMyHeight(spritePreset.getMyHeight());
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
