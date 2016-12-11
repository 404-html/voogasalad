package game_engine;

import game_data.Controllable;
import game_data.Game;
import game_data.Level;
import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.Impassable;
import game_data.characteristics.InvincibilityPowerUpper;
import game_data.characteristics.SpeedPowerUpper;
import game_data.characteristics.PacerAlternative;
import game_data.characteristics.ScoreBasedOnPosition;
import game_data.characteristics.ScoreBasedOnTime;
import game_data.characteristics.StickyTop;
import game_data.characteristics.StickyTopHorizontal;
import game_data.characteristics.StickyTopVertical;
import game_data.characteristics.TransparentBottomImpassable;
import game_data.characteristics.Winnable;

import game_data.sprites.Character;
import game_data.sprites.Enemy;
import game_data.sprites.Player;
import game_data.sprites.Terrain;
import game_data.states.Health;
import game_data.states.Physics;
import game_data.states.Score;
import game_data.states.State;
import game_engine.actions.Action;

import game_engine.actions.Bounce;
import game_engine.actions.Hit;
import game_engine.properties.RandomMoveHandler;
import game_engine.properties.RandomMoveHandler.Orientation;

import game_engine.actions.Launch;
import game_engine.actions.LaunchProxy;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import game_engine.actions.MoveUpJump;
import javafx.scene.input.KeyCode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sun.javafx.scene.traversal.Direction;

/**
 * @author Katrina, Austin, Lucia
 *
 */
public class EnginePlayerController implements IEnginePlayerControllerInterface {
	//private Level myLevel;
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
		//myLevel = null;
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
		
