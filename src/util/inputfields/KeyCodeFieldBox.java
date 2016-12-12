package util.inputfields;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;

public class KeyCodeFieldBox extends TextFieldBox {

	private KeyCode myKeyCode;
	
	public KeyCodeFieldBox() {
		super();
		getTextField().setEditable(false);
		getTextField().setOnKeyPressed( e -> {
			myKeyCode = e.getCode();
			getTextField().setText(e.getCode().toString());
			getTextField().setStyle("");
		});
	}
	
	public KeyCode getCode() throws UnsupportedOperationException{
		if(myKeyCode != null)
			return myKeyCode;
		else
			displayKeyCodeNotFilled();
			throw new UnsupportedOperationException("No KeyCode Specified in Field");
		
	}
	
	public void displayKeyCodeNotFilled() {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Key Code Error");
		alert.setHeaderText("Key Code must be filled in");
		alert.setContentText("Press a key to map to a movement");
		getTextField().setStyle("-fx-border-color: red");
		alert.showAndWait();
	}
	
	public void setCode(KeyCode aKeyCode){
		myKeyCode = aKeyCode;
		getTextField().setText(aKeyCode.toString());
	} 
	
}
