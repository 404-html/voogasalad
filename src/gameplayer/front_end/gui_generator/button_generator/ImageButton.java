package gameplayer.front_end.gui_generator.button_generator;

import java.io.File;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

public class ImageButton extends AbstractButton {
	
	public Button createButton(String aMessage, int aXPos, int aYPos, EventHandler<? super MouseEvent> aHandler) {
		Button newButton = super.createButton("", aXPos, aYPos, aHandler);
		Image image = new Image("file:" + new File(aMessage).getAbsolutePath());
		ImageView imageView = new ImageView(image);
		newButton.setGraphic(imageView);
		imageView.setFitHeight(40);
		imageView.setFitWidth(40);
		return newButton;
		
	}

}