		// myLevel=new Level();
		Level myLevel = myGame.getCurrentLevel();
		// temporary to see if moving the player works, hardcoded
		for(Sprite s: myLevel.getMySpriteList()){
			if(s instanceof Player){
				myLevel.setPlayerSprite((Player)s);
//				myLevel.getMainPlayer().addState(new Physics(new SpritePhysics()));
				myLevel.getMainPlayer().addState(new Health(200));

				myLevel.getMainPlayer().addState(new Score());
//				myLevel.getMainPlayer().addCharacteristic(new ScoreBasedOnPosition(s, Direction.RIGHT));
				//myLevel.getMainPlayer().addState(new Physics(new SpritePhysics()));
				//myLevel.getMainPlayer().addState(new Health(1));
				//myLevel.getMainPlayer().addState(new LevelWon());
				myLevel.getMainPlayer()
						.setControllable(new Controllable(myLevel.getMainPlayer(), generateDefaultKeyPressedMap()));
		
				myLevel.getMainPlayer().resetTerminalVelocities();
				myLevel.getMainPlayer().setLevel(myLevel);
			}
			else if(s instanceof Enemy){
				//s.addState(new Physics(new SpritePhysics()));
				if(s.getName().equals("goomba")){
					s.setXVelocity(100);
				}
			}
			else{
				//s.addState(new Physics(new SpritePhysics(0.0)));
				//if(s.getCharacteristics().size()>1){
				//	s.setXVelocity(100.0);
				//	s.addState(new Health(10));
					//s.setYVelocity(200);
				//}
			}
		}
		/*int j = 1;
		myLevel.setPlayerSprite((Player) myLevel.getMySpriteList().get(0));
		myLevel.getMainPlayer().addState(new Physics(new SpritePhysics()));
		myLevel.getMainPlayer().addState(new Health(10000));
		myLevel.getMainPlayer().addState(new LevelWon());
		myLevel.getMainPlayer()
				.setControllable(new Controllable(myLevel.getMainPlayer(), generateDefaultKeyPressedMap(), myLevel));

		myLevel.getMainPlayer().resetTerminalVelocities();
		int j = 1;
		
		// for(int i = 226; i<10260; i+=1000){

		for (int i = 226; i < 8226; i += 100) {
			
				myLevel.addNewSprite(
						new Terrain(new Location(i, 500), 100, 100, "block" + j, "author/images/betterblock.png"));
			
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
		int k = 842;
		for (int g = 1926; g < 4226; g += 300) {
			myLevel.addNewSprite(
					new Terrain(new Location(g, 130), 100, 100, "block" + k, "author/images/betterblock.png"));
			// myLevel.getMySpriteList().get(j).addCharacteristic(new
			// Bouncer(20, myLevel.getMySpriteList().get(j)));
			// System.out.println(myLevel.getMySpriteList().get(j).getStates().size());
			// myLevel.getMySpriteList().get(j).addState(new Physics(new
			// SpritePhysics(0.0)));
			// System.out.println(myLevel.getMySpriteList().get(j).getStates().size());
			// System.out.println(j);
			// System.out.println(myLevel.getMySpriteList().size());
			k++;
		}
		
		//myLevel.addNewSprite(new Terrain(new Location(726, 400), 100, 100, "block5000", "author/images/betterblock.png"));
		//myLevel.addNewSprite(new Terrain(new Location(826, 300), 100, 100, "block500001", "author/images/betterblock.png"));
		//myLevel.addNewSprite(new Terrain(new Location(0, 0), 200, 50, "block123123123", "author/images/betterblock.png"));
		
		myLevel.addNewSprite(new Terrain(new Location(0, 100), 200, 50, "blockmoving", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(0, 200), 200, 50, "blockmoving", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(0, 300), 200, 50, "blockmoving", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(200, 400), 200, 50, "blockmoving", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(600, 500), 200, 50, "blockmoving2", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(0, 600), 200, 50, "blockmoving2", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(0, 700), 200, 50, "blockmoving2", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(0, 800), 200, 50, "blockmoving2", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(400, 900), 200, 50, "blockmoving2", "author/images/betterblock.png"));
		myLevel.addNewSprite(new Terrain(new Location(000, 1000), 200, 50, "blockmoving2", "author/images/victory_flag.png"));
		
		//myLevel.addNewSprite(new Enemy(new Location(1226, 401), 80, 80, "goomba1", "author/images/angry_goomba.png"));
		//myLevel.addNewSprite(new Enemy(new Location(1426, 401), 80, 80, "goomba2", "author/images/angry_goomba.png"));
		//myLevel.addNewSprite(new Enemy(new Location(2226, -1), 80, 80, "goomba3", "author/images/angry_goomba.png"));
		//myLevel.addNewSprite(new Item(new Location(4040, 30), 50, 100, "flag", "author/images/victory_flag.png"));
		for (Sprite s : myLevel.getMySpriteList()) {
			if (!(s instanceof Player || s instanceof Enemy)) {
				if(s.getName().equals("blockmoving")){
					//s.addCharacteristic(new BouncerTop(500, s));
					s.addCharacteristic(new TransparentBottomImpassable(s));
					//s.addCharacteristic(new StickyTopVertical(s));
					s.addCharacteristic(new BouncerTop(8000,s));
					s.setMyRandomMoveHandler(new RandomMoveHandler(Orientation.VERTICAL));
				}
				else if(s.getName().equals("blockmoving2")) {
					s.addCharacteristic(new TransparentBottomImpassable(s));
					s.addCharacteristic(new BouncerTop(8000,s));
					s.setMyRandomMoveHandler(new RandomMoveHandler(Orientation.VERTICAL));
					s.addCharacteristic(new PacerAlternative("HORIZONTAL", Math.random()*500,s));
					s.setMyXVelocity(Math.random()*200 + 100);
				}
				s.addState(new Physics(new SpritePhysics(0.0)));
			}
		}
		
		Item t = new Item(new Location(726, 400), 100, 100, "block5000", "author/images/angry_goomba.png");
//		 t.addCharacteristic(new SpeedPowerUpper(20, 5000, t));
		 t.addCharacteristic(new Damager(20, t));
//		myLevel.addNewSprite(
//				new Terrain(new Location(726, 400), 100, 100, "block5000", "author/images/betterblock.png"));
//		
		myLevel.addNewSprite(t);
		t.addCharacteristic(new Bouncer(GameResources.BOUNCE_SPEED_HORIZONTAL.getDoubleResource(), GameResources.BOUNCE_SPEED_VERTICAL.getDoubleResource(), t));
		myLevel.addNewSprite(
				new Terrain(new Location(826, 300), 100, 100, "block500001", "author/images/betterblock.png"));
		myLevel.addNewSprite(
				new Terrain(new Location(926, 200), 100, 100, "block123123123", "author/images/betterblock.png"));

		myLevel.addNewSprite(
				new Terrain(new Location(1126, 300), 200, 25, "blockmoving", "author/images/betterblock.png"));
		myLevel.addNewSprite(
				new Terrain(new Location(1350, 250), 200, 25, "blockmoving2", "author/images/betterblock.png"));

		myLevel.addNewSprite(new Enemy(new Location(1226, 401), 80, 80, "goomba1", "author/images/angry_goomba.png"));
		myLevel.addNewSprite(new Enemy(new Location(1426, 401), 80, 80, "goomba2", "author/images/angry_goomba.png"));
		myLevel.addNewSprite(new Enemy(new Location(2226, -1), 80, 80, "goomba3", "author/images/angry_goomba.png"));
		myLevel.addNewSprite(new Item(new Location(4040, 30), 50, 100, "flag", "author/images/victory_flag.png"));
		for (Sprite s : myLevel.getMySpriteList()) {
			if (!(s instanceof Player || s instanceof Enemy)) {
				// if(s.getName().equals("block1")){
				// s.addCharacteristic(new Bouncer(100, s));
				// }
				// else{
				// // s.addCharacteristic(new Impassable(s));
				// <<<<<<< HEAD
				// if (s.getName().equals("block5000")) {
				// s.addCharacteristic(new Breakable(true, true,true, true, 1,
				// s));
				//// s.addCharacteristic(new SpeedPowerUpper(20, 5000, s));
				// s.addCharacteristic(new HealthPowerUpper(20, s));
				//
				// }
				//
				// else {
				// s.addCharacteristic(new Impassable(s));
//				if (s.getName().equals("block5000")) {
//					s.addCharacteristic(new Damager(25, s));
//				}
<<<<<<< HEAD
=======
<<<<<<< HEAD
//				if (s.getName().equals("block5000")) {
//					s.addCharacteristic(new Damager(25, s));
//				}
=======
>>>>>>> 48d78ac850e44a47957b530a216ee8172c366a8c
>>>>>>> 1c26035e97064aea2aac7e7592cf824b2cffee2f
=======
>>>>>>> staging
				// }
				// s.addState(new Health(10));
				// =======
				// if (s.getName().equals("block5000")) {
				// s.addCharacteristic(new Damager(25, s));
				// }
				// }
				if (s.getName().equals("blockmoving")) {
					// s.addCharacteristic(new BouncerTop(500, s));
					s.addCharacteristic(new TransparentBottomImpassable(s));
					s.addCharacteristic(new StickyTopVertical(s));
				} else if (s.getName().equals("blockmoving2")) {
					// s.addCharacteristic(new BouncerTop(500, s));
					// s.addCharacteristic(new TransparentBottomImpassable(s));
					s.addCharacteristic(new Impassable(s));
					s.addCharacteristic(new StickyTopHorizontal(s));
				} else if (!s.getName().equals("flag")) {
					s.addCharacteristic(new Impassable(s));
				}
				if (s.getName().equals("flag")) {
					s.addCharacteristic(new Winnable(s));
				}
				s.addState(new Physics(new SpritePhysics(0.0)));
			}
			if (s instanceof Enemy && !s.getName().equals("goomba2")) {
				s.addCharacteristic(new PacerAlternative("HORIZONTAL", 500, s));
				s.setXVelocity(100);
				s.addCharacteristic(new Damager(1, s));
				s.addState(new Physics(new SpritePhysics()));
			}
			if (s.getName().equals("goomba2")) {
				s.addCharacteristic(new PacerAlternative("HORIZONTAL", 500, s));
				s.setXVelocity(300);
				s.addCharacteristic(new Damager(1, s));
				s.addState(new Physics(new SpritePhysics()));
			}
			if (s.getName().equals("blockmoving")) {
				s.addCharacteristic(new PacerAlternative("VERTICAL", 200, s));
				s.setYVelocity(-200);
			}
			if (s.getName().equals("blockmoving2")) {
				s.addCharacteristic(new PacerAlternative("HORIZONTAL", 300, s));
				s.setXVelocity(200);
			}
		}*/

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
		myLevel.setMyControllableSpriteList();
		updateSpriteData();
	}
	private Map<KeyCode, Action> generateDefaultKeyPressedMap() {
		Map<KeyCode, Action> myKeyPressedMap = new HashMap<KeyCode, Action>();
		//System.out.println(GameResources.MOVE_RIGHT_SPEED.getDoubleResource());
		//System.out.println(myLevel.getMainPlayer()==null); 
		Level myLevel = myGame.getCurrentLevel();
		myKeyPressedMap.put(KeyCode.RIGHT, 
				new MoveRight(myLevel.getMainPlayer(), GameResources.MOVE_RIGHT_SPEED.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.LEFT, 
				new MoveLeft(myLevel.getMainPlayer(), GameResources.MOVE_LEFT_SPEED.getDoubleResource()));
		myKeyPressedMap.put(KeyCode.UP, 
				new MoveUpJump(myLevel.getMainPlayer(), GameResources.JUMP_SPEED.getDoubleResource()));
		Terrain myProjectile = new Terrain(new Location(myLevel.getMainPlayer().getLocation().getXLocation(),
				myLevel.getMainPlayer().getLocation().getYLocation()+100), 25, 25, 0, 0, "block", "author/images/betterblock.png");

		myProjectile.addState(new Physics(0.0, 0.0));
		myProjectile.addCharacteristic(new Impassable(myProjectile));
		myKeyPressedMap.put(KeyCode.SPACE, new LaunchProxy(myLevel.getMainPlayer(), myProjectile, 0, 0));
		//System.out.println("doing the shit");
		return myKeyPressedMap;
		// myKeyPressedMap.put(KeyCode.SPACE, new
		// Launch(myLevel.getMainPlayer(), 10, 0));
	}

	public void updateControllerData() {
		Level myLevel=myGame.getCurrentLevel();
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
			mySpriteImagePathList.add(((Sprite) sprite).getImagePath());
			Location location = ((Sprite) sprite).getLocation();
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


	public int getWidth() {
		return myWidth;
	}

	public int getHeight() {
		return myHeight;
	}

	public String getMyBackgroundImageFilePath() {
		return myBackgroundImageFilePath;
	}

	public List<Sprite> getMySpriteList() {
		return myGame.getCurrentLevel().getMySpriteList();
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