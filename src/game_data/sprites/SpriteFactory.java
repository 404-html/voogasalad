package game_data.sprites;

import game_data.Location;
import game_data.Sprite;
import game_data.sprites.builders.SpriteDefaultBuilder;
import game_data.sprites.builders.defaults.EmptyImage;
import game_data.sprites.builders.defaults.EnemyDefaultBuilder;
import game_data.sprites.builders.defaults.ItemDefaultBuilder;
import game_data.sprites.builders.defaults.PlayerDefaultBuilder;
import game_data.sprites.builders.defaults.ProjectileDefaultBuilder;
import game_data.sprites.builders.defaults.TerrainDefaultBuilder;

@FunctionalInterface
interface SpriteBuilder {
	/**
	 * Returns new instance of sprite with given arguments as constructor
	 * operands.
	 * 
	 * @param aLocation
	 * @param aFilePath
	 * @return new instance of sprite
	 */
	Sprite buildSprite(Location aLocation, int aWidth, int aHeight, double xVelocity, double yVelocity, String aName, String aImagePath);
}

/**
 * Sprite factory
 * 
 * @author George Bernard (ghb5), Addison Howenstine (awh55)
 */
public enum SpriteFactory implements SpriteBuilder, SpriteDefaultBuilder {
	PLAYER((loc, width, height, xvel, yvel, name, path) -> {
		return new Player(loc, width, height, xvel, yvel, name, path);
	}, () -> new PlayerDefaultBuilder().build()),

	ENEMY((loc, width, height, xvel, yvel, name, path) -> { 
		return new Enemy(loc, width, height, xvel, yvel, name, path);
	}, () -> new EnemyDefaultBuilder().build()),	

	TERRAIN((loc, width, height, xvel, yvel, name, path) -> {
		return new Terrain(loc, width, height, xvel, yvel, name, path);
	}, () -> new TerrainDefaultBuilder().build()),

	ITEM((loc, width, height, xvel, yvel, name, path) -> {
		return new Item(loc, width, height, xvel, yvel, name, path);
	}, () -> new ItemDefaultBuilder().build()),

	PROJECTILE(
			(loc, width, height, xvel, yvel, name, path) -> { return new Projectile(loc, width, height, xvel, yvel, name, path);} , 
			() -> new ProjectileDefaultBuilder().build() );

	private SpriteBuilder myBuilder;
	private SpriteDefaultBuilder myDefaultBuilder;

	private SpriteFactory(SpriteBuilder SpriteMaker, SpriteDefaultBuilder aSpriteDefaultBuilder) {
		myBuilder = SpriteMaker;
		myDefaultBuilder = aSpriteDefaultBuilder;
	}

	public Sprite buildEmpty() {
		return buildSprite(new Location(0, 0), 0, 0, 0, 0, "No Name", EmptyImage.INSTANCE.getFile().toString());
	}

	@Override
	public Sprite buildSprite(Location aLocation, int aWidth, int aHeight, double XVelocity, double yVelocity, String aName, String aFilePath) {
		return myBuilder.buildSprite(aLocation, aHeight, aHeight, XVelocity, yVelocity, aName, aFilePath);
	}

	@Override
	public Sprite build() {
		return myDefaultBuilder.build();
	}

}
