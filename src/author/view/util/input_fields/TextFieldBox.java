package author.view.util.input_fields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

public class TextFieldBox {

	private static final int SPACING = 5;
	
	private Pane myPane;
	private TextField myTextField;
	private Label myLabel;
	
	public TextFieldBox() {
		myPane = new HBox(SPACING);
		myTextField = new TextField();
		myLabel = new Label();		
		
		myPane.getChildren().addAll(myLabel, myTextField);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
	}
	
	public TextFieldBox(String aText) {
		this();
		getLabel().setText(aText);
	}

	public Pane getPane() {
		return myPane;
	}
	
	public final TextField getTextField(){
		return myTextField;
	}
	
	protected final Label getLabel(){
		return myLabel;
	}

	public String getText() {
		// TODO Auto-generated method stub
		return getTextField().getText();
	}
}
