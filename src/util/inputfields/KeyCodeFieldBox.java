package util.inputfields;

import javafx.scene.input.KeyCode;

public class KeyCodeFieldBox extends TextFieldBox {

	private KeyCode myKeyCode;
	
	public KeyCodeFieldBox() {
		super();
		getTextField().setEditable(false);
		getTextField().setOnKeyPressed( e -> {
			myKeyCode = e.getCode();
			getTextField().setText(e.getCode().toString());
		});
	}
	
	public KeyCode getCode() throws UnsupportedOperationException{
		if(myKeyCode != null)
			return myKeyCode;
		else
			throw new UnsupportedOperationException("No KeyCode Specified in Field");
		
	}
	
	public void setCode(KeyCode aKeyCode){
		myKeyCode = aKeyCode;
		getTextField().setText(aKeyCode.toString());
	} 
	
}
