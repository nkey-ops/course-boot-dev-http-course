package practicehttp.course.whyhttp1.ex.urls3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.fluent.Content;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;


public class Application {
	static String apiKey;
	static JSONArray items;

	public static void main(String[] args) throws IOException {
		apiKey = generateKey();
		items = getItemData("https://api.boot.dev/v1/courses_rest_api/learn-http/items");

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
		for (Object object : items.toList()) {
			System.out.println(object);
		}
	}

}

