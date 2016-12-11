package game_engine;

import java.util.List;

import game_data.Level;
import game_data.Sprite;
import javafx.scene.input.KeyCode;
import sun.awt.SunHints.Key;

public interface IEnginePlayerControllerInterface {
	public void updateControllerData();

	public Level getMyLevel();

	public int getWidth();

	public int getHeight();

	public String getMyBackgroundImageFilePath();

	public List<Sprite> getMySpriteList();

	public List<Double> getMySpriteXCoordinateList();

	public List<Double> getMySpriteYCoordinateList();

	public List<Double> getMySpriteHeadingList();

	public List<String> getMySpriteImagePathList();

	public List<Integer> getMySpriteHealthList();

	public List<Boolean> getMySpriteIsAliveList();

}
