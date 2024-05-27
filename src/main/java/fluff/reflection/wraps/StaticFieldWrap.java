package fluff.reflection.wraps;

import java.lang.reflect.Field;

/**
 * A wrapper class for accessing and modifying a specified static field using reflection.
 *
 * @param <V> the type of the static field
 */
public class StaticFieldWrap<V> {
	
	private final Field field;
	
    /**
     * Constructs a new {@code StaticFieldWrap} instance for the specified static field.
     *
     * @param field the static field to be accessed and modified
     */
	public StaticFieldWrap(Field field) {
		this.field = field;
	}
	
    /**
     * Retrieves the value of the wrapped static field.
     *
     * @return the value of the static field, or {@code null} if an exception occurs
     */
	public V Get() {
		try {
			return (V) field.get(null);
		} catch (Exception e) {}
		return null;
	}
	
    /**
     * Sets the value of the wrapped static field.
     *
     * @param value the value to set
     */
	public void Set(V value) {
		try {
			field.set(null, value);
		} catch (Exception e) {}
	}
}
