package author.view.pages.level_editor.windows;

import author.controller.IAuthorController;
import author.view.util.facades.ToolBarBuilder;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * This window will allow a user to see the progression of their levels.
 * (@george so this window will need to automatically update when a user changes
 * the 'end level' condition on a level?)
 * 
 * @author Jordan Frazier
 * @see ../LevelEditor
 *
 */
public class LevelProgressionWindow extends AbstractLevelEditorWindow {

	private ScrollPane progressionScroller;
	private HBox container;

	public LevelProgressionWindow(IAuthorController authorController) {
		super(authorController);
	}

	@Override
	protected void createToolBar() {
		ToolBarBuilder tbb = new ToolBarBuilder();
		tbb.addBurst(new Label("Level Progression"));

		container = new HBox();
		progressionScroller = new ScrollPane();
		progressionScroller.setFitToHeight(true);
		progressionScroller.setFitToWidth(true);

		progressionScroller.setVbarPolicy(ScrollBarPolicy.NEVER);
		progressionScroller.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		progressionScroller.prefViewportHeightProperty().bind(super.getWindow().heightProperty());
		progressionScroller.prefViewportWidthProperty().bind(super.getWindow().widthProperty());

		progressionScroller.setContent(container);
		super.getWindow().setMaxHeight(150);
		super.getWindow().setMinHeight(150);

		super.getWindow().getChildren().add(tbb.getToolBar());
		super.getWindow().getChildren().add(progressionScroller);
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
			getController().getModel().getGame().getLevels().forEach((level) -> {
				if (level.getBackgroundImageFilePath() != null) {
					ImageView imageView = getImageView(level.getBackgroundImageFilePath(),
							progressionScroller.prefViewportWidthProperty(),
							progressionScroller.prefViewportHeightProperty());
					this.container.getChildren().add(imageView);
				}
			});
		});
	}

}
