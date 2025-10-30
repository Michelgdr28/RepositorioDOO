package co.edu.uco.nose.crosscuting.helper;

public final class ObjectHelper {

	private ObjectHelper() {
		
	}
	
	public static <O> boolean isNull(O object) {
		return object == null;
	}
	
	public static <O> O getDefault (final O object,final O defaultValue) {
		
		return isNull (object) ? defaultValue : object;
	}

	public static ObjectHelper getObjectHelper() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
