package practicehttp.course.pathsandparameters9.queryparamsmultiple;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.client.fluent.Request;
import org.apache.http.client.utils.URIBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

public class Application {
	public static void main(String[] args) throws IOException, URISyntaxException {
		String url = "https://api.boot.dev/v1/courses_rest_api/learn-http/items";
		String apiKey = generateKey();
		
		
		JSONArray commonLoot = lootTreasure(url, "Common");
		logLoot(commonLoot);
		
		JSONArray rareLoot = lootTreasure(url, "Rare");
		logLoot(rareLoot);
		
		JSONArray legendaryLoot = lootTreasure(url, "Legendary");
		logLoot(legendaryLoot );
	}
	
	
	

	private static JSONArray lootTreasure(String url, String rarity) 
											throws IOException, URISyntaxException {
		
		int limit = rarity.equals("Common") ? 1 : rarity.equals("Rare") ? 3 : 5; 
		
		URI fullUrl = new URIBuilder(url)
				.setParameter("sort", "quality")
				.setParameter("limit", String.valueOf(limit))
				.build();
	
		System.out.println(fullUrl.toString());
		return getItems(fullUrl.toString(), generateKey());
	}




	private static JSONArray getItems(String url,  String apiKey) throws IOException, URISyntaxException {
		String content = 
				Request.Get(url)
						.addHeader("X-API-Key", apiKey)
						.addHeader("Content-Type", "application/json")
						.execute()
						.returnContent()
						.asString();

		return new JSONArray(content);
	}
	
	private static void logLoot(JSONArray rareLoot) {   
		System.out.println("Looting rare treasure chest...");
		
		for (int i = 0; i < rareLoot.length(); i++) {      
			JSONObject jsonObject = rareLoot.getJSONObject(i);

			System.out.printf("Acquired a %s with quality score: %s\n",
							jsonObject.getString("name"),     
							jsonObject.getInt("quality"));
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
