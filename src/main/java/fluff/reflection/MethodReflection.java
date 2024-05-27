package fluff.reflection;

import java.lang.reflect.Method;
import fluff.functions.gen.obj.VoidFunc1;
import fluff.reflection.wraps.MethodWrap;
import fluff.reflection.wraps.StaticMethodWrap;

/**
 * A class for reflecting on methods of a specified class.
 */
public class MethodReflection {
    
    private final Class<?> clazz;
    private final String method;
    
    /**
     * Constructs a new {@code MethodReflection} instance for the specified class and method name.
     *
     * @param clazz the class containing the method
     * @param method the name of the method
     */
    MethodReflection(Class<?> clazz, String method) {
        this.clazz = clazz;
        this.method = method;
    }
    
    /**
     * Wraps the specified method.
     *
     * @param <V> the type of the method's return value
     * @param params the parameter types of the method
     * @return a {@code MethodWrap} instance for the method, or {@code null} if an exception occurs
     */
    public <V> MethodWrap<V> Wrap(Class<?>... params) {
        try {
            return new MethodWrap<>(method(params));
        } catch (Exception e) {}
        return null;
    }
    
    /**
     * Wraps the specified static method.
     *
     * @param <V> the type of the method's return value
     * @param params the parameter types of the method
     * @return a {@code StaticMethodWrap} instance for the static method, or {@code null} if an exception occurs
     */
    public <V> StaticMethodWrap<V> WrapStatic(Class<?>... params) {
        try {
            return new StaticMethodWrap<>(method(params));
        } catch (Exception e) {}
        return null;
    }
    
    /**
     * Invokes the specified static method with no arguments.
     *
     * @param <V> the type of the method's return value
     * @return the return value of the method, or {@code null} if an exception occurs
     */
    public <V> V InvokeStatic() {
        return InvokeStatic(new Object[0]);
    }
    
    /**
     * Invokes the specified static method with the given arguments.
     *
     * @param <V> the type of the method's return value
     * @param args the arguments to pass to the method
     * @return the return value of the method, or {@code null} if an exception occurs
     */
    public <V> V InvokeStatic(Object... args) {
        return InvokeStatic((VoidFunc1<Class<?>[]>) null, args);
    }
    
    /**
     * Invokes the specified static method with the given arguments, allowing for customization of parameter types.
     *
     * @param <V> the type of the method's return value
     * @param afterAutoParams a function to customize the parameter types
     * @param args the arguments to pass to the method
     * @return the return value of the method, or {@code null} if an exception occurs
     */
    public <V> V InvokeStatic(VoidFunc1<Class<?>[]> afterAutoParams, Object... args) {
        Class<?>[] params = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            params[i] = args[i].getClass();
        }
        
        if (afterAutoParams != null) afterAutoParams.invoke(params);
        
        return InvokeStatic(params, args);
    }
    
    /**
     * Invokes the specified static method with the given parameter types and arguments.
     *
     * @param <V> the type of the method's return value
     * @param params the parameter types of the method
     * @param args the arguments to pass to the method
     * @return the return value of the method, or {@code null} if an exception occurs
     */
    public <V> V InvokeStatic(Class<?>[] params, Object... args) {
        return Invoke(null, params, args);
    }
    
    /**
     * Invokes the specified method on the given instance with no arguments.
     *
     * @param <V> the type of the method's return value
     * @param instance the instance on which to invoke the method
     * @return the return value of the method, or {@code null} if an exception occurs
     */
    public <V> V Invoke(Object instance) {
        return Invoke(instance, new Object[0]);
    }
    
    /**
     * Invokes the specified method on the given instance with the given arguments.
     *
     * @param <V> the type of the method's return value
     * @param instance the instance on which to invoke the method
     * @param args the arguments to pass to the method
     * @return the return value of the method, or {@code null} if an exception occurs
     */
    public <V> V Invoke(Object instance, Object... args) {
        return Invoke(instance, (VoidFunc1<Class<?>[]>) null, args);
    }
    
    /**
     * Invokes the specified method on the given instance with the given arguments, allowing for customization of parameter types.
     *
     * @param <V> the type of the method's return value
     * @param instance the instance on which to invoke the method
     * @param afterAutoParams a function to customize the parameter types
     * @param args the arguments to pass to the method
     * @return the return value of the method, or {@code null} if an exception occurs
     */
    public <V> V Invoke(Object instance, VoidFunc1<Class<?>[]> afterAutoParams, Object... args) {
        Class<?>[] params = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            params[i] = args[i].getClass();
        }
        
        if (afterAutoParams != null) afterAutoParams.invoke(params);
        
        return Invoke(instance, params, args);
    }
    
    /**
     * Invokes the specified method on the given instance with the given parameter types and arguments.
     *
     * @param <V> the type of the method's return value
     * @param instance the instance on which to invoke the method
     * @param params the parameter types of the method
     * @param args the arguments to pass to the method
     * @return the return value of the method, or {@code null} if an exception occurs
     */
    public <V> V Invoke(Object instance, Class<?>[] params, Object... args) {
        try {
            return (V) method(params).invoke(instance, args);
        } catch (Exception e) {}
        return null;
    }
    
    private Method method(Class<?>[] params) throws Exception {
    	Method m = clazz.getDeclaredMethod(method, params);
        m.setAccessible(true);
        return m;
    }
}
