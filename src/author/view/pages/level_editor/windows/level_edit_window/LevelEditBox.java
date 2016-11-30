/**
 * 
 */
package author.view.pages.level_editor.windows.level_edit_window;


import java.io.File;

import author.view.util.FileLoader;
import author.view.util.FileLoader.FileType;
import author.view.util.authoring_buttons.ButtonFactory;
import game_data.Level;
import javafx.beans.value.ChangeListener;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class LevelEditBox {

	//private static final String levelEditResources ="author.view.resources.LevelEditResources.properties";
	private String backgroundPath;
	private TextField nameField = new TextField();
	private Level level;
	
	private static final int DEFAULT_LEVEL_WIDTH = 700;
	private static final int DEFAULT_LEVEL_HEIGHT = 550;
	
	private VBox container;
	LevelEditBox() {
		this.container = initializeVBox();
		this.level = new Level(null, 0, 0, null);
	}
	
	LevelEditBox(Level aLevel){
		this();
		this.level = aLevel;
		this.backgroundPath = aLevel.getBackgroundImageFilePath();
		this.nameField.setText(aLevel.getName());
	}
	
	public VBox getEditBox(){
		return this.container;
	}
	
	public Level getLevel(){
		try{
			this.level.setBackgroundImageFilePath(this.backgroundPath);
			this.level.setName(nameField.getText());
			this.level.setWidth(DEFAULT_LEVEL_WIDTH);
			this.level.setHeight(DEFAULT_LEVEL_HEIGHT);
		}
		catch (NumberFormatException e) {
			//  LOL
		}
		
		return this.level;
	}
	
	
	private VBox initializeVBox(){
		VBox editBox = new VBox(5);
		editBox.setPadding(new Insets(3));
		editBox.setAlignment(Pos.CENTER);
		
		editBox.getChildren().add(createHBox(new Label("Name: "), nameField));
		editBox.getChildren().add(new ButtonFactory().createButton("Choose Background", e -> updateBackgroundString()).getButton());
		return editBox;
	}
	
	private void updateBackgroundString(){
		File file = new FileLoader(
				FileType.GIF, 
				FileType.JPEG, 
				FileType.PNG,
				FileType.JPG ).loadImage();
		
		if (file !=null)
			this.backgroundPath = file.toURI().toString();
	}
	
	private HBox createHBox(Node...nodes){
		HBox hBox = new HBox();
		hBox.getChildren().addAll(nodes);
		return hBox;
	}
	
	private TextField createNumberField(){
		TextField myNumberField = new TextField();
		myNumberField.textProperty().addListener(makeOnlyNumberProperty(myNumberField));
		return myNumberField;
	}
	
	private ChangeListener<? super String> makeOnlyNumberProperty(TextField aTextField){
		return (obs, ov, nv) -> { 
			if(!nv.matches("\\d+(\\.\\d+)")){
				aTextField.setText(nv.replaceAll("[^\\d+(\\.\\d+)]", ""));
			}
		}; 
	}
	

}
