package practicehttp.course.headers6.whyheaderspractice3;
import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	static String apiKey;
	static JSONArray items;

	public static void main(String[] args) throws IOException {
		String url = "https://api.boot.dev/v1/courses_rest_api/learn-http/locations/52fdfc07-2182-454f-963f-5f0f9a621d72";
		apiKey = generateKey();
		String newLocationData =
				"""
				{
				"discovered": false,
				"id": "52fdfc07-2182-454f-963f-5f0f9a621d72",
				"name": "Bloodstone Swamp",
				"recommendedLevel": 10
				}
				""";

		JSONObject location = getLocationResponse(apiKey, url);
		System.out.println("Got old location:");
		System.out.println("- name: " + location.getString("name") + 
				", recommendedLevel: " + location.getInt("recommendedLevel"));
		System.out.println("-".repeat(20));
		
		
		JSONObject putLocation = putLocation(apiKey, url, newLocationData);
		System.out.println("Location is updated");
		System.out.println("-".repeat(20));
		
//		apiKey = generateKey();
		JSONObject newLocation = getLocationResponse(apiKey, url);
		System.out.println("Got new location:");
		System.out.println("- name: " + newLocation.getString("name") + 
				", recommendedLevel: " + newLocation.getInt("recommendedLevel"));
		System.out.println("-".repeat(20));
		
	}

	private static JSONObject getLocationResponse(String apiKey, String url)
			throws ClientProtocolException, IOException {
		String conttent = Request.Get(url)
				.addHeader("X-API-Key", apiKey)
				.addHeader("Content-Type", "application/json")
				.execute()
				.returnContent()
				.asString();

		return new JSONObject(conttent);
	}

	private static JSONObject putLocation(String apiKey, String url, String data)
			throws ClientProtocolException, IOException {
		String content = 
				Request.Put(url)
				.addHeader("X-API-Key", apiKey)
				.addHeader("Content-Type", "application/json")
				.bodyString(data, ContentType.APPLICATION_JSON)
				.execute()
				.returnContent()
				.asString();

		return new JSONObject(content);
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
