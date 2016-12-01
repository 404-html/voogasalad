package gameplayer.front_end.background_display;

import java.io.FileNotFoundException;

import javafx.scene.Node;

public interface IBackgroundDisplay {
	
	public Node buildBackgroundDisplay(String aFileName) throws FileNotFoundException;

}
