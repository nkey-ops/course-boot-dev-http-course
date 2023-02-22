package practicehttp.course.dns2.ex.addresses1;

import java.io.IOException;

import org.apache.http.client.fluent.Request;
import org.json.JSONObject;

public class Application {
	public static void main(String[] args) throws IOException {
		String domain = "en.wikipedia.org";
		String iPAddress = getIPAddress(domain);
		
		if(iPAddress != null)
			System.out.println("found IP address for domain " + domain + ": " + iPAddress);
		else 
			System.out.println("something went wrong in fetchIPAddress");
	}

	private static String getIPAddress(String domain) throws IOException {
		String urlAddress = 
				String.format("https://cloudflare-dns.com/dns-query?name=%s&type=A", domain);
		
		String response = 
				Request.Get(urlAddress)
					.setHeader("accept", "application/dns-json")
					.execute()
					.returnContent()
					.asString();
		
		JSONObject jsonObject = new JSONObject(response);
		
		return jsonObject.getJSONArray("Answer")
					     .getJSONObject(1)
					     .get("data")
					     .toString();
	}
}
