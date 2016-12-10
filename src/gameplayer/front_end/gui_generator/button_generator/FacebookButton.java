package gameplayer.front_end.gui_generator.button_generator;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;

public class FacebookButton extends AbstractButton {

	@Override
	public Button createButton(String aMessage, int aXPos, int aYPos, EventHandler<? super MouseEvent> aHandler) {
		Button button = super.createButton(aMessage, aXPos, aYPos, aHandler); 
		HBox box = new HBox(10);
		button.setId("facebook-button");
		Label fbLabel = new Label(aMessage);
		ImageView fbImage = new ImageView(new File("data/gui/facebook_icon.png").toURI().toString());
		fbImage.setFitHeight(20);
		fbImage.setPreserveRatio(true);
		fbLabel.setId("facebook-label");
		box.getChildren().add(fbImage);
		box.getChildren().add(fbLabel);
		button.setGraphic(box);
		return button;
	}

}
