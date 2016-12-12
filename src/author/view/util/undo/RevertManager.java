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
	private Stack<GameChangeEvent> eventHistory = new Stack<>();
	private Stack<GameChangeEvent> eventFuture = new Stack<>();
	
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
		if (!this.eventHistory.isEmpty()){
			GameChangeEvent gameChangeEvent = eventHistory.pop();
			eventFuture.push(gameChangeEvent);
			if (!gameChangeEvent.undo()){
				removeEvent(gameChangeEvent.getSprite());
			}
		}
	}
	
	@Override
	public void redo(){
		if (!this.eventFuture.isEmpty()){
			GameChangeEvent gameChangeEvent = eventFuture.pop();
			eventHistory.push(gameChangeEvent);
			gameChangeEvent.redo();
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
	public void addHistory(GameChangeEvent aGameChangeEvent) {
		this.eventHistory.push(aGameChangeEvent);
	}
	
	@Override
	public void addFuture(GameChangeEvent aGameChangeEvent){
		this.eventFuture.push(aGameChangeEvent);
	}
	
	private void removeEvent(Sprite aSprite) {
		if (this.existingSprites.containsKey(aSprite)){
			this.existingSprites.get(aSprite).removeListener();
			this.eventHistory.removeAll(Collections.singleton(this.existingSprites.get(aSprite)));
			this.eventFuture.removeAll(Collections.singleton(this.existingSprites.get(aSprite)));
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
