package util.inputfields;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.ReadOnlyBooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

/**
 * JavaFX Helper class to handle boolean inputs.  
 * 
 * @author George Bernard
 */
public class BooleanSelector {

	private BooleanProperty myBool;
	
	private Pane myPane;
	private String myText;
	private CheckBox myCheckBox;
	
	/**
	 * Constructs a BooleanSelector without descriptive text
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
	 * Constructs a BooleanSelector with the given text
	 * 
	 * @param text description
	 */
	public BooleanSelector(String aText){
		this();
		setText(aText);
	}
	
	/**
	 * Constructs a BooleanSelector in the given boolean state
	 * 
	 * @param aBoolean
	 */
	public BooleanSelector(boolean aBoolean) {
		this();
		setBoolean(aBoolean);
	}

	
	/**
	 * Constructs a BooleanSelector with the given Boolean state and with the given text.
	 * 
	 * @param aBoolean
	 * @param aText
	 */
	public BooleanSelector(boolean aBoolean, String aText){
		this(aText);
		setBoolean(aBoolean);
	}
	
	/**
	 * Returns primitive boolean value of underlying selector
	 * 
	 * @return if selected is true
	 */
	public boolean getBoolean(){
		return myBool.get();
	}
	
	/**
	 * Sets whether the selector is selected
	 * 
	 * @param Selection Value
	 */
	public void setBoolean(boolean aBoolean){
		myBool.set(aBoolean);
	}

	/**
	 * Returns the ReadOnly/immutable boolean property of the underlying selector
	 * 
	 * @return immutable boolean property
	 */
	public ReadOnlyBooleanProperty getBooleanProperty(){
		return BooleanProperty.readOnlyBooleanProperty(myBool);
		
	}
	
	/**
	 * Returns the javafx Pane showing the selector field
	 * 
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
