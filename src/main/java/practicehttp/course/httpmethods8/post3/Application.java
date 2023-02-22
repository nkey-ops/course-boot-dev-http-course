package practicehttp.course.httpmethods8.post3;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.entity.ContentType;
import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	public static void main(String[] args) throws IOException {
		String userToCreate = 				
				""" 
				{
  				  "characterName": "Grendel",
				  "class" : "Warrior",
				  "level" : 1,
				  "pvpEnabled" : false,
				  "user" : {
				    "name" : "Allan",
				    "location" : "USA",
				    "age" : 27
				  }
				}""";
		
		
		String url = "https://api.boot.dev/v1/courses_rest_api/learn-http/users";
		String apiKey = generateKey();
		
		System.out.println("Retrieving user data...");
		JSONArray userDataFirst = getUsers(url, apiKey);
		logUsers(userDataFirst);
		System.out.println("---");

		System.out.println("Creating new character...");
		JSONObject creationResponse = createUser(apiKey, url, userToCreate);
		System.out.println("Creation response body: " + creationResponse);
		System.out.println("---");


		System.out.println("Retrieving user data...");
		JSONArray userDataSecond = getUsers(url, apiKey);
		logUsers(userDataSecond);
		System.out.println("---");
		
	}
	
	private static JSONObject createUser(String apiKey, String url, String data) 						throws ClientProtocolException, IOException {
		String content = 
				Request.Post(url)
				.addHeader("X-API-Key", apiKey)
				.addHeader("Content-Type", "application/json")
				.bodyString(data, ContentType.APPLICATION_JSON)
				.execute()
				.returnContent()
				.asString();

		return new JSONObject(content);
	}

	private static void logUsers(JSONArray users) {
		for (int i = 0; i < users.length(); i++) {
			JSONObject jsonObject = users.getJSONObject(i);
			System.out.printf(
					 		  "Character name: %s, "
							+ "Class: %s, "
							+ "Level: %s, "
							+ "User: %s\n", 
							jsonObject.getString("characterName"),
							jsonObject.getString("class"),
							jsonObject.getInt("level"),
							jsonObject.getJSONObject("user")
								    	.getString("name"));
		}
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

	private static String generateKey() {
		String characters = "ABCDEF0123456789";
		String result = "";
		for (var i = 0; i < 16; i++) {
			result += characters.charAt((int) Math.floor(Math.random() * characters.length()));
		}

		return result;
	}
}
