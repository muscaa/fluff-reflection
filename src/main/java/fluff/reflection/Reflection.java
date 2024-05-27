package fluff.reflection;

/**
 * A utility class for simplified Java reflection operations.
 *
 * @param <V> the type of the class or object to reflect on
 */
public class Reflection<V> {
    
    /**
     * Creates a new {@code ClassReflection} instance for the specified class.
     *
     * @param <V>   the type of the class
     * @param clazz the class to reflect on
     * @return a new {@code ClassReflection} instance
     */
    public static <V> ClassReflection<V> Class(Class<V> clazz) {
        return new ClassReflection<>(clazz);
    }
    
    /**
     * Creates a new {@code MethodReflection} instance for the specified method in the given class.
     *
     * @param clazz  the class containing the method
     * @param method the name of the method to reflect on
     * @return a new {@code MethodReflection} instance
     */
    public static MethodReflection Method(Class<?> clazz, String method) {
        return new MethodReflection(clazz, method);
    }
    
    /**
     * Creates a new {@code FieldReflection} instance for the specified field in the given class.
     *
     * @param clazz the class containing the field
     * @param field the name of the field to reflect on
     * @return a new {@code FieldReflection} instance
     */
    public static FieldReflection Field(Class<?> clazz, String field) {
        return new FieldReflection(clazz, field);
    }
}
