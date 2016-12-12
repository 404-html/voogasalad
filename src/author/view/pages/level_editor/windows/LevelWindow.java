package author.view.pages.level_editor.windows;

import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteMovableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.model.game_observables.draggable_sprite.context_menu.FunctionalMenuItemFactory;
import author.model.game_observables.draggable_sprite.context_menu.SpriteContextMenu;
import author.view.pages.level_editor.windows.level_window.ILevelWindowPane;
import author.view.pages.level_editor.windows.level_window.LevelWindowPaneFactory;
import author.view.pages.level_editor.windows.level_window.LevelWindowScrollerFactory;
import author.view.pages.level_editor.windows.level_window.LevelWindowToolBarFactory;
import author.view.util.undo.IRevertManager;
import author.view.util.undo.RevertManagerFactory;
import game_data.Level;
import game_data.Location;
import game_data.ScrollType;
import game_data.Sprite;
import game_engine.properties.RandomMoveDisjointHandler;
import game_engine.properties.RandomMoveHandler;
import game_engine.properties.RandomMoveHandler.Orientation;
import javafx.application.Platform;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SetProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleSetProperty;
import javafx.collections.FXCollections;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * This window is the actual level editor, where sprites will be placed from the
 * EntityWindow
 * 
 * @author Jordan Frazier, Cleveland Thompson
 * @see EntityWindow
 * @see ../LevelEditor
 */
public class LevelWindow extends AbstractLevelEditorWindow implements ILevelWindowInternal {

	private ScrollPane levelScroller;
	private ILevelWindowPane levelWindowPane;
	private static final String STYLESHEET = "data/gui/scrollViewport.css";

	private IntegerProperty horizontalPanes = new SimpleIntegerProperty(1);
	private IntegerProperty verticalPanes = new SimpleIntegerProperty(1);
	private Map<Level, ILevelWindowPane> levelPanes = new HashMap<>();
	private Map<Level, IRevertManager> revertManagers = new HashMap<>();
	private SetProperty<DraggableSprite> selectedSprites = new SimpleSetProperty<>(FXCollections.observableSet());
	private DraggableSprite selectedSprite;

	public LevelWindow(IAuthorController authorController) {
		super(authorController);
		createScroller();
		super.getWindow().getStylesheets().add(getStyleSheet());
		super.getWindow().getStyleClass().add("lol");
		
	}

	private void createScroller() {
		this.levelWindowPane = new LevelWindowPaneFactory((ILevelWindowInternal) this, this.getController()).create();
		this.levelScroller = new LevelWindowScrollerFactory((ILevelEditorWindowInternal) this).create();
		this.levelScroller.setContent(this.levelWindowPane.getPane());
		this.getWindow().getChildren().add(this.levelScroller);
	}

	@Override
	protected void createToolBar() {
		super.getWindow().getChildren()
				.add(new LevelWindowToolBarFactory().createToolBar((ILevelWindowInternal) this, this.getController()));
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

			this.levelWindowPane = new LevelWindowPaneFactory((ILevelWindowInternal) this, this.getController())
					.create();
			this.levelPanes.put(aLevel, this.levelWindowPane);
			this.levelScroller.setContent(this.levelWindowPane.getPane());

			this.levelScroller.boundsInLocalProperty()
					.addListener((listener) -> updateLevelSize(this.levelWindowPane.getPane(), aLevel));
			this.horizontalPanes.addListener((listener) -> updateLevelSize(this.levelWindowPane.getPane(), aLevel));
			this.verticalPanes.addListener((listener) -> updateLevelSize(this.levelWindowPane.getPane(), aLevel));
			createUndo(aLevel);
			initSelectedSpritesListener(aLevel);
			aLevel.addListener((level) -> {
				updatePane(aLevel);
			});
			updatePaneSize(this.levelWindowPane.getPane(), aLevel);
		}

