package practicehttp.course.pathsandparameters9.queryparamsort3;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class Application {

	public static void main(String[] args) throws IOException, URISyntaxException {
		String URL = "https://api.boot.dev/v1/courses_rest_api/learn-http/users";
		String apiKey = generateKey();
		
		logUsers(getUsers(URL, apiKey));
		
	}

	private static JSONArray getUsers(String url,  String apiKey) throws IOException, URISyntaxException {
		URI uri = new URIBuilder(url)
				.setParameter("sort", "level")
				.build();
		
		String content = 
				Request.Get(uri)
						.addHeader("X-API-Key", apiKey)
						.addHeader("Content-Type", "application/json")
						.execute()
						.returnContent()
						.asString();

		return new JSONArray(content);
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
	
	private static String generateKey() {
		String characters = "ABCDEF0123456789";
		String result = "";
		for (var i = 0; i < 16; i++) {
			result += characters.charAt((int) Math.floor(Math.random() * characters.length()));
		}

		return result;
	}

}
