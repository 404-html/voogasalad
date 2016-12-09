package util.facades;

import javafx.scene.Node;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

/**
 * Simple Facade for adding new tabs to TabPane. 
 * If Greater functionality required then use getTabPane().
 * 
 * @see TabPane
 * @author George Bernard
 */
public class TabPaneFacade {
	
	private TabPane myTabPane;
	
	/**
	 * Constructs Object
	 */
	public TabPaneFacade(){
		myTabPane = new TabPane();
	}
	
	/**
	 * Adds tab page to TabPane. 
	 * 
	 * @param aName is the tab text
	 * @param aContent is the page content
	 */
	public void addTab(String aName, Node aContent){
		Tab tab = new Tab();
		tab.setText(aName);
		tab.setClosable(false);
        tab.setContent(aContent);
        myTabPane.getTabs().add(tab);
	}

	/**
	 * Returns internal tabpane if greater functionality is required.
	 * 
	 * @return TabPane
	 */
	public TabPane getTabPane(){
		return myTabPane;
	}
	
}
