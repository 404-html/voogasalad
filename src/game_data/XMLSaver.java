package game_data;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
* Saves an Object using XStream serialization
*
* @author Addison
*/
public class XMLSaver {
    
    /**
     * Can be used to serialize a Game, Level, Sprite, etc. as XML
     *
     * @param o
     * @return String of XML representation of serialized Object
     * @author Addison
     */
    public String serialize(Object o){
        XStream mySerializer = new XStream(new DomDriver());
        return mySerializer.toXML(o);
    }

}