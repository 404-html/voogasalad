package author.view.pages.characteristics;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import author.view.util.FolderListor;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CharacteristicSelector {
	
	private Map<String, BooleanProperty> myCharacteristicSelectedMap;
	private Pane myPane;
	
	private static final String CHARACTERISTIC_FOLDER = "./src/game_data/characteristics/";
	private static final String EXTENSION = ".java";
	
	public CharacteristicSelector() {
		myPane = new VBox();
		myCharacteristicSelectedMap = new TreeMap<String, BooleanProperty>();
		
		for(String s : new FolderListor(CHARACTERISTIC_FOLDER).getFilesWithExtension(EXTENSION) ) {
			CheckBox check = new CheckBox(s);
			check.setAllowIndeterminate(false);
			
			myPane.getChildren().add(check);
			myCharacteristicSelectedMap.put(s, check.selectedProperty());
		}
	}
	
	public Pane getPane(){
		return myPane;
	}
	
	public Map<String, BooleanProperty> getCharacteristicSelectedMap(){
		return myCharacteristicSelectedMap;
	}
}
