package fluff.reflection.wraps;

import java.lang.reflect.Constructor;

public class ClassWrap<V> {
	
	private final Constructor<V> ctor;
	
	public ClassWrap(Constructor<V> ctor) {
		this.ctor = ctor;
	}
	
	public V New() {
		return New(new Object[0]);
	}
	
	public V New(Object... args) {
		try {
			return ctor.newInstance(args);
		} catch (Exception e) {}
		return null;
	}
}
