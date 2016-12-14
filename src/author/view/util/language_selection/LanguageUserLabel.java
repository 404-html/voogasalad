package author.view.util.language_selection;

import javafx.beans.Observable;
import javafx.scene.control.Label;

public class LanguageUserLabel extends Label implements ILanguageUser {
	
	private String myKey;
	private ILanguageHolder myLanguageHolder;
	
	public LanguageUserLabel(String aKey, ILanguageHolder aLanguageHolder) {
		myKey = aKey;
		myLanguageHolder = aLanguageHolder;
		myLanguageHolder.addListener(this);
		setText();
	}

	private void setText() {
		this.setText(myLanguageHolder.getDisplayText(myKey));
	}

	@Override
	public void invalidated(Observable arg0) {
		setText();
	}

}
