package game_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_data.Location;
import game_data.Sprite;
import game_data.characteristics.BouncerTop;
import game_data.characteristics.Characteristic;
import game_data.characteristics.TransparentBottomImpassable;
import game_data.sprites.Player;
import game_data.sprites.Terrain;
import game_data.states.Physics;
import game_data.states.State;
import javafx.geometry.Side;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

/**
 * @author Austin Gartside, Lucia
 *
 */
public class ListOfCollidingSprites {
	//private static double SHIFT_CONSTANT = .005;
	private double shiftX;
	private double shiftY;

	private static double SHIFT_CONSTANT = .010;
	private Sprite targetSprite;
	private List<Sprite> spriteList;
	private Map<Sprite, Side> collisionSprites;
	private Map<Sprite, ImageView> mySpriteImages;

	private long frameTime;

	// private double myTimeElapsed;
	// public ListOfCollidingSprites(Sprite targetSprite, List<Sprite>
	// spriteList, Map<Sprite, ImageView> spriteImages, double timeElapsed) {
	public ListOfCollidingSprites(Sprite targetSprite, List<Sprite> spriteList, Map<Sprite, ImageView> spriteImages) {
		this.targetSprite = targetSprite;
		this.spriteList = spriteList;
		this.mySpriteImages=spriteImages;
		shiftX = .005;
		shiftY = .005;
		//myTimeElapsed=timeElapsed;
		getListOfSpritesCollided();
	}

	private Map<Sprite, Side> getListOfSpritesCollided() {
		collisionSprites = new HashMap<Sprite, Side>();
		/*
		 * UpdateLocation updateTargetLocation = new
		 * UpdateLocation(targetSprite, myTimeElapsed); double oldX =
		 * targetSprite.getMyLocation().getXLocation(); double oldY =
		 * targetSprite.getMyLocation().getYLocation(); double oldXVelo =
		 * targetSprite.getMyXVelocity(); double oldYVelo =
		 * targetSprite.getMyYVelocity(); double oldXAccel =
		 * targetSprite.getMyXAcceleration(); double oldYAccel =
		 * targetSprite.getMyYAcceleration();
		 * updateTargetLocation.updateSpriteParameters();
		 * mySpriteImages.get(targetSprite).setX(targetSprite.getMyLocation().
		 * getXLocation());
		 * mySpriteImages.get(targetSprite).setY(targetSprite.getMyLocation().
		 * getYLocation());
		 */
		for (Sprite mySprite : spriteList) {
			/*
			 * UpdateLocation updateLocation = new UpdateLocation(mySprite,
			 * myTimeElapsed); double oldX2 =
			 * mySprite.getMyLocation().getXLocation(); double oldY2 =
			 * mySprite.getMyLocation().getYLocation(); double oldXVelo2 =
			 * mySprite.getMyXVelocity(); double oldYVelo2 =
			 * mySprite.getMyYVelocity(); double oldXAccel2 =
			 * mySprite.getMyXAcceleration(); double oldYAccel2 =
			 * mySprite.getMyYAcceleration();
			 * updateLocation.updateSpriteParameters();
			 * mySpriteImages.get(mySprite).setX(mySprite.getMyLocation().
			 * getXLocation());
			 * mySpriteImages.get(mySprite).setY(mySprite.getMyLocation().
			 * getYLocation());
			 */
			// System.out.println("this seems null" +
			// mySpriteImages.get(mySprite));
			// findSideOfCollision(mySprite);
			frameTime = System.currentTimeMillis();
			if (!mySprite.getName().equals(targetSprite.getName()) && (mySpriteImages.get(mySprite).getBoundsInParent())
					.intersects(mySpriteImages.get(targetSprite).getBoundsInParent())) {
				collisionSprites.put(mySprite, findSideOfCollision(mySprite));
			}
			/*
			 * mySprite.getMyLocation().setLocation(oldX2, oldY2);
			 * mySprite.setMyXVelocity(oldXVelo2);
			 * mySprite.setMyYVelocity(oldYVelo2);
			 * mySprite.setMyXAcceleration(oldXAccel2);
			 * mySprite.setMyYAcceleration(oldYAccel2);
			 * mySpriteImages.get(mySprite).setX(mySprite.getMyLocation().
			 * getXLocation());
			 * mySpriteImages.get(mySprite).setY(mySprite.getMyLocation().
			 * getYLocation());
			 */
		}
		/*
		 * targetSprite.getMyLocation().setLocation(oldX, oldY);
		 * targetSprite.setMyXVelocity(oldXVelo);
		 * targetSprite.setMyYVelocity(oldYVelo);
		 * targetSprite.setMyXAcceleration(oldXAccel);
		 * targetSprite.setMyYAcceleration(oldYAccel);
		 * mySpriteImages.get(targetSprite).setX(targetSprite.getMyLocation().
		 * getXLocation());
		 * mySpriteImages.get(targetSprite).setY(targetSprite.getMyLocation().
		 * getYLocation());
		 */
		return collisionSprites;
	}

