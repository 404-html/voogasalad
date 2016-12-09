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
import game_data.Level;
import game_data.Sprite;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class RevertManager implements IRevertManager, IRevertManagerInternal{
	private Map<Sprite, GameChangeEvent> existingSprites = new HashMap<>();
	private Stack<GameChangeEvent> eventList = new Stack<>();
	/**
	 * 
	 */
	RevertManager(Level level) {
		initLevelListener(level);
	}
	
	@Override
	public void undo(){
		if (!this.eventList.isEmpty()){
			System.out.println("Undo");
			GameChangeEvent gameChangeEvent = eventList.pop();
			gameChangeEvent.restore();
		}
	}
	
	private void initLevelListener(Level aLevel){
		aLevel.addListener((level) -> {
			Set<Sprite> newSprites = getNewSprites(this.existingSprites.keySet(), aLevel.getMySpriteList());
			//Set<Sprite> removedSprites = getRemovedSprites(this.existingSprites.keySet(), aLevel.getMySpriteList());
			addSprites(newSprites);
		});
	}
	
	private void addSprites(Collection<Sprite> aSprites){
		
		aSprites.forEach((sprite) -> {
			System.out.println("Add Sprite");
			this.existingSprites.put(sprite, new GameChangeEvent(sprite, (IRevertManagerInternal) this));			
		});
	}
	
	@Override
	public void addEvent(GameChangeEvent gameChangeEvent) {
		this.eventList.push(gameChangeEvent);
	}
	
	
	
	
	@SuppressWarnings("unused")
	private void removeSprites(Collection<Sprite> aSprites){
		
	}
	
	private Set<Sprite> getNewSprites(Collection<Sprite> aOldSprites, Collection<Sprite> aNewSprites){
		Set<Sprite> sprites = new HashSet<>(aNewSprites);
		sprites.removeAll(aOldSprites);
		return sprites;
	}
	
	@SuppressWarnings("unused")
	private Set<Sprite> getRemovedSprites(Collection<Sprite> aOldSprites, Collection<Sprite> aNewSprites){
		Set<Sprite> sprites = new HashSet<>(aOldSprites);
		sprites.removeAll(aNewSprites);
		return sprites;
	}



}
