package practicehttp.course.pathsandparameters9.urlpath1;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	public static void main(String[] args) throws IOException {
		JSONArray locations = getResources("/v1/courses_rest_api/learn-http/locations");
		System.out.println("Locations:")                                                      ;
		logResources(locations)                                                        ;
		System.out.println(" --- ")                                                           ;

		JSONArray items = getResources("/v1/courses_rest_api/learn-http/items")      ;
		System.out.println("Items:")                                                          ;
		logResources(items)                                                            ;
		System.out.println(" --- ")                                                           ;
		
		JSONArray users = getResources("/v1/courses_rest_api/learn-http/users")      ;
		System.out.println("Users:")                                                          ;
		logResources(users)                                                            ;
		
	}

	private static void logResources(JSONArray resources) {
		for (int i = 0; i < resources.length(); i++) {
			JSONObject jsonObject = resources.getJSONObject(i);
			System.out.println(jsonObject);
		}
	}

	private static JSONArray getResources(String urlPath) 
										throws ClientProtocolException, IOException {
		String content = Request.Get("https://api.boot.dev" + urlPath)
				.addHeader("X-API-Key", generateKey())
				.addHeader("Content-Type", "application/json")
				.execute()
				.returnContent()
				.asString();

		return new JSONArray(content);
	}

	private static String generateKey() {
		String characters = "ABCDEF0123456789";
		String result = "";
		for (var i = 0; i < 16; i++) {
			result += characters.charAt((int) Math.floor(Math.random() * characters.length()));
		}

		return result;
	}

}
