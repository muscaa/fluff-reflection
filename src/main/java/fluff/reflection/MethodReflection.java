package fluff.reflection;

import java.lang.reflect.Method;

import fluff.functions.gen.obj.VoidFunc1;
import fluff.reflection.wraps.MethodWrap;
import fluff.reflection.wraps.StaticMethodWrap;

public class MethodReflection {
	
	private final Class<?> clazz;
	private final String method;
	
	MethodReflection(Class<?> clazz, String method) {
		this.clazz = clazz;
		this.method = method;
	}
	
	public <V> MethodWrap<V> Wrap(Class<?>... params) {
		try {
			Method m = clazz.getDeclaredMethod(method, params);
			m.setAccessible(true);
			return new MethodWrap<>(m);
		} catch (Exception e) {}
		return null;
	}
	
	public <V> StaticMethodWrap<V> WrapStatic(Class<?>... params) {
		try {
			Method m = clazz.getDeclaredMethod(method, params);
			m.setAccessible(true);
			return new StaticMethodWrap<>(m);
		} catch (Exception e) {}
		return null;
	}
	
	// STATIC
	
	public <V> V InvokeStatic() {
		return InvokeStatic(new Object[0]);
	}
	
	public <V> V InvokeStatic(Object... args) {
		return InvokeStatic((VoidFunc1<Class<?>[]>) null, args);
	}
	
	public <V> V InvokeStatic(VoidFunc1<Class<?>[]> afterAutoParams, Object... args) {
		Class<?>[] params = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			params[i] = args[i].getClass();
		}
		
		if (afterAutoParams != null) afterAutoParams.invoke(params);
		
		return InvokeStatic(params, args);
	}
	
	public <V> V InvokeStatic(Class<?>[] params, Object... args) {
		try {
			Method m = clazz.getDeclaredMethod(method, params);
			m.setAccessible(true);
			return (V) m.invoke(null, args);
		} catch (Exception e) {}
		return null;
	}
	
	// DYNAMIC
	
	public <V> V Invoke(Object instance) {
		return Invoke(instance, new Object[0]);
	}
	
	public <V> V Invoke(Object instance, Object... args) {
		return Invoke(instance, (VoidFunc1<Class<?>[]>) null, args);
	}
	
	public <V> V Invoke(Object instance, VoidFunc1<Class<?>[]> afterAutoParams, Object... args) {
		Class<?>[] params = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			params[i] = args[i].getClass();
		}
		
		if (afterAutoParams != null) afterAutoParams.invoke(params);
		
		return Invoke(instance, params, args);
	}
	
	public <V> V Invoke(Object instance, Class<?>[] params, Object... args) {
		try {
			Method m = clazz.getDeclaredMethod(method, params);
			m.setAccessible(true);
			return (V) m.invoke(instance, args);
		} catch (Exception e) {}
		return null;
	}
}
