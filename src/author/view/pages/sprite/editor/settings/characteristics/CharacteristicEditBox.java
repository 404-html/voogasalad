package author.view.pages.sprite.editor.settings.characteristics;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.sun.scenario.Settings;

import author.view.pages.sprite.editor.settings.SettingsEditBox;
import author.view.pages.sprite.editor.settings.characteristics.CharacteristicFactory.AcceptedParameterTypes;
import author.view.util.input_fields.BooleanSelector;
import author.view.util.input_fields.NumberFieldBox;
import author.view.util.input_fields.TextFieldBox;
import game_data.Sprite;
import game_data.characteristics.Characteristic;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class CharacteristicEditBox extends SettingsEditBox {

	private CharacteristicFactory myFactory;
	
	CharacteristicEditBox(Sprite aSprite, String aName) {
		super(aName);
		myFactory = new CharacteristicFactory(aName, aSprite);
		buildSelectors();
	}

	void buildSelectors(){
		Map<String, AcceptedParameterTypes> paramMap = myFactory.getParameterTextToTypeMap();
		
		for( Entry<String, AcceptedParameterTypes> e: paramMap.entrySet()) {
			
			switch (e.getValue()) {
			case BOOL:
				addBooleanSelectors(e.getKey(), new BooleanSelector(e.getKey()) );
				break;
			case INT:
				addIntegerBox(e.getKey(), new NumberFieldBox(e.getKey()) );
				break;
			case DOUBLE:
				addDoubleBox(e.getKey(), new NumberFieldBox(e.getKey()) );
				break;
			case STRING:
				addTextBoxes(e.getKey(), new TextFieldBox(e.getKey()) );
				break;
			case UNACCEPTABLE:
			case SPRITE:
			default:
				break;
			}
		}
	}
	
	Characteristic getCharacteristic(){
		Map<String, Object> textToValueMap = new HashMap<>();
		
		getBooleanFieldMap().forEach( (s, b) -> textToValueMap.put(s, b.getBoolean()) );
		getIntegerFieldMap().forEach( (s, n) -> textToValueMap.put(s, n.getInteger()));
		getDoubleFieldMap().forEach( (s, d) -> textToValueMap.put(s, d.getDouble()));
		getTextFieldMap().forEach( (s, t) -> textToValueMap.put(s, t.getText()));
		
		return myFactory.getCharacteristicInstance(textToValueMap);
	}
}
