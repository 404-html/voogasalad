package gameplayer.front_end.game_display_information;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class GameDisplayInformation implements Serializable, IViewableGameDisplayInformation {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private Map<String, String> myGameImageMap;
	private Map<String, String> myGameDescriptionMap; 
	
	public GameDisplayInformation() {
		//HighScore information = (HighScore) aSerializer.fromXML("");
		//init(information.gamename, information.)
		//get the serialized information.. please teach me how to use this
	}
	
	public String getGameImage(String aGameName) {
		if (myGameImageMap.containsKey(aGameName)) {
			return myGameImageMap.get(aGameName);
		}
		return null;
	}
	
	public String getGameDescriptionMap(String aGameName) {
		if (myGameDescriptionMap.containsKey(aGameName)) {
			return myGameDescriptionMap.get(aGameName);
		} 
		return null;
	}
	
	public Collection<String> getCurrentGameNames() {
		Set<String> set = new HashSet<String>();
		set.addAll(myGameImageMap.keySet());
		set.addAll(myGameDescriptionMap.keySet());
		return myGameDescriptionMap.keySet();
	}
	

}
