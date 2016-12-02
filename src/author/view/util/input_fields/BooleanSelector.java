package author.view.util.input_fields;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class BooleanSelector {

	
	private BooleanProperty myBool;
	
	private Pane myPane;
	private String myText;
	private CheckBox myCheckBox;
	
	public BooleanSelector() {
		myPane = new HBox();
		myCheckBox = new CheckBox();
		myCheckBox.setAllowIndeterminate(false);
		myPane.getChildren().add(myCheckBox);
		
		myBool = myCheckBox.selectedProperty();
		
		setBoolean(false);
		setText("");
	}
	
	public BooleanSelector(String aText){
		this();
		setText(aText);
	}
	
	public BooleanSelector(boolean aBoolean) {
		this();
		setBoolean(aBoolean);
	}

	public BooleanSelector(boolean aBoolean, String aText){
		this(aText);
		setBoolean(aBoolean);
	}
	
	public boolean getBoolean(){
		return myBool.get();
	}
	
	public void setBoolean(boolean aBoolean){
		myBool.set(aBoolean);
	}

	public Pane getPane(){
		return myPane;
	}
	
	private void setText(String aText){
		myText = aText;
		myCheckBox.setText(myText);
	}
}
