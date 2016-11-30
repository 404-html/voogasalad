package author.images;

import java.io.File;


public enum EmptyImage {
	INSTANCE;

	private static final String FILE_PATH = "author/images/";
	private static final String EMPTY_IMAGE_FILENAME = "Empty.png";
	private File myFile;

	private EmptyImage(){
		myFile = new File(FILE_PATH + EMPTY_IMAGE_FILENAME);
	}

	public String getFile(){
		return myFile.toString();
	}
} 
