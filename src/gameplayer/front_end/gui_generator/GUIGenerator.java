package gameplayer.front_end.gui_generator;

import gameplayer.front_end.gui_generator.button_generator.ButtonFactory;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public class GUIGenerator implements IGUIGenerator {
	
	private ButtonFactory myButtonBuilder;
	
	public GUIGenerator() {
		myButtonBuilder = new ButtonFactory();
	}
	
	
	public TextField createTextField(String aplaceholder, int x, int y, int width){
		TextField newTextField = new TextField(aplaceholder);
		newTextField.setTranslateX(x);
		newTextField.setTranslateY(y);
		newTextField.setMaxWidth(width);
		return newTextField;
	}
	
	public Label createLabel(String atext, int x, int y){
		Label newLabel = new Label(atext);
		newLabel.setTranslateX(x);
		newLabel.setTranslateY(y);
		return newLabel;
	}
	
	//private BackgroundImage createBackgroundImage(String afilepath){
		//return new BackgroundImage(new Image(afilepath, false, true), BackgroundRepeat.REPEAT, 
			//BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
	//}
	
	public void setBackground(Pane avalue, String afilepath){
		//avalue.setBackground(new Background(createBackgroundImage(afilepath)));
	}
	
	public void setBackground(Pane avalue, Paint fill){
		avalue.setBackground(new Background(new BackgroundFill(fill, CornerRadii.EMPTY, Insets.EMPTY)));
	}


	@Override
	public Button createButton(String aMessage, int aXPos, int aYPos, EventHandler<? super MouseEvent> aHandler,
			ButtonDisplay aDisplayType) {
		return myButtonBuilder.buildButton(aMessage, aXPos, aYPos, aHandler, aDisplayType);
	}

}
