package practicehttp.course.headers6.headers1;

import java.io.IOException;
import java.util.Arrays;

import org.apache.http.HttpResponse;
import org.apache.http.client.fluent.Request;

public class Application {
	static String apiKey;

	public static void main(String[] args) throws IOException {
		apiKey = generateKey();
		showHeaders("https://api.boot.dev/v1/courses_rest_api/learn-http/locations");
		
	}

	private static void showHeaders(String url) throws IOException {
		
		HttpResponse response = 
				Request.Get(url)
				.setHeader("X-API-KEY", apiKey)
				.setHeader("Content-Type", "application/json")
				.execute()
				.returnResponse();
		
		System.out.println(Arrays.toString(response.getAllHeaders()));
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

}
