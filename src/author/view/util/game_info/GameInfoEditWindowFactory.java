package author.view.util.game_info;

import author.view.util.language_selection.ILanguageHolder;
import game_data.Game;

public class GameInfoEditWindowFactory{

	public iGameInfoEditWindow create(Game aGame, ILanguageHolder aLanguageHolder) {
		return new GameInfoEditWindow(aGame, aLanguageHolder);
	}

}
