package util.inputfields;

import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Loads a properties file and creates both a checkbox list for each key, but also gives you 
 * the booleanproperty of each checkbox to determine if activated.
 * 
 * @see CheckBox
 * @see BooleanProperty
 * @author George Bernard
 */
public class PropertySelector {
	
	private Map<String, BooleanProperty> mySelectedMap;
	private Pane myPane;
	private ResourceBundle myResources;
	
	private PropertySelector() {
		myPane = new VBox();
		mySelectedMap = new TreeMap<String, BooleanProperty>();
	}
	
	/**
	 * Must specify file path
	 * 
	 * @param aFilePath
	 */
	public PropertySelector(String aFilePath) {
		this();
		myResources = PropertyResourceBundle.getBundle( aFilePath );
		Set<String> keySet = new TreeSet<>(myResources.keySet());
		
		for(String s : keySet ) {
			CheckBox check = new CheckBox(s);
			check.setAllowIndeterminate(false);
			myPane.getChildren().add(check);
			mySelectedMap.put(s, check.selectedProperty());
		}
	}
	
	/**
	 * @return CheckBox List Pane
	 */
	public Pane getPane(){
		return myPane;
	}
	
	
	/**
	 * @return Map of properties key to boolean selected property
	 */
	public Map<String, BooleanProperty> getSelectedMap(){
		return mySelectedMap;
	}
}
