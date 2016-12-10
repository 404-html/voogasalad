package gameplayer.back_end.Resources;

public enum FrontEndResources {
	
	BOX_INSETS(20),
	SCENE_SIZE(1000),
	STYLESHEET("data/gui/style.css");
	
	
	private double resourceDouble;
	private String resourceString;
	
	FrontEndResources(double aInt) {
		resourceDouble = aInt;
	}
	
	FrontEndResources(String aString) {
		resourceString = aString;
	}

	public double getDoubleResource() {
		return resourceDouble;
	}
	
	public String getStringResource() {
		return resourceString;
	}
}


