package fluff.reflection.wraps;

import java.lang.reflect.Method;

/**
 * A wrapper class for invoking a specified method using reflection.
 *
 * @param <V> the return type of the method
 */
public class MethodWrap<V> {
    
    private final Method method;
    
    /**
     * Constructs a new {@code MethodWrap} instance for the specified method.
     *
     * @param method the method to be invoked
     */
    public MethodWrap(Method method) {
        this.method = method;
    }
    
    /**
     * Invokes the wrapped method on the specified instance with no arguments.
     *
     * @param instance the object on which to invoke the method
     * @return the result of invoking the method, or {@code null} if an exception occurs
     */
    public V Invoke(Object instance) {
        return Invoke(instance, new Object[0]);
    }
    
    /**
     * Invokes the wrapped method on the specified instance with the specified arguments.
     *
     * @param instance the object on which to invoke the method
     * @param args the arguments to pass to the method
     * @return the result of invoking the method, or {@code null} if an exception occurs
     */
    public V Invoke(Object instance, Object... args) {
        try {
            return (V) method.invoke(instance, args);
        } catch (Exception e) {}
        return null;
    }
}
