package gameplayer.front_end.gui_generator;

import java.util.List;

import gameplayer.application_controller.Choosable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public interface IGUIGenerator {
	
	public enum ButtonDisplay {
		CSS, TEXT, IMAGE, FACEBOOK
	}
	
	public ImageView createImage(String aFileName, double aWidth);
		
	public Button createButton(String aMessage, int aXPos, int aYPos, EventHandler<? super MouseEvent> aHandler, ButtonDisplay aDisplayType);
		
	public TextField createTextField(String aPlaceHolder, int aXPos, int aYPos, int aWidth);
		
	public Label createLabel(String aText, int aXPos, int aYPos);

	public Menu createMenu(String aTitle, String[] aString, EventHandler<ActionEvent>[] aHandler);

	public Menu createMenu(ImageView aImage, String[] aString, EventHandler<ActionEvent>[] aHandler);
	
	public ComboBox createComboBox(List<String> aList, Choosable aChoice);

	
}
