/**
 * 
 */
package author.view.pages.level_editor.windows.level_window;

import javafx.scene.layout.Pane;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
public interface ILevelWindowPane {

	public Pane getPane();
	
	public void updateGrid(int width, int height);
	
	public void removeGrid();
	
	public int adjustX(int currentX);
	
	public int adjustY(int currentY);
}
