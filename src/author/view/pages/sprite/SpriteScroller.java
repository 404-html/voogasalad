package author.view.pages.sprite;

import java.util.HashSet;
import java.util.Set;

import author.controller.IAuthorController;
import author.view.util.ToolBarBuilder;
import author.view.util.authoring_buttons.ButtonFactory;
import game_data.Sprite;
import game_data.sprites.SpriteFactory;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;

public class SpriteScroller {

	private ScrollPane myScroll;
	private Pane myColumn;
	private IAuthorController myController;
	private SpriteFactory mySpriteFactory;
	private Set<Sprite> mySprites;
	
	public SpriteScroller(SpriteFactory aSpriteFactory, IAuthorController aController) {
		myController = aController;
		mySpriteFactory = aSpriteFactory;
		myScroll = new ScrollPane();
		myColumn = new VBox();
		mySprites = new HashSet<>();
		
		Pane content = new VBox();
		myScroll.setContent(content);
		content.minWidthProperty().bind(myScroll.widthProperty());
		HBox.setHgrow(content, Priority.ALWAYS);
		myScroll.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		
		content.getChildren().addAll(buildToolbar(aSpriteFactory.toString()), myColumn);
	}

	public Node getNode(){
		return myScroll;
	}
	
	public void giveSprite(Sprite aSprite){
		if(!mySprites.contains(aSprite)){
			mySprites.add(aSprite);
			myColumn.getChildren().add(new SpriteViewBox(aSprite).getNode());
		}
	}
	
	private Node buildToolbar(String aScrollType){
		ToolBarBuilder toolBarBuilder = new ToolBarBuilder();
		toolBarBuilder.addBurst(new Label(aScrollType));
		toolBarBuilder.addBurst(new ButtonFactory().createButton("new", e -> {buildNewSprite();}).getButton());
		
		return toolBarBuilder.getToolBar();
	}
	
	private void buildNewSprite(){
		Sprite ns = mySpriteFactory.buildEmpty();
		giveSprite(ns);
		myController.getModel().getGame().addPreset(ns);		
	}
	
	
}
