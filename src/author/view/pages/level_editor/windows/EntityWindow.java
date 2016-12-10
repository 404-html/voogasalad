package author.view.pages.level_editor.windows;

import author.controller.IAuthorController;
import author.model.game_observables.draggable_sprite.ConcreteDraggableSprite;
import author.model.game_observables.draggable_sprite.DraggableSprite;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.FlowPane;
import util.facades.ToolBarBuilder;

/**
 * This window contains all of the preset sprites. A user will drag and drop
 * sprites from this window onto the LevelWindow
 * 
 * @author Jordan Frazier, Cleveland Thompson
 * @see LevelWindow
 * @see ../LevelEditor
 */
public class EntityWindow extends AbstractLevelEditorWindow {

	private ScrollPane entityScroller;
	private FlowPane container = new FlowPane();
	
	private static final int PADDING = 5;
	private static final int SPRITE_SIZE = 40;

	public EntityWindow(IAuthorController authorController) {
		super(authorController);
		super.getWindow().getChildren().add(createScroller());
		styleContainer();
	}

	@Override
	protected void createToolBar() {
		ToolBarBuilder tbb = new ToolBarBuilder();
		tbb.addBurst(new Label("Entity Selector"));
		super.getWindow().getChildren().add(tbb.getToolBar());
	}

	private ScrollPane createScroller() {
		this.entityScroller = new ScrollPane();
		this.entityScroller.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		this.entityScroller.setHbarPolicy(ScrollBarPolicy.NEVER);
		this.entityScroller.setFitToHeight(true);
		this.entityScroller.setFitToWidth(true);
		this.entityScroller.prefHeightProperty().bind(super.getWindow().heightProperty());
		this.entityScroller.prefWidthProperty().bind(super.getWindow().widthProperty());
		this.entityScroller.setContent(this.container);
		return entityScroller;
	}
	
	private void styleContainer() {
		container.setPadding(new Insets(PADDING));
		container.setHgap(PADDING);
		container.setVgap(PADDING);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see author.view.pages.level_editor.windows.AbstractLevelEditorWindow#
	 * initListener(author.controller.IAuthorController, game_data.Level)
	 */
	@Override
	protected void initListener() {
		getController().getModel().getGame().addListener((game) -> {
			this.container.getChildren().clear();
			getController().getModel().getGame().getPresets().forEach((sprite) -> {
				DraggableSprite dragSprite = new ConcreteDraggableSprite(sprite);
				dragSprite.getDraggableItem().setPrefHeight(SPRITE_SIZE);
				dragSprite.getDraggableItem().setPrefWidth(SPRITE_SIZE);
				this.container.getChildren().add(dragSprite.getDraggableItem());
			});
		});
	}
}
