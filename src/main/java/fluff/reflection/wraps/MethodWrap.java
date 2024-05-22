package fluff.reflection.wraps;

import java.lang.reflect.Method;

public class MethodWrap<V> {
	
	private final Method method;
	
	public MethodWrap(Method method) {
		this.method = method;
	}
	
	public V Invoke(Object instance) {
		return Invoke(instance, new Object[0]);
	}
	
	public V Invoke(Object instance, Object... args) {
		try {
			return (V) method.invoke(instance, args);
		} catch (Exception e) {}
		return null;
	}
}
