package author.view.pages.sprite.editor.settings.characteristics;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.Characteristic;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

public class CharacteristicFactory {

	public enum AcceptedParameterTypes {
		BOOL(boolean.class),
		STRING(String.class),
		INT(int.class),
		DOUBLE(double.class),
		SPRITE(Sprite.class),
		UNACCEPTABLE(null);
		
		private Class<?> myType;
		
		private AcceptedParameterTypes(Class<?> aType){
			myType = aType;
		}
		
		Class<?> getType() {
			return myType;
		}
		
		public static AcceptedParameterTypes discernType(Class<?> aClass){
			if(aClass == null) return UNACCEPTABLE;
			
			for(AcceptedParameterTypes apt : AcceptedParameterTypes.class.getEnumConstants()) {
				if(aClass.equals(apt.myType)){
					return apt;
				}
			}
			
			return UNACCEPTABLE;
		}
		
	}
	
	private static final String ROOT = "game_data.characteristics.";
	
	private Class<?> myCharacteristicClass;
	private Constructor<?> myCharacteristicConstructor;
	private Sprite mySprite;
	
	private Map<String, AcceptedParameterTypes> myParameterTextToTypeMap;
	
	public CharacteristicFactory(String aCharacteristicName, Sprite aSprite) {
			myParameterTextToTypeMap = new LinkedHashMap<>();
			mySprite = aSprite;
			
			String filePath = ROOT + aCharacteristicName;
			
			try {
				myCharacteristicClass = Class.forName(filePath);
				myCharacteristicConstructor = myCharacteristicClass.getConstructors()[0];

				Class<?>[] parameterTypes = myCharacteristicConstructor.getParameterTypes();
				ParameterAnnotation annotations = (ParameterAnnotation) myCharacteristicConstructor.getAnnotations()[0];
				
				for(int i = 0; i < annotations.parameters().length && i < parameterTypes.length; i++){
					myParameterTextToTypeMap.put(
							annotations.parameters()[i],
							AcceptedParameterTypes.discernType(parameterTypes[i])
							);
				}
								
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}

			
	}

	public Characteristic getCharacteristicInstance(Map<String, Object> aTextToValueMap) {
		Object[] parameters = new Object[aTextToValueMap.size() + 1];
		
		String[] keys = new String[myParameterTextToTypeMap.size()];
		myParameterTextToTypeMap.keySet().toArray(keys);
		
		for(int i = 0; i < parameters.length - 1; i++){
			 parameters[i] = aTextToValueMap.get(keys[i]);
		}
		
		parameters[parameters.length - 1] = mySprite;
		
		try {
			return (Characteristic) myCharacteristicConstructor.newInstance(parameters);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			System.out.println("For now this does nothing [Characteristic Factory]");
			System.out.print(Arrays.asList(parameters));
			e.printStackTrace();
			return null;
		}
	}
		
	public Map<String, AcceptedParameterTypes> getParameterTextToTypeMap() {
		return myParameterTextToTypeMap;
	} 
	
}
