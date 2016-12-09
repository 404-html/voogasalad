package author.view.pages.sprite.editor.controllable;

import game_data.Sprite;
import game_engine.actions.MoveLeft;
import game_engine.actions.*;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class ControllableEditor {
	private Pane myPane;
	
	private enum MoveFactory {
		MOVE_RIGHT("Move Right", (s, d) -> new MoveRight(s, d)),
		MOVE_LEFT("Move Left", (s, d) -> new MoveLeft(s, d)),
		MOVE_UP_JUMP("Jump Up", (s, d) -> new MoveUpJump(s, d)),
		MOVE_UP_FLY("Fly Up",  (s, d) -> new MoveUpFly(s, d));
		
		@FunctionalInterface
		private interface MoveMaker {
			public Move make(Sprite aSprite, Double aVelocity);
		}
		
		private MoveFactory (String aDescription, MoveMaker maker) {
			myDescription = aDescription;
		}
		
		String myDescription;
		
		public String toString() {
			return myDescription;
		}
	}
	
	public ControllableEditor(){
		myPane = new VBox();
		
		for (MoveFactory accept : MoveFactory.values() ) {
			myPane.getChildren().add(new Label(accept.toString() + ": "));
			Pane editBox = new HBox(10);
			TextField keyField = new TextField();
			TextField numField = new TextField();
			keyField.setEditable(false);
			keyField.setOnKeyPressed( e -> {
				keyField.setText(e.getCode().name());
			});
			editBox.getChildren().addAll(
					new Label("Key: "),
					keyField, 
					new Label("Velocity: "),
					numField); 
			myPane.getChildren().add(editBox);
		}
		
	}

	public Pane getPane(){
		return myPane;
	}
	
	
	
}