package gameplayer.application_scene;

import java.io.File;
import javafx.stage.FileChooser;
import javafx.stage.FileChooser.ExtensionFilter;
import javafx.stage.Stage;

public class FileManager {

	FileChooser myFileChooser;
	
	public FileManager(){
		myFileChooser = new FileChooser();
		myFileChooser.setTitle("Choose New Game");
        myFileChooser.getExtensionFilters().add(new ExtensionFilter("Game Files", "*.xml"));
	}
	
	public File show(Stage stage){
		return myFileChooser.showOpenDialog(stage);
	}
}
