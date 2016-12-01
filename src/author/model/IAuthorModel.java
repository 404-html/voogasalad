/**
 * 
 */
package author.model;

import java.io.File;

import game_data.Game;

/**
 * @author Cleveland Thompson V (ct168), Addison Howenstine
 *
 */
public interface IAuthorModel {
	
	public void newGame();
	
	public Game getGame();
	
	public void saveGame(String aFileName);
	
	public void loadGame(File aFile);
}
