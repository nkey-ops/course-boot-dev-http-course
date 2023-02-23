package practicehttp.course.httpmethods8.delete8;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	public static void main(String[] args) throws IOException, URISyntaxException {
		String userId = "0194fdc2-fa2f-4cc0-81d3-ff12045b73c8";
		String baseURL = "https://api.boot.dev/v1/courses_rest_api/learn-http/users";
		String generatedKey = generateKey();
			
		JSONArray users = getUsers(baseURL, generatedKey);
		logUsers(users);
		System.out.println("-".repeat(20));
		
		deleteUser(baseURL, userId, generatedKey);
		System.out.println("Deleted user with id; " + userId);
		System.out.println("-".repeat(20));
		
		JSONArray newUsers = getUsers(baseURL, generatedKey);
		logUsers(newUsers);
		System.out.println("-".repeat(20));
		
		
	}
	
	private static JSONArray getUsers(String url, String apiKey) 
			throws ClientProtocolException, IOException {
			String content = 
					Request.Get(url)
							.addHeader("X-API-Key", apiKey)
							.addHeader("Content-Type", "application/json")
							.execute()
							.returnContent()
							.asString();

			return new JSONArray(content);
	}
	
	private static JSONObject deleteUser(String url, String id, String apiKey) 
										throws ClientProtocolException, IOException {
		
		String content = 
				Request.Delete(url + "/" + id)
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
		System.out.printf("UUId: %s  Character name: %s, " + 
				"Class: %s, " + 
				"Level: %s, " + 
				"User: %s\n",
				user.getString("id"),
				user.getString("characterName"), 
				user.getString("class"), 
				user.getInt("level"),
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
