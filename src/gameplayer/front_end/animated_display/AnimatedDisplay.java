package gameplayer.front_end.animated_display;

import javafx.animation.FadeTransition;
import javafx.scene.Node;
import javafx.util.Duration;

public class AnimatedDisplay {
	

	public Node makeFadeTransition(Node aObject) {
		FadeTransition fadeTransition = 
	            new FadeTransition(Duration.millis(5000), aObject);
	    fadeTransition.setFromValue(0f);
	    fadeTransition.setToValue(1.0f);
	    fadeTransition.setCycleCount(1);
	    //fadeTransition.setAutoReverse(false);
	    //fadeTransition.setAutoReverse(true);
	    fadeTransition.play();
		return aObject;
	}

}
