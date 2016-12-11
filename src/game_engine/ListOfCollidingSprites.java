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

import javafx.scene.Node;

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
		 * targetSprite.getLocation().getXLocation(); double oldY =
		 * targetSprite.getLocation().getYLocation(); double oldXVelo =
		 * targetSprite.getXVelocity(); double oldYVelo =
		 * targetSprite.getYVelocity(); double oldXAccel =
		 * targetSprite.getMyXAcceleration(); double oldYAccel =
		 * targetSprite.getMyYAcceleration();
		 * updateTargetLocation.updateSpriteParameters();
		 * mySpriteImages.get(targetSprite).setX(targetSprite.getLocation().
		 * getXLocation());
		 * mySpriteImages.get(targetSprite).setY(targetSprite.getLocation().
		 * getYLocation());
		 */
		for (Sprite mySprite : spriteList) {
			/*
			 * UpdateLocation updateLocation = new UpdateLocation(mySprite,
			 * myTimeElapsed); double oldX2 =
			 * mySprite.getLocation().getXLocation(); double oldY2 =
			 * mySprite.getLocation().getYLocation(); double oldXVelo2 =
			 * mySprite.getXVelocity(); double oldYVelo2 =
			 * mySprite.getYVelocity(); double oldXAccel2 =
			 * mySprite.getMyXAcceleration(); double oldYAccel2 =
			 * mySprite.getMyYAcceleration();
			 * updateLocation.updateSpriteParameters();
			 * mySpriteImages.get(mySprite).setX(mySprite.getLocation().
			 * getXLocation());
			 * mySpriteImages.get(mySprite).setY(mySprite.getLocation().
			 * getYLocation());
			 */
			// System.out.println("this seems null" +
			// mySpriteImages.get(mySprite));
			// findSideOfCollision(mySprite);
			frameTime = System.currentTimeMillis();
			
			if ((!mySprite.getName().equals(targetSprite.getName())) 
					&& (mySpriteImages.get(mySprite).getBoundsInParent())
					.intersects(mySpriteImages.get(targetSprite).getBoundsInParent())) {
				collisionSprites.put(mySprite, findSideOfCollision(mySprite));
			}
			/*
			 * mySprite.getLocation().setLocation(oldX2, oldY2);
			 * mySprite.setXVelocity(oldXVelo2);
			 * mySprite.setYVelocity(oldYVelo2);
			 * mySprite.setXAcceleration(oldXAccel2);
			 * mySprite.setYAcceleration(oldYAccel2);
			 * mySpriteImages.get(mySprite).setX(mySprite.getLocation().
			 * getXLocation());
			 * mySpriteImages.get(mySprite).setY(mySprite.getLocation().
			 * getYLocation());
			 */
		}
		/*
		 * targetSprite.getLocation().setLocation(oldX, oldY);
		 * targetSprite.setXVelocity(oldXVelo);
		 * targetSprite.setYVelocity(oldYVelo);
		 * targetSprite.setXAcceleration(oldXAccel);
		 * targetSprite.setYAcceleration(oldYAccel);
		 * mySpriteImages.get(targetSprite).setX(targetSprite.getLocation().
		 * getXLocation());
		 * mySpriteImages.get(targetSprite).setY(targetSprite.getLocation().
		 * getYLocation());
		 */
		return collisionSprites;
	}

	public Map<Sprite, Side> getCollisionSpriteMap() {
		return this.collisionSprites;
	}


