package practicehttp.course.httpmethods8.put6;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	public static void main(String[] args) throws IOException, URISyntaxException {
		String userId = "2f8282cb-e2f9-496f-b144-c0aa4ced56db";
		String generatedKey = generateKey();
		String baseURL = "https://api.boot.dev/v1/courses_rest_api/learn-http/users";

		JSONObject userData = getUserById(baseURL, userId, generatedKey);
		logUser(userData);

		System.out.println("Updating user with id: " + userId);
		userData.put("characterName", "D ellbiar");
		userData.put("level", 7);
		userData.put("class", "Warrior");
		userData.put("pvpEnabled", true);
		userData.put("user", userData.getJSONObject("user").put("name", "Allan"));

		updateUser(baseURL, userId, userData.toString(), generatedKey);
		
		JSONObject newUser = getUserById(baseURL, userId, generatedKey);
		logUser(newUser);
	}

	private static void updateUser(String url, String id, String data, String apiKey)
			throws IOException, URISyntaxException {


		Request.Put(url + "/" + id)
						.addHeader("X-API-Key", apiKey)
						.addHeader("Content-Type", "application/json")
						.bodyString(data, ContentType.APPLICATION_JSON)
						.execute().returnContent().asString();
	}

	private static JSONObject getUserById(String url, String id, String apiKey) throws IOException, URISyntaxException {
		String content = 
				Request.Get(url + "/" + id)
				.addHeader("X-API-Key", apiKey)
				.addHeader("Content-Type", "application/json")
				.execute()
				.returnContent()
				.asString();

		return new JSONObject(content);
	}

	private static void logUsers(JSONArray users) {
		for (int i = 0; i < users.length(); i++) {
			logUser(users.getJSONObject(i));
		}
	}

	private static void logUser(JSONObject user) {
		System.out.printf("Character name: %s, " + "Class: %s, " + "Level: %s, " + "User: %s\n",
				user.getString("characterName"), user.getString("class"), user.getInt("level"),
				user.getJSONObject("user").getString("name"));
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
