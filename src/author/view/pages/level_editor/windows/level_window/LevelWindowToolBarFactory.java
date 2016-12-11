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
import util.RelativePathFinder;
import util.facades.ToolBarBuilder;
import util.filehelpers.FileLoader.FileExtension;
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
		tbb.addBurst(new Label("Level Window"));
		tbb.addFiller();
		tbb.addBurst(createCheckBox("Randomized"), new ButtonFactory().createButton("Set Background", e -> {
			newBackgroundImage();
		}).getButton(), new ButtonFactory().createButton("Extend Width", e -> {
			this.iLevelWindowInternal.getHorizontalPanes()
					.set(this.iLevelWindowInternal.getHorizontalPanes().get() + 1);
		}).getButton(), new ButtonFactory().createButton("Extend Height", e -> {
			this.iLevelWindowInternal.getVerticalPanes().set(this.iLevelWindowInternal.getVerticalPanes().get() + 1);
		}).getButton(), new ButtonFactory().createButton("Shrink Width", e -> {
			this.iLevelWindowInternal.getHorizontalPanes()
					.set(this.iLevelWindowInternal.getHorizontalPanes().get() - 1);
		}).getButton(), new ButtonFactory().createButton("Shrink Height", e -> {
			this.iLevelWindowInternal.getHorizontalPanes().set(this.iLevelWindowInternal.getVerticalPanes().get() - 1);
		}).getButton(), new ButtonFactory().createButton("Reset Size", e -> {
			this.iLevelWindowInternal.getHorizontalPanes().set(1);
			this.iLevelWindowInternal.getVerticalPanes().set(1);
		}).getButton());

		return tbb.getToolBar();
	}

	private void newBackgroundImage() {
		File file;
		try {
			file = new FileLoader("data/images/level_images/", FileType.RASTER_IMAGE).loadSingle();
			RelativePathFinder pf = new RelativePathFinder();
			this.authorController.getModel().getGame().getCurrentLevel().setBackgroundImageFilePath(pf.getPath(file));
		} catch (FileNotFoundException e) {
			// TODO: Show error screen if file not found
			e.printStackTrace();
		}

	}
	
	
	private CheckBox createCheckBox(String text){
		CheckBox checkBox = new CheckBox(text);
		checkBox.selectedProperty().bindBidirectional(this.iLevelWindowInternal.getRandomProperty());
		return checkBox;
	}

}