//	private Rectangle createRectangle(Sprite aSprite) {
//		double x = aSprite.getLocation().getXLocation();
//		double y = aSprite.getLocation().getYLocation();
//		double width = aSprite.getWidth();
//		double height = aSprite.getHeight();
//		return new Rectangle(x, y, width, height);

	
	private Rectangle createRectangle(Sprite aSprite){
		double x = aSprite.getLocation().getXLocation();
		double y = aSprite.getLocation().getYLocation();
		double width = aSprite.getWidth();
		double height = aSprite.getHeight();
		return new Rectangle(x,y,width,height);
	}

	private int getClosestEdge(double leftDistance, double rightDistance, double topDistance, double bottomDistance) {
		Map<Integer, Double> thing = new HashMap<Integer, Double>();

//		thing.put(0, leftDistance + 0.05 * targetSprite.getWidth());
//		thing.put(1, rightDistance + 0.05 * targetSprite.getWidth());

		thing.put(0, leftDistance+0.05*targetSprite.getWidth());
		thing.put(1, rightDistance+0.05*targetSprite.getWidth());

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
//		//	mySprite.getLocation().setLocation(mySprite.getLocation().getXLocation(), targetSprite.getLocation().getYLocation() -0.5- mySprite.getHeight());
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
//		return myPlayerSprite.getLocation().getYLocation()+myPlayerSprite.getHeight()<targetSprite
//						.getLocation().getYLocation()+(targetSprite.getHeight()*.5) && myPlayerSprite.getYVelocity()>0;
		double myTopLeftX = mySprite.getLocation().getXLocation();
		double myTopLeftY = mySprite.getLocation().getYLocation();
		// System.out.println(mySprite.getLocation().getYLocation());
		double myTopRightX = mySprite.getLocation().getXLocation() + mySprite.getWidth();
		double myTopRightY = mySprite.getLocation().getYLocation();

		double myBottomLeftX = mySprite.getLocation().getXLocation();
		double myBottomLeftY = mySprite.getLocation().getYLocation() + mySprite.getHeight();

		double myBottomRightX = mySprite.getLocation().getXLocation() + mySprite.getWidth();
		double myBottomRightY = mySprite.getLocation().getYLocation() + mySprite.getHeight();

		double targetTopLeftX = targetSprite.getLocation().getXLocation();
		double targetTopLeftY = targetSprite.getLocation().getYLocation();

		double targetTopRightX = targetSprite.getLocation().getXLocation() + targetSprite.getWidth();
		double targetTopRightY = targetSprite.getLocation().getYLocation();

		double targetBottomLeftX = targetSprite.getLocation().getXLocation();
		double targetBottomLeftY = targetSprite.getLocation().getYLocation() + targetSprite.getHeight();

		double targetBottomRightX = targetSprite.getLocation().getXLocation() + targetSprite.getWidth();
		double targetBottomRightY = targetSprite.getLocation().getYLocation() + targetSprite.getHeight();

		//if (mySprite instanceof Player) {
		if (!(mySprite instanceof Terrain)) {

			double mySprite_bottom = mySprite.getLocation().getYLocation() + mySprite.getHeight();
			double targetSprite_bottom = targetSprite.getLocation().getYLocation() + targetSprite.getHeight();
			double mySprite_right = mySprite.getLocation().getXLocation() + mySprite.getWidth();
			double targetSprite_right = targetSprite.getLocation().getXLocation() + targetSprite.getWidth();

			double bottom_collision = targetSprite_bottom - mySprite.getLocation().getYLocation();
			double top_collision = mySprite_bottom - targetSprite.getLocation().getYLocation();
			double left_collision = mySprite_right - targetSprite.getLocation().getXLocation();
			double right_collision = targetSprite_right - mySprite.getLocation().getXLocation();

			if (top_collision < bottom_collision && top_collision < left_collision && top_collision < right_collision) {
				//System.out.println("top");
				if((targetSprite instanceof Terrain && !isTransparent()) || (isTransparent() && pastPlatform(mySprite))){
					mySprite.setLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
				}
				if (frameTime + (.001 * 1000 / 60) > System.currentTimeMillis()) {
					return findSideOfCollision(mySprite);
				}
				return new Top();
				//return Side.TOP;
			} else if (bottom_collision < top_collision && bottom_collision < left_collision
					&& bottom_collision < right_collision) {
				//System.out.println("bottom");
				if(targetSprite instanceof Terrain && !isTransparent()){
					mySprite.setLocation(new Location(myTopLeftX, myTopLeftY + bottom_collision));
				}
				if (frameTime + (.001 * 1000 / 60) > System.currentTimeMillis()) {
					return findSideOfCollision(mySprite);
				}
				//return Side.BOTTOM;
				return new Bottom();
			} else if (left_collision < right_collision && left_collision < top_collision
					&& left_collision < bottom_collision) {
				//System.out.println("left");
				//if (left_collision > 10) {
					if(targetSprite instanceof Terrain && !isTransparent()){
						mySprite.setLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
						mySprite.setLocation(new Location(myTopLeftX - left_collision, myTopLeftY));
					}
					if (frameTime + (.001 * 1000 / 60) > System.currentTimeMillis()) {
						return findSideOfCollision(mySprite);
					}
					//return Side.LEFT;
					return new Left();
				//}

			} else if (right_collision < left_collision && right_collision < top_collision
					&& right_collision < bottom_collision) {
				//System.out.println("right");
				//if (right_collision > 10) {
				if(targetSprite instanceof Terrain && !isTransparent()){
					mySprite.setLocation(new Location(myTopLeftX, myTopLeftY - top_collision));
					mySprite.setLocation(new Location(myTopLeftX + right_collision, myTopLeftY));
				}
					if (frameTime + (.001 * 1000 / 120) > System.currentTimeMillis()) {
						return findSideOfCollision(mySprite);
					}
					//return Side.RIGHT;
					return new Right();
				//}

			}
		}
		//return Side.TOP;
		return new Top();
	}

	private boolean pastPlatform(Sprite myPlayerSprite) {
		return myPlayerSprite.getLocation().getYLocation() + myPlayerSprite.getHeight() < targetSprite
				.getLocation().getYLocation()+.2*targetSprite.getHeight() && myPlayerSprite.getYVelocity() >= 0;
	
//	private boolean pastPlatform(Sprite myPlayerSprite){
//		return myPlayerSprite.getLocation().getYLocation()+myPlayerSprite.getHeight()<targetSprite
//						.getLocation().getYLocation()+(targetSprite.getHeight()*.5) && myPlayerSprite.getYVelocity()>0;
		//return myPlayerSprite.getYVelocity()>0;
	}

	private boolean isTransparent() {
		for (Characteristic c : targetSprite.getCharacteristics()) {
			if (c instanceof TransparentBottomImpassable || c instanceof BouncerTop) {
				return true;
			}
		}
		return false;
	}

