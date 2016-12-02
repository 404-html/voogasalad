package author.view.util.input_fields;

import javafx.beans.value.ChangeListener;

/**
 * Helpful wrapper for TextField with Label. Only accepts Numerical inputs
 * 
 * @author George Bernard
 */
public class NumberFieldBox extends TextFieldBox {

	public NumberFieldBox() {
		super();
		getTextField().textProperty().addListener(makeOnlyNumberProperty());
		setValue(0);
	}

	public NumberFieldBox(String aText) {
		super(aText);
		getTextField().textProperty().addListener(makeOnlyNumberProperty());
		getTextField().setText( aText );
		getTextField().setText( getTextField().getText().equals("") ? "0" : aText );
	}
	
	/**
	 * Returns int parsed from textfield. If not an integer, returns zero
	 * 
	 * @return parsed int
	 */
	public int getInteger(){
		Integer x;
		
		try {
			x = Integer.parseInt(getTextField().getText());	
		} catch (NumberFormatException e){
			x = 0;
		}
		
		return x;
	}
	
	/**
	 * Returns double parsed from textfield. If not a double, returns 0
	 * 
	 * @return parsed Double
	 */
	public double getDouble(){
		Double x;
		
		try {
			x = Double.parseDouble(getTextField().getText()); 
		} catch (NumberFormatException e) {
			x = 0.0;
		}
		
		return x;
	}
	
	/**
	 * Sets the value of the number inside of the textfield
	 * 
	 * @param aNumber
	 */
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
