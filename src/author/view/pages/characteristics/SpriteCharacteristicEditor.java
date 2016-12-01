package author.view.pages.characteristics;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

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
	private CharacteristicSelector myCharacteristicSelector;
	
	public SpriteCharacteristicEditor() {
		myPane = new HBox(5);
		myAccordion = new Accordion();
		myCharacteristicEditBoxList = new ArrayList<>();
		myCharacteristicSelector = new CharacteristicSelector();
		
		myPane.getChildren().add(myCharacteristicSelector.getPane());
		
		for(Entry<String, BooleanProperty> e: myCharacteristicSelector.getCharacteristicSelectedMap().entrySet()){
			CharacteristicEditBox editBox = new CharacteristicEditBox();
			myCharacteristicEditBoxList.add(editBox);	
			
			TitledPane charTitledPane = new TitledPane(e.getKey(), editBox.getPane());
			//charTitledPane.visibleProperty().bind(e.getValue());
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

	public Node getNode(){
		return myPane;
	}
	
}
