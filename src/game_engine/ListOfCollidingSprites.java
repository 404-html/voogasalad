package game_engine;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import game_data.Sprite;
import game_data.sprites.Player;
import javafx.geometry.Side;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

public class ListOfCollidingSprites {
	
	private static double SHIFT_CONSTANT = .01;
	private Sprite targetSprite;
	private List<Sprite> spriteList;
	private Map<Sprite, Side> collisionSprites;
	private Map<Sprite, ImageView> mySpriteImages;

	public ListOfCollidingSprites(Sprite targetSprite, List<Sprite> spriteList, Map<Sprite, ImageView> spriteImages) {
		this.targetSprite = targetSprite;
		this.spriteList = spriteList;
		this.mySpriteImages=spriteImages;
		getListOfSpritesCollided();
	}
	
	private Map<Sprite, Side> getListOfSpritesCollided(){
		collisionSprites = new HashMap<Sprite, Side>();
		for(Sprite mySprite:spriteList){
			if(!mySprite.getName().equals(targetSprite.getName()) && (mySpriteImages.get(mySprite)
					.getBoundsInParent()).intersects(mySpriteImages.get(targetSprite).getBoundsInParent())) {
				collisionSprites.put(mySprite, findSideOfCollision(mySprite));
			
			}
		}
		return collisionSprites;
	}
	
	public Map<Sprite, Side> getCollisionSpriteMap(){
		return this.collisionSprites;
	}
	
	private Rectangle createRectangle(Sprite aSprite){
		double x = aSprite.getMyLocation().getXLocation();
		double y = aSprite.getMyLocation().getYLocation();
		double width = aSprite.getMyWidth();
		double height = aSprite.getMyHeight();
		return new Rectangle(x,y,width,height);
	}

	private int getMaxEdge(double leftDistance, double rightDistance, double topDistance, double bottomDistance) {
		Map<Integer, Double> thing = new HashMap<Integer, Double>();
		thing.put(0, leftDistance);
		thing.put(1, rightDistance);
		thing.put(2, topDistance);
		thing.put(3, bottomDistance);
		int min = 0;
		for(Integer d: thing.keySet()){
			if(thing.get(d)<thing.get(min)){
				min = d;
			}
		}
		return min;
	}

	private Side findSideOfCollision(Sprite mySprite) {
		Rectangle player = createRectangle(mySprite);
		Rectangle block = createRectangle(targetSprite);
		Shape intersection = Shape.intersect(block, player);

		double middleX = (intersection.getBoundsInParent().getMinX()+intersection.getBoundsInParent().getMaxX())/2.0;
		double middleY = (intersection.getBoundsInParent().getMinY()+intersection.getBoundsInParent().getMaxY())/2.0;
	
		double leftDistance = Math.abs(block.getX()-middleX);
		double rightDistance = Math.abs(block.getX()+block.getWidth()-middleX);
		double topDistance = Math.abs(block.getY()-middleY);
		double bottomDistance = Math.abs(block.getY()+block.getHeight()-middleY);
	
		int min = getMaxEdge(leftDistance, rightDistance, topDistance, bottomDistance);
		if(mySprite instanceof Player){
			shiftPlayer(min, mySprite, leftDistance, rightDistance, topDistance, bottomDistance);
			//printSide(min);
		}
		return pickSide(min);
	}

	private void shiftPlayer(int min, Sprite aSprite, double leftDistance, double rightDistance,
			double topDistance, double bottomDistance){
		if(min == 0){
			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation()-leftDistance+SHIFT_CONSTANT, 
					aSprite.getMyLocation().getYLocation());
		}
		if(min == 1){
			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation()+rightDistance-SHIFT_CONSTANT, 
					aSprite.getMyLocation().getYLocation());
		}
		if(min == 2){
			aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation(), 
					aSprite.getMyLocation().getYLocation()-topDistance+SHIFT_CONSTANT);
		}
		//if(min == 3){
		//	aSprite.getMyLocation().setLocation(aSprite.getMyLocation().getXLocation(), 
		//			aSprite.getMyLocation().getYLocation()+bottomDistance-SHIFT_CONSTANT);
		//}
	}
	
	private Side pickSide(int min) {
		if(min == 0){
			return Side.LEFT;
		}
		if(min == 1){
			return Side.RIGHT;
		}
		if(min == 2){
			return Side.TOP;
		}
		if(min == 3){
			return Side.BOTTOM;
		}
		return null;
	}
	
	private void printSide(int min){
		if(min == 0){
			System.out.println("left");
		}
		if(min == 1){
			System.out.println("right");
		}
		if(min == 2){
			System.out.println("top");
		}
		if(min == 3){
			System.out.println("bottom");
		}
	}
}