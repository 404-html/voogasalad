package author.main;

import author.controller.AuthorControllerFactory;
import author.controller.IAuthorControllerExternal;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	@Override
	public void start(Stage aStage) throws Exception {
		IAuthorControllerExternal authorControllerExternal = new AuthorControllerFactory().create();
		aStage.setTitle("VOOGASalad");
		
		Scene scene = authorControllerExternal.getScene();
		aStage.setMaximized(true);
		aStage.setResizable(true);
		aStage.setScene(scene);
		aStage.show();
	}

	public static void main(String[] args){
		Application.launch(args);
	}
}
