package game_engine;
/**
 * 
 * @author LuciaMartos
 *
 */
public class SpritePhysics {
	//environment
	private double verticalGravity;
	private double horizontalGravity;

	//default settings "normal physics"
	public SpritePhysics() {
		setDefaults();
	}
	
	//change vertical gravity
	public SpritePhysics(double verticalGravity){
		setDefaults();
		this.verticalGravity = verticalGravity;
	}
	
	//change horizontal and vertical gravity
	public SpritePhysics(double verticalGravity, double horizontalGravity){
		this.verticalGravity = verticalGravity;
		this.horizontalGravity = horizontalGravity;
	}

	//set the defaults from resources
	private void setDefaults() {
		this.verticalGravity = GameResources.DEFAULT_VERTICAL_GRAVITY.getDoubleResource();
		this.horizontalGravity = GameResources.DEFAULT_HORIZONTAL_GRAVITY.getDoubleResource();
	}

	public double getVerticalGravity(){
		return verticalGravity;
	}
	
	public double getHorizontalGravity(){
		return horizontalGravity;
	}
	
	public SpritePhysics copy(){
		return new SpritePhysics(verticalGravity);
	}
}
