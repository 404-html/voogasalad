package author.view.pages.sprite.editor.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import author.view.pages.sprite.editor.settings.SettingsFactory.AcceptedParameterTypes;
import author.view.util.input_fields.BooleanSelector;
import author.view.util.input_fields.NumberFieldBox;
import author.view.util.input_fields.SettingsEditBox;
import author.view.util.input_fields.TextFieldBox;
import game_data.Sprite;

public abstract class SpriteSettingsEditBox extends SettingsEditBox {

	private SettingsFactory<?> myFactory;
	private Sprite mySprite;
	
	public SpriteSettingsEditBox(Sprite aSprite, String aName) {
		super(aName);
		mySprite = aSprite;
		myFactory = buildSettingFactory();
		buildSelectors();
	}
	
	public abstract void addSpriteSetting();
	
	protected abstract SettingsFactory<?> buildSettingFactory();
	
	protected final void buildSelectors(){
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

	protected Map<String, Object> makeTextToValueMap(){
		Map<String, Object> textToValueMap = new HashMap<>();
		
		getBooleanFieldMap().forEach( (s, b) -> textToValueMap.put(s, b.getBoolean()) );
		getIntegerFieldMap().forEach( (s, n) -> textToValueMap.put(s, n.getInteger()));
		getDoubleFieldMap().forEach( (s, d) -> textToValueMap.put(s, d.getDouble()));
		getTextFieldMap().forEach( (s, t) -> textToValueMap.put(s, t.getText()));
		
		textToValueMap.put("Sprite", mySprite);
		
		return textToValueMap;
	}
	
	protected final Sprite getSprite(){
		return mySprite;
	}
		
	protected final SettingsFactory<?> getSettingFactory(){
		return myFactory;
	}
	
	
	
}
