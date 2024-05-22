package fluff.reflection;

public class Reflection<V> {
	
	public static <V> ClassReflection<V> Class(Class<V> clazz) {
		return new ClassReflection<>(clazz);
	}
	
	public static MethodReflection Method(Class<?> clazz, String method) {
		return new MethodReflection(clazz, method);
	}
	
	public static FieldReflection Field(Class<?> clazz, String field) {
		return new FieldReflection(clazz, field);
	}
}
