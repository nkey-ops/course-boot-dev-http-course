package practicehttp.course.uris3.urlparts2;

import java.net.MalformedURLException;
import java.net.URL;

public class Application {
	public static void main(String[] args) throws MalformedURLException {
		String url = "http://dragonslayer:pwn3d@fantasyquest.com:8080/maps?sort=rank#id";
		printURLParts(url);
	}

	private static void printURLParts(String url) throws MalformedURLException {
		URL urlObject = new URL(url);
		String[] userInfo = urlObject.getUserInfo().split(":");
		
		System.out.println(
					   "protocol: " + urlObject.getProtocol() +
				"\n" + "username: " + userInfo[0]+ 
				"\n" + "password: " + userInfo[1] +                 
				"\n" + "hostname: " + urlObject.getHost() +    
				"\n" + "port: " 	 + urlObject.getPort() +    
				"\n" + "pathname: " + urlObject.getPath() +    
				"\n" + "search: "   + urlObject.getQuery() +   
				"\n" + "hash: "     + urlObject.getRef()       
		);
	}
	
	
}
