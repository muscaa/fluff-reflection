package fluff.reflection.wraps;

import java.lang.reflect.Method;

public class StaticMethodWrap<V> {
	
	private final Method method;
	
	public StaticMethodWrap(Method method) {
		this.method = method;
	}
	
	public V Invoke() {
		return Invoke(new Object[0]);
	}
	
	public V Invoke(Object... args) {
		try {
			return (V) method.invoke(null, args);
		} catch (Exception e) {}
		return null;
	}
}
