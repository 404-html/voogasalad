package game_engine;


public enum GameResources {
    GAME_TITLE("GAME TITLE HERE"),
    
    DEFAULT_VERTICAL_GRAVITY(800),
    DEFAULT_HORIZONTAL_GRAVITY(0.0),
    
    //Default accelerations
    DEFAULT_DOWN_ACCELERATION(0.0),
	DEFAULT_UP_ACCELERATION(0.0),
	DEFAULT_RIGHT_ACCELERATION(0.0),
	DEFAULT_LEFT_ACCELERATION(0.0),
	
	//Default velocities 
	DEFAULT_DOWN_VELOCITY(0.0),
	DEFAULT_RIGHT_VELOCITY(5.0),
	DEFAULT_LEFT_VELOCITY(5.0),
	DEFAULT_UP_VELOCITY(10.0),
	
	//Default speeds for keys
	MOVE_RIGHT_SPEED(300),
	MOVE_LEFT_SPEED(300),
	JUMP_SPEED(600), 
	//MOVE_RIGHT_SPEED(30.0),
	//MOVE_LEFT_SPEED(30.0),
	//JUMP_SPEED(100.0);
	
	//terminal 
	TERMINAL_X_VELOCITY(1600),
	TERMINAL_Y_VELOCITY(1600), 
	
	
	FLASH_RATE(10),
	TIME_FRAME(60),
	
	//speed boost and bounce speed
	SPEED_BOOST(300), BOUNCE_SPEED_HORIZONTAL(400), BOUNCE_SPEED_VERTICAL(800), RECOVERY_TIME(120);

    private double resourceDouble;
    private String resourceString;

    GameResources(String resource) {
        resourceString = resource;
        resourceDouble = -1;
    }

    GameResources(double resource) {
        resourceString = null;
        resourceDouble = resource;
    }

    public String getResource() {
        return resourceString;
    }

    public double getDoubleResource() {
        return resourceDouble;
    }
}