package author.view.pages.sprite.page;

import java.io.File;
import java.util.HashSet;
import java.util.Set;

import util.XMLTranslator;
import author.controller.IAuthorController;
import author.view.util.authoring_buttons.ButtonFactory;
import author.view.util.facades.ToolBarBuilder;
import author.view.util.file_helpers.FileLoader;
import author.view.util.file_helpers.FolderListor;
import author.view.util.file_helpers.FileLoader.FileType;
import game_data.Sprite;
import game_data.sprites.SpriteFactory;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class SpriteScroller {

	private ScrollPane myScroller;
	private FlowPane myFlowPane;
	private Pane myContainer;
	private IAuthorController myController;
	private SpriteFactory mySpriteFactory;
	private Set<Sprite> mySprites;

	public SpriteScroller(SpriteFactory aSpriteFactory, IAuthorController aController) {
		myController = aController;
		myScroller = new ScrollPane();
		mySpriteFactory = aSpriteFactory;
		myFlowPane = new FlowPane();
		myContainer = new VBox();
		mySprites = new HashSet<>();
		
		myContainer.getChildren().addAll(buildToolbar(aSpriteFactory.toString()), myFlowPane);
//		setupContainer();
		setupFlowPane();
//		setupScrollPane();
	}

	public Node getNode() {
		return myContainer;
	}

	public void giveSprite(Sprite aSprite) {
		if (!mySprites.contains(aSprite)) {
			mySprites.add(aSprite);
			SpriteViewBox svb = new SpriteViewBox(aSprite);
			myFlowPane.getChildren().add(svb.getPane());
		}
	}
	
	private void setupContainer() {
		myContainer.getPrefWidth();
	}
	
	private void setupScrollPane() {
		myScroller.prefViewportWidthProperty().bind(myContainer.prefWidthProperty());
		myScroller.prefViewportHeightProperty().bind(myContainer.prefHeightProperty());
		myScroller.setHbarPolicy(ScrollBarPolicy.NEVER);
		myScroller.setVbarPolicy(ScrollBarPolicy.AS_NEEDED);
		myScroller.setContent(myFlowPane);
	}
	
	private void setupFlowPane() {
		myFlowPane.setHgap(5);
		myFlowPane.setVgap(5);
//		myFlowPane.prefWidthProperty().bind(myScroller.prefViewportWidthProperty());
//		myFlowPane.prefHeightProperty().bind(myScroller.prefViewportHeightProperty());

	}

	private Node buildToolbar(String aScrollType) {
		ToolBarBuilder toolBarBuilder = new ToolBarBuilder();
		toolBarBuilder.addFiller();
//		toolBarBuilder.addBurst(new Label(aScrollType));
		toolBarBuilder.addBurst(new ButtonFactory().createButton("New " + aScrollType, e -> {
			buildNewSprite();
		}).getButton());
		toolBarBuilder.addBurst(new ButtonFactory().createButton("Load Saved " + aScrollType, e -> {
			loadSprite(new FileLoader(FileType.XML).loadImage());
		}).getButton());

		return toolBarBuilder.getToolBar();
	}

	private void buildNewSprite() {
		Sprite ns = mySpriteFactory.buildEmpty();
		giveSprite(ns);
		myController.getModel().getGame().addPreset(ns);
	}
	
	private void loadSprite(File aFile){
		if(aFile == null)
			return;
		XMLTranslator myLoader = new XMLTranslator();
		Sprite ns = (Sprite) myLoader.loadFromFile(aFile);
		myController.getModel().getGame().addPreset(ns);
	}

}
