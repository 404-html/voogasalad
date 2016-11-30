package game_engine;

import java.util.List;

import javafx.scene.input.KeyCode;

public class EngineReceiverController {

	private List<KeyCode> myKeys;
	private String XMLRoute;
	
	public EngineReceiverController(IEngineReceiverControllerInterface myInterface) {
//		myKeys = myInterface.getKeysBeingPressed();
		XMLRoute = myInterface.getXMLRoute();
	}
	
//	public List<KeyCode> getKeys(){
//		return myKeys;
//	}
	
	//xml translator to deserialize cast the object to what we extect
	public String getXMLRoute(){
		return XMLRoute;
	}

}
