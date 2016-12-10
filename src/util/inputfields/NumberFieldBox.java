package util.inputfields;

import javafx.beans.value.ChangeListener;

/**
 * Helpful wrapper for TextField with Label. Only accepts Numerical inputs
 * 
 * @author George Bernard
 */
public class NumberFieldBox extends TextFieldBox {

	private static final String MATCH_NOT_NUMBER_REGEX = "\\d+(\\.\\d+)";
	private static final String REPLACE_NOT_NUM_REGEX = "[^\\d+(\\.\\d+)]";
	private static final String REPLACE_NOT_NUM_CHAR = "";
	
	private static final Number DEFAULT_VALUE = 0;
	
	
	public NumberFieldBox() {
		super();
		getTextField().textProperty().addListener(makeOnlyNumberProperty());
		setValue(DEFAULT_VALUE);
	}

	public NumberFieldBox(String aText) {
		super(aText);
		getTextField().textProperty().addListener(makeOnlyNumberProperty());
		getTextField().setText( aText );
		getTextField().setText( getTextField().getText().equals("") ? DEFAULT_VALUE.toString() : aText );
	}
	
	/**
	 * Returns int parsed from textfield. If not an integer, throws NumberFormatException
	 * 
	 * @throws NumberFormatException
	 * @return parsed int
	 */
	public int getInteger() throws NumberFormatException{
		Integer x;
		x = Integer.parseInt(getTextField().getText());	
		return x;
	}
	
	/**
	 * Returns double parsed from textfield. If not a double, throws NumberFormatException
	 * 
	 * @throws NumberFormatException
	 * @return parsed Double
	 */
	public double getDouble() throws NumberFormatException{
		Double x;
		x = Double.parseDouble(getTextField().getText()); 
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
			if(!nv.matches(MATCH_NOT_NUMBER_REGEX)){
				getTextField().setText(nv.replaceAll(REPLACE_NOT_NUM_REGEX, REPLACE_NOT_NUM_CHAR));
			}
		}; 

	}
	
}
