package fluff.reflection;

import java.lang.reflect.Constructor;

import fluff.functions.gen.obj.VoidFunc1;
import fluff.reflection.wraps.ClassWrap;

public class ClassReflection<V> {
	
	private final Class<V> clazz;
	
	ClassReflection(Class<V> clazz) {
		this.clazz = clazz;
	}
	
	public ClassWrap<V> Wrap(Class<?>... params) {
		try {
			Constructor<V> ctor = clazz.getDeclaredConstructor(params);
			ctor.setAccessible(true);
			return new ClassWrap<>(ctor);
		} catch (Exception e) {}
		return null;
	}
	
	public V New() {
		return New(new Object[0]);
	}
	
	public V New(Object... args) {
		return New((VoidFunc1<Class<?>[]>) null, args);
	}
	
	public V New(VoidFunc1<Class<?>[]> afterAutoParams, Object... args) {
		Class<?>[] params = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			params[i] = args[i].getClass();
		}
		
		if (afterAutoParams != null) afterAutoParams.invoke(params);
		
		return New(params, args);
	}
	
	public V New(Class<?>[] params, Object... args) {
		try {
			Constructor<V> ctor = clazz.getDeclaredConstructor(params);
			ctor.setAccessible(true);
			return ctor.newInstance(args);
		} catch (Exception e) {}
		return null;
	}
}
