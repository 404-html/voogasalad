package author.view.pages.level_editor.windows;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteMovableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.pages.level_editor.windows.level_window.LevelWindowPaneFactory;
import author.view.pages.level_editor.windows.level_window.LevelWindowScrollerFactory;
import author.view.pages.level_editor.windows.level_window.LevelWindowToolBarFactory;
import game_data.Level;
import game_data.Location;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * This window is the actual level editor, where sprites will be placed from the
 * EntityWindow
 * 
 * @author Jordan Frazier, Cleveland Thompson
 * @see EntityWindow
 * @see ../LevelEditor
 */
public class LevelWindow extends AbstractLevelEditorWindow implements ILevelWindowInternal{

	private ScrollPane levelScroller;
	private Pane levelPane;

	private IntegerProperty horizontalPanes = new SimpleIntegerProperty(1);
	private IntegerProperty verticalPanes = new SimpleIntegerProperty(1);
	private Map<Level, Pane> levelPanes = new HashMap<>();
	private Set<Level> selectedSprites = new HashSet<>();

	public LevelWindow(IAuthorController authorController) {
		super(authorController);
		createScroller();
	}

	private void createScroller() {
		this.levelScroller = new LevelWindowScrollerFactory((ILevelEditorWindowInternal) this).create();
		this.levelScroller.setContent(this.levelPane);
		this.getWindow().getChildren().add(this.levelScroller);
	}

	@Override
	protected void createToolBar() {
		super.getWindow().getChildren().add(new LevelWindowToolBarFactory().createToolBar((ILevelWindowInternal) this, this.getController()));
	}

	@Override
	protected void initListener() {
		this.getController().getModel().getGame().addListener((game) -> {
			Level currentLevel = getController().getModel().getGame().getCurrentLevel();
			if (currentLevel != null)
				updateLevel(currentLevel);
		});
	}
	
	
	
	
	

	private void updateLevel(Level aLevel) {
		if (!this.levelPanes.containsKey(aLevel)) {

			Pane newLevelPane = new LevelWindowPaneFactory((ILevelEditorWindowInternal) this, this.getController())
					.create();

			this.levelPane = newLevelPane;
			this.levelPanes.put(aLevel, this.levelPane);
			this.levelScroller.setContent(this.levelPane);
			
			this.levelScroller.boundsInLocalProperty().addListener((listener) -> updateLevelSize(newLevelPane, aLevel));
			this.horizontalPanes.addListener((listener) -> updateLevelSize(newLevelPane, aLevel));
			this.verticalPanes.addListener((listener) -> updateLevelSize(newLevelPane, aLevel));

			aLevel.addListener((level) -> {
				updatePane(aLevel);
			});
			updatePaneSize(newLevelPane, aLevel);
		}

		this.levelPane = this.levelPanes.get(aLevel);
		this.levelScroller.setContent(this.levelPane);
		updatePane(aLevel);
	}

	private void updatePane(Level aLevel) {
		if (aLevel.getBackgroundImageFilePath() != null)
			setBackgroundImage(aLevel.getBackgroundImageFilePath());
		
		addSprites(this.getNewSprites(this.getMovableSprites(), aLevel.getMySpriteList()));
		removeSprites(this.getRemovedSprites(this.getMovableSprites(), aLevel.getMySpriteList()));
	}
	

	

	
	
	
	
	
	
	//Helper Methods
	
	private void addSprites(Set<Sprite> addedSprites){
		addedSprites.forEach((sprite) -> {
			ConcreteMovableSprite draggableSprite = new ConcreteMovableSprite(sprite, sprite.getPreset());
			this.addMovableSprite(draggableSprite);
//			DragResizeMod.makeResizable(draggableSprite.getDraggableItem(), null);
			addRightClickListener(draggableSprite);
			this.levelPane.getChildren().addAll(draggableSprite.getDraggableItem());
		});
	}
	
	private void addRightClickListener(DraggableSprite draggableSprite){
		EventHandler<? super MouseEvent> currentHandler = draggableSprite.getDraggableItem().getOnMouseClicked();
		draggableSprite.getDraggableItem().setOnMouseClicked((event) -> {
			if (((MouseEvent) event).getButton() == MouseButton.SECONDARY){
				this.getController().getModel().getGame().getCurrentLevel().removeSprite(draggableSprite.getSprite());
			}
			else{
				currentHandler.handle(event);
			}
		});
	}
	
	private void removeSprites(Set<Sprite> removedSprites){
		removedSprites.forEach((removedSprite) -> {
			this.getMovableSprites().forEach((movableSprite) -> {
				if (movableSprite.getSprite() == removedSprite){
					movableSprite.removeListener();
					movableSprite.removePresetListener();
					this.levelPane.getChildren().remove(movableSprite.getDraggableItem());
				}
			});
		});
	}
	
	private void setBackgroundImage(String filePath) {
		Image image = new Image(filePath);
		BackgroundImage backIm = new BackgroundImage(image, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT,
				BackgroundPosition.DEFAULT, new BackgroundSize(100, 100, true, true, true, true));
		this.levelPane.setBackground(new Background(backIm));
	}
	
	private void updateLevelSize(Pane aLevelPane, Level aLevel) {
		if (this.levelScroller.getWidth() != 0.0) {
			aLevel.setWidth((int) this.levelScroller.getViewportBounds().getWidth() * this.horizontalPanes.get());
			aLevel.setHeight((int) this.levelScroller.getViewportBounds().getHeight() * this.verticalPanes.get());
		}
		updatePaneSize(aLevelPane, aLevel);
	}

	private void updatePaneSize(Pane levelPane, Level aLevel) {
		levelPane.setPrefWidth(aLevel.getWidth());
		levelPane.setPrefHeight(aLevel.getHeight());
	}

	@Override
	public IntegerProperty getHorizontalPanes() {
		return this.horizontalPanes;
	}

	@Override
	public IntegerProperty getVerticalPanes() {
		return this.verticalPanes;
	}

	@Override
	public Set<Level> getSelectedSprites() {
		return this.selectedSprites;
	}
}
