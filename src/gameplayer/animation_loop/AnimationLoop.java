package gameplayer.animation_loop;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;

public class AnimationLoop {

	private static final int FRAMES_PER_SECOND = 60;
    private static final int MILLISECOND_DELAY = 1000/FRAMES_PER_SECOND;
    private final double SECOND_DELAY = 1.0 / FRAMES_PER_SECOND;
	private Timeline myAnimation;
	
	public AnimationLoop() {
		myAnimation = new Timeline();
	}
	
	public void init(IAnimationLoop a) {
		KeyFrame frame = new KeyFrame(Duration.millis(MILLISECOND_DELAY), e -> a.init(SECOND_DELAY));
		myAnimation.setCycleCount(Timeline.INDEFINITE);
		myAnimation.getKeyFrames().add(frame);
		myAnimation.play();
	}
	
	public void stop() {
		myAnimation.stop();
	}
}