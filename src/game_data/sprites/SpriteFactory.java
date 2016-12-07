package game_data.sprites;

import author.images.EmptyImage;
import game_data.Location;
import game_data.Sprite;

@FunctionalInterface
abstract interface SpriteBuilder{
	/**
	 * Returns new instance of sprite with given arguments as constructor operands.
	 * 
	 * @param aLocation
	 * @param aFilePath
	 * @return new instance of sprite
	 */
	Sprite buildSprite(Location aLocation, int aWidth, int aHeight, String aName, String aImagePath);
}

/**
 * Sprite factory
 * 
 * @author George Bernard
 */
public enum SpriteFactory implements SpriteBuilder {
	PLAYER( (loc, width, height, name, path) -> { return new Player(loc, width, height, name, path );}),
	ENEMY( (loc, width, height, name, path) -> { return new Enemy(loc, width, height, name, path );} ),
	TERRAIN( (loc, width, height, name, path) -> { return new Terrain(loc, width, height, name, path) ;} ),
	ITEM( (loc, width, height, name, path) -> { return new Item(loc, width, height, name, path );} ),
	PROJECTILE(	(loc, width, height, name, path) -> { return new Projectile(loc, width, height, name, path );} );
	
	private SpriteBuilder myBuilder;	
	private SpriteFactory(SpriteBuilder SpriteMaker){
		myBuilder = SpriteMaker;
	}
	
	public Sprite buildEmpty(){
		return buildSprite(new Location(0,0), 0, 0, "No Name", EmptyImage.INSTANCE.getFile().toString());
	}
	
	@Override
	public Sprite buildSprite(Location aLocation, int aWidth, int aHeight, String aName, String aFilePath) {
		return myBuilder.buildSprite(aLocation, aHeight, aHeight, aName, aFilePath);
	}
		
}
