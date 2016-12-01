package game_data.characteristics;

import game_data.characteristics.characteristic_annotations.CharacteristicAnnotation;

/**
 * @author austingartside
 * potentially there will be some common "lose" action that will activate for all characteristics of this type
 * which is why it is a super class for now despite doing nothing
 */
<<<<<<< HEAD
public class Losable { 
=======
@CharacteristicAnnotation(name = "Losable")
public class Losable{
>>>>>>> a21ba578045e2d967a8654259a322cef7a204506

	public Losable(){
		//literally do nothing
		//should access the health state and change isAlive to false
	}
}
