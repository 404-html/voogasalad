/**
 * 
 */
package author.view.pages.level_editor.windows.level_window;

import java.io.File;
import java.io.FileNotFoundException;

import author.controller.IAuthorController;
import author.view.pages.level_editor.windows.ILevelWindowInternal;
import author.view.util.authoring_buttons.ButtonFactory;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToolBar;
import javafx.stage.Stage;
import util.RelativePathFinder;
import util.facades.ToolBarBuilder;
import util.filehelpers.FileLoader.FileLoader;
import util.filehelpers.FileLoader.FileType;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public class LevelWindowToolBarFactory {
	private ILevelWindowInternal iLevelWindowInternal;
	private IAuthorController authorController;

	public ToolBar createToolBar(ILevelWindowInternal iLevelWindowInternal, IAuthorController authorController) {
		this.iLevelWindowInternal = iLevelWindowInternal;
		this.authorController = authorController;
		ToolBarBuilder tbb = new ToolBarBuilder();
		tbb.addBurst(new Label(authorController.getDisplayText("LevelWindow")));
		tbb.addFiller();
		tbb.addBurst(createCheckBox(authorController.getDisplayText("Randomized")), new ButtonFactory().createButton(authorController.getDisplayText("SetBackground"), e -> {
			newBackgroundImage();
		}).getButton(), new ButtonFactory().createButton(authorController.getDisplayText("ExtendWidth"), e -> {
			this.iLevelWindowInternal.getHorizontalPanes()
					.set(this.iLevelWindowInternal.getHorizontalPanes().get() + 1);
		}).getButton(), new ButtonFactory().createButton(authorController.getDisplayText("ExtendHeight"), e -> {
			this.iLevelWindowInternal.getVerticalPanes().set(this.iLevelWindowInternal.getVerticalPanes().get() + 1);
		}).getButton(), new ButtonFactory().createButton(authorController.getDisplayText("ShrinkWidth"), e -> {
			if (this.iLevelWindowInternal.getHorizontalPanes().get() > 1) {
				this.iLevelWindowInternal.getHorizontalPanes()
						.set(this.iLevelWindowInternal.getHorizontalPanes().get() - 1);
			}
		}).getButton(), new ButtonFactory().createButton(authorController.getDisplayText("ShrinkHeight"), e -> {
			if (this.iLevelWindowInternal.getVerticalPanes().get() > 1) {
				this.iLevelWindowInternal.getVerticalPanes()
						.set(this.iLevelWindowInternal.getVerticalPanes().get() - 1);
			}
		}).getButton(), new ButtonFactory().createButton(authorController.getDisplayText("ResetSize"), e -> {
			this.iLevelWindowInternal.getHorizontalPanes().set(1);
			this.iLevelWindowInternal.getVerticalPanes().set(1);
		}).getButton());

		return tbb.getToolBar();
	}

	private void newBackgroundImage() {
		File file;
		try {
			file = new FileLoader(authorController.getPathString("LevelImages"), FileType.RASTER_IMAGE, new Stage()).loadSingle();
			RelativePathFinder pf = new RelativePathFinder();
			if(this.authorController.getModel().getGame().getCurrentLevel()!= null)
				this.authorController.getModel().getGame().getCurrentLevel().setBackgroundImageFilePath(pf.getPath(file));
				this.authorController.getModel().getGame().setCurrentLevel(this.authorController.getModel().getGame().getCurrentLevel());
		} catch (FileNotFoundException e) {
			// TODO: Show error screen if file not found
			e.printStackTrace();
		} catch (NullPointerException e) {
			// TODO: Show different error screen
			e.printStackTrace();
		}

	}
	
	
	private CheckBox createCheckBox(String text){
		CheckBox checkBox = new CheckBox(text);
		checkBox.selectedProperty().bindBidirectional(this.iLevelWindowInternal.getRandomProperty());
		return checkBox;
	}

}
