package practicehttp.course.dns2.ex.subdomain;

import java.io.IOException;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;

public class Application {
	static String apiKey;
	static JSONArray items;

	public static void main(String[] args) throws IOException {
		apiKey = generateKey();
		items = getItemData("api.boot.dev");

		logItems();
	}

	private static JSONArray getItemData(String domain) throws IOException {
		String url = 
				String.format(
						"https://%s/v1/courses_rest_api/learn-http/items", domain);
		
		Content content = 
				Request.Get(url)
				.setHeader("X-API-KEY", apiKey)
				.setHeader("Content-Type", "application/json")
				.execute()
				.returnContent();
		
		
		return new JSONArray(content.asString());
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
		for (Object object : items.toList()) {
			System.out.println(object);
		}
	}
}
