package game_data.states;

import game_data.characteristics.characteristic_annotations.ParameterAnnotation;
import game_data.characteristics.characteristic_annotations.ViewableMethodOutput;

public class ScreenRatio implements State{

	private double topRatio;
	private double bottomRatio;
	
	
	@ParameterAnnotation(parameters={"Top Ratio", "Bottom Ratio"})
	public ScreenRatio(double topRatio, double bottomRatio){
		this.topRatio = topRatio;
		this.bottomRatio = bottomRatio;
	}
	
	@Override
	public State copy() {
		return new ScreenRatio(topRatio, bottomRatio);
	}
	
	@ViewableMethodOutput(description="Top Ratio", type=int.class)
	public double getTopRatio() {
		return topRatio;
	}
	@ViewableMethodOutput(description="Bottom Ratio", type=int.class)
	public double getBottomRatio() {
		return bottomRatio;
	}
	
	@Override
	public void updateState(double pointsUp) {

	}	
	
}
