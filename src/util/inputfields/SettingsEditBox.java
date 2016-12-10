package util.inputfields;

import java.util.HashMap;
import java.util.Map;

import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Flexible JavaFX helper class designed to hold an arbitrary amount of primitive input fields.
 * <p>
 * If you need to break encapsulation to get access to input information, feel free to subclass.
 * 
 * @author George Bernard
 */
public class SettingsEditBox {

	private Pane myPane;
	private String myName;
	
	private Map<String, BooleanSelector> myBooleanFieldMap;
	private Map<String, NumberFieldBox> myIntegerFieldMap;
	private Map<String, NumberFieldBox> myDoubleFieldMap;
	private Map<String, TextFieldBox> myTextFieldMap;

	/**
	 * @param aName
	 */
	public SettingsEditBox(String aName) {
		myName = aName;
		
		myPane = new VBox();
		myBooleanFieldMap = new HashMap<>();
		myDoubleFieldMap = new HashMap<>();
		myIntegerFieldMap = new HashMap<>();
		myTextFieldMap = new HashMap<>();

	}
	
	protected final Map<String, BooleanSelector> getBooleanFieldMap() {
		return myBooleanFieldMap;
	}
	
	protected final Map<String, NumberFieldBox> getIntegerFieldMap() {
		return myIntegerFieldMap;
	}
	
	protected final Map<String, NumberFieldBox> getDoubleFieldMap() {
		return myDoubleFieldMap;
	} 
	
	protected final Map<String, TextFieldBox> getTextFieldMap() {
		return myTextFieldMap;
	}
	
	protected final void addTextBoxes( String aText, TextFieldBox aTextFieldBox ){
			myTextFieldMap.put(aText, aTextFieldBox);
			myPane.getChildren().add(aTextFieldBox.getPane());
	}
	
	protected final void addBooleanSelectors( String aText, BooleanSelector aBooleanSelector ){
		myBooleanFieldMap.put(aText, aBooleanSelector);
		myPane.getChildren().add(aBooleanSelector.getPane());
	}
	
	protected final void addIntegerBox(String aText, NumberFieldBox aNumberField ){
		myIntegerFieldMap.put(aText, aNumberField);
		myPane.getChildren().add(aNumberField.getPane());
	}
	
	protected final void addDoubleBox(String aText, NumberFieldBox aNumberField){
		myDoubleFieldMap.put(aText, aNumberField);
		myPane.getChildren().add(aNumberField.getPane());
	}
	
	/**
	 * JavaFX Box containing input information
	 * 
	 * @return JavaFX Pane
	 */
	public Pane getPane(){
		return myPane;
	}	
	
	/**
	 * Returns name of editbox
	 * 
	 * @return name of box
	 */
	public String getName(){
		return myName;
	}

}
