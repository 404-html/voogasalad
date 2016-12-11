package author.view.pages.sprite.editor.settings;

import java.util.Arrays;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import author.view.pages.sprite.editor.settings.view.SettingsViewColumn;
import game_data.Sprite;
import javafx.beans.property.BooleanProperty;
import javafx.scene.Node;
import javafx.scene.control.Accordion;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import util.inputfields.PropertySelector;

public abstract class SpriteSettingsEditor {

	private Pane myPane;
	private Accordion myAccordion;
	private Map<String, SpriteSettingsEditBox> myEditBoxMap;
	private PropertySelector mySelector;
	private Sprite mySprite;

	public SpriteSettingsEditor(Sprite aSprite) {
		mySprite = aSprite;

		myPane = new HBox();
		myAccordion = new Accordion();
		myEditBoxMap = new TreeMap<>();
		mySelector = new PropertySelector( getDirectoryPath() + mySprite.getClass().getSimpleName());

		for(Entry<String, BooleanProperty> e: mySelector.getSelectedMap().entrySet()){

			SpriteSettingsEditBox editBox = makeEditBox(aSprite, e.getKey());
			myEditBoxMap.put(e.getKey(), editBox);	

			TitledPane charTitledPane = new TitledPane(e.getKey(), editBox.getPane());
			charTitledPane.disableProperty().bind(e.getValue().not());

			myAccordion.getPanes().add(charTitledPane);
		}

		ScrollPane scroll = new ScrollPane();
		scroll.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
		scroll.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
		scroll.minViewportWidthProperty().bind(myAccordion.widthProperty());
		scroll.setContent(myAccordion);

		Pane viewColumn = makeViewColumn(aSprite).getPane();

		Arrays.asList(new Node[] { mySelector.getPane(), scroll, viewColumn }).forEach(n -> {
			myPane.getChildren().add(n);
		});

		updateSettings();
	}

	public final void addSettings(){

		getEditBoxList().values().forEach( e -> {
			Boolean charIsAvailable = getPropertySelector()
					.getSelectedMap()
					.get(e.getName())
					.getValue();

			if ( charIsAvailable ) 
				e.addSpriteSetting();	
			else 				   
				e.removeSpriteSetting();
		});
	}

	public abstract void updateSettings();


	protected abstract String getDirectoryPath();

	protected abstract SpriteSettingsEditBox makeEditBox(Sprite aSprite, String aName);

	protected abstract SettingsViewColumn makeViewColumn(Sprite aSprite);

	protected final Map<String, SpriteSettingsEditBox> getEditBoxList(){
		return myEditBoxMap;
	}

	protected final Sprite getSprite(){
		return mySprite;
	}

	protected final PropertySelector getPropertySelector() {
		return mySelector;
	}

	public final Pane getPane() {
		return myPane;
	}

}
