/**
 * 
 */
package author.view.pages.level_editor.windows.level_window;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javafx.scene.control.Label;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
class LevelWindowPane implements ILevelWindowPane{
	
	private Pane levelPane = new Pane();
	private GridPane gridPane;
	private int width = 0;
	private int height = 0;
	

	LevelWindowPane() {
	}

	@Override
	public Pane getPane() {
		return this.levelPane;
	}
	
	private GridPane createGridPane(int width, int height){
		GridPane gridPane = new GridPane();
		gridPane.setGridLinesVisible(true);
		gridPane.getColumnConstraints().setAll(getColumnConstraints(width));
		gridPane.getRowConstraints().setAll(getRowConstraints(height));
		return gridPane;
	}
	
	private Collection<ColumnConstraints> getColumnConstraints(int width){
		Set<ColumnConstraints> columnConstraintsSet = new HashSet<>();
		for (int i = 0; i < levelPane.getWidth()/width; i++){
			ColumnConstraints columnConstraints = new ColumnConstraints();
			columnConstraints.setPrefWidth(width);
			columnConstraintsSet.add(columnConstraints);
		}
		
		return columnConstraintsSet;
	}
	
	private Collection<RowConstraints> getRowConstraints(int height){
		Set<RowConstraints> rowConstraintsSet = new HashSet<>();
		for (int i = 0; i < levelPane.getWidth()/height; i++){
			RowConstraints rowConstraints = new RowConstraints();
			rowConstraints.setPrefHeight(height);
			rowConstraintsSet.add(rowConstraints);
		}
		return rowConstraintsSet;
	}

	@Override
	public void updateGrid(int width, int height) {
		this.width = width;
		this.height = height;
		this.gridPane = createGridPane(width, height);
		this.levelPane.getChildren().add(gridPane);
		
		this.gridPane.toBack();
	}

	@Override
	public void removeGrid() {
		this.levelPane.getChildren().remove(this.gridPane);
	}

	/* (non-Javadoc)
	 * @see author.view.pages.level_editor.windows.level_window.ILevelWindowPane#adjustX(int)
	 */
	@Override
	public int adjustX(int currentX) {
		return (currentX/width)*width;
	}

	/* (non-Javadoc)
	 * @see author.view.pages.level_editor.windows.level_window.ILevelWindowPane#adjustY(int)
	 */
	@Override
	public int adjustY(int currentY) {
		return (currentY/height)*height;
	}
}
