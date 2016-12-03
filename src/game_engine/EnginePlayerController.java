package game_engine;

import game_data.Game;
import game_data.Level;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Bouncer;
import game_data.characteristics.BouncerTop;
import game_data.characteristics.Damager;
import game_data.characteristics.Impassable;
import game_data.characteristics.PacerAlternative;
import game_data.characteristics.StickyTop;
import game_data.characteristics.StickyTopHorizontal;
import game_data.characteristics.StickyTopVertical;
import game_data.characteristics.TransparentBottomImpassable;
import game_data.characteristics.Winnable;
import game_data.sprites.Character;
import game_data.sprites.Enemy;
import game_data.sprites.Item;
import game_data.sprites.Player;
import game_data.sprites.Terrain;
import game_data.states.Health;
import game_data.states.LevelWon;
import game_data.states.Physics;
import game_data.states.State;
import game_engine.actions.Bounce;
import game_engine.actions.Hit;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Katrina, Austin, Lucia
 *
 */
public class EnginePlayerController implements IEnginePlayerControllerInterface {
	private Level myLevel;
	private int myWidth, myHeight;
	private String myBackgroundImageFilePath;
	private List<Sprite> mySpriteList;
	private List<Double> mySpriteXCoordinateList;
	private List<Double> mySpriteYCoordinateList;
	private List<Double> mySpriteHeadingList;
	private List<String> mySpriteImagePathList;
	private List<Integer> mySpriteHealthList;
	private List<Boolean> mySpriteIsAliveList;
	private Game myGame;

	public EnginePlayerController() {
		myGame = null;
		myLevel = null;
		mySpriteList = null;
		myBackgroundImageFilePath = "";
		myWidth = 0;
		myHeight = 0;
		mySpriteXCoordinateList = new ArrayList<>();
		mySpriteYCoordinateList = new ArrayList<>();
		mySpriteHeadingList = new ArrayList<>();
		mySpriteImagePathList = new ArrayList<>();
		mySpriteHealthList = new ArrayList<>();
		mySpriteIsAliveList = new ArrayList<>();
	}

	public EnginePlayerController(Game game) {
		myGame = game;
		myLevel = myGame.getCurrentLevel();
		// temporary to see if moving the player works, hardcoded
		myLevel.setPlayerSprite((Player) myLevel.getMySpriteList().get(0));
		myLevel.getMainPlayer().addState(new Physics(new SpritePhysics()));
		myLevel.getMainPlayer().addState(new Health(1));
		myLevel.getMainPlayer().addState(new LevelWon());

		int j = 1;
		// for(int i = 226; i<10260; i+=1000){
		for (int i = 226; i < 13226; i += 105) {
			myLevel.addNewSprite(new Terrain(new Location(i, 500), 100, 100, "block" + j, "author/images/betterblock.png"));
			// myLevel.getMySpriteList().get(j).addCharacteristic(new
			// Bouncer(20, myLevel.getMySpriteList().get(j)));
			// System.out.println(myLevel.getMySpriteList().get(j).getStates().size());
			// myLevel.getMySpriteList().get(j).addState(new Physics(new
			// SpritePhysics(0.0)));
			// System.out.println(myLevel.getMySpriteList().get(j).getStates().size());
			// System.out.println(j);
			// System.out.println(myLevel.getMySpriteList().size());
			j++;
		}
		myLevel.addNewSprite(new Terrain(new Location(726, 400), 100, 100, "block5000", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(826, 300), 100, 100, "block500001", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(926, 200), 100, 100, "block123123123", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(1126, 300), 200, 40, "blockmoving", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(1350, 250), 200, 40, "blockmoving2", "author/images/betterblock.png"));
		
		myLevel.addNewSprite(new Enemy(new Location(1226, 401), 100, 100, "goomba1", "author/images/angry_goomba.png"));
		myLevel.addNewSprite(new Item(new Location(2000, 400), 50, 200, "flag", "author/images/victory_flag.png"));
		for (Sprite s : myLevel.getMySpriteList()) {
			if (!(s instanceof Player || s instanceof Enemy)) {
				// if(s.getName().equals("block1")){
				// s.addCharacteristic(new Bouncer(100, s));
				// }
				// else{
				// s.addCharacteristic(new Impassable(s));
//				if (s.getName().equals("block5000")) {
//					s.addCharacteristic(new Damager(25, s));
//				}
				// }
				if(s.getName().equals("blockmoving")){
					//ss.addCharacteristic(new TransparentBottomImpassable(s));
					s.addCharacteristic(new TransparentBottomImpassable(s));
					s.addCharacteristic(new StickyTopVertical(s));
				}
				else if(s.getName().equals("blockmoving2")) {
					s.addCharacteristic(new TransparentBottomImpassable(s));
					s.addCharacteristic(new StickyTopHorizontal(s));
				}
				else if(!s.getName().equals("flag")){
					s.addCharacteristic(new Impassable(s));
				}
				if(s.getName().equals("flag")){
					s.addCharacteristic(new Winnable(s));
				}
				s.addState(new Physics(new SpritePhysics(0.0)));
			}
			if(s instanceof Enemy){
				s.addCharacteristic(new PacerAlternative("HORIZONTAL", 500, s));
				s.setMyXVelocity(100);
				s.addCharacteristic(new Damager(1, s));
				s.addState(new Physics(new SpritePhysics()));
			}
			if(s.getName().equals("blockmoving")){
				s.addCharacteristic(new PacerAlternative("VERTICAL", 300, s));
				s.setMyYVelocity(-200);
			}
			if(s.getName().equals("blockmoving2")){
				s.addCharacteristic(new PacerAlternative("HORIZONTAL", 300, s));
				s.setMyXVelocity(200);
			}
		}
		// System.out.println(myLevel.getMySpriteList().get(1).getName() + " " +
		// myLevel.getMySpriteList().get(1).getStates().size());
		// System.out.println(myLevel.getMySpriteList().get(2).getName() + " " +
		// myLevel.getMySpriteList().get(2).getStates().size());
		// System.out.println(myLevel.getMySpriteList().get(3).getName() + " " +
		// myLevel.getMySpriteList().get(3).getStates().size());

		// end temporary stuff
		mySpriteList = myLevel.getMySpriteList();
		myBackgroundImageFilePath = myLevel.getBackgroundImageFilePath();
		myWidth = myLevel.getWidth();
		myHeight = myLevel.getHeight();
		mySpriteXCoordinateList = new ArrayList<>();
		mySpriteYCoordinateList = new ArrayList<>();
		mySpriteHeadingList = new ArrayList<>();
		mySpriteImagePathList = new ArrayList<>();
		mySpriteHealthList = new ArrayList<>();
		mySpriteIsAliveList = new ArrayList<>();
		updateSpriteData();
	}

