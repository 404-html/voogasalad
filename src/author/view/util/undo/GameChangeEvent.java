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
	
	public void restore(){
		
		if (!this.history.isEmpty()){
			System.out.println("Restore");
			Sprite restoreSprite = this.history.pop();
			System.out.println("Current X:  " + this.activeSprite.getMyLocation().getXLocation() + "   New X: " + restoreSprite.getMyLocation().getXLocation());
			this.activeSprite.setMyLocation(restoreSprite.getMyLocation());
			this.activeSprite.setMyHeight(restoreSprite.getMyHeight());
			this.activeSprite.setMyWidth(restoreSprite.getMyWidth());
			this.activeSprite.setMyImagePath(restoreSprite.getMyImagePath());
		}
	}
	
	private void createSnapShot(){
		System.out.println("Creating Snapshot");
		Sprite clone = this.activeSprite.clone();
		System.out.println(clone.getMyLocation().getXLocation());
		this.history.push(clone);
	}
	
	private void initSpriteListener(Sprite aSprite){
		InvalidationListener invalidationListener = ((listener) -> {
			System.out.println("Listen");
			if (this.history.isEmpty()){
				createSnapShot();
				this.iRevertManagerInternal.addEvent(this);
			}
			else if (!compareSnapShot(aSprite, this.history.peek())){
				
				createSnapShot();
				this.iRevertManagerInternal.addEvent(this);
			}
		});
		aSprite.addListener(invalidationListener);
		this.invalidationListener = invalidationListener;
	}
	
	private Boolean compareSnapShot(Sprite activeSprite, Sprite snapshotSprite){
		return (activeSprite.getMyLocation().equals(snapshotSprite.getMyLocation()) && activeSprite.getMyImagePath().equals(snapshotSprite.getMyImagePath()) && activeSprite.getMyWidth() == snapshotSprite.getMyWidth() && activeSprite.getMyHeight() == snapshotSprite.getMyHeight());
	}
	

}
