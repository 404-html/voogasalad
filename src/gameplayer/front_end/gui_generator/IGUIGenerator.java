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
import javafx.scene.layout.Pane;

public interface IGUIGenerator {
	
	public enum ButtonDisplay {
		CSS, TEXT, IMAGE, FACEBOOK
	}
	
	public ImageView createImage(String aFileName, double aWidth);
		
	public Button createButton(String aMessage, double aXPos, double aYPos, 
			EventHandler<? super MouseEvent> aHandler, ButtonDisplay aDisplayType);
	
	public TextField createTextField(String aPlaceHolder, double aXPos, double aYPos, double aWidth);

	public Menu createMenu(String aTitle, String[] aString, EventHandler<ActionEvent>[] aHandler);

	public Menu createMenu(ImageView aImage, String[] aString, EventHandler<ActionEvent>[] aHandler);
	
	public ComboBox<Pane> createComboBox(String aLabel, List<String> aList, List<String> aListOfFiles, 
			List<String> aListOfDescriptions, Choosable aChoice);

	public Label createLabel(String aText, double aXPos, double aYPos);
	
}
