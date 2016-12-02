package gameplayer.front_end.gui_generator;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;

public interface IGUIGenerator {
	
	public enum ButtonDisplay {
		CSS, TEXT, IMAGE, FACEBOOK
	}
		
	public Button createButton(String aMessage, int aXPos, int aYPos, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aDisplayType);
		
	public TextField createTextField(String aPlaceHolder, int aXPos, int aYPos, int aWidth);
		
	public Label createLabel(String aText, int aXPos, int aYPos);

	public void setBackground(Pane aValue, String aFilepath);
		
	public void setBackground(Pane aValue, Paint aFill);

	public Menu createMenu(String aTitle, String[] aString, EventHandler<ActionEvent>[] aHandler);

	public Menu createMenu(ImageView aImage, String[] aString, EventHandler<ActionEvent>[] aHandler);

	
}
