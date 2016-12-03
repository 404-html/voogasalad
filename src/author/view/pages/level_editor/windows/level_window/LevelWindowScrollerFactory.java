/**
 * 
 */
package author.view.pages.level_editor.windows.level_window;


import author.view.pages.level_editor.windows.ILevelEditorWindowInternal;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.Pane;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class LevelWindowScrollerFactory {
	
	private ILevelEditorWindowInternal levelEditorWindowInternal;
	private ScrollPane levelScroller;
	/**
	 * 
	 */
	public LevelWindowScrollerFactory(ILevelEditorWindowInternal levelEditorWindowInternal) {
		this.levelEditorWindowInternal = levelEditorWindowInternal;
	}
	
	public ScrollPane create(){
		this.levelScroller = new ScrollPane();
		this.levelScroller.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.levelScroller.setHbarPolicy(ScrollBarPolicy.AS_NEEDED);
		this.levelScroller.prefViewportWidthProperty().bind(this.levelEditorWindowInternal.getWindow().widthProperty());
		this.levelScroller.prefViewportHeightProperty().bind(this.levelEditorWindowInternal.getWindow().heightProperty());
		return this.levelScroller;
	}

}
