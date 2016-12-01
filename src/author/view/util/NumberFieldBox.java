package author.view.util;

import javafx.beans.value.ChangeListener;

public class NumberFieldBox extends TextFieldBox {

	public NumberFieldBox() {
		super();
		getTextField().textProperty().addListener(makeOnlyNumberProperty());
	}

	public NumberFieldBox(String aText) {
		super(aText);
		getTextField().textProperty().addListener(makeOnlyNumberProperty());
	}
	
	public int getInteger(){
		Integer x;
		
		try {
			x = Integer.parseInt(getTextField().getText());	
		} catch (NumberFormatException e){
			x = 0;
		}
		
		return x;
	}
	
	public double getDouble(){
		Double x;
		
		try {
			x = Double.parseDouble(getTextField().getText()); 
		} catch (NumberFormatException e) {
			x = 0.0;
		}
		
		return x;
	}
	
	public void setValue(Number aNumber){
		getTextField().setText(aNumber.toString());
	}
		
	/**
	 * Standing on the shoulders of Evan Knowles and limc here
	 * http://stackoverflow.com/questions/7555564/what-is-the-recommended-way-to-make-a-numeric-textfield-in-javafx
	 * http://stackoverflow.com/questions/5011855/matching-decimals-in-strings-using-matcher
	 * 
	 * @param aTextField
	 * @return
	 */
	private final ChangeListener<? super String> makeOnlyNumberProperty(){
		return (obs, ov, nv) -> { 
			if(!nv.matches("\\d+(\\.\\d+)")){
				getTextField().setText(nv.replaceAll("[^\\d+(\\.\\d+)]", ""));
			}
		}; 

	}
	
}
