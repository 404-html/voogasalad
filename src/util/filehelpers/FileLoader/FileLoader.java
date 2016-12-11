package util.filehelpers.FileLoader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * A helpful facade class for the filechooser javafx object. 
 * Construct with the nested enum to pick files with the relevant extension.
 * Currently opens up a new stage outside of the current javafx stage
 * <p>
 * Default Starting directory is Java Root folder
 * 
 * @see FileChooser
 * @see FileExtension
 * @author George Bernard
 */
public class FileLoader {

	private static final String TITLE = "Load File";
	private static final String ERR_MSG = "File Failed To Load";
	private static final StartDirectory DEFAULT_START = StartDirectory.SOURCE_DIRECTORY;

	/**
	 * Starting Directories for FileLoader 
	 * 
	 * @author George Bernard
	 */
	public enum StartDirectory{
		/**
		 * Platform agnostic user home directory
		 */
		HOME_DIRECTORY(System.getProperties().getProperty("user.dir")),
		/**
		 * Java Root directory
		 */
		SOURCE_DIRECTORY("./");

		private String myPath;

		private StartDirectory(String aPath){
			myPath = aPath;
		}

		/**
		 * @return path to directory
		 */
		public String getPath(){
			return myPath;
		}
	}		

	private Stage myStage;
	private FileChooser myFileChooser;
	private Collection<FileExtension> myExtensions;

	/**
	 * Constructed with no arguments, the loader will simply use all files.
	 */
	private FileLoader(StartDirectory aStart) {
		myFileChooser = new FileChooser();
		myFileChooser.setTitle(TITLE);
		myFileChooser.setInitialDirectory(new File(aStart.getPath()));
	}

	private FileLoader() {
		this(DEFAULT_START);
	}

	/**
	 * Constructed with the FileTypes to be used as extensions
	 * 
	 * @see FileExtension
	 * @param aFileExtension
	 */
	@Deprecated
	public FileLoader(FileExtension... aFileExtension) {
		this();
		myExtensions = Arrays.asList(aFileExtension);
		myExtensions.forEach(fileType -> myFileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter(fileType.name(), fileType.getExtension())));
	}

	/**
	 * If custom file extensions are absolutely required, then use this constructor
	 * 
	 * @param aCustomExtensions
	 */
	public FileLoader( String... aCustomExtensions) {
		this();
		Arrays.asList(aCustomExtensions).forEach( s -> myFileChooser.getExtensionFilters()
				.add(new FileChooser.ExtensionFilter(s, s)));
	}
		
	/**
	 * Constructs a file chooser with given StartDirectory and FileExtension List
	 * 
	 * @see StartDirectory
	 * @see FileExtension
	 * 
	 * @param aStart
	 * @param aFileExtension
	 */
	public FileLoader(StartDirectory aStart, FileExtension... aFileExtension) {
		this(aFileExtension);
	}

	
	/**
	 * Constructs a file chooser with given StartDirectory and FileType
	 * 
	 * @see StartDirectory
	 * @see FileType
	 * 
	 * @param aStart
	 * @param aFileType
	 */
	public FileLoader(StartDirectory aStart, FileType aFileType) {
		this(aStart);
		myExtensions = aFileType.myFileExtensions;
		FileChooser.ExtensionFilter filter = 
				new FileChooser.ExtensionFilter(
						aFileType.name(),
						aFileType.myFileExtensions.stream()
						.map(f -> {return f.getExtension();})
						.collect(Collectors.toList())
						);
		
		myFileChooser.getExtensionFilters().add(filter);
	}
	
	public FileLoader(StartDirectory aStart, FileType aFileType, Stage aStage) {
		this(aStart, aFileType);
		myStage = aStage;
	}

	public FileLoader(String aDirectory, FileType aFileType) {
		this(DEFAULT_START, aFileType);
		myFileChooser.setInitialDirectory(new File(aDirectory));
	}

	public FileLoader(String aDirectory, FileType aFileType, Stage aStage) {
		this(aDirectory, aFileType);
		myStage = aStage;
	}
	
	/**
	 * Returns the file chosen after the file chooser is completed 
	 * 
	 * @see File
	 * @return the file chosen after choosing file 
	 */
	public File loadSingle() throws FileNotFoundException {
		File loadedFile = myFileChooser.showOpenDialog(makeStage());

		if(loadedFile == null) {
			throw new FileNotFoundException(ERR_MSG);
		}

		return loadedFile;
	}
	
	public List<File> loadMultiple() throws FileNotFoundException {
		List<File> loadedFiles = myFileChooser.showOpenMultipleDialog(makeStage());
		
		for (File file : loadedFiles) 
			if (file == null) 
				throw new FileNotFoundException(ERR_MSG);
		
		return loadedFiles;
		
	}

	private Stage makeStage() {
		if(myStage == null) return new Stage();
		return myStage;
	}
	
}
