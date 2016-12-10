package author.view.pages.sprite.editor.settings;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import author.view.pages.sprite.editor.settings.SettingsFactory.AcceptedParameterTypes;
import game_data.Sprite;
import util.inputfields.BooleanSelector;
import util.inputfields.NumberFieldBox;
import util.inputfields.SettingsEditBox;
import util.inputfields.TextFieldBox;

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
		getIntegerFieldMap().forEach( (s, n) -> {
			int x;
			try {
				x = n.getInteger();
			} catch(NumberFormatException e) {
				x = 0;
			}
			textToValueMap.put(s, x);
		});
		getDoubleFieldMap().forEach( (s, d) -> {
			double x;
			try {
				x = d.getDouble();
			}
			catch(NumberFormatException e) {
				x = 0.0;
			}
			textToValueMap.put(s, x);
		});
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

	public abstract void removeSpriteSetting();

	public void updateSettings(Map<String, Object> aInfoToValueMap){
		for(Entry<String, Object> e : aInfoToValueMap.entrySet()){

			if(getBooleanFieldMap().containsKey(e.getKey()))
				getBooleanFieldMap().get(e.getKey()).setBoolean((boolean) e.getValue());
			else if(getIntegerFieldMap().containsKey(e.getKey()))
				getIntegerFieldMap().get(e.getKey()).setValue((Number) e.getValue());
			else if(getDoubleFieldMap().containsKey(e.getKey()))
				getDoubleFieldMap().get(e.getKey()).setValue((Number) e.getValue());
			else if(getTextFieldMap().containsKey(e.getKey()))
				getTextFieldMap().get(e.getKey()).getTextField().setText(e.toString());
		}
	}

}
