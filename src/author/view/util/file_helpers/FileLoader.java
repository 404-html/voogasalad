package author.view.util.file_helpers;

import java.io.File;
import java.util.Arrays;
import java.util.List;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

/**
 * A helpful facade class for the filechooser javafx object. 
 * Construct with the nested enum to pick files with the relevant extension.
 * Currently opens up a new stage outside of the current javafx stage
 * <p>
 * @see FileChooser
 * @see FileType
 * @author George Bernard
 */
public class FileLoader {

	/**
	 * Nested enumeration of acceptable file types.
	 * <p>
	 * @author George Bernard
	 */
	// If you want to add more accepted filetypes add to this enum
	/**
	 * @author George Bernard
	 *
	 */
	public enum FileType{
		/**
		 * All files with any extension
		 */
		ALL(""),	
		/**
		 * The PNG image file
		 */
		PNG("*.png"),
		/**
		 * The GIF animated file
		 */
		GIF("*.gif"),
		/**
		 * The JPG image file
		 */
		JPG("*.jpg"),
		/**
		 * The JPEG image file
		 */
		JPEG("*.jpeg"),
		/**
		 * The TXT text file
		 */
		TXT("*.txt"),
		/**
		 * The XML markup file
		 */
		XML("*.xml");

		private String myExtension;

		private FileType(String aExtension) {
			myExtension = aExtension;
		}

		/**
		 * @return the string text filter for extensions
		 */
		public String getExtension() {
			return myExtension;
		}

	}

	private static final String TITLE = "Load File";

	private FileChooser myFileChooser;
	private List<FileType> myExtensions;

	/**
	 * Constructed with no arguments, the loader will simply use all files.
	 */
	public FileLoader() {
		myFileChooser = new FileChooser();
		myFileChooser.setTitle(TITLE);
		myFileChooser.setInitialDirectory(new File("./"));
	}

	
	/**
	 * Constructed with the FileTypes to be used as extensions
	 * 
	 * @see FileType
	 * @param aFileTypes
	 */
	public FileLoader(FileType... aFileTypes) {
		this();
		myExtensions = Arrays.asList(aFileTypes);
		myExtensions.forEach(fileType -> myFileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter(fileType.toString(), fileType.getExtension())));
	}

	/**
	 * Returns the file chosen after the file chooser is completed 
	 * CAN RETURN NULL
	 * 
	 * @see File
	 * @return the file chosen after choosing file 
	 */
	public File loadImage() {
		return myFileChooser.showOpenDialog(new Stage());
	}

}
