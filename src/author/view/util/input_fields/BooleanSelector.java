package author.view.util.input_fields;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * Helpful wrapper class for boolean input
 * 
 * @author George Bernard
 */
public class BooleanSelector {

	private BooleanProperty myBool;
	
	private Pane myPane;
	private String myText;
	private CheckBox myCheckBox;
	
	/**
	 * If made without a label
	 */
	public BooleanSelector() {
		myPane = new HBox();
		myCheckBox = new CheckBox();
		myCheckBox.setAllowIndeterminate(false);
		myPane.getChildren().add(myCheckBox);
		
		myBool = myCheckBox.selectedProperty();
		
		setBoolean(false);
		setText("");
	}
	
	/**
	 * If to be made with a label
	 * 
	 * @param aText
	 */
	public BooleanSelector(String aText){
		this();
		setText(aText);
	}
	
	/**
	 * If to be made with a boolean on construction
	 * 
	 * @param aBoolean
	 */
	public BooleanSelector(boolean aBoolean) {
		this();
		setBoolean(aBoolean);
	}

	
	/**
	 * If to be made with a boolean and label on construction
	 * 
	 * @param aBoolean
	 * @param aText
	 */
	public BooleanSelector(boolean aBoolean, String aText){
		this(aText);
		setBoolean(aBoolean);
	}
	
	/**
	 * @return if selected
	 */
	public boolean getBoolean(){
		return myBool.get();
	}
	
	/**
	 * @param Selection Value
	 */
	public void setBoolean(boolean aBoolean){
		myBool.set(aBoolean);
	}

	/**
	 * @return immutable boolean property
	 */
	public ReadOnlyBooleanProperty getBooleanProperty(){
		return BooleanProperty.readOnlyBooleanProperty(myBool);
		
	}
	
	/**
	 * @return Input Field
	 */
	public Pane getPane(){
		return myPane;
	}
	
	/**
	 * Sets text label of input field
	 * 
	 * @param aText
	 */
	private void setText(String aText){
		myText = aText;
		myCheckBox.setText(myText);
	}
}
