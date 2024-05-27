package fluff.reflection;

import java.lang.reflect.Field;
import fluff.reflection.wraps.FieldWrap;
import fluff.reflection.wraps.StaticFieldWrap;

/**
 * A class for reflecting on fields of a specified class.
 */
public class FieldReflection {
    
    private final Class<?> clazz;
    private final String field;
    
    /**
     * Constructs a new {@code FieldReflection} instance for the specified class and field name.
     *
     * @param clazz the class containing the field
     * @param field the name of the field
     */
    FieldReflection(Class<?> clazz, String field) {
        this.clazz = clazz;
        this.field = field;
    }
    
    /**
     * Wraps the specified field.
     *
     * @param <V> the type of the field
     * @return a {@code FieldWrap} instance for the field, or {@code null} if an exception occurs
     */
    public <V> FieldWrap<V> Wrap() {
        try {
            return new FieldWrap<>(field());
        } catch (Exception e) {}
        return null;
    }
    
    /**
     * Wraps the specified static field.
     *
     * @param <V> the type of the field
     * @return a {@code StaticFieldWrap} instance for the static field, or {@code null} if an exception occurs
     */
    public <V> StaticFieldWrap<V> WrapStatic() {
        try {
            return new StaticFieldWrap<>(field());
        } catch (Exception e) {}
        return null;
    }
    
    /**
     * Gets the value of the specified static field.
     *
     * @param <V> the type of the field
     * @return the value of the static field, or {@code null} if an exception occurs
     */
    public <V> V GetStatic() {
        return Get(null);
    }
    
    /**
     * Sets the value of the specified static field.
     *
     * @param value the value to set
     */
    public void SetStatic(Object value) {
        Set(null, value);
    }
    
    /**
     * Gets the value of the specified field from the given instance.
     *
     * @param <V> the type of the field
     * @param instance the instance from which to get the field value
     * @return the value of the field, or {@code null} if an exception occurs
     */
    public <V> V Get(Object instance) {
        try {
            return (V) field().get(instance);
        } catch (Exception e) {}
        return null;
    }
    
    /**
     * Sets the value of the specified field for the given instance.
     *
     * @param instance the instance for which to set the field value
     * @param value    the value to set
     */
    public void Set(Object instance, Object value) {
        try {
            field().set(instance, value);
        } catch (Exception e) {}
    }
    
    private Field field() throws Exception {
    	Field f = clazz.getDeclaredField(field);
        f.setAccessible(true);
        return f;
    }
}
