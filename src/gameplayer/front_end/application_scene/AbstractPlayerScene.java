package gameplayer.front_end.application_scene;

import gameplayer.back_end.resources.FrontEndResources;
import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator.ButtonDisplay;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;

/**
 * Representation of generic functionality each scene might need
 * 
 * @author tedmarchildon, hannah
 *
 */

public abstract class AbstractPlayerScene implements IDisplay {
	
	protected Scene myScene;
	private IGUIGenerator myGUIGenerator;
	private VBox myOptions;
	
	public AbstractPlayerScene() {
	    myGUIGenerator = new GUIGenerator();
	    myOptions = new VBox(FrontEndResources.BOX_INSETS.getDoubleResource());
	    myOptions.setAlignment(Pos.CENTER);
	    myOptions.setFillWidth(true);
	}
	
	public void clear() {
		myOptions.getChildren().clear();
	}
	
	protected IGUIGenerator getGUIGenerator(){
		return myGUIGenerator;
	}
	
	protected VBox getOptions(){
		return myOptions;
	}
	
	public void addButton(String aLabel, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aType) {
		myOptions.getChildren().add(getGUIGenerator().createButton(aLabel, 0, 0, aHandler, aType));
	}
	
	public void addNode(Node aNode) {
		aNode.setLayoutX(0);
		aNode.setLayoutY(0);
		myOptions.getChildren().add(aNode);
	}
	
	@Override
	public Scene init() {
		return myScene;
	}
	
}
