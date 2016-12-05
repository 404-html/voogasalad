package gameplayer.back_end.user_information;

import java.util.prefs.Preferences;

public class UserDefaults {
	
	Preferences myPrefs;
	private static final String FONT_COLOR = "fontcolor";
	private static final String KEY_INPUT = "keyinput";
	
	public UserDefaults(String path){
		myPrefs = Preferences.userRoot().node(path);
	}
	
	public void setFontColor(String value){
		myPrefs.put(FONT_COLOR, value);
	}
	
	public String getFontColor(String def){
		return myPrefs.get(FONT_COLOR, def);
	}
	
	public void setKeyInput(String value){
		myPrefs.put(KEY_INPUT, value);
	}
	
	public String getKeyInputColor(String def){
		return myPrefs.get(KEY_INPUT, def);
	}
}
