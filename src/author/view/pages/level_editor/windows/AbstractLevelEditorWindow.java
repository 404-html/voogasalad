package author.view.pages.level_editor.windows;


import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteMovableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.pages.level_editor.windows.level_edit_window.ILevelEditorWindowExternal;
import game_data.Level;
import game_data.Sprite;
import game_engine.actions.Move;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * The abstract class that windows in the level editor will extend
 * 
 * @author Jordan Frazier, Cleveland Thompson
 *
 */
public abstract class AbstractLevelEditorWindow implements ILevelEditorWindowExternal, ILevelEditorWindowInternal{

	private Pane myWindow;
	private Map<Level, Set<DraggableSprite>> draggableSprites = new HashMap<>();
	private IAuthorController authorController;
	
	private BooleanProperty isRandom = new SimpleBooleanProperty(false);
	
	public AbstractLevelEditorWindow(IAuthorController authorController){
		this.authorController = authorController;
		createWindow();
		createToolBar();
		initListener();
	}
	protected abstract void initListener();
	
	protected abstract void createToolBar();
	
	public Pane getWindow() {
		return myWindow;
	}
	
	protected void addMovableSprite(Level aLevel, DraggableSprite movableSprite){
		if (!this.draggableSprites.containsKey(aLevel)){
			Set<DraggableSprite> draggableSprites = new HashSet<>();
			draggableSprites.add(movableSprite);
		}
		else{
			this.draggableSprites.get(aLevel).add(movableSprite);
		}
	}
	
	
	protected void removeMovableSprites(Level aLevel, Collection<DraggableSprite> movableSprites){
		if (this.draggableSprites.containsKey(aLevel)){
			this.draggableSprites.get(aLevel).removeAll(movableSprites);
		}
	}
	
	protected Set<DraggableSprite> getMovableSprites(Level aLevel){
		if (this.draggableSprites.containsKey(aLevel)){
			return this.draggableSprites.get(aLevel);
		}else{
			Set<DraggableSprite> draggableSprites = new HashSet<>();
			this.draggableSprites.put(aLevel, draggableSprites);
			return draggableSprites;
		}
	}
	
	protected Pane createWindow() {
		myWindow = new VBox();
		return myWindow;
	}
	
	protected IAuthorController getController() {
		return authorController;
	}
	
	protected ImageView getImageView(String path, ReadOnlyDoubleProperty width, ReadOnlyDoubleProperty height){
		ImageView imageView = new ImageView(path);
		imageView.setFitWidth(width.doubleValue());
		imageView.setFitHeight(height.doubleValue());
		return imageView;
	}
	
	
	protected Set<Sprite> getNewSprites(Set<DraggableSprite> set, Collection<Sprite> aLevelSprites){
		Set<Sprite> sprites = new HashSet<>();
		set.forEach((draggableSprite) -> sprites.add(draggableSprite.getSprite()));
		Set<Sprite> levelSprites = new HashSet<>(aLevelSprites);
		levelSprites.removeAll(sprites);
		return levelSprites;
	}
	
	protected Set<Sprite> getRemovedSprites(Set<DraggableSprite> set, Collection<Sprite> aLevelSprites){
		Set<Sprite> sprites = new HashSet<>();
		set.forEach((draggableSprite) -> sprites.add(draggableSprite.getSprite()));
		Set<Sprite> levelSprites = new HashSet<>(aLevelSprites);
		sprites.removeAll(levelSprites);
		return sprites;
	}
	
	public BooleanProperty getRandomProperty() {
		return this.isRandom;
	}
}
