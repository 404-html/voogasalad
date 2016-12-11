package author.view.pages.menu;

import game_data.Game;

import java.util.Observable;
import java.util.Observer;

import javafx.beans.InvalidationListener;
import javafx.scene.control.Label;
import javafx.scene.text.TextAlignment;

public class GameNameDisplay extends Label implements InvalidationListener{

	Game myGame;
	
	public GameNameDisplay(Game aGame) {
		super(aGame.getName());
		aGame.addListener(this);
		this.getStyleClass().add("GameTitle");
		myGame = aGame;
	}


	@Override
	public void invalidated(javafx.beans.Observable arg0) {
		this.setText(myGame.getName());
	}

}
