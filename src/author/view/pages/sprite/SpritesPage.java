package author.view.pages.sprite;

import java.util.Arrays;

import author.controller.IAuthorController;
import game_data.Sprite;
import game_data.sprites.Enemy;
import game_data.sprites.Item;
import game_data.sprites.Player;
import game_data.sprites.Projectile;
import game_data.sprites.SpriteFactory;
import game_data.sprites.Terrain;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;

public class SpritesPage implements InvalidationListener {
	
	private Pane myPane;
	private SpriteScroller myPlayerScroller;
	private SpriteScroller myEnemyScroller;
	private SpriteScroller myTerrainScroller;
	private SpriteScroller myItemScroller;
	private SpriteScroller myProjectileScroller;
	
	private IAuthorController myController;
	
	public SpritesPage(IAuthorController aController){
		myController = aController;
		myController.getModel().getGame().addListener(this);
		
		myPane = new HBox();
		myPlayerScroller = new SpriteScroller(SpriteFactory.PLAYER, myController);
		myEnemyScroller = new SpriteScroller(SpriteFactory.ENEMY, myController);
		myTerrainScroller = new SpriteScroller(SpriteFactory.TERRAIN, myController);
		myItemScroller = new SpriteScroller(SpriteFactory.ITEM, myController);
		myProjectileScroller = new SpriteScroller(SpriteFactory.PROJECTILE, myController);
		
		SpriteScroller[] scrollers = new SpriteScroller[]{
				myPlayerScroller,
				myEnemyScroller,
				myTerrainScroller,
				myItemScroller,
				myProjectileScroller};
		
		Arrays.asList(scrollers).forEach(s -> {
			myPane.getChildren().add(s.getNode());
			HBox.setHgrow(s.getNode(), Priority.ALWAYS);
		});
	}
	
	@Override
	public void invalidated(Observable arg0) {
		myController.getModel().getGame().getPresets().forEach(s -> {routeSprite(s);});
	}
	
	private void routeSprite(Sprite aSprite) {
		
		if(aSprite instanceof Player) myPlayerScroller.giveSprite(aSprite);
		if(aSprite instanceof Enemy) myEnemyScroller.giveSprite(aSprite);
		if(aSprite instanceof Terrain) myTerrainScroller.giveSprite(aSprite);
		if(aSprite instanceof Item) myItemScroller.giveSprite(aSprite);
		if(aSprite instanceof Projectile) myProjectileScroller.giveSprite(aSprite);
		
	}
	
	public Region getRegion(){
		return myPane;
	}
	
	@Override
	public String toString(){
		return "Sprite Editor";
	}

}
