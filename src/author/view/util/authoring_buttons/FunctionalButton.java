package author.view.util.authoring_buttons;

import author.view.util.language_selection.ILanguageHolder;
import author.view.util.language_selection.ILanguageUser;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
/**
 * A way to add functionality to a button through lambda
 * @author Jordan Frazier
 *
 */
public class FunctionalButton implements ILanguageUser {

	private Button myButton;
	private String myKey;
	private ILanguageHolder myLanguageHolder;

	public FunctionalButton(String aKey, EventHandler<ActionEvent> e, ILanguageHolder aLanguageHolder) {
		myLanguageHolder = aLanguageHolder;
		myLanguageHolder.addListener(this);
		myKey = aKey;
		myButton = new Button();
		setName();
		myButton.setOnAction(e);
	}
	
	public FunctionalButton(String aName, EventHandler<ActionEvent> e) {
		myButton = new Button(aName);
		myButton.setOnAction(e);
	}

	public Button getButton() {
		return myButton;
	}
	
	public Button getButton(String CSStag){
		myButton.getStyleClass().add(CSStag);
		return myButton;
	}
	
	private void setName() {
		myButton.setText(myLanguageHolder.getDisplayText(myKey));
	}

	@Override
	public void invalidated(Observable arg0) {
		setName();
	}

}
