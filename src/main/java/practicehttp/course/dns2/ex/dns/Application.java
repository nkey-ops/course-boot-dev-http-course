package practicehttp.course.dns2.ex.dns;

import java.net.MalformedURLException;
import java.net.URL;

public class Application {
	public static void main(String[] args) throws MalformedURLException {
		String url = "https://github.com/nkey-ops/practice-boot-dev-http-course";
		String domain = getDomainNameFromUrl(url);
		System.out.printf("The domain name for %s is %s\n", url, domain);
	}

	private static String getDomainNameFromUrl(String url) throws MalformedURLException {
		URL urlO = new URL(url);
		return urlO.getHost();
	}

}
