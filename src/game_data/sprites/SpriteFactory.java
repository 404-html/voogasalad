package game_data.sprites;

import author.images.EmptyImage;
import game_data.Location;
import game_data.Sprite;
import game_data.sprites.builders.SpriteDefaultBuilder;
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
	Sprite buildSprite(Location aLocation, int aWidth, int aHeight, String aName, String aImagePath);
}

/**
 * Sprite factory
 * 
 * @author George Bernard
 */
public enum SpriteFactory implements SpriteBuilder, SpriteDefaultBuilder {
	PLAYER((loc, width, height, name, path) -> {
		return new Player(loc, width, height, name, path);
	}, () -> new PlayerDefaultBuilder().build()),

	ENEMY((loc, width, height, name, path) -> {
		return new Player(loc, width, height, name, path);
	}, () -> new EnemyDefaultBuilder().build()),

	TERRAIN((loc, width, height, name, path) -> {
		return new Terrain(loc, width, height, name, path);
	}, () -> new TerrainDefaultBuilder().build()),

	ITEM((loc, width, height, name, path) -> {
		return new Terrain(loc, width, height, name, path);
	}, () -> new ItemDefaultBuilder().build()),

	PROJECTILE((loc, width, height, name, path) -> {
		return new Terrain(loc, width, height, name, path);
	}, () -> new ProjectileDefaultBuilder().build());

	private SpriteBuilder myBuilder;
	private SpriteDefaultBuilder myDefaultBuilder;

	private SpriteFactory(SpriteBuilder SpriteMaker, SpriteDefaultBuilder aSpriteDefaultBuilder) {
		myBuilder = SpriteMaker;
		myDefaultBuilder = aSpriteDefaultBuilder;
	}

	public Sprite buildEmpty() {
		return buildSprite(new Location(0, 0), 0, 0, "No Name", EmptyImage.INSTANCE.getFile().toString());
	}

	@Override
	public Sprite buildSprite(Location aLocation, int aWidth, int aHeight, String aName, String aFilePath) {
		return myBuilder.buildSprite(aLocation, aHeight, aHeight, aName, aFilePath);
	}

	@Override
	public Sprite build() {
		return myDefaultBuilder.build();
	}

}
