package gameplayer.back_end.user_information;

import java.io.File;

import util.XMLTranslator;

public class XMLHandler {

	public void save(Object aObject, String aFilepath) {
		XMLTranslator mySaver = new XMLTranslator();
		mySaver.saveToFile(aObject, "XMLGameFiles/", aFilepath);
	}
	
	public Object load(File aFilePath) {
		XMLTranslator aLoader = new XMLTranslator();
		return aLoader.loadFromFile(aFilePath);
	}
	
	public Object load(String aFilePath) {
		XMLTranslator aLoader = new XMLTranslator();
		return aLoader.loadFromFile("XMLGameFiles/", aFilePath);
	}
}
