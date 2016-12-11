/**
 * 
 */
package author.view.util.undo;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;
import game_data.Level;
import game_data.Sprite;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class RevertManager implements IRevertManager, IRevertManagerInternal{
	private Map<Sprite, GameChangeEvent> existingSprites = new HashMap<>();
	private Stack<GameChangeEvent> eventList = new Stack<>();
	
	private Level level;
	/**
	 * 
	 */
	RevertManager(Level level) {
		this.level = level;
		initLevelListener(level);
	}
	
	@Override
	public void undo(){
		if (!this.eventList.isEmpty()){
			GameChangeEvent gameChangeEvent = eventList.pop();
			if (!gameChangeEvent.restore()){
				removeEvent(gameChangeEvent.getSprite());
			}
		}
		else{
			System.out.println("Event List is Empty");
		}
	}
	
	private void initLevelListener(Level aLevel){
		aLevel.addListener((level) -> {
			Set<Sprite> newSprites = getNewSprites(this.existingSprites.keySet(), aLevel.getMySpriteList());
			Set<Sprite> removedSprites = getRemovedSprites(this.existingSprites.keySet(), aLevel.getMySpriteList());
			addSprites(newSprites);
			removeSprites(removedSprites);
		});
	}
	
	private void addSprites(Collection<Sprite> aSprites){
		aSprites.forEach((sprite) -> {
			this.existingSprites.put(sprite, new GameChangeEvent(sprite, (IRevertManagerInternal) this));			
		});
	}
	
	@Override
	public void addEvent(GameChangeEvent gameChangeEvent) {
		this.eventList.push(gameChangeEvent);
		System.out.println("Size: " + eventList.size());
		this.eventList.forEach((myGameChangeEvent) -> {
			System.out.println(myGameChangeEvent);
		});
	}
	
	private void removeEvent(Sprite aSprite) {
		if (this.existingSprites.containsKey(aSprite)){
			this.existingSprites.get(aSprite).removeListener();
			this.eventList.removeAll(Collections.singleton(this.existingSprites.get(aSprite)));
			this.level.removeSprite(aSprite);
			this.existingSprites.remove(aSprite);
		}
	}
	
	
	
	
	private void removeSprites(Collection<Sprite> aSprites){
		aSprites.forEach((sprite) -> removeEvent(sprite));
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
