package gameplayer.front_end.gui_generator;

import java.io.File;
import java.util.List;

import javax.swing.event.ChangeListener;

import gameplayer.front_end.gui_generator.button_generator.ButtonFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

public class GUIGenerator implements IGUIGenerator {
	
	private ButtonFactory myButtonBuilder;
	
	public GUIGenerator() {
		myButtonBuilder = new ButtonFactory();
	}
	
	public ImageView createImage(String aFileName, double aWidth) {
		ImageView image = new ImageView(new File(aFileName).toURI().toString());
		image.setPreserveRatio(true);
		image.setFitWidth(aWidth);
		return image;
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
	
	public void setBackground(Pane avalue, String afilepath){
		//avalue.setBackground(new Background(createBackgroundImage(afilepath)));
	}
	
	public ComboBox<Pane> createComboBox(List<Pane> aListOfPanes) {
		ComboBox<Pane> box = new ComboBox<Pane>();
		ObservableList<Pane> items = FXCollections.observableArrayList(aListOfPanes);
		box.setItems(items);
		box.setPromptText("CHOOSE GAME");
		box.setEditable(true);        
		box.setOnAction(e -> {
		    //Pane selectedPane = box.getSelectionModel().getSelectedItem();
		    //String name = ((Label) selectedPane.getChildren().get(0)).getText();
		 	//box.setAccessibleText(name.);
		    //System.out.println("ComboBox Action (selected: " + name + ")");
		});
		return box;
	}

	@Override
	public Button createButton(String aMessage, int aXPos, int aYPos, EventHandler<? super MouseEvent> aHandler,
			ButtonDisplay aDisplayType) {
		return myButtonBuilder.buildButton(aMessage, aXPos, aYPos, aHandler, aDisplayType);
	}

	@Override
	public Menu createMenu(ImageView aImage, String[] aString, EventHandler<ActionEvent>... aHandler) {
		Menu menu = new Menu();
		menu.setGraphic(aImage);
		for (int i = 0; i < aHandler.length; i++) {
			MenuItem item = new MenuItem(aString[i]);
			item.setOnAction(aHandler[i]);
			menu.getItems().add(item);
		}
		return menu;
	}
	
	@Override
	public Menu createMenu(String aTitle, String[] aString, EventHandler<ActionEvent>...aHandler) {
		Menu menu = new Menu(aTitle); 
		for (int i = 0; i < aHandler.length; i++) {
			MenuItem item = new MenuItem(aString[i]);
			item.setOnAction(aHandler[i]);
			menu.getItems().add(item);
		}
		return menu;
	}
	
}
