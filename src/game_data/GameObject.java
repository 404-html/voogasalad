/**
 * 
 */
package game_data;

import java.util.Collection;
import java.util.HashSet;

import com.thoughtworks.xstream.annotations.XStreamOmitField;

import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public abstract class GameObject implements Observable{
	
	@XStreamOmitField
	private Collection<InvalidationListener> invalidationListeners = new HashSet<>();
	
	private String name;

	/* (non-Javadoc)
	 * @see javafx.beans.Observable#addListener(javafx.beans.InvalidationListener)
	 */
	@Override
	public void addListener(InvalidationListener listener) {
		if (this.invalidationListeners == null){
			this.invalidationListeners = new HashSet<>();
		}
		this.invalidationListeners.add(listener);
	}

	/* (non-Javadoc)
	 * @see javafx.beans.Observable#removeListener(javafx.beans.InvalidationListener)
	 */
	@Override
	public void removeListener(InvalidationListener listener) {
		if (this.invalidationListeners == null){
			this.invalidationListeners = new HashSet<>();
		}
		this.invalidationListeners.remove(listener);
	}
	
	protected void notifyListeners(){
		if (this.invalidationListeners == null){
			this.invalidationListeners = new HashSet<>();
		}
		this.invalidationListeners.forEach((listener) -> {
			if (listener!=null){
				listener.invalidated(this);
			}
		});
	}
	
	public String getName(){
		return this.name;
	}
	
	public void setName(String aName){
		this.name = aName;
		notifyListeners();
	}

}
