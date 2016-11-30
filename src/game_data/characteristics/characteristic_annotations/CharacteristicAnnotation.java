/**
 * 
 */
package game_data.characteristics.characteristic_annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author Cleveland Thompson V (ct168)
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)

public @interface CharacteristicAnnotation {
	public String name();
}
