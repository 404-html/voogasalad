package author.view.pages.menu;

import java.io.File;

import author.controller.IAuthorController;
import author.view.pages.level_editor.ILevelEditorExternal;
import author.view.util.file_helpers.FileLoader;
import author.view.util.file_helpers.FileLoader.FileType;
import game_data.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

public class AuthorMenu {

	private HBox myContainer;
	private MenuBar myMenu;
	private IAuthorController myAuthorController;
	private ILevelEditorExternal myLevelEditor;

	public AuthorMenu(IAuthorController authorController, ILevelEditorExternal levelEditor) {
		myAuthorController = authorController;
		myLevelEditor = levelEditor;
		myContainer = new HBox();
		buildMenu();
		addFiller();
		addGameTitle();
	}

	private void buildMenu() {
		myMenu = new MenuBar();
		Menu menuNew = new Menu("New");
		Menu menuSave = new Menu("Save");
		Menu menuLoad = new Menu("Load");
		Menu menuHelp = new Menu("Help");
		myMenu.getMenus().addAll(menuNew, menuSave, menuLoad, menuHelp);

		addNewMenuItem(menuNew);
		addSaveMenuItem(menuSave);
		addLoadMenuItem(menuLoad);
		addHelpMenuItem(menuHelp);

		myContainer.getChildren().add(myMenu);
	}

	public void addFiller(){
		final Pane filler = new Pane();
		HBox.setHgrow(
				filler,
				Priority.SOMETIMES
				);  
		myContainer.getChildren().add(filler);
	}

	private void addGameTitle() {
		Label gameTitle = new Label(myAuthorController.getModel().getGame().getName());
		gameTitle.setTextAlignment(TextAlignment.JUSTIFY);
		myContainer.getChildren().add(gameTitle);
	}

	// TODO: Consolidate other methods into this one that accepts lambdas
	private void addMenuItem(Menu menuItem, String name, EventHandler<ActionEvent> e) {
		menuItem.getItems().add(new MenuFactory().createItem(name, e).getItem());
	}

	private void addHelpMenuItem(Menu menuLoad) {
		menuLoad.getItems().add(new MenuFactory().createItem("Help", e -> {
			openHelpDialog();
		}).getItem());
	}

	private void addLoadMenuItem(Menu menuLoad) {
		menuLoad.getItems().add(new MenuFactory().createItem("Load Game", e -> {
			File aFile =loadFileChooser();
			if (aFile != null)
				myAuthorController.getModel().loadGame(loadFileChooser());
		}).getItem());
	}

	private void addSaveMenuItem(Menu menuSave) {
		menuSave.getItems().add(new MenuFactory().createItem(("Save Game"), e -> {
			openSaveDialog();
		}).getItem());
	}

	private void addNewMenuItem(Menu menuNew) {
		menuNew.getItems().addAll(new MenuFactory().createItem("New Game", e -> {
			this.myAuthorController.getModel().newGameWindow();
		}).getItem(), new MenuFactory().createItem("New Level", e -> {
			Level createdLevel = this.myLevelEditor.createLevel();
			if (createdLevel != null) {
				this.myAuthorController.getModel().getGame().addNewLevel(createdLevel);
			}
		}).getItem());
	}

	private void openSaveDialog() {
		TextInputDialog input = new TextInputDialog(myAuthorController.getModel().getGame().getName());
		input.setTitle("Save Dialog");
		input.setHeaderText("Input Game Name");
		input.setContentText("Name: ");
		input.setOnCloseRequest(e -> {
			myAuthorController.getModel().saveGame(input.getResult());
		});
		input.showAndWait();
	}

	private File loadFileChooser() {
		File file = new FileLoader("XMLGameFiles/", FileType.XML).loadImage();
		return file;
	}

	private void openHelpDialog() {
		HelpDialog helpDialog = new HelpDialog();
		helpDialog.display();
	}

	public MenuBar getMenu() {
		return myMenu;
	}

	public HBox getContainer() {
		return myContainer;
	}
}
