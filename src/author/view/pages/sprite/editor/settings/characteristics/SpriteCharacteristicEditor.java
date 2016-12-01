package author.view.pages.sprite.editor.settings.characteristics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import author.view.util.file_helpers.PropertySelector;

import java.util.ResourceBundle;

import game_data.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public class SpriteCharacteristicEditor {

	
	private Pane myPane;
	private Accordion myAccordion;
	private List<CharacteristicEditBox> myCharacteristicEditBoxList;
	private PropertySelector myCharacteristicSelector;
	private Sprite mySprite;
	
	public SpriteCharacteristicEditor(Sprite aSprite, String aSpriteType) {
		mySprite = aSprite;
		
		myPane = new HBox(5);
		myAccordion = new Accordion();
		myCharacteristicEditBoxList = new ArrayList<>();
		myCharacteristicSelector = new PropertySelector( "sprite.characteristics." +  aSpriteType);
		
		myPane.getChildren().add(myCharacteristicSelector.getPane());
		
		for(Entry<String, BooleanProperty> e: myCharacteristicSelector.getCharacteristicSelectedMap().entrySet()){
			if(e.getKey().equals("Characteristic")) continue;
			
			CharacteristicEditBox editBox = new CharacteristicEditBox(aSprite, e.getKey());
			myCharacteristicEditBoxList.add(editBox);	
			
			TitledPane charTitledPane = new TitledPane(e.getKey(), editBox.getPane());
			charTitledPane.disableProperty().bind(e.getValue().not());
			
			myAccordion.getPanes().add(charTitledPane);
		}
		
		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.minViewportWidthProperty().bind(myAccordion.widthProperty());
		scroll.setContent(myAccordion);
		
		myPane.getChildren().add(scroll);
		
	}

	public void addCharacteristics(){
		myCharacteristicEditBoxList.forEach( e -> {
			Boolean charIsAvailable = myCharacteristicSelector
					.getCharacteristicSelectedMap()
					.get(e.getName())
					.getValue();
			if ( charIsAvailable ) mySprite.addCharacteristic(e.getCharacteristic());	
		});
	}
	
	public Node getNode(){
		return myPane;
	}
	
}
