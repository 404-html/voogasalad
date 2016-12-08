/**
 * 
 */
package author.view.util.undo;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import game_data.Level;
import game_data.Sprite;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class RevertManager implements IRevertManager{
	private Map<Sprite, InvalidationListener> existingSprites = new HashMap<>();
	private Stack<Sprite> eventList = new Stack<>();
	/**
	 * 
	 */
	RevertManager(Level level) {
		level.getMySpriteList().forEach((sprite) -> initSpriteListener(sprite));
	}
	
	private void initLevelListener(Level aLevel){
		aLevel.addListener((level) -> {
			Set<Sprite> newSprites = getNewSprites(this.existingSprites.keySet(), aLevel.getMySpriteList());
			Set<Sprite> removedSprites = getRemovedSprites(this.existingSprites.keySet(), aLevel.getMySpriteList());
		
		});
	}
	
	private void initSpriteListener(Sprite aSprite){
		InvalidationListener invalidationListener = ((listener) -> {
			
		});
		aSprite.addListener(invalidationListener);
		this.existingSprites.put(aSprite, invalidationListener);
	}
	
	private Set<Sprite> getNewSprites(Collection<Sprite> aOldSprites, Collection<Sprite> aNewSprites){
		Set<Sprite> sprites = new HashSet<>(aNewSprites);
		sprites.removeAll(aOldSprites);
		return sprites;
	}
	
	private Set<Sprite> getRemovedSprites(Collection<Sprite> aOldSprites, Collection<Sprite> aNewSprites){
		Set<Sprite> sprites = new HashSet<>(aOldSprites);
		sprites.removeAll(aNewSprites);
		return sprites;
	}

}
