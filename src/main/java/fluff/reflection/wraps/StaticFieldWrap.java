package fluff.reflection.wraps;

import java.lang.reflect.Field;

public class StaticFieldWrap<V> {
	
	private final Field field;
	
	public StaticFieldWrap(Field field) {
		this.field = field;
	}
	
	public V Get() {
		try {
			return (V) field.get(null);
		} catch (Exception e) {}
		return null;
	}
	
	public void Set(V value) {
		try {
			field.set(null, value);
		} catch (Exception e) {}
	}
}
