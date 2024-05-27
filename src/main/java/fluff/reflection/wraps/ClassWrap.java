package fluff.reflection.wraps;

import java.lang.reflect.Constructor;

/**
 * A wrapper class for constructing instances of a specified class using reflection.
 *
 * @param <V> the type of the class to be constructed
 */
public class ClassWrap<V> {
    
    private final Constructor<V> ctor;
    
    /**
     * Constructs a new {@code ClassWrap} instance for the specified constructor.
     *
     * @param ctor the constructor to use for creating instances
     */
    public ClassWrap(Constructor<V> ctor) {
        this.ctor = ctor;
    }
    
    /**
     * Creates a new instance using the wrapped constructor with no arguments.
     *
     * @return a new instance of the class, or {@code null} if an exception occurs
     */
    public V New() {
        return New(new Object[0]);
    }
    
    /**
     * Creates a new instance using the wrapped constructor with the given arguments.
     *
     * @param args the arguments to pass to the constructor
     * @return a new instance of the class, or {@code null} if an exception occurs
     */
    public V New(Object... args) {
        try {
            return ctor.newInstance(args);
        } catch (Exception e) {}
        return null;
    }
}
