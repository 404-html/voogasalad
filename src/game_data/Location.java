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
	@Override
	public boolean equals(Object object){
		if (object instanceof Location) {
			Location location = (Location) object;
			return (location.getXLocation() == this.getXLocation() 
					&& location.getYLocation() == this.getYLocation());
		}
		return false;
	}
	
	public double calculateDistance(Location location){
		double xDiff = myXLocation - location.getXLocation();
		double yDiff = myYLocation - location.getYLocation();
		return Math.sqrt((xDiff * xDiff) + (yDiff * yDiff));
	}

	@Override
	public String toString(){
		return myXLocation + " x "  + myYLocation;
	}
	
}
