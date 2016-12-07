package author.model.game_observables.draggable_sprite.drag_resize;

import author.model.game_observables.draggable_sprite.ResizableSprite;
import javafx.scene.Cursor;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
/**
 * Doesn't work now
 * @author jf
 *
 */
public class DragResize {

	private ResizableSprite mySprite;
	private HBox node;

	private DragState state;
	private double mouseX;
	private double mouseY;
	private double clickX;
	private double clickY;
	private static final int MARGIN = 10;

	public DragResize(ResizableSprite sprite) {
		mySprite = sprite;
		node = sprite.getDraggableItem();
		makeResizable();
	}

	private void makeResizable() {
		node.setOnMouseMoved(e -> {
			onMouseOver(e);
		});
		node.setOnMousePressed(e -> {
			onMousePressed(e);
		});
		node.setOnMouseDragged(e -> {
			onMouseDragged(e);
		});
	}

	private void onMouseOver(MouseEvent e) {
		Cursor cursor = getCursor(getDragState(e));
		node.setCursor(cursor);
	}

	private void onMousePressed(MouseEvent e) {
		mouseX = e.getSceneX();
		mouseY = e.getSceneY();
		clickX = e.getX();
		clickY = e.getY();
		node.toFront();
		if (inResizeZone(e)) {
			state = getDragState(e);
		} else {
			state = DragState.DRAG;
		}
	}

	private void onMouseDragged(MouseEvent e) {
		double newX = node.getBoundsInParent().getMinX();
        double newY = node.getBoundsInParent().getMinY();
        double newH = node.getHeight();
        double newW = node.getWidth();
        
		double deltaX = e.getSceneX() - mouseX;
		double deltaY = e.getSceneY() - mouseY;
		
		double delX = e.getX() - clickX;
		double delY = e.getY() - clickY;
		System.out.println("delx: " + deltaX + ",   del Y : " + deltaY);
		if (state == DragState.DRAG) {
			node.relocate(node.getLayoutX() + deltaX, node.getLayoutY() + deltaY);
			mouseX = e.getSceneX();
			mouseY = e.getSceneY();
		} else {
			if (state == DragState.SE_RESIZE) {
//				newW = node.getWidth() + deltaX - 2;
				newH = node.getHeight() + deltaY - node.getBoundsInParent().getMinY();
				System.out.println("node width: " + node.getWidth() + ", node ehight:" + node.getHeight());
				System.out.println("new width: " + newW + ", new height: " + newH);
//				node.setPrefWidth(newW);
				node.setPrefHeight(newH);
				clickX = e.getX();
				clickY = e.getY();
				mouseX = e.getSceneX();
				mouseY = e.getSceneY();
			}
		}
	}
	
	private void resizeNode(double x, double y, double width, double height) {
		node.setLayoutX(x);
		node.setLayoutY(y);
		node.setPrefWidth(width);
		node.setPrefHeight(height);
	}

	private Cursor getCursor(DragState state) {
		switch (state) {
		case NW_RESIZE:
			return Cursor.NW_RESIZE;
		case SW_RESIZE:
			return Cursor.SW_RESIZE;
		case NE_RESIZE:
			return Cursor.NE_RESIZE;
		case SE_RESIZE:
			return Cursor.SE_RESIZE;
		default:
			return Cursor.HAND;
		}
	}

	private DragState getDragState(MouseEvent e) {
		DragState state = DragState.DRAG;
		if (inNWZone(e)) {
			state = DragState.NW_RESIZE;
		} else if (inNEZone(e)) {
			state = DragState.NE_RESIZE;
		} else if (inSWZone(e)) {
			state = DragState.SW_RESIZE;
		} else if (inSEZone(e)) {
			state = DragState.SE_RESIZE;
		} else {
			state = DragState.DRAG;
		}
		return state;
	}

	private boolean inResizeZone(MouseEvent e) {
		return inNWZone(e) || inNEZone(e) || inSWZone(e) || inSEZone(e);
	}

	private boolean inNWZone(MouseEvent e) {
		return mouseInCorner(0, e.getX()) && mouseInCorner(0, e.getY());
	}

	private boolean inNEZone(MouseEvent e) {
		return mouseInCorner(node.getBoundsInParent().getWidth(), e.getX()) && mouseInCorner(0, e.getY());
	}

	private boolean inSWZone(MouseEvent e) {
		return mouseInCorner(0, e.getX()) && mouseInCorner(node.getBoundsInParent().getHeight(), e.getY());
	}

	private boolean inSEZone(MouseEvent e) {
		return mouseInCorner(node.getBoundsInParent().getWidth(), e.getX())
				&& mouseInCorner(node.getBoundsInParent().getHeight(), e.getY());

	}

	private boolean mouseInCorner(double side, double point) {
		return side + MARGIN > point && side - MARGIN < point;
	}

}
