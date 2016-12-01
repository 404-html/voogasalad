package util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * Saves and loads an Object using XStream serialization to/from a file
 * 
 * @author Addison
 */
public class XMLTranslator {
	

	/**
	 * Can be used to serialize a Game, Level, Sprite, etc. as XML
	 * 
	 * @param o
	 * @return String of XML representation of serialized Object
	 */
	public String serialize(Object o){
		XStream mySerializer = new XStream(new DomDriver());
		mySerializer.autodetectAnnotations(true);;
		return mySerializer.toXML(o);
	}

	/**
	 * @param o - Object to serialize
	 * @param aFilePath - folder where file should be saved
	 * @param aFileName - name of file (not including .xml)
	 * @return the file to which o was saved
	 */
	public File saveToFile(Object o, String aFilePath, String aFileName){
		File aFile = new File(aFilePath + aFileName + ".xml");
		return saveToFile(o, aFile);
	}

	/**
	 * @param o - Object to serialize
	 * @param aFile - file to save
	 * @return the file to which o was saved
	 */
	public File saveToFile(Object o, File aFile){
		try {
			FileWriter fw = new FileWriter(aFile);
			fw.write(this.serialize(o));
			fw.close();
		} catch (IOException e) {
			System.out.println("Trouble printing XML to file");
		}
		return aFile;
	}

	/**
	 * @param aFile - to deserialize
	 * @return Object representation of what is stored in .xml file
	 */
	public Object deserialize(File aFile){
		XStream mySerializer = new XStream(new DomDriver());
		mySerializer.autodetectAnnotations(true);
		return mySerializer.fromXML(aFile);
	}
	
	/**
	 * @param aXMLString - to deserialize
	 * @return Object representation of XMLString
	 */
	public Object deserialize(String aXMLString){
		XStream mySerializer = new XStream(new DomDriver());
		mySerializer.autodetectAnnotations(true);
		return mySerializer.fromXML(aXMLString);
	}

	/**
	 * @param aFilePath - where to look for file
	 * @param aFileName - name of file to look for
	 * @return Object representation of XMLString
	 */
	public Object loadFromFile(String aFilePath, String aFileName){
		File aFile = new File(aFilePath + aFileName + ".xml");
		return deserialize(aFile);
	}

	/**
	 * @param aFile - file from which to load
	 * @return Object representation of XMLString
	 */
	public Object loadFromFile(File aFile){
		return deserialize(aFile);
	}
}
