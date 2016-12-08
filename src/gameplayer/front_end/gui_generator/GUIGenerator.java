package gameplayer.front_end.gui_generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import gameplayer.application_controller.Choosable;
import gameplayer.front_end.gui_generator.button_generator.ButtonFactory;
import gameplayer.front_end.gui_generator.combobox_generator.ComboBoxFactory;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class GUIGenerator implements IGUIGenerator {
	
	private ButtonFactory myButtonBuilder;
	private ComboBoxFactory myComboBoxBuilder; 

	public GUIGenerator() {
		myButtonBuilder = new ButtonFactory();
		myComboBoxBuilder = new ComboBoxFactory();
	}
	
	@Override
	public TextField createTextField(String aplaceholder, int x, int y, int width) {
		TextField newTextField = new TextField(aplaceholder);
		newTextField.setTranslateX(x);
		newTextField.setTranslateY(y);
		newTextField.setMaxWidth(width);
		return newTextField;
	}
	
	@Override
	public Label createLabel(String atext, int x, int y) {
		Label newLabel = new Label(atext);
		newLabel.setTranslateX(x);
		newLabel.setTranslateY(y);
		return newLabel;
	}
	
	@Override
	public ImageView createImage(String aFileName, double aWidth) {
		ImageView image = new ImageView(new File(aFileName).toURI().toString());
		image.setPreserveRatio(true);
		image.setFitWidth(aWidth);
		return image;
	}
	
	@Override
	public ComboBox<HBox> createComboBox(List<String> aListOfNames, List<String> aListOfFilePaths, Choosable aChooser) {
		ComboBox<HBox> box = new ComboBox<HBox>();
		List<HBox> options = new ArrayList<HBox>();
		for(int i = 0; i < aListOfNames.size(); i++){
			HBox hbox = new HBox();
			if(aListOfFilePaths != null && i < aListOfFilePaths.size()){
				System.out.println("here");
				hbox.getChildren().add(createImage(aListOfFilePaths.get(i), 75));
			} else {
				hbox.getChildren().add(new ImageView());
			}
			hbox.getChildren().add(new Label(aListOfNames.get(i)));
			options.add(hbox);
		}
		ObservableList<HBox> items = FXCollections.observableArrayList(options);
		box.setItems(items);
		box.setPromptText("CHOOSE GAME");
		box.setEditable(true);        
		box.setOnAction(e -> {
			Label label = (Label) box.getSelectionModel().getSelectedItem().getChildren().get(1);
		    aChooser.choose(label.getText());
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

//	@Override
//	public ComboBox<Pane> createComboBox(List<Pane> aDisplayOfGames) {
//		return myComboBoxBuilder.createComboBox(aDisplayOfGames);
//	}
}
