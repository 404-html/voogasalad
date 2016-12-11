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

	private InvalidationListener invalidationListener;
	
	private Sprite activeSprite;
	private Sprite activeBackup;
	private Stack<Sprite> history = new Stack<>();
	private IRevertManagerInternal iRevertManagerInternal;
	private Boolean isEditing = false;
	
	/**
	 * 
	 */
	public GameChangeEvent(Sprite activeSprite, IRevertManagerInternal iRevertManagerInternal) {
		this.iRevertManagerInternal = iRevertManagerInternal;
		this.activeSprite = activeSprite;
		this.activeBackup = activeSprite.clone();
		this.iRevertManagerInternal.addEvent(this);
		initSpriteListener(activeSprite);
	}
	
	public Boolean restore(){
		
		if (!this.history.isEmpty()){
			this.activeBackup = this.history.pop();
			this.isEditing = true;
			this.activeSprite.setLocation(this.activeBackup.getLocation());
			this.activeSprite.setHeight(this.activeBackup.getHeight());
			this.activeSprite.setWidth(this.activeBackup.getWidth());
			this.activeSprite.setImagePath(this.activeBackup.getImagePath());
			this.isEditing = false;
			return true;
		}
		return false;
	}
	
	public void removeListener(){
		this.activeSprite.removeListener(this.invalidationListener);
	}
	
	public Sprite getSprite(){
		return this.activeSprite;
	}
	
	private void createSnapShot(){
		Sprite clone = this.activeSprite.clone();
		this.history.push(this.activeBackup);
		this.activeBackup = clone;
	}
	
	private void initSpriteListener(Sprite aSprite){
		InvalidationListener invalidationListener = ((listener) -> {
			System.out.println("isEditing: " + this.isEditing);
			if (!this.isEditing && this.history.isEmpty()){
				createSnapShot();
				this.iRevertManagerInternal.addEvent(this);
			}
			else if (!this.isEditing && !compareSnapShot(aSprite, this.history.peek())){
				createSnapShot();
				this.iRevertManagerInternal.addEvent(this);
			}
		});
		aSprite.addListener(invalidationListener);
		this.invalidationListener = invalidationListener;
	}
	
	private Boolean compareSnapShot(Sprite activeSprite, Sprite snapshotSprite){
		return (activeSprite.getLocation().equals(snapshotSprite.getLocation()) && activeSprite.getImagePath().equals(snapshotSprite.getImagePath()) && activeSprite.getWidth() == snapshotSprite.getWidth() && activeSprite.getHeight() == snapshotSprite.getHeight());
	}
	

}
