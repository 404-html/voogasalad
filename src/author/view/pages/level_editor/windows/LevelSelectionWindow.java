package author.view.pages.level_editor.windows;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import author.controller.IAuthorController;
import author.view.util.authoring_buttons.FunctionalLabel;
import author.view.util.authoring_buttons.LabelFactory;
import game_data.Level;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import util.facades.ToolBarBuilder;

/**
 * This window serves as the container for all levels, where a user will be able
 * to select a level name and the LevelWindow will change to reflect that new
 * level
 * 
 * @author Jordan Frazier
 * @see ../LevelEditor
 */
public class LevelSelectionWindow extends AbstractLevelEditorWindow {

	private ScrollPane levelScroller;
	private VBox container;
	private Map<String, FunctionalLabel> labelMap = new HashMap<>();
	private List<Level> garbageLevels = new ArrayList<>();

	public LevelSelectionWindow(IAuthorController authorController) {
		super(authorController);
	}

	@Override
	protected void createToolBar() {
		ToolBarBuilder tbb = new ToolBarBuilder();
		tbb.addBurst(new Label("Level Selection"));

		container = new VBox();
		levelScroller = new ScrollPane();
		levelScroller.setFitToHeight(true);
		levelScroller.setFitToWidth(true);

		levelScroller.setVbarPolicy(ScrollBarPolicy.ALWAYS);
		levelScroller.setHbarPolicy(ScrollBarPolicy.NEVER);
		levelScroller.prefHeightProperty().bind(super.getWindow().heightProperty());
		levelScroller.prefWidthProperty().bind(super.getWindow().widthProperty());

		// container.prefHeightProperty().bind(levelScroller.prefViewportHeightProperty());
		// container.prefWidthProperty().bind(levelScroller.prefViewportWidthProperty());

		levelScroller.setContent(container);

		super.getWindow().getChildren().add(tbb.getToolBar());
		super.getWindow().getChildren().add(levelScroller);
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
			clearContainers();
			getController().getModel().getGame().getLevels().forEach((level) -> {
				if (uniqueLevelName(level)) {
					FunctionalLabel label = new LabelFactory().createLabel(level.getName(), e -> {
						updateCurrentLevel(level);
					});
					this.container.getChildren().add(label.getLabel());
					labelMap.put(level.getName(), label);
				}
			});
			if (!garbageLevels.isEmpty()) {
				removeNonUniqueLevels();
			}
			if (getController().getModel().getGame().getCurrentLevel() != null) {
				highlightCurrentLabel(getController().getModel().getGame().getCurrentLevel().getName());
			}
		});
	}

	private void clearContainers() {
		this.container.getChildren().clear();
		labelMap.clear();
		garbageLevels.clear();
	}

	private boolean uniqueLevelName(Level level) {
		if (labelMap.containsKey(level.getName())) {
			garbageLevels.add(level);
			displayUniqueNameError(level.getName());
			return false;
		}
		return true;
	}

	private void removeNonUniqueLevels() {
		garbageLevels.forEach(level -> {
			getController().getModel().getGame().getLevels().remove(level);
		});
		updateCurrentLevel(getController().getModel().getGame().getLevels().get(0));
	}

	private void displayUniqueNameError(String name) {
		Alert alert = new Alert(AlertType.WARNING);
		alert.setTitle("Level Name Not Unique");
		alert.setHeaderText("Level name: " + name + " already exists");
		alert.setContentText("Create a new level with a unique name");
		alert.showAndWait();
	}

	private void updateCurrentLevel(Level aNewCurrentLevel) {
		getController().getModel().getGame().setCurrentLevel(aNewCurrentLevel);
	}

	private void highlightCurrentLabel(String name) {
		FunctionalLabel label = labelMap.get(name);
		label.getLabel().setTextFill(Color.CHOCOLATE);
	}
}
