package practicehttp.course.whyhttp1.ex.webcommunication1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONArray;

public class Application {
	static String apiKey;
	static JSONArray items;

	public static void main(String[] args) throws IOException {
		apiKey = generateKey();
		items = getItemData();

		logItems();
	}

	private static JSONArray getItemData() throws IOException {
		URL url = new URL("https://api.boot.dev/v1/courses_rest_api/learn-http/items");
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();

		connection.setRequestMethod("GET");
		connection.setRequestProperty("X-API-KEY", apiKey);
		connection.setRequestProperty("Content-Type", "application/json");

		StringBuilder content = new StringBuilder();
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(connection.getInputStream()))) {

			String inputLine;
			while ((inputLine = in.readLine()) != null) {
				content.append(inputLine);
			}
			in.close();

		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
		
		return new JSONArray(content.toString());

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
