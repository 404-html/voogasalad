package author.view.pages.menu;

import java.io.File;
import java.io.FileNotFoundException;

import author.controller.IAuthorController;
import author.view.pages.level_editor.ILevelEditorExternal;
import game_data.Level;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import util.filehelpers.FileLoader.FileExtension;
import util.filehelpers.FileLoader.FileLoader;
import util.filehelpers.FileLoader.FileType;

public class AuthorMenu {
	private MenuBar myMenu;
	private IAuthorController myAuthorController;
	private ILevelEditorExternal myLevelEditor;

	public AuthorMenu(IAuthorController authorController, ILevelEditorExternal levelEditor) {
		myAuthorController = authorController;
		myLevelEditor = levelEditor;
		buildMenu();
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
			this.myAuthorController.getModel().newGame();
		}).getItem(), new MenuFactory().createItem("New Level", e -> {
			Level createdLevel = this.myLevelEditor.createLevel();
			if (createdLevel != null) {
				this.myAuthorController.getModel().getGame().addNewLevel(createdLevel);
			}
		}).getItem());
	}

	private void openSaveDialog() {
		TextInputDialog input = new TextInputDialog("Sample_Name");
		input.setTitle("Save Dialog");
		input.setHeaderText("Input Game Name");
		input.setContentText("Name: ");
		input.setOnCloseRequest(e -> {
			myAuthorController.getModel().saveGame(input.getResult());
		});
		input.showAndWait();
	}

	private File loadFileChooser() {
		File file;
		try {
			file = new FileLoader(FileExtension.XML).loadSingle();
			return file;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	private void openHelpDialog() {
		HelpDialog helpDialog = new HelpDialog();
		helpDialog.display();
	}

	public MenuBar getMenu() {
		return myMenu;
	}
}
