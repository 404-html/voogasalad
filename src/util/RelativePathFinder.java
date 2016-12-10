package util;
import java.io.File;
/**
 * Used to find the relative path of a file in the 
 * voogasalad_letsjustprayitworks/ folder
 * 
 * @author Addison
 *
 */
public class RelativePathFinder {
	private final String RELATIVE = "voogasalad_letsjustprayitworks/";
	/**
	 * @param aFile
	 * @return a String of the file path. If the file is in the 
	 * voogasalad_letsjustprayitworks/ folder, the path is relative to this,
	 * else it is stored locally on the computer and the local absolute
	 * path is returned
	 */
	public String getPath(File aFile){
		String absolutePath = aFile.toURI().toString();
		String relativePath = absolutePath;
		if (absolutePath.contains(RELATIVE))
			relativePath = absolutePath.substring(absolutePath.indexOf(RELATIVE) + RELATIVE.length());
		return relativePath;
	}
}