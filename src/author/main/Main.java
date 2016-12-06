package author.main;

import author.controller.AuthorControllerFactory;
import author.controller.IAuthorControllerExternal;
import author.view.pages.level_editor.windows.splash_screen.AuthoringSplashScreen;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage aStage) throws Exception {
		AuthoringSplashScreen ass = new AuthoringSplashScreen();
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
