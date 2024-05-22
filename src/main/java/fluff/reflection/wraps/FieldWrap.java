package fluff.reflection.wraps;

import java.lang.reflect.Field;

public class FieldWrap<V> {
	
	private final Field field;
	
	public FieldWrap(Field field) {
		this.field = field;
	}
	
	public V Get(Object instance) {
		try {
			return (V) field.get(instance);
		} catch (Exception e) {}
		return null;
	}
	
	public void Set(Object instance, V value) {
		try {
			field.set(instance, value);
		} catch (Exception e) {}
	}
}
