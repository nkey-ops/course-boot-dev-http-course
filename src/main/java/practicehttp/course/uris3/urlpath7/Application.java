package practicehttp.course.uris3.urlpath7;

import java.io.IOException;
import java.util.List;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	static String apiKey;
	static JSONArray items;

	public static void main(String[] args) throws IOException {
		apiKey = generateKey();
		items = getItemData("https://api.boot.dev/v1/courses_rest_api/learn-http/locations");

		logItems();
	}

	private static JSONArray getItemData(String url) throws IOException {
		
		Content content = 
				Request.Get(url)
				.setHeader("X-API-KEY", apiKey)
				.setHeader("Content-Type", "application/json")
				.execute()
				.returnContent();
		
		
				
		return new JSONArray( content.asString());
	}

	private static String generateKey() {
		String characters = "ABCDEF0123456789";
		String result = "";
				  for (var i = 0; i < 16; i++){
				    result += characters.charAt(
				    		(int) Math.floor(Math.random() * characters.length()));
				  }
				  
		return result;
	}

	private static void logItems() {
		for (int i = 0; i < items.length(); i++) {
			JSONObject jsonObject = items.getJSONObject(i); 
			System.out.printf("Location: %s, Recommended Character Level: %s\n", 
					jsonObject.get("name"), jsonObject.get("recommendedLevel"));
		}
	}
}
