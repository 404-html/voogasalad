package gameplayer.front_end.game_display_information;

import java.util.Collection;

public interface IViewableGameDisplayInformation {
	
	public String getGameImage(String aGameName);
	
	public String getGameDescriptionMap(String aGameName);
	
	public Collection<String> getCurrentGameNames();

}
 