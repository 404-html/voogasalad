package author.view.util.file_helpers;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class FileLoader {

	public enum FileType{
		ALL(""),
		PNG("*.png"),
		GIF("*.gif"),
		JPG("*.jpg"),
		JPEG("*.jpeg"),
		TXT("*.txt"),
		XML("*.xml");

		private String myExtension;

		private FileType(String aExtension) {
			myExtension = aExtension;
		}

		public String getExtension() {
			return myExtension;
		}

	}

	private static final String TITLE = "Load File";

	private FileChooser myFileChooser;
	private List<FileType> myExtensions;

	public FileLoader() {
		myFileChooser = new FileChooser();
		myFileChooser.setTitle(TITLE);
		myFileChooser.setInitialDirectory(new File("./"));
	}

	public FileLoader(FileType... aFileTypes) {
		this();
		myExtensions = Arrays.asList(aFileTypes);
		myExtensions.forEach(fileType -> myFileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter(fileType.toString(), fileType.getExtension())));
	}

	public File loadImage() {
		return myFileChooser.showOpenDialog(new Stage());
	}

}
