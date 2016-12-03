package author.view.pages.sprite.editor.settings;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import author.view.util.file_helpers.PropertySelector;
import game_data.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;

public abstract class SpriteSettingsEditor {

	private Pane myPane;
	private Accordion myAccordion;
	private List<SpriteSettingsEditBox> myEditBoxList;
	private PropertySelector mySelector;
	private Sprite mySprite;
	
	
	public SpriteSettingsEditor(Sprite aSprite) {
		mySprite = aSprite;
		
		myPane = new HBox(5);
		myAccordion = new Accordion();
		myEditBoxList = new ArrayList<>();
		mySelector = new PropertySelector( getDirectoryPath() + mySprite.getClass().getSimpleName());
		
		myPane.getChildren().add(mySelector.getPane());
		
		for(Entry<String, BooleanProperty> e: mySelector.getSelectedMap().entrySet()){
			
			SpriteSettingsEditBox editBox = makeEditBox(aSprite, e.getKey());
			myEditBoxList.add(editBox);	
			
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
		
		myPane.getChildren().add(makeViewColumn(aSprite).getPane());

	}

	public final void addSettings(){
		getEditBoxList().forEach( e -> {
			Boolean charIsAvailable = getPropertySelector()
					.getSelectedMap()
					.get(e.getName())
					.getValue();
			if ( charIsAvailable ) e.addSpriteSetting();	
		});
	}
	
	protected abstract String getDirectoryPath();
	
	protected abstract SpriteSettingsEditBox makeEditBox(Sprite aSprite, String aName);
	
	protected abstract SettingsViewColumn makeViewColumn(Sprite aSprite);
	
	protected final List<SpriteSettingsEditBox> getEditBoxList(){
		return myEditBoxList;
	}
	
	protected final PropertySelector getPropertySelector(){
		return mySelector;
	}

	public final Pane getPane(){
		return myPane;
	}
	
}