	public Map<Sprite, Side> getCollisionSpriteMap() {
		return this.collisionSprites;
	}

	private Rectangle createRectangle(Sprite aSprite) {
		double x = aSprite.getMyLocation().getXLocation();
		double y = aSprite.getMyLocation().getYLocation();
		double width = aSprite.getMyWidth();
		double height = aSprite.getMyHeight();
		return new Rectangle(x, y, width, height);
	}

	private int getClosestEdge(double leftDistance, double rightDistance, double topDistance, double bottomDistance) {
		Map<Integer, Double> thing = new HashMap<Integer, Double>();
		thing.put(0, leftDistance + 0.05 * targetSprite.getMyWidth());
		thing.put(1, rightDistance + 0.05 * targetSprite.getMyWidth());
		thing.put(2, topDistance);
		thing.put(3, bottomDistance);
		int min = 0;
		for (Integer d : thing.keySet()) {
			if (thing.get(d) < thing.get(min)) {
				min = d;
			}
		}
		return min;
	}

	private Side findSideOfCollision(Sprite mySprite) {
//		Rectangle player = createRectangle(mySprite);
//		Rectangle block = createRectangle(targetSprite);
//		Shape intersection = Shape.intersect(block, player);
//
//		double middleX = (intersection.getBoundsInParent().getMinX()+intersection.getBoundsInParent().getMaxX())/2.0;
//		double middleY = (intersection.getBoundsInParent().getMinY()+intersection.getBoundsInParent().getMaxY())/2.0;
//		double leftDistance = Math.abs(block.getX()-middleX);
//		double rightDistance = Math.abs(block.getX()+block.getWidth()-middleX);
//		double topDistance = Math.abs(block.getY()-middleY);
//		double bottomDistance = Math.abs(block.getY()+block.getHeight()-middleY);
//	
//		int min = getClosestEdge(leftDistance, rightDistance, topDistance, bottomDistance);
//		//if(!(mySprite instanceof Terrain)){
//		if(mySprite instanceof Player && targetSprite instanceof Terrain && !isTransparent()){
//		//	mySprite.getMyLocation().setLocation(mySprite.getMyLocation().getXLocation(), targetSprite.getMyLocation().getYLocation() -0.5- mySprite.getMyHeight());
//			printSide(min);
//			System.out.println("block x is " + block.getX());
//			System.out.println("middleX is " + middleX);
//			shiftPlayer(min, mySprite, leftDistance, rightDistance, topDistance, bottomDistance);
//		}
//		if(mySprite instanceof Player && isTransparent() && pastPlatform(mySprite)){
//			if(min == 2){
//				shiftPlayer(min, mySprite, leftDistance, rightDistance, topDistance, bottomDistance);	
//			}
//		}
//		return pickSide(min, mySprite);
//	}
//	
//	private boolean pastPlatform(Sprite myPlayerSprite){
//		return myPlayerSprite.getMyLocation().getYLocation()+myPlayerSprite.getMyHeight()<targetSprite
//						.getMyLocation().getYLocation()+(targetSprite.getMyHeight()*.5) && myPlayerSprite.getMyYVelocity()>0;
		double myTopLeftX = mySprite.getMyLocation().getXLocation();
		double myTopLeftY = mySprite.getMyLocation().getYLocation();
		// System.out.println(mySprite.getMyLocation().getYLocation());
		double myTopRightX = mySprite.getMyLocation().getXLocation() + mySprite.getMyWidth();
		double myTopRightY = mySprite.getMyLocation().getYLocation();

		double myBottomLeftX = mySprite.getMyLocation().getXLocation();
		double myBottomLeftY = mySprite.getMyLocation().getYLocation() + mySprite.getMyHeight();

		double myBottomRightX = mySprite.getMyLocation().getXLocation() + mySprite.getMyWidth();
		double myBottomRightY = mySprite.getMyLocation().getYLocation() + mySprite.getMyHeight();

		double targetTopLeftX = targetSprite.getMyLocation().getXLocation();
		double targetTopLeftY = targetSprite.getMyLocation().getYLocation();

		double targetTopRightX = targetSprite.getMyLocation().getXLocation() + targetSprite.getMyWidth();
		double targetTopRightY = targetSprite.getMyLocation().getYLocation();

		double targetBottomLeftX = targetSprite.getMyLocation().getXLocation();
		double targetBottomLeftY = targetSprite.getMyLocation().getYLocation() + targetSprite.getMyHeight();

		double targetBottomRightX = targetSprite.getMyLocation().getXLocation() + targetSprite.getMyWidth();
		double targetBottomRightY = targetSprite.getMyLocation().getYLocation() + targetSprite.getMyHeight();

		//if (mySprite instanceof Player) {
		if (mySprite instanceof Player) {

			double mySprite_bottom = mySprite.getMyLocation().getYLocation() + mySprite.getMyHeight();
			double targetSprite_bottom = targetSprite.getMyLocation().getYLocation() + targetSprite.getMyHeight();
			double mySprite_right = mySprite.getMyLocation().getXLocation() + mySprite.getMyWidth();
			double targetSprite_right = targetSprite.getMyLocation().getXLocation() + targetSprite.getMyWidth();

			double bottom_collision = targetSprite_bottom - mySprite.getMyLocation().getYLocation();
			double top_collision = mySprite_bottom - targetSprite.getMyLocation().getYLocation();
			double left_collision = mySprite_right - targetSprite.getMyLocation().getXLocation();
			double right_collision = targetSprite_right - mySprite.getMyLocation().getXLocation();

			if (top_collision < bottom_collision && top_collision < left_collision && top_collision < right_collision) {
				System.out.println("top");
				//if(targetSprite instanceof Terrain && !isTransparent()){
					mySprite.setMyLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
				//}
				if (frameTime + (.001 * 1000 / 120) > System.currentTimeMillis()) {
					return findSideOfCollision(mySprite);
				}
				return Side.TOP;
			} else if (bottom_collision < top_collision && bottom_collision < left_collision
					&& bottom_collision < right_collision) {
				System.out.println("bottom");
				mySprite.setMyLocation(new Location(myTopLeftX, myTopLeftY + bottom_collision));
				if (frameTime + (.001 * 1000 / 120) > System.currentTimeMillis()) {
					return findSideOfCollision(mySprite);
				}
				return Side.BOTTOM;
			} else if (left_collision < right_collision && left_collision < top_collision
					&& left_collision < bottom_collision) {
				System.out.println("left");
				if (left_collision > 10) {
					mySprite.setMyLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
					mySprite.setMyLocation(new Location(myTopLeftX - left_collision, myTopLeftY));
					if (frameTime + (.001 * 1000 / 120) > System.currentTimeMillis()) {
						return findSideOfCollision(mySprite);
					}
					return Side.LEFT;
				}

			} else if (right_collision < left_collision && right_collision < top_collision
					&& right_collision < bottom_collision) {
				System.out.println("right");
				if (right_collision > 10) {
					mySprite.setMyLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
					mySprite.setMyLocation(new Location(myTopLeftX + right_collision, myTopLeftY));
					if (frameTime + (.001 * 1000 / 120) > System.currentTimeMillis()) {
						return findSideOfCollision(mySprite);
					}
					return Side.RIGHT;
				}

			}
		}
		return Side.TOP;
	}

