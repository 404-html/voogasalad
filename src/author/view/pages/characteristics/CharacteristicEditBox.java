package author.view.pages.characteristics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import author.view.util.BooleanSelector;
import author.view.util.NumberFieldBox;
import author.view.util.TextFieldBox;
import game_data.characteristics.Characteristic;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

class CharacteristicEditBox {

	private Pane myPane;
	private Characteristic myCharacteristic;

	private List<BooleanSelector> myBoolSelectList;
	private List<NumberFieldBox> myNumberFieldList;
	private List<TextFieldBox> myTextFieldBoxList;

	CharacteristicEditBox() {
		myPane = new VBox();
		myBoolSelectList = new ArrayList<>();
		myNumberFieldList = new ArrayList<>();
		myTextFieldBoxList = new ArrayList<>();

		addBooleanSelectors(new BooleanSelector("Boolean Selector"));
		addNumberFields(new NumberFieldBox("Number Field"));
		addTextBoxes(new TextFieldBox("Text Field"));
	}

	void addTextBoxes( TextFieldBox... aTextFieldBoxes ){
		Arrays.asList( aTextFieldBoxes ).forEach( e -> {
			myTextFieldBoxList.add(e);
			myPane.getChildren().add(e.getPane());
		});
	}
	
	void addBooleanSelectors( BooleanSelector... aBooleanSelectors ){
		Arrays.asList( aBooleanSelectors ).forEach( e -> {
			myBoolSelectList.add(e);
			myPane.getChildren().add(e.getPane());
		});		
	}
	
	void addNumberFields( NumberFieldBox... aNumberFields ){
		Arrays.asList( aNumberFields ).forEach( e -> {
			myNumberFieldList.add(e);
			myPane.getChildren().add(e.getPane());
		});
		myNumberFieldList.addAll( Arrays.asList(aNumberFields) );
	}
	
	Pane getPane(){
		return myPane;
	}	

	Characteristic getCharacteristic(){
		return myCharacteristic;
	}
}
