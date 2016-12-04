package author.view.pages.sprite.page;

import java.io.File;
import util.XMLTranslator;
import author.controller.IAuthorController;
import author.view.util.facades.TabPaneFacade;
import author.view.util.file_helpers.FolderListor;
import game_data.Sprite;
import game_data.sprites.Enemy;
import game_data.sprites.Item;
import game_data.sprites.Player;
import game_data.sprites.Projectile;
import game_data.sprites.SpriteFactory;
import game_data.sprites.Terrain;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.layout.Region;

public class SpritesPage implements InvalidationListener {
	
	private TabPaneFacade myPane;
	private SpriteScroller myPlayerScroller;
	private SpriteScroller myEnemyScroller;
	private SpriteScroller myTerrainScroller;
	private SpriteScroller myItemScroller;
	private SpriteScroller myProjectileScroller;
	
	private IAuthorController myController;
	
	public SpritesPage(IAuthorController aController){
		myController = aController;
		myController.getModel().getGame().addListener(this);
		
		createSpriteTabs();
		addTabsToFacade();
	}

	private void createSpriteTabs() {
		myPlayerScroller = new SpriteScroller(SpriteFactory.PLAYER, myController);
		myEnemyScroller = new SpriteScroller(SpriteFactory.ENEMY, myController);
		myTerrainScroller = new SpriteScroller(SpriteFactory.TERRAIN, myController);
		myItemScroller = new SpriteScroller(SpriteFactory.ITEM, myController);
		myProjectileScroller = new SpriteScroller(SpriteFactory.PROJECTILE, myController);
		loadDefaultSprites();
	}
	
	private void loadDefaultSprites() {
		FolderListor fl = new FolderListor("data/sprite/default_sprites/");
		for(String fileName : fl.getFileNames()){
			File aFile = new File(fileName);
			XMLTranslator myLoader = new XMLTranslator();
			myController.getModel().getGame().addPreset((Sprite) myLoader.loadFromFile(aFile));
		}
	}

	private void addTabsToFacade() {
		myPane = new TabPaneFacade();
		myPane.addTab(SpriteFactory.PLAYER.name(), myPlayerScroller.getNode());
		myPane.addTab(SpriteFactory.ENEMY.name(), myEnemyScroller.getNode());
		myPane.addTab(SpriteFactory.TERRAIN.name(), myTerrainScroller.getNode());
		myPane.addTab(SpriteFactory.ITEM.name(), myItemScroller.getNode());
		myPane.addTab(SpriteFactory.PROJECTILE.name(), myProjectileScroller.getNode());
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
		return myPane.getTabPane();
	}
	
	@Override
	public String toString(){
		return "Sprite Editor";
	}

}
