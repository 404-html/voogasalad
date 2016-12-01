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
	
	private Sprite targetSprite;
	private List<Sprite> spriteList;
	private Map<Sprite, Side> collisionSprites;
	private Map<Sprite, ImageView> mySpriteImages;

	public ListOfCollidingSprites(Sprite targetSprite, List<Sprite> spriteList, Map<Sprite, ImageView> spriteImages) {
		this.targetSprite = targetSprite;
		this.spriteList = spriteList;
		this.mySpriteImages=spriteImages;
		//collisionSprites = new HashMap<Sprite, Side>();
		getListOfSpritesCollided();
	}
	
	private Map<Sprite, Side> getListOfSpritesCollided(){
		collisionSprites = new HashMap<Sprite, Side>();
		//System.out.println(spriteList.size());
		for(Sprite mySprite:spriteList){
			//System.out.println(mySprite.getName());
//			if(!mySprite.getName().equals(targetSprite.getName())){
//				System.out.println("target is " + targetSprite.getMyLocation().getYLocation());
//				System.out.println("other is " + mySprite.getMyLocation().getYLocation());
//			}
			//if(mySprite != targetSprite && mySpriteImages.get(mySprite).getBoundsInLocal().intersects(mySpriteImages.get(targetSprite).getBoundsInLocal())){
			//if(!mySprite.getName().equals(targetSprite.getName()) && mySpriteImages.get(mySprite).getBoundsInLocal().intersects(mySpriteImages.get(targetSprite).getBoundsInLocal())){
			if(!mySprite.getName().equals(targetSprite.getName()) && (mySpriteImages.get(mySprite).getBoundsInParent()).intersects(mySpriteImages.get(targetSprite).getBoundsInParent())) {
				//image.intersects(localBounds)
				//image.getBoundsInLocal().int
				collisionSprites.put(mySprite, findSideOfCollission(mySprite, targetSprite));
			
			}}
			/*if(!mySprite.getName().equals(targetSprite.getName()) && mySprite.getMyLocation().getYLocation()<targetSprite.getMyLocation().getYLocation()+10
					&&mySprite.getMyLocation().getYLocation()>targetSprite.getMyLocation().getYLocation()-10){
				collisionSprites.put(mySprite, findSideOfCollission(mySprite, targetSprite));
			}
		}	
		//System.out.println("numColliding " + collisionSprites.size());
		return collisionSprites;
	}*/
		return collisionSprites;
	}

/*	private  Side findSideOfCollission(Sprite mySprite, Sprite targetSprite) {
		if(mySprite.getMyLocation().getYLocation() <= targetSprite.getMyLocation().getYLocation()){
			return Side.BOTTOM;
		}
		if(mySprite.getMyLocation().getYLocation() >= targetSprite.getMyLocation().getYLocation()){
			return Side.TOP;
		}
		if(mySprite.getMyLocation().getXLocation() < targetSprite.getMyLocation().getXLocation()){
			return Side.LEFT;
		}
		if(mySprite.getMyLocation().getXLocation() > targetSprite.getMyLocation().getXLocation()){
			return Side.RIGHT;
		}
		return null;
		
	}*/
	
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
		//System.out.println("left is " + leftDistance);
		//System.out.println("right is " + rightDistance);
		//System.out.println("top is " + topDistance);
		//System.out.println("bottom is " + bottomDistance);
		Map<Integer, Double> thing = new HashMap<Integer, Double>();
		thing.put(0, leftDistance);
		thing.put(1, rightDistance);
		thing.put(2, topDistance);
		thing.put(3, bottomDistance);
		int max = 0;
		for(Integer d: thing.keySet()){
		if(thing.get(d)>thing.get(max)){
		max = d;
		}
		}
		return max;
		}

		private Side findSideOfCollission(Sprite mySprite, Sprite targetSprite) {
		Rectangle block = createRectangle(mySprite);
		Rectangle player = createRectangle(targetSprite);
		Shape test = Shape.intersect(block, player);
		//if(targetSprite instanceof Player){
		//System.out.println("bound " + test.getBoundsInParent().getMinX()+test.getBoundsInParent().getWidth());
		//System.out.println("left " + block.getX());
		//}
		double middleX = (test.getBoundsInParent().getMinX()+test.getBoundsInParent().getMaxX())/2.0;
		double middleY = (test.getBoundsInParent().getMinY()+test.getBoundsInParent().getMaxY())/2.0;

		double leftDistance = Math.abs(block.getX()-middleX);
		double rightDistance = Math.abs(block.getX()+block.getWidth()-middleX);
		double topDistance = Math.abs(block.getY()-middleY);
		double bottomDistance = Math.abs(block.getY()+block.getHeight()-middleY);

		int max = getMaxEdge(leftDistance, rightDistance, topDistance, bottomDistance);
		//if(targetSprite instanceof Player){
		//printSide(max);
		//}
		return pickSide(max);

		 
		//Shape test = Shape.intersect(mySpriteImages.get(mySprite), mySpriteImages.get(targetSprite));//mySpriteImages.get(mySprite).getBoundsInParent(), 

		/*if(mySprite.getMyLocation().getYLocation() <= targetSprite.getMyLocation().getYLocation()){
		return Side.BOTTOM;
		}
		if(mySprite.getMyLocation().getYLocation() >= targetSprite.getMyLocation().getYLocation()){
		return Side.TOP;
		}
		if(mySprite.getMyLocation().getXLocation() < targetSprite.getMyLocation().getXLocation()){
		return Side.LEFT;
		}
		if(mySprite.getMyLocation().getXLocation() > targetSprite.getMyLocation().getXLocation()){
		return Side.RIGHT;
		}
		return null;*/

		}

		private Side pickSide(int max) {
		if(max == 0){
		return Side.RIGHT;
		}
		if(max == 1){
		return Side.LEFT;
		}
		if(max == 2){
		return Side.BOTTOM;
		}
		if(max == 3){
		return Side.TOP;
		}
		return null;
		}

}