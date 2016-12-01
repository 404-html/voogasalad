package author.view.pages.level_editor.windows;


import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import author.view.pages.level_editor.windows.level_edit_window.ILevelEditorWindowExternal;
import game_data.Sprite;
import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.scene.Node;
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
	private Set<DraggableSprite> draggableSprites = new HashSet<>();
	private IAuthorController authorController;
	
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
	
	protected void addDraggableSprite(DraggableSprite draggableSprite){
		this.draggableSprites.add(draggableSprite);
	}
	
	protected Set<DraggableSprite> getDraggableSprites(){
		return this.draggableSprites;
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
	
	
	protected Set<Sprite> getNewSprites(Set<DraggableSprite> aDraggableSprites, Collection<Sprite> aLevelSprites){
		Set<Sprite> sprites = new HashSet<>();
		aDraggableSprites.forEach((draggableSprite) -> sprites.add(draggableSprite.getSprite()));
		Set<Sprite> levelSprites = new HashSet<>(aLevelSprites);
		levelSprites.removeAll(sprites);
		return levelSprites;
	}
}
