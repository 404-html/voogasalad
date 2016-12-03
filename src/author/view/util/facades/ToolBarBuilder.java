package author.view.util.facades;

import java.util.Arrays;
import java.util.Collection;

import javafx.scene.Node;
import javafx.scene.control.Separator;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Provides a simple interface for building a toolbar to minimize code reuse.
 * If greater functionality or specification required use getToolBar() method.
 * 
 * 
 * @author George Bernard
 */
public class ToolBarBuilder {

	private ToolBar myToolBar;
	
	public ToolBarBuilder(){
		myToolBar = new ToolBar();
	}
	
	/**
	 * @param aNodes
	 */
	public void addBurst(Node... aNodes){
		this.addBurst(Arrays.asList(aNodes));
	}
	
	/**
	 * Adds a "burst" of javaFX nodes to the toolbar.
	 * 
	 * @param aNodes
	 */
	public void addBurst(Collection<Node> aNodes){
		myToolBar.getItems().add(new Separator());
		myToolBar.getItems().addAll(aNodes);
		myToolBar.getItems().add(new Separator());
	}
	
	/**
	 * Adds a filler block between bursts in the toolbar
	 */
	public void addFiller(){
		final Pane filler = new Pane();
        HBox.setHgrow(
                filler,
                Priority.SOMETIMES
        );
        
        myToolBar.getItems().add(filler);
	}
	
	/**
	 * @return the toolbar under construction
	 */
	public ToolBar getToolBar(){
		return myToolBar;
	}
}
