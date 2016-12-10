/**
 * 
 */
package author.view.util.edit_window;

import game_data.GameObject;
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
abstract class GameObjectEditBox <T extends GameObject>{
	private String imagePath;
	private TextField nameField = new TextField();
	
	private VBox container;
	GameObjectEditBox() {
		this.container = initializeVBox();
	}
	
	public VBox getEditBox(){
		return this.container;
	}
	
	public abstract T getResult();
	
	protected String getImagePath(){
		return this.imagePath;
	}
	
	protected String getName(){
		return this.nameField.getText();
	}
	
	private VBox initializeVBox(){
		VBox editBox = new VBox(5);
		editBox.setPadding(new Insets(3));
		editBox.setAlignment(Pos.CENTER);
		
		editBox.getChildren().add(createHBox(new Label("Name: "), nameField));
		return editBox;
	}
	
	private HBox createHBox(Node...nodes){
		HBox hBox = new HBox();
		hBox.getChildren().addAll(nodes);
		return hBox;
	}
	
	protected ChangeListener<? super String> makeOnlyNumberProperty(TextField aTextField){
		return (obs, ov, nv) -> { 
			if(!nv.matches("\\d+(\\.\\d+)")){
				aTextField.setText(nv.replaceAll("[^\\d+(\\.\\d+)]", ""));
			}
		}; 
	}

}
