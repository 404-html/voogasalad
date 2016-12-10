/**
 * 
 */
package author.view.util.edit_window;

import author.view.util.authoring_buttons.ButtonFactory;
import game_data.GameObject;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import util.facades.ToolBarBuilder;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
abstract class GameObjectEditPage <T extends GameObject>{
	
	private IGameObjectEditWindowInternal iGameObjectEditWindowInternal;
	private VBox container;
	private GameObjectEditBox<T> gameObjectEditBox;
	private ToolBarBuilder toolBarBuilder;
	private T gameObject;
	
	
	GameObjectEditPage(IGameObjectEditWindowInternal iGameObjectEditWindowInternal) {
		this.iGameObjectEditWindowInternal = iGameObjectEditWindowInternal;
		this.gameObjectEditBox = getGameObjectBox();
		initToolBarBuilder();
		initPane();
	}
	
	protected abstract GameObjectEditBox<T> getGameObjectBox();
	
	private void initToolBarBuilder(){
		this.toolBarBuilder = new ToolBarBuilder();
		this.toolBarBuilder.addBurst(new ButtonFactory().createButton("Save and Close", e -> saveAndClose(this.gameObjectEditBox.getResult())).getButton());
		this.toolBarBuilder.addBurst(new ButtonFactory().createButton("Cancel", e -> cancel()).getButton());
	}
	
	private void initPane(){
		this.container = new VBox();
		this.container.getChildren().addAll(this.toolBarBuilder.getToolBar(), this.gameObjectEditBox.getEditBox());
	}
	
	public Pane getPane(){
		return this.container;
	}
	
	private void saveAndClose(T aGameObject){
		if (aGameObject != null){
			this.gameObject = aGameObject;
			this.iGameObjectEditWindowInternal.close();
		}
	}
	
	private void cancel(){
		this.iGameObjectEditWindowInternal.close();
	}
	public T getResult(){
		return this.gameObject;
	}

}
