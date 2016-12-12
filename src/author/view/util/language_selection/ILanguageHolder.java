package author.view.util.language_selection;

import javafx.beans.Observable;

public interface ILanguageHolder extends Observable{
	
	public void setLanguage(String aLanguage);
	
	public String getDisplayText(String aKey);
	
	public String getPathString(String aKey);
	
}
