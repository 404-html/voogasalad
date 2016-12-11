package util;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;

public class InfoMap {
	
	private Map<String, Object> myInfoMap;
	
	public InfoMap (Object aObj) {
		myInfoMap = new TreeMap<>();
		buildInfoMap(aObj);
	}
	
	public Map<String, Object> getInfoMap() {
		return myInfoMap;
	}
	
 	private void buildInfoMap(Object input) {
		Method[] methods = input.getClass().getMethods();

		for(Method m : methods){
			List<Annotation> annotationList = Arrays.asList(m.getAnnotations());

			ViewableMethodOutput vvAnnotation = null;

			for(Annotation a : annotationList){
				if(a instanceof ViewableMethodOutput){ 
					ViewableMethodOutput vv = (ViewableMethodOutput) a;
					try {
						myInfoMap.put( vv.description(), m.invoke(input, (Object[]) null) );
					} catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						continue;
					}
				}
			}

			if(vvAnnotation == null) continue; 
		}		
	}
}

