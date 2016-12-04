package author.view.pages.sprite.editor.settings.view;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_data.characteristics.Breakable;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsViewBox {

	Pane myPane;
	Map<String, Object> myInfo;

	public SettingsViewBox(Object input) {
		myInfo = new TreeMap<>();
		myPane = new VBox();

		//myPane.getChildren().add(new Label("Class: " + input.toString()));
		buildViewBox(input);			
	}

	public void buildViewBox(Object input) {
		Method[] methods = input.getClass().getMethods();

		Arrays.asList(methods).forEach( m -> {
			System.out.println();
			System.out.println(m + " | " + Arrays.asList(m.getAnnotations()));
		});;
		
		for(Method m : methods){
			List<Annotation> annotationList = Arrays.asList(m.getAnnotations());
			
			ViewableMethodOutput vvAnnotation = null;

			for(Annotation a : annotationList){
				if(a instanceof ViewableMethodOutput){ 
					ViewableMethodOutput vv = (ViewableMethodOutput) a;
					try {
						myInfo.put( vv.description(), m.invoke(input, (Object[]) null) );
					} catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						System.out.println("Seems like you cucked this one");
						continue;
					}
				}
			}

			if(vvAnnotation == null) continue; 
		}		
	}

	public static void main(String[] args) {
		SettingsViewBox svb = new SettingsViewBox(new Breakable(true, false, true, false, 20, null));
		
		System.out.println(svb.myInfo);
	} 
	
}