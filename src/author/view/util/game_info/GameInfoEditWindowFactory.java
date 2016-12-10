package author.view.util.game_info;

import game_data.Game;

public class GameInfoEditWindowFactory{

	public iGameInfoEditWindow create(Game aGame) {
		return new GameInfoEditWindow(aGame);
	}

}
