package util.filehelpers;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Makes a list of all the filenames in a folder.
 * 
 * Standing on the shoulders of yeputons: 
 * http://stackoverflow.com/questions/5694385/getting-the-filenames-of-all-files-in-a-folder
 * 
 * @author George Bernard
 */
public class FolderListor {

	private List<String> myFileNameList;

	private static final char PATH_SEPARATOR = File.pathSeparatorChar;
	
	/**
	 * Must specify path to file
	 * 
	 * @param aFilePath
	 */
	public FolderListor(String aFilePath) {
		File folder = new File(aFilePath);	
		
		Function<File, String> fileConverter = (s) -> s.toString();
		Function<String, String> pathRemover = (s) -> s.substring(s.lastIndexOf(PATH_SEPARATOR, s.length()) + 1);
		
		myFileNameList = Arrays.asList(folder.listFiles())
			.stream()
			.map( fileConverter )
			.map( pathRemover )
			.collect(Collectors.toList());
	}

	/**
	 * @return all filenames within file
	 */
	public List<String> getFileNames() {
		return myFileNameList;
	}
	
	
	/**
	 * @param Extension to filter by
	 * @return filterd files
	 */
	public List<String> getFilesWithExtension(String aExtension) {
		Function<String, String> extensionRemover = (s) -> s.substring(0, s.lastIndexOf(aExtension));
		
		return myFileNameList.stream()
				.filter(s -> {return s.endsWith(aExtension);})
				.map(extensionRemover)
				.collect(Collectors.toList());
	}
}
