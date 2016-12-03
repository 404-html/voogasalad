package gameplayer.front_end.heads_up_display;

import gameplayer.front_end.gui_generator.GUIGenerator;
import gameplayer.front_end.gui_generator.IGUIGenerator;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.MenuBar;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class HeadsUpDisplay {
	
	private MenuBar myTopMenu; 
	private IGUIGenerator myGUIGenerator;
	private BorderPane myRoot;
	
	public HeadsUpDisplay(double aWidth, double aHeight) {
		myGUIGenerator = new GUIGenerator();
		myRoot = new BorderPane();
	}
	
	public void addMenu(String aTitle, String[] aText, @SuppressWarnings("unchecked") EventHandler<ActionEvent>... aHandler) {
		myTopMenu.getMenus().add(myGUIGenerator.createMenu(aTitle, aText, aHandler));
	}
	
	public void addMenu(ImageView aImage, String[] aText, @SuppressWarnings("unchecked") EventHandler<ActionEvent> ... aHandler) {
		myTopMenu.getMenus().add(myGUIGenerator.createMenu(aImage, aText, aHandler));
	}

	private Node createTop() {
		myTopMenu = new MenuBar();
		//myTopMenu.setAlignment(Pos.CENTER);
		return myTopMenu;
	}
	
	private Node createLeft(){
		VBox leftMenu = new VBox();
		leftMenu.getChildren().add(myGUIGenerator.createLabel("Left", 0, 0));
		leftMenu.setAlignment(Pos.CENTER);
		return leftMenu;
	}
	
	private Node createRight(){
		VBox rightMenu = new VBox();
		rightMenu.getChildren().add(myGUIGenerator.createLabel("Right", 0, 0));
		rightMenu.setAlignment(Pos.CENTER);
		return rightMenu;
	}
	
	private Node createBottom(){
		HBox bottomMenu = new HBox();
		bottomMenu.getChildren().add(myGUIGenerator.createLabel("Bottom", 0, 0));
		bottomMenu.setAlignment(Pos.CENTER);
		return bottomMenu;
	}
	
	public Pane init() {
		myRoot.setRight(createRight());
		myRoot.setLeft(createLeft());
		myRoot.setBottom(createBottom());
		myRoot.setTop(createTop());
		myRoot.setBackground(Background.EMPTY);
		return myRoot;
	}
}
