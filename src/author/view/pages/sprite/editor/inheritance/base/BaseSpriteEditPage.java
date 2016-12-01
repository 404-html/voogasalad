package author.view.pages.sprite.editor.inheritance.base;

import author.view.pages.sprite.editor.inheritance.character.EnemySpriteEditPage;
import author.view.pages.sprite.editor.inheritance.character.PlayerSpriteEditPage;
import author.view.pages.sprite.editor.inheritance.item.ItemSpriteEditPage;
import author.view.pages.sprite.editor.inheritance.projectile.ProjectileSpriteEditPage;
import author.view.pages.sprite.editor.inheritance.terrain.TerrainSpriteEditPage;
import author.view.pages.sprite.editor.settings.characteristics.SpriteCharacteristicEditor;
import author.view.util.authoring_buttons.ButtonFactory;
import author.view.util.facades.TabPaneFacade;
import author.view.util.facades.ToolBarBuilder;
import game_data.Location;
import game_data.Sprite;
import game_data.sprites.Enemy;
import game_data.sprites.Item;
import game_data.sprites.Player;
import game_data.sprites.Projectile;
import game_data.sprites.Terrain;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

/**
 * Abstract class for Sprite viewing and editing
 * 
 * 
 * @author George Bernard
 */
public abstract class BaseSpriteEditPage {
	
	public enum SpriteType{
		ENEMY,
		PLAYER,
		ITEM,
		PROJECTILE,
		TERRAIN,
		NOT_A_SPRITE;
		
		public static SpriteType discern(Sprite aSprite) {
			
			if(aSprite instanceof Enemy) return ENEMY;
			if(aSprite instanceof Player) return PLAYER;
			if(aSprite instanceof Item) return ITEM;
			if(aSprite instanceof Projectile) return PROJECTILE;
			if(aSprite instanceof Terrain) return TERRAIN;
			else return NOT_A_SPRITE;
			
		}
		
	}
	
	private Pane myPane;
	private ToolBarBuilder myToolBarBuilder;
	
	private Sprite mySprite;
	private BaseSpriteEditBox mySpriteEditBox;
	private SpriteCharacteristicEditor myCharacteristicEditor;
	private TabPaneFacade myTabPaneFacade;
	
	public BaseSpriteEditPage(Sprite aSprite){
		mySprite = aSprite;
		
		myPane = new VBox();
		myCharacteristicEditor = new SpriteCharacteristicEditor(mySprite, getSpriteType());
		myTabPaneFacade = new TabPaneFacade();
		myToolBarBuilder = new ToolBarBuilder();
		mySpriteEditBox = new BaseSpriteEditBox();
		
		myTabPaneFacade.addTab("Base", mySpriteEditBox.getPane());
		myTabPaneFacade.addTab("Characteristics", this.myCharacteristicEditor.getNode());
		myPane.getChildren().addAll(myToolBarBuilder.getToolBar(), myTabPaneFacade.getTabPane());
		Button buildButton = new ButtonFactory().createButton(
				"Save", 
				e -> {editSprite(); myCharacteristicEditor.addCharacteristics(); }
				).getButton();
		myToolBarBuilder.addBurst(new Label(getSpriteType()));
		myToolBarBuilder.addBurst(buildButton);
		
		mySpriteEditBox.setLocation(aSprite.getMyLocation());
		mySpriteEditBox.setImageFile(aSprite.getMyImagePath());
		mySpriteEditBox.setName(aSprite.getName());
		mySpriteEditBox.setSize(aSprite.getMyWidth(), aSprite.getMyHeight());
	}
	
	public static BaseSpriteEditPage build( Sprite aSprite){
		
		switch (SpriteType.discern(aSprite)) {
		case PLAYER:	 return new PlayerSpriteEditPage(aSprite);
		case ENEMY:		 return new EnemySpriteEditPage(aSprite);
		case TERRAIN:	 return new TerrainSpriteEditPage(aSprite);
		case ITEM: 		 return new ItemSpriteEditPage(aSprite);
		case PROJECTILE: return new ProjectileSpriteEditPage(aSprite);
		default: return null;
		}
	}
	
	public abstract String getSpriteType();

	public Sprite editSprite(){
		
		getSprite().setMyLocation(getLocation());
		getSprite().setMyImagePath(getImageFile().toString());
		getSprite().setMyWidth(getWidth());
		getSprite().setMyHeight(getHeight());
		getSprite().setName(getSpriteName());

		return getSprite();
	}
	
	public Pane getPane(){
		return myPane;
	}
	
	protected final String getSpriteName(){
		return mySpriteEditBox.getName();
	}
	
	protected final void setSpriteName(String aSpriteName){
		mySpriteEditBox.setName(aSpriteName);
	}
	
	protected final int getWidth(){
		return mySpriteEditBox.getWidth();
	}
	
	protected final int getHeight(){
		return mySpriteEditBox.getHeight();
	}
	
	protected final boolean hasSprite(){
		return mySprite == null;
	}

	protected final String getImageFile(){
		return mySpriteEditBox.getImageFile();
	}
	
	protected final Location getLocation(){
		return mySpriteEditBox.getLocation();
	}
	
	protected ToolBarBuilder getToolBarBuilder(){
		return myToolBarBuilder;
	}

	protected Sprite getSprite(){
		return mySprite;
	}

}
