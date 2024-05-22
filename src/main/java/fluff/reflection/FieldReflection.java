package fluff.reflection;

import java.lang.reflect.Field;

import fluff.reflection.wraps.FieldWrap;
import fluff.reflection.wraps.StaticFieldWrap;

public class FieldReflection {
	
	private final Class<?> clazz;
	private final String field;
	
	FieldReflection(Class<?> clazz, String field) {
		this.clazz = clazz;
		this.field = field;
	}
	
	public <V> FieldWrap<V> Wrap() {
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			return new FieldWrap<>(f);
		} catch (Exception e) {}
		return null;
	}
	
	public <V> StaticFieldWrap<V> WrapStatic() {
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			return new StaticFieldWrap<>(f);
		} catch (Exception e) {}
		return null;
	}
	
	// STATIC
	
	public <V> V GetStatic() {
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			return (V) f.get(null);
		} catch (Exception e) {}
		return null;
	}
	
	public void SetStatic(Object value) {
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			f.set(null, value);
		} catch (Exception e) {}
	}
	
	// DYNAMIC
	
	public <V> V Get(Object instance) {
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			return (V) f.get(instance);
		} catch (Exception e) {}
		return null;
	}
	
	public void Set(Object instance, Object value) {
		try {
			Field f = clazz.getDeclaredField(field);
			f.setAccessible(true);
			f.set(instance, value);
		} catch (Exception e) {}
	}
}