//	private void shiftPlayer(int min, Sprite aSprite, double leftDistance, double rightDistance,
//			double topDistance, double bottomDistance){
//		shiftX = targetSprite.getWidth()/20000.0;
//		shiftY = targetSprite.getHeight()/20000.0;
//		if(min == 0){
//			//aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation()-leftDistance+SHIFT_CONSTANT, 
//			//		aSprite.getLocation().getYLocation());
//			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation()-leftDistance+shiftX, 
//	private void shiftPlayer(int min, Sprite aSprite, double leftDistance, double rightDistance, double topDistance,
//			double bottomDistance) {
//		if (min == 0) {
//			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation() - leftDistance + SHIFT_CONSTANT,
//					aSprite.getLocation().getYLocation());
//			System.out.println("sprite x is " + aSprite.getLocation().getXLocation());
//			System.out.println("left distance is " + leftDistance);
//			System.out.println("shiftX distance is " + shiftX);
//			System.out.println();
//		}
//		if(min == 1){
////			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation()+rightDistance-SHIFT_CONSTANT, 
////					aSprite.getLocation().getYLocation());
//			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation()+rightDistance-shiftX, 
//					aSprite.getLocation().getYLocation());
//		}
//		if(min == 2){
////			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation(), 
////					aSprite.getLocation().getYLocation()-topDistance+SHIFT_CONSTANT);
//			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation(), 
//					aSprite.getLocation().getYLocation()-topDistance+shiftY);
//		}
//		if (min == 1) {
//			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation() + rightDistance - SHIFT_CONSTANT,
//					aSprite.getLocation().getYLocation());
//		}
//		if (min == 2) {
//			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation(),
//					aSprite.getLocation().getYLocation() - topDistance + SHIFT_CONSTANT);
//		}
//		// if(min == 3){
//		// aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation(),
//		// aSprite.getLocation().getYLocation()+bottomDistance-SHIFT_CONSTANT);
//		// }
//	} 
	private void shiftPlayer(int min, Sprite aSprite, double leftDistance, double rightDistance, double topDistance,
			double bottomDistance) {
		if (min == 0) {
			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation() - leftDistance + SHIFT_CONSTANT,
					aSprite.getLocation().getYLocation());
			System.out.println("sprite x is " + aSprite.getLocation().getXLocation());
			System.out.println("left distance is " + leftDistance);
			System.out.println("shiftX distance is " + shiftX);
			//System.out.println();
		}
		if(min == 1){
			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation()+rightDistance-SHIFT_CONSTANT, 
					aSprite.getLocation().getYLocation());
		}
		if(min == 2){
			aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation(), 
					aSprite.getLocation().getYLocation()-topDistance+SHIFT_CONSTANT);
		}
		//if(min == 3){
		//	aSprite.getLocation().setLocation(aSprite.getLocation().getXLocation(), 
		//			aSprite.getLocation().getYLocation()+bottomDistance-SHIFT_CONSTANT);
		//}
	}
	
	private Side pickSide(int min, Sprite mySprite) {
		if (min == 0) {
			//return Side.LEFT;
			return new Left();
		}
		if (min == 1) {
			//return Side.RIGHT;
			return new Right();

		}
		if (min == 2) {
			//return Side.TOP;
			return new Top();
		}
		if (min == 3) {
			//return Side.BOTTOM;
			return new Bottom();
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