	public void updateControllerData() {
		// need to update level in here

		mySpriteList = myLevel.getMySpriteList();
		myBackgroundImageFilePath = myLevel.getBackgroundImageFilePath();
		myWidth = myLevel.getWidth();
		myHeight = myLevel.getHeight();
		updateSpriteData();
	}

	private void updateSpriteData() {
		for (int i = 0; i < mySpriteList.size(); i++) {
			Object sprite = mySpriteList.get(i);
			// mySpriteImagePathList.set(i, ((Sprite) sprite).getMyImagePath());
			mySpriteImagePathList.add(((Sprite) sprite).getMyImagePath());
			Location location = ((Sprite) sprite).getMyLocation();
			// mySpriteXCoordinateList.set(i, location.getXLocation());
			mySpriteXCoordinateList.add(location.getXLocation());
			// mySpriteYCoordinateList.set(i, location.getYLocation());
			mySpriteYCoordinateList.add(location.getYLocation());
			// mySpriteHeadingList.set(i, location.getMyHeading());
			if (sprite instanceof Character) {
				for (State myState : ((Character) sprite).getStates()) {
					if (myState instanceof Health) {
						// mySpriteHealthList.set(i, ((Health)
						// myState).getMyHealth());
						mySpriteHealthList.add(((Health) myState).getMyHealth());
						// mySpriteIsAliveList.set(i, ((Health)
						// sprite).isAlive());
					}
					if (sprite instanceof Health) {
						mySpriteIsAliveList.add(((Health) sprite).isAlive());
					}
				}
			} else {
				// mySpriteHealthList.set(i, null);
				// mySpriteIsAliveList.set(i, null);
			}
		}
	}

	public Level getMyLevel() {
		return myLevel;
	}

	public int getMyWidth() {
		return myWidth;
	}

	public int getMyHeight() {
		return myHeight;
	}

	public String getMyBackgroundImageFilePath() {
		return myBackgroundImageFilePath;
	}

	public List<Sprite> getMySpriteList() {
		return mySpriteList;
	}

	public List<Double> getMySpriteXCoordinateList() {
		return mySpriteXCoordinateList;
	}

	public List<Double> getMySpriteYCoordinateList() {
		return mySpriteYCoordinateList;
	}

	public List<Double> getMySpriteHeadingList() {
		return mySpriteHeadingList;
	}

	public List<String> getMySpriteImagePathList() {
		return mySpriteImagePathList;
	}

	public List<Integer> getMySpriteHealthList() {
		return mySpriteHealthList;
	}

	public List<Boolean> getMySpriteIsAliveList() {
		return mySpriteIsAliveList;
	}

	public Game getMyGame() {
		return myGame;
	}
}