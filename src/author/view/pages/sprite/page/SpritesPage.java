package author.view.pages.sprite.page;

import java.io.File;

import util.XMLTranslator;
import util.facades.TabPaneFacade;
import util.filehelpers.FolderListor;
import author.controller.IAuthorController;
import author.view.util.language_selection.ILanguageUser;
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

/**
 * @author Addison Howenstine
 *
 */
public class SpritesPage implements InvalidationListener, ILanguageUser {

	private TabPaneFacade myPane;
	private SpriteScroller myPlayerScroller;
	private SpriteScroller myEnemyScroller;
	private SpriteScroller myTerrainScroller;
	private SpriteScroller myItemScroller;
	private SpriteScroller myProjectileScroller;

	private IAuthorController myController;

	public SpritesPage(IAuthorController aController){
		myController = aController;
		myController.addListener(this);
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
	}

	private void addTabsToFacade() {
		myPane = new TabPaneFacade();
		myPane.addTab(SpriteFactory.PLAYER.name(), myPlayerScroller.getNode());
		myPane.addTab(SpriteFactory.ENEMY.name(), myEnemyScroller.getNode());
		myPane.addTab(SpriteFactory.TERRAIN.name(), myTerrainScroller.getNode());
		myPane.addTab(SpriteFactory.ITEM.name(), myItemScroller.getNode());
		myPane.addTab(SpriteFactory.PROJECTILE.name(), myProjectileScroller.getNode());
		setTabNames();
	}

	private void setTabNames() {
		myPane.getTabPane().getTabs().forEach(t -> {
			t.setText(myController.getDisplayText(t.getContent().getId()).toUpperCase());
		});
	}

	@Override
	public void invalidated(Observable arg0) {
		myController.getModel().getGame().getPresets().forEach(s -> {routeSprite(s);});
		setTabNames();
	}

	private void routeSprite(Sprite aSprite) {

		if(aSprite instanceof Player) myPlayerScroller.giveSprite(aSprite);
		if(aSprite instanceof Enemy) myEnemyScroller.giveSprite(aSprite);
		if(aSprite instanceof Terrain) myTerrainScroller.giveSprite(aSprite);
		if(aSprite instanceof Item) myItemScroller.giveSprite(aSprite);
		if(aSprite instanceof Projectile) myProjectileScroller.giveSprite(aSprite);

	}

	public Region getRegion(){
		myPane.getTabPane().setId("SpriteEditor");
		return myPane.getTabPane();
	}

	@Override
	public String toString(){
		return myController.getDisplayText("SpriteEditor");
	}

}
