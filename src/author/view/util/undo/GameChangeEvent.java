/**
 * 
 */
package author.view.util.undo;

import java.util.Stack;

import game_data.Sprite;
import javafx.beans.InvalidationListener;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class GameChangeEvent {

	@SuppressWarnings("unused")
	private InvalidationListener invalidationListener;
	
	private Sprite activeSprite;
	private Stack<Sprite> history = new Stack<>();
	private IRevertManagerInternal iRevertManagerInternal;
	
	/**
	 * 
	 */
	public GameChangeEvent(Sprite activeSprite, IRevertManagerInternal iRevertManagerInternal) {
		this.iRevertManagerInternal = iRevertManagerInternal;
		System.out.println(activeSprite);
		this.activeSprite = activeSprite;
		this.history.push(activeSprite.clone());
		initSpriteListener(activeSprite);
	}
	
	public Boolean restore(){
		
		if (this.history.size()>1){
			Sprite restoreSprite = this.history.pop();
			restoreSprite=this.history.pop();
			this.activeSprite.setLocation(restoreSprite.getLocation());
			this.activeSprite.setHeight(restoreSprite.getHeight());
			this.activeSprite.setWidth(restoreSprite.getWidth());
			this.activeSprite.setImagePath(restoreSprite.getImagePath());
			return true;
		}
		return false;
	}
	
	private void createSnapShot(){
		Sprite clone = this.activeSprite.clone();
		System.out.println(clone.getLocation().getXLocation());
		this.history.push(clone);
	}
	
	private void initSpriteListener(Sprite aSprite){
		InvalidationListener invalidationListener = ((listener) -> {
			if (this.history.isEmpty()){
				createSnapShot();
			}
			else if (!compareSnapShot(aSprite, this.history.peek())){
				createSnapShot();
			}
			this.iRevertManagerInternal.addEvent(this);
		});
		aSprite.addListener(invalidationListener);
		this.invalidationListener = invalidationListener;
	}
	
	private Boolean compareSnapShot(Sprite activeSprite, Sprite snapshotSprite){
		return (activeSprite.getLocation().equals(snapshotSprite.getLocation()) && activeSprite.getImagePath().equals(snapshotSprite.getImagePath()) && activeSprite.getWidth() == snapshotSprite.getWidth() && activeSprite.getHeight() == snapshotSprite.getHeight());
	}
	

}
