package author.view.pages.sprite.editor.settings.view;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SettingsViewBox {

	Pane myPane;
	Map<String, Object> myInfo;

	public SettingsViewBox(Object input) {
		myInfo = new TreeMap<>();
		myPane = new VBox();

		buildInfoMap(input);
		buildViewBox(input, myInfo);
	}

	public Pane getPane(){
		return myPane;
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
						myInfo.put( vv.description(), m.invoke(input, (Object[]) null) );
					} catch (ExceptionInInitializerError | IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
						e.printStackTrace();
						System.out.println("You're a cuck");
						continue;
					}
				}
			}

			if(vvAnnotation == null) continue; 
		}		
	}

	private void buildViewBox(Object input, Map<String, Object> infoMap){
		myPane.getChildren().addAll(new ToolBar(new Label(input.toString())) );

		infoMap.entrySet().forEach( e -> {
			Pane box = new HBox();
			box.getChildren().addAll(
					new Label(e.getKey().toString()),
					new Label(e.getValue().toString())
					);
			myPane.getChildren().add(box);
		} );		
	}

}