		this.levelWindowPane = this.levelPanes.get(aLevel);
		this.levelScroller.setContent(this.levelWindowPane.getPane());
		updatePane(aLevel);
	}
	
	private void initSelectedSpritesListener(Level aLevel){
		selectedSprites.addListener((observable, oldval, newval) -> {
			getMovableSprites(aLevel).forEach((draggablesprite) -> draggablesprite.setDeselected());
			this.selectedSprites.get().forEach((draggablesprite) -> draggablesprite.setSelected());
		});
	}

	private void createUndo(Level aLevel) {
		this.revertManagers.put(aLevel, new RevertManagerFactory().create(aLevel));
		this.getWindow().setOnKeyPressed((event) -> {
			if (event.getCode().equals(KeyCode.Z) && event.isControlDown()) {
				this.revertManagers.get(this.getController().getModel().getGame().getCurrentLevel()).undo();
			} else if (event.getCode().equals(KeyCode.Y) && event.isControlDown()) {
				this.revertManagers.get(this.getController().getModel().getGame().getCurrentLevel()).redo();
			}
		});
	}

	private void updatePane(Level aLevel) {
		if (aLevel.getBackgroundImageFilePath() != null)
			setBackgroundImage(aLevel.getBackgroundImageFilePath());

		addSprites(this.getNewSprites(this.getMovableSprites(aLevel), aLevel.getMySpriteList()), aLevel);
		removeSprites(this.getRemovedSprites(this.getMovableSprites(aLevel), aLevel.getMySpriteList()), aLevel);
	}

	// Helper Methods

	private void addSprites(Set<Sprite> addedSprites, Level aLevel) {
		addedSprites.forEach((sprite) -> {
			DraggableSprite draggableSprite = new ConcreteMovableSprite(sprite, sprite.getPreset());
			this.addMovableSprite(aLevel, draggableSprite);
			addSpriteClickListeners(draggableSprite);

			this.levelWindowPane.getPane().getChildren().addAll(draggableSprite.getDraggableItem());
		});

	}

	private void addSpriteClickListeners(DraggableSprite draggableSprite) {
		// EventHandler<? super MouseEvent> clickedHandler =
		// draggableSprite.getDraggableItem().getOnMouseClicked();
		draggableSprite.getDraggableItem().setOnMouseClicked((event) -> {
			//clickedHandler.handle(event);
			this.getWindow().requestFocus();
			if (((MouseEvent) event).getButton() == MouseButton.SECONDARY) {
				openContextMenu(draggableSprite, event);
				event.consume();
			} else if (event.isShiftDown()) {
				this.selectedSprite = draggableSprite;
				this.levelWindowPane.updateGrid(this.selectedSprite.getSprite().getWidth(),
						this.selectedSprite.getSprite().getHeight());
				event.consume();
			} else if (event.isControlDown()) {
				if (!this.selectedSprites.contains(draggableSprite)) {
					this.selectedSprites.add(draggableSprite);
				} else {
					this.selectedSprites.remove(draggableSprite);
				}
				event.consume();
			}

		});
		EventHandler<? super MouseEvent> draggedHandler = draggableSprite.getDraggableItem().getOnMouseDragged();
		draggableSprite.getDraggableItem().setOnMouseDragged((event) -> {
			draggedHandler.handle(event);
			if (event.isShiftDown()) {
				this.levelWindowPane.updateGrid(draggableSprite.getSprite().getWidth(),
						draggableSprite.getSprite().getHeight());
			}

		});
		EventHandler<? super MouseEvent> releasedHandler = draggableSprite.getDraggableItem().getOnMouseReleased();
		draggableSprite.getDraggableItem().setOnMouseReleased((event) -> {

			if (event.isShiftDown()) {
				int newX = this.levelWindowPane.adjustX((int) draggableSprite.getDraggableItem().getLayoutX()
						+ draggableSprite.getSprite().getWidth() / 2);
				int newY = this.levelWindowPane.adjustY((int) draggableSprite.getDraggableItem().getLayoutY()
						+ draggableSprite.getSprite().getHeight() / 2);
				draggableSprite.getSprite().setLocation(new Location(newX, newY));
				this.levelWindowPane.removeGrid();
			} else {
				releasedHandler.handle(event);
			}
		});

	}

	private void removeSprites(Set<Sprite> removedSprites, Level aLevel) {
		Set<DraggableSprite> removeDraggables = new HashSet<>();
		removedSprites.forEach((removedSprite) -> {
			this.getMovableSprites(aLevel).forEach((movableSprite) -> {
				if (movableSprite.getSprite() == removedSprite) {
					movableSprite.removeListener();
					movableSprite.removePresetListener();
					removeDraggables.add(movableSprite);
					this.levelWindowPane.getPane().getChildren().remove(movableSprite.getDraggableItem());
				}
			});
		});
		this.removeMovableSprites(aLevel, removeDraggables);
	}

	private void openContextMenu(DraggableSprite sprite, MouseEvent event) {
		SpriteContextMenu contextMenu = new SpriteContextMenu(sprite, this.getController());
		if (this.getRandomProperty().get()) contextMenu.getMenu().getItems().add(new FunctionalMenuItemFactory().create("Randomize", e -> {
			ScrollType scrollType = getController().getModel().getGame().getScrollType();
			if (scrollType.equals(ScrollType.CENTER)){
				showAlert();
			}
			else{
				RandomMoveHandler randomMoveHandler = new RandomMoveDisjointHandler(scrollType.equals(ScrollType.HORIZONTAL_LEFT) | scrollType.equals(ScrollType.HORIZONTAL_RIGHT) ? Orientation.HORIZONTAL : Orientation.VERTICAL);
				sprite.getSprite().setMyRandomMoveHandler(randomMoveHandler);
			}
			
		}).getItem());
		contextMenu.getMenu().show(sprite.getImageView(), event.getScreenX(), event.getScreenY());
	}
	
	private void showAlert(){
		Alert scrollAlert = new Alert(AlertType.WARNING);
		scrollAlert.setTitle("Improper Scroller Type");
		scrollAlert.setContentText("Cannot Randomize a Centered Scroller");
		scrollAlert.show();
	}

	private void setBackgroundImage(String filePath) {
		String imagePath = new File(filePath).toURI().toString();
		getWindow().setStyle("-fx-background-image: url('" + imagePath + "');"
				+ "    -fx-background-repeat: no-repeat;"+ "-fx-background-size: 100%;" + "-fx-background-position: center center;");
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

	private String getStyleSheet() {
		File css = new File(STYLESHEET);
		return css.toURI().toString();
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
	public DraggableSprite getSelectedSprite() {
		return this.selectedSprite;
	}

	@Override
	public SetProperty<DraggableSprite> getSelectedSprites() {
		return this.selectedSprites;
	}

}