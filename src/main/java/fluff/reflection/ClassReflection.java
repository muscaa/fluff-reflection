package fluff.reflection;

import java.lang.reflect.Constructor;
import fluff.functions.gen.obj.VoidFunc1;
import fluff.reflection.wraps.ClassWrap;

/**
 * A class for reflecting on Java classes to create instances and wrap constructors.
 *
 * @param <V> the type of the class to reflect on
 */
public class ClassReflection<V> {
    
    private final Class<V> clazz;
    
    /**
     * Constructs a new {@code ClassReflection} instance for the specified class.
     *
     * @param clazz the class to reflect on
     */
    ClassReflection(Class<V> clazz) {
        this.clazz = clazz;
    }
    
    /**
     * Wraps the constructor of the class with the specified parameter types.
     *
     * @param params the parameter types of the constructor
     * @return a {@code ClassWrap} instance for the constructor, or {@code null} if an exception occurs
     */
    public ClassWrap<V> Wrap(Class<?>... params) {
        try {
            return new ClassWrap<>(constructor(params));
        } catch (Exception e) {}
        return null;
    }
    
    /**
     * Creates a new instance of the class using the default constructor.
     *
     * @return a new instance of the class, or {@code null} if an exception occurs
     */
    public V New() {
        return New(new Object[0]);
    }
    
    /**
     * Creates a new instance of the class using the constructor with the specified arguments.
     *
     * @param args the arguments to pass to the constructor
     * @return a new instance of the class, or {@code null} if an exception occurs
     */
    public V New(Object... args) {
        return New((VoidFunc1<Class<?>[]>) null, args);
    }
    
    /**
     * Creates a new instance of the class using the constructor with the specified arguments
     * and an optional callback to modify the parameter types.
     *
     * @param afterAutoParams a callback to modify the parameter types after they are automatically determined
     * @param args            the arguments to pass to the constructor
     * @return a new instance of the class, or {@code null} if an exception occurs
     */
    public V New(VoidFunc1<Class<?>[]> afterAutoParams, Object... args) {
        Class<?>[] params = new Class<?>[args.length];
        for (int i = 0; i < args.length; i++) {
            params[i] = args[i].getClass();
        }
        
        if (afterAutoParams != null) afterAutoParams.invoke(params);
        
        return New(params, args);
    }
    
    /**
     * Creates a new instance of the class using the constructor with the specified parameter types and arguments.
     *
     * @param params the parameter types of the constructor
     * @param args   the arguments to pass to the constructor
     * @return a new instance of the class, or {@code null} if an exception occurs
     */
    public V New(Class<?>[] params, Object... args) {
        try {
            return constructor(params).newInstance(args);
        } catch (Exception e) {}
        return null;
    }
    
    public Constructor<V> constructor(Class<?>[] params) throws Exception {
    	Constructor<V> c = clazz.getDeclaredConstructor(params);
        c.setAccessible(true);
        return c;
    }
}
