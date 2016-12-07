package game_data;

/**
 * Holds X and Y location of a Sprite
 * 
 * @author Addison
 */
public class Location {

	private double myXLocation;
	private double myYLocation;
	
	public Location(double aXLocation, double aYLocation){
		setLocation(aXLocation, aYLocation);
	}
	
	
	public double getXLocation(){
		return myXLocation;
	}
	
	public double getYLocation(){
		return myYLocation;
	}
	public void setLocation(double myXLocation, double myYLocation){
		this.myXLocation = myXLocation;
		this.myYLocation = myYLocation;
	}

}
