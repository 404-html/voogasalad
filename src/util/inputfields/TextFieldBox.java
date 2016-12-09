package util.inputfields;

import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;

/**
 * Helpful wrapper for TextField
 * 
 * @author George Bernard
 */
public class TextFieldBox {

	private static final int SPACING = 5;
	
	private Pane myPane;
	private TextField myTextField;
	private Label myLabel;
	
	
	/**
	 * Used if to not be constructed with Name Label
	 */
	public TextFieldBox() {
		myPane = new HBox(SPACING);
		myTextField = new TextField();
		myLabel = new Label();		
		
		myPane.getChildren().addAll(myLabel, myTextField);
		HBox.setHgrow(myTextField, Priority.ALWAYS);
	}
	
	/**
	 * @param Name to label textfield
	 */
	public TextFieldBox(String aText) {
		this();
		((HBox) myPane ).setSpacing(SPACING);
		getLabel().setText(aText);
	}

	/**
	 * @return pane for text
	 */
	public Pane getPane() {
		return myPane;
	}
	
	/**
	 * @return inner text field if facade is to be broken
	 */
	public final TextField getTextField(){
		return myTextField;
	}
	
	protected final Label getLabel(){
		return myLabel;
	}

	/**
	 * @return String of text in TextField
	 */
	public String getText() {
		return getTextField().getText();
	}
}
