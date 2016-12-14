package author.view.pages.sprite.editor.controllable;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.apache.commons.exec.ExecuteException;

import game_data.Controllable;
import game_data.Sprite;
import game_data.sprites.SpriteFactory;
import game_engine.actions.Action;
import game_engine.actions.Launch;
import game_engine.actions.LaunchProxyHorizontal;
import game_engine.actions.LaunchProxyVertical;
import game_engine.actions.Move;
import game_engine.actions.MoveLeft;
import game_engine.actions.MoveRight;
import game_engine.actions.MoveUpFly;
import game_engine.actions.MoveUpJump;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.util.Pair;
import util.inputfields.EnabledField;
import util.inputfields.KeyCodeFieldBox;
import util.inputfields.NumberFieldBox;

public class ControllableEditor implements InvalidationListener {
	private Pane myPane;
	private Sprite mySprite;

	private Map<MoveFactory, EnabledField<Pair<KeyCodeFieldBox,NumberFieldBox>>> myMoveToFieldMap;

	private enum MoveFactory {
		MOVE_RIGHT(MoveRight.class, "Move Right", (s, d) -> new MoveRight(s, d)),
		MOVE_LEFT(MoveLeft.class, "Move Left", (s, d) -> new MoveLeft(s, d)),
		MOVE_UP_JUMP(MoveUpJump.class, "Jump Up", (s, d) -> new MoveUpJump(s, d)),
		MOVE_UP_FLY(MoveUpFly.class, "Fly Up",  (s, d) -> new MoveUpFly(s, d)),
		LAUNCH_HORIZONTAL(LaunchProxyHorizontal.class, "Launch Horizontal", (s, d) -> new LaunchProxyHorizontal(s, SpriteFactory.PROJECTILE.buildDefault(), d)),
		LAUNCH_VERTICAL(LaunchProxyVertical.class, "Launch Vertical", (s, d) -> new LaunchProxyVertical(s, SpriteFactory.PROJECTILE.buildDefault(), d));
		;

		@FunctionalInterface
		private interface ActionMaker {
			public Action make(Sprite aSprite, Double aVelocity);
		}

		private ActionMaker myMoveMaker;
		private Class<? extends Action> myMoveClass;
		private String myDescription;

		private MoveFactory (Class<? extends Action> aMoveClass, String aDescription, ActionMaker maker) {
			myDescription = aDescription;
			myMoveClass = aMoveClass;
			myMoveMaker = maker;
		}

		public static MoveFactory discern(Class<? extends Action> aMoveClass ) throws ExecuteException{
			for (MoveFactory move : MoveFactory.values()) {
				if(move.myMoveClass.equals(aMoveClass)) return move;
			}
			throw new ExecuteException("No Move Found", 0);
		}

		public Action make(Sprite aSprite, Double aVelocity) {
			return this.myMoveMaker.make(aSprite, aVelocity);
		}

		public String toString() {
			return myDescription;
		}
	}

	public ControllableEditor(Sprite aSprite){
		myPane = new VBox();
		myMoveToFieldMap = new HashMap<>();
		mySprite = aSprite;

		buildMap();
		buildPane();
		
		if(mySprite.getControllable().getMyKeyPressedMap() != null) 
			update(aSprite.getControllable());
	}

	public void setControllable(){
		Map<KeyCode, Action> map = new TreeMap<>();

		myMoveToFieldMap.entrySet().forEach( e -> {
			if( ! e.getValue().isEnabled() ) return;
			
			map.put(e.getValue().get().getKey().getCode(), 
				e.getKey().make(mySprite, e.getValue().get().getValue().getDouble()));
		});

		Controllable control = new Controllable(mySprite, map);
		mySprite.setControllable(control);
	}

	public Pane getPane(){
		return myPane;
	}

	@Override
	public void invalidated(Observable observable) {
		if(observable instanceof Sprite) {
			update(((Sprite) observable).getControllable()); 
		}
	}

	private void buildMap() {

		for (MoveFactory accept : MoveFactory.values() ) {

			myPane.getChildren().add(new Label(accept.toString() + ": "));
			Pane editBox = new HBox(10);

			KeyCodeFieldBox keyField = new KeyCodeFieldBox();
			NumberFieldBox numField = new NumberFieldBox();

			editBox.getChildren().addAll(
					keyField.getPane(),
					numField.getPane()
					);

			myMoveToFieldMap.put(accept, new EnabledField<>(new Pair<>(keyField, numField), editBox));
		}

	}

	private void update(Controllable aControl) {
		aControl.getMyKeyPressedMap().entrySet().forEach( e -> {

			MoveFactory move;
			try{
				move = MoveFactory.discern(e.getValue().getClass());
			}
			catch ( ExecuteException exception ) {
				return;
			}

			myMoveToFieldMap.get(move).get().getKey().setCode(e.getKey());
			myMoveToFieldMap.get(move).setEnabled(true);
			if(e.getValue() instanceof Move){
				myMoveToFieldMap.get(move).get().getValue().setValue(((Move) e.getValue()).getVelocity());
			}
			else if(e.getValue() instanceof Launch)
				myMoveToFieldMap.get(move).get().getValue().setValue(((Launch) e.getValue()).getVelocity());
		});		

		buildPane();
	}

	private void buildPane(){
		myPane.getChildren().clear();
		myMoveToFieldMap.entrySet().forEach( e -> {
			myPane.getChildren().add(new Label(e.getKey().toString()));
			myPane.getChildren().add(e.getValue().getPane());
			});
	}

}