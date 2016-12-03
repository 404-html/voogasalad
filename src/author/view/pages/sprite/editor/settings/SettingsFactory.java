package author.view.pages.sprite.editor.settings;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import game_data.Sprite;
import game_data.characteristics.characteristic_annotations.ParameterAnnotation;

public abstract class SettingsFactory<T> {

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

	protected Map<String, AcceptedParameterTypes> myParameterTextToTypeMap;
	protected Sprite mySprite;
	protected Class<?> myClass;
	protected Constructor<?> myConstructor;
		
	
	public SettingsFactory(String aName, Sprite aSprite) {
		myParameterTextToTypeMap = new LinkedHashMap<>();
		
		String filePath = getRootDirectoryPath() + aName;
		
		try {
			myClass = Class.forName(filePath);
			myConstructor = myClass.getConstructors()[0];

			Class<?>[] parameterTypes = myConstructor.getParameterTypes();
			System.out.println(myConstructor);
			ParameterAnnotation annotations = (ParameterAnnotation) myConstructor.getAnnotations()[0];
			
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

	public Map<String, AcceptedParameterTypes> getParameterTextToTypeMap() {
		return myParameterTextToTypeMap;
	} 
	
	public T getSettingInstance(Map<String, Object> aTextToValueMap) {
		Object[] parameters = new Object[aTextToValueMap.size()];
		
		String[] keys = new String[myParameterTextToTypeMap.size()];
		myParameterTextToTypeMap.keySet().toArray(keys);
		
		for(int i = 0; i < parameters.length ; i++){
			 parameters[i] = aTextToValueMap.get(keys[i]);
		}
		
		parameters[parameters.length] = mySprite;
		
		try {
			return (T) myConstructor.newInstance(parameters);
		} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
				| InvocationTargetException e) {
			//System.out.println("For now this does nothing [Characteristic Factory]");
			//System.out.print(Arrays.asList(parameters));
			e.printStackTrace();
			return null;
		}
	}
	
	protected abstract String getRootDirectoryPath();
		
}
