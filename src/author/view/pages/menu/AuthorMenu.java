package author.view.pages.menu;

import java.io.File;
import java.io.FileNotFoundException;

import author.controller.IAuthorController;
import author.view.pages.level_editor.ILevelEditorExternal;
import author.view.util.game_info.GameInfoEditWindowFactory;
import author.view.util.game_info.iGameInfoEditWindow;
import author.view.util.language_selection.ILanguageUser;
import game_data.Level;
import javafx.beans.Observable;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import util.filehelpers.FileLoader.FileExtension;
import util.filehelpers.FileLoader.FileLoader;
import util.filehelpers.FileLoader.FileType;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.text.TextAlignment;

public class AuthorMenu implements ILanguageUser{

	private HBox myContainer;
	private MenuBar myMenu;
	private IAuthorController myAuthorController;
	private ILevelEditorExternal myLevelEditor;

	public AuthorMenu(IAuthorController authorController, ILevelEditorExternal levelEditor) {
		myAuthorController = authorController;
		myAuthorController.addListener(this);
		myLevelEditor = levelEditor;
		myContainer = new HBox();
		buildMenu();
	}

	private void buildMenu() {
		myMenu = new MenuBar();
		Menu menuFile = new Menu(myAuthorController.getDisplayText("File"));
		Menu menuEdit = new Menu(myAuthorController.getDisplayText("Edit"));
		Menu menuHelp = new Menu(myAuthorController.getDisplayText("Help"));
		myMenu.getMenus().addAll(menuFile, menuEdit, menuHelp);

		addFileMenuItem(menuFile);
		addEditMenuItem(menuEdit);
		addHelpMenuItem(menuHelp);

		myContainer.getChildren().clear();
		myContainer.getChildren().add(myMenu);
		addFiller();
		addGameTitle();
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
		Label gameTitle = new GameNameDisplay(myAuthorController.getModel().getGame());
		myContainer.getChildren().add(gameTitle);
	}

	// TODO: Consolidate other methods into this one that accepts lambdas
	private void addMenuItem(Menu menuItem, String name, EventHandler<ActionEvent> e) {
		menuItem.getItems().add(new MenuFactory().createItem(name, e).getItem());
	}

	private void addHelpMenuItem(Menu menuLoad) {
		menuLoad.getItems().add(new MenuFactory().createItem(myAuthorController.getDisplayText("Help"), e -> {
			openHelpDialog();
		}).getItem());
	}


	private void addFileMenuItem(Menu menuFile) {
		menuFile.getItems().add(new MenuFactory().createItem(myAuthorController.getDisplayText("NewGame"), e -> {
			this.myAuthorController.getModel().newGameWindow();
		}).getItem());
		
		menuFile.getItems().add(new MenuFactory().createItem(myAuthorController.getDisplayText("NewLevel"), e -> {
			Level createdLevel = this.myLevelEditor.createLevel();
			if (createdLevel != null) {
				this.myAuthorController.getModel().getGame().addNewLevel(createdLevel);
			}
		}).getItem());
		
		menuFile.getItems().add(new MenuFactory().createItem((myAuthorController.getDisplayText("SaveGame")), e -> {
			openSaveDialog();
		}).getItem());
		
		menuFile.getItems().add(new MenuFactory().createItem(myAuthorController.getDisplayText("LoadGame"), e -> {
			File aFile = loadFileChooser();
			if (aFile != null)
				myAuthorController.getModel().loadGame(aFile);
		}).getItem());
	}
	
	private void addEditMenuItem(Menu menuEdit) {
		menuEdit.getItems().add(new MenuFactory().createItem((myAuthorController.getDisplayText("EditGame")), e -> {
			iGameInfoEditWindow infoEditor = new GameInfoEditWindowFactory().create(myAuthorController.getModel().getGame(), myAuthorController);
			infoEditor.display();
		}).getItem());
		
		menuEdit.getItems().add(new MenuFactory().createItem((myAuthorController.getDisplayText("AuthorInEnglish")), e -> {
			myAuthorController.setLanguage("English");
		}).getItem());
		
		menuEdit.getItems().add(new MenuFactory().createItem((myAuthorController.getDisplayText("AuthorInSpanish")), e -> {
			myAuthorController.setLanguage("Spanish");
		}).getItem());
	}


	private void openSaveDialog() {
		TextInputDialog input = new TextInputDialog(myAuthorController.getModel().getGame().getName());
		input.setTitle(myAuthorController.getDisplayText("SaveDialog"));
		input.setHeaderText(myAuthorController.getDisplayText("InputGameName"));
		input.setContentText(myAuthorController.getDisplayText("Name"));
		input.setOnCloseRequest(e -> {
			myAuthorController.getModel().saveGame(input.getResult());
		});
		input.showAndWait();
	}

	private File loadFileChooser() {
		File file;
		try {
			file = new FileLoader(myAuthorController.getPathString("XMLGameFiles"), FileType.DATA).loadSingle();
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

	public HBox getContainer() {
		return myContainer;
	}

	@Override
	public void invalidated(Observable arg0) {
		buildMenu();
	}
}