	private boolean pastPlatform(Sprite myPlayerSprite) {
		return myPlayerSprite.getMyLocation().getYLocation() + myPlayerSprite.getMyHeight() < targetSprite
				.getMyLocation().getYLocation()
				// return myPlayerSprite.getMyYVelocity()>0;
				&& myPlayerSprite.getMyYVelocity() > 0;
	}

	private boolean isTransparent() {
		for (Characteristic c : targetSprite.getCharacteristics()) {
			if (c instanceof TransparentBottomImpassable || c instanceof BouncerTop) {// &&
																						// pastPlatform(playerSprite)){
				return true;
			}
		}
		return false;
	}

//	private void shiftPlayer(int min, Sprite aSprite, double leftDistance, double rightDistance,
//			double topDistance, double bottomDistance){
//		shiftX = targetSprite.getMyWidth()/20000.0;
//		shiftY = targetSprite.getMyHeight()/20000.0;
//		if(min == 0){
//			//aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation()-leftDistance+SHIFT_CONSTANT, 
//			//		aSprite.getMyLocation().getYLocation());
//			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation()-leftDistance+shiftX, 
	private void shiftPlayer(int min, Sprite aSprite, double leftDistance, double rightDistance, double topDistance,
			double bottomDistance) {
		if (min == 0) {
			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation() - leftDistance + SHIFT_CONSTANT,
					aSprite.getMyLocation().getYLocation());
			System.out.println("sprite x is " + aSprite.getMyLocation().getXLocation());
			System.out.println("left distance is " + leftDistance);
			System.out.println("shiftX distance is " + shiftX);
			System.out.println();
		}
		if(min == 1){
//			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation()+rightDistance-SHIFT_CONSTANT, 
//					aSprite.getMyLocation().getYLocation());
			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation()+rightDistance-shiftX, 
					aSprite.getMyLocation().getYLocation());
		}
		if(min == 2){
//			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation(), 
//					aSprite.getMyLocation().getYLocation()-topDistance+SHIFT_CONSTANT);
			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation(), 
					aSprite.getMyLocation().getYLocation()-topDistance+shiftY);
		}
		if (min == 1) {
			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation() + rightDistance - SHIFT_CONSTANT,
					aSprite.getMyLocation().getYLocation());
		}
		if (min == 2) {
			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation(),
					aSprite.getMyLocation().getYLocation() - topDistance + SHIFT_CONSTANT);
		}
		// if(min == 3){
		// aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation(),
		// aSprite.getMyLocation().getYLocation()+bottomDistance-SHIFT_CONSTANT);
		// }
	}

	private Side pickSide(int min, Sprite mySprite) {
		if (min == 0) {
			return Side.LEFT;
		}
		if (min == 1) {
			return Side.RIGHT;

		}
		if (min == 2) {
			return Side.TOP;
		}
		if (min == 3) {
			return Side.BOTTOM;
		}
		return null;
	}

	private void printSide(int min) {
		if (min == 0) {
			System.out.println("left");
		}
		if (min == 1) {
			System.out.println("right");
		}
		if (min == 2) {
			System.out.println("top");
		}
		if (min == 3) {
			System.out.println("bottom");
		}
	}
}