package util.inputfields;

import javafx.scene.Node;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class EnabledField<T> {

	Pane myPane;
	Pane myContents;
	
	CheckBox myCheckBox;
	T myType;
	Node myNode;
	
	public EnabledField(T aType, Node aNode) {
		myType = aType;
		myNode = aNode;
				
		myPane = new HBox(10);
		myContents = new HBox();
		
		myCheckBox = new CheckBox();
		myCheckBox.setIndeterminate(false);
		myCheckBox.minHeightProperty().bind(myPane.heightProperty());
		myCheckBox.minWidthProperty().bind(myCheckBox.minHeightProperty());
		myCheckBox.setStyle("-fx-padding: 10px;");
		myContents.disableProperty().bind(myCheckBox.selectedProperty().not());
		myContents.getChildren().addAll( aNode );
		myPane.getChildren().addAll(myCheckBox, myContents);
	}
	
	public T get() {
		return myType;
	}
	
	public void setEnabled(boolean aEnabled){
		myCheckBox.setSelected(aEnabled);
	}
	
	public boolean isEnabled(){
		return myCheckBox.isSelected();
	}
	
	public Pane getPane(){
		return myPane;
	}
}
