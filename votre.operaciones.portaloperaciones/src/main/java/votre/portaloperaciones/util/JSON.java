package votre.portaloperaciones.util;

import com.google.gson.Gson;

public class JSON {

	private static final Gson gson = new Gson();
	
	public static String dump(Object term) {
		return gson.toJson(term);
	}
	
	public static <T> T load(String json, Class<T> classOfT) {
		return gson.fromJson(json, classOfT);
	}
}
