package fluff.reflection.wraps;

import java.lang.reflect.Method;

/**
 * A wrapper class for invoking a specified static method using reflection.
 *
 * @param <V> the return type of the static method
 */
public class StaticMethodWrap<V> {
	
	private final Method method;
	
    /**
     * Constructs a new {@code StaticMethodWrap} instance for the specified static method.
     *
     * @param method the static method to be invoked
     */
	public StaticMethodWrap(Method method) {
		this.method = method;
	}
	
    /**
     * Invokes the wrapped static method with no arguments.
     *
     * @return the result of invoking the static method, or {@code null} if an exception occurs
     */
	public V Invoke() {
		return Invoke(new Object[0]);
	}
	
    /**
     * Invokes the wrapped static method with the specified arguments.
     *
     * @param args the arguments to pass to the static method
     * @return the result of invoking the static method, or {@code null} if an exception occurs
     */
	public V Invoke(Object... args) {
		try {
			return (V) method.invoke(null, args);
		} catch (Exception e) {}
		return null;
	}
}
