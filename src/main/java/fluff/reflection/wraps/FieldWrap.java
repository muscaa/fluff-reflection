package fluff.reflection.wraps;

import java.lang.reflect.Field;

/**
 * A wrapper class for accessing and modifying a specified field using reflection.
 *
 * @param <V> the type of the field
 */
public class FieldWrap<V> {
    
    private final Field field;
    
    /**
     * Constructs a new {@code FieldWrap} instance for the specified field.
     *
     * @param field the field to be accessed and modified
     */
    public FieldWrap(Field field) {
        this.field = field;
    }
    
    /**
     * Retrieves the value of the wrapped field from the specified instance.
     *
     * @param instance the object from which the field value is to be extracted
     * @return the value of the field, or {@code null} if an exception occurs
     */
    public V Get(Object instance) {
        try {
            return (V) field.get(instance);
        } catch (Exception e) {}
        return null;
    }
    
    /**
     * Sets the value of the wrapped field for the specified instance.
     *
     * @param instance the object in which the field value is to be set
     * @param value the value to set
     */
    public void Set(Object instance, V value) {
        try {
            field.set(instance, value);
        } catch (Exception e) {}
    }
}
