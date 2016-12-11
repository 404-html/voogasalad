package gameplayer.front_end.gui_generator;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


import gameplayer.application_controller.Choosable;
import gameplayer.back_end.resources.FrontEndResources;
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
import javafx.scene.control.SelectionModel;
import javafx.scene.control.SingleSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.StringConverter;

public class GUIGenerator implements IGUIGenerator {
	
	private ButtonFactory myButtonBuilder;

	public GUIGenerator() {
		myButtonBuilder = new ButtonFactory();
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
	
	public Label createComboBoxLabel(String aLabel) {
		Label label = createLabel(aLabel, 0, 0); 
		label.setId("combo-box-label");
		label.autosize();
		return label;
	}
	
	@Override
	public ComboBox<Pane> createComboBox(String aLabel, List<String> aListOfNames, List<String> aListOfFilePaths, List<String> aListOfDescriptions, Choosable aChooser) {
		ComboBox<Pane> box = new ComboBox<Pane>();
		box.setPromptText(aLabel);
		List<HBox> options = createListOfComboBoxHbox(aListOfNames, aListOfFilePaths, aListOfDescriptions, box);
		ObservableList<Pane> items = FXCollections.observableArrayList(options);
		box.setItems(items);
		box.setEditable(true);
		box.setConverter(new StringConverter<Pane>() {

		    @Override
		    public Pane fromString(String string) {
		        return box.getSelectionModel().getSelectedItem();
		    }

			@Override
			public String toString (Pane object) {
				if (object == null) return null;
				if (object.getChildren().size() >= 1) {
					return ((Label) object.getChildren().get(1)).getText();
				}
				return null;
			}
		});
		box.setOnAction(e -> {
			String label = box.getConverter().toString(box.getSelectionModel().getSelectedItem());
			box.setPromptText(label);
		    aChooser.choose(label);
		});
		return box;
	}

	private List<HBox> createListOfComboBoxHbox(List<String> aListOfNames, List<String> aListOfFilePaths,
			List<String> aListOfDescriptions, ComboBox<Pane> box) {
		List<HBox> options = new ArrayList<HBox>();
		for(int i = 0; i < aListOfNames.size(); i++){
			HBox hbox = new HBox(FrontEndResources.BOX_INSETS.getDoubleResource());
			hbox.setMaxWidth(box.getMaxWidth());
			if(aListOfFilePaths != null && i < aListOfFilePaths.size()){
				hbox.getChildren().add(createImage(aListOfFilePaths.get(i), 40));
			} else {
				hbox.getChildren().add(new ImageView());
			}
			Label name = createComboBoxLabel(aListOfNames.get(i));
			Label des = createComboBoxLabel(aListOfDescriptions.get(i));
			hbox.setHgrow(name, Priority.ALWAYS);
			hbox.setHgrow(des, Priority.ALWAYS);
			hbox.getChildren().add(name);
			hbox.getChildren().add(des);
			options.add(hbox);
		}
		return options;
